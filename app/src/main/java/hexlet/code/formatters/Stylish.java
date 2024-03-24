package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String format(Map<String, List<Object>> diffMap) {

        var result = new StringBuilder("{\n");

        for (var key : diffMap.keySet()) {
            result.append(formatLine(key, diffMap.get(key)));
            result.append("\n");
        }

        if (result.length() == 2) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.append("}").toString();
    }

    public static String formatLine(String key, List<Object> valueList) {
        var type = (String) valueList.get(0);

        return switch (type) {
            case "deleted" -> "  - " + key + ": " + valueList.get(1);
            case "added" -> "  + " + key + ": " + valueList.get(1);
            case "changed" -> "  - " + key + ": " + valueList.get(1)
                    + "\n  + " + key + ": " + valueList.get(2);
            case "not changed" -> "    " + key + ": " + valueList.get(1);
            default -> throw new RuntimeException("Unknown type: " + type);
        };
    }
}
