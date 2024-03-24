package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String format(Map<String, List<Object>> diffMap) {

        var result = new StringBuilder();

        for (var key : diffMap.keySet()) {
            if (!formatLine(key, diffMap.get(key)).isEmpty()) {
                result.append(formatLine(key, diffMap.get(key)));
                result.append("\n");
            }
        }

        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    public static String formatLine(String key, List<Object> valueList) {
        var type = (String) valueList.get(0);

        return switch (type) {
            case "deleted" -> "Property '" + key + "' was removed";
            case "added" -> "Property '" + key + "' was added with value: " + getValue(valueList.get(1));
            case "changed" -> "Property '" + key + "' was updated. From " + getValue(valueList.get(1))
                    + " to " + getValue(valueList.get(2));
            case "not changed" -> "";
            default -> throw new RuntimeException("Unknown type: " + type);
        };
    }

    public static String getValue(Object value) {
        if (value instanceof Collection<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
