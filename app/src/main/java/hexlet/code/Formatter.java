package hexlet.code;

import java.util.Map;
import java.util.stream.Collectors;

public class Formatter {

    public static String formatStylish(Map<String, Object> diff) {
        if (diff.isEmpty()) {
            return "{}";
        }
        return diff.keySet().stream()
                .map(key -> formatKeyStylish(key) + ": " + diff.get(key))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    public static String formatKeyStylish(String key) {
        return switch (key.split(" ")[1]) {
            case "0" -> "    " + key.split(" ")[0];
            case "1" -> "  - " + key.split(" ")[0];
            case "2" -> "  + " + key.split(" ")[0];
            default -> key;
        };
    }

    public static String formatPlainText(Map<String, Object> diff) {
        return diff.toString();
    }

    public static String formatJson(Map<String, Object> diff) {
        return diff.toString();
    }
}
