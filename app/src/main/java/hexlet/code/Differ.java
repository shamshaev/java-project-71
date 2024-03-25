package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var content1 = getContent(filePath1);
        var content2 = getContent(filePath2);

        var dataFormat1 = getDataFormat(filePath1);
        var dataFormat2 = getDataFormat(filePath2);

        var map1 = Parser.parse(content1, dataFormat1);
        var map2 = Parser.parse(content2, dataFormat2);

        var diffMap = Mapper.generateDiffMap(map1, map2);

        return Formatter.format(diffMap, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        var formatName = "stylish";

        return generate(filePath1, filePath2, formatName);
    }

    public static String getContent(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
