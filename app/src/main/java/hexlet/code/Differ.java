package hexlet.code;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var map1 = Parser.parse(filePath1);
        var map2 = Parser.parse(filePath2);

        var diffMap = Mapper.generateDiffMap(map1, map2);

        return Formatter.format(diffMap, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        var formatName = "stylish";
        return generate(filePath1, filePath2, formatName);
    }
}
