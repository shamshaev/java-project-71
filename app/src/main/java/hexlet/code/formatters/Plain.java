package hexlet.code.formatters;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Plain {

    public static String format(Map<String, Map<String, Object>> diff) {

        var result = new StringBuilder();

        for (var key : diff.keySet()) {
            if (!formatLine(key, diff.get(key)).isEmpty()) {
                result.append(formatLine(key, diff.get(key)));
                result.append("\n");
            }
        }

        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    public static String formatLine(String key, Map<String, Object> valueMap) {

        var map = new TreeMap<>(valueMap);

        if (map.size() == 1 && map.firstKey().equals("1")) {
            return "Property '" + key + "' was removed";
        } else if (map.size() == 1 && map.firstKey().equals("2"))  {
            return "Property '" + key + "' was added with value: " + getValue(map.get(map.firstKey()));
        } else if (!Objects.equals(map.get(map.firstKey()), map.get(map.lastKey()))) {
            return "Property '" + key + "' was updated. From " + getValue(map.get(map.firstKey()))
                    + " to " + getValue(map.get(map.lastKey()));
        } else {
            return "";
        }
    }

    public static String getValue(Object value) {
        if (value instanceof Collection<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        return String.valueOf(value);
    }
}
