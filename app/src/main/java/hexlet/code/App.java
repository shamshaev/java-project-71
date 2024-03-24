package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]",
            paramLabel = "format")
    private String formatName;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filePath2;


    @Override
    public final Integer call() throws Exception {
        var diff = Differ.generate(filePath1, filePath2, formatName);
        System.out.println(diff);

        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
