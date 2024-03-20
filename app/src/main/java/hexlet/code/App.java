package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]",
            paramLabel = "format")
    private String format;

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        var content1 = Files.readString(path1);
        var content2 = Files.readString(path2);

        var map1 = Parser.parse(filepath1, content1);
        var map2 = Parser.parse(filepath2, content2);
        var diff = Differ.generate(map1, map2);

        switch (format) {
            case "stylish" -> System.out.println(Formatter.formatStylish(diff));
            case "plain text" -> System.out.println(Formatter.formatPlainText(diff));
            case "json" -> System.out.println(Formatter.formatJson(diff));
            default -> System.out.println("Unknown format: " + format);
        }

        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
