package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        versionProvider = App.ManifestVersionProvider.class,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;


    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Show this help message and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Print version information and exit.")
    boolean usageHelpRequested;

    @Override
    public Integer call() {
        try {
            var filePath1 = "";
            var filePath2 = "";
            var diff = Differ.generate(filePath1, filePath2);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }

        return SUCCESS_EXIT_CODE;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * {@link CommandLine.IVersionProvider} implementation
     * that returns version information from the app-x.x.jar file's
     * {@code /META-INF/MANIFEST.MF} file.
     */
    static class ManifestVersionProvider implements CommandLine.IVersionProvider {
        public String[] getVersion() throws Exception {
            Enumeration<URL> resources = CommandLine.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try {
                    Manifest manifest = new Manifest(url.openStream());
                    if (isApplicableManifest(manifest)) {
                        Attributes attr = manifest.getMainAttributes();
                        String title = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
                        String version = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
                        return new String[]{title + " " + version};
                    }
                } catch (IOException ex) {
                    return new String[]{"Unable to read from " + url + ": " + ex};
                }
            }
            return new String[0];
        }

        private boolean isApplicableManifest(Manifest manifest) {
            Attributes attributes = manifest.getMainAttributes();
            return "gendiff".equals(get(
                    attributes,
                    "Implementation-Title")); // Adjust to match your project's name or title
        }

        private static Object get(Attributes attributes, String key) {
            return attributes.get(new Attributes.Name(key));
        }
    }
}
