package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Stylish {

    public static String format(Map<String, Map<String, Object>> diff) {

        var result = new StringBuilder("{\n");

        for (var key : diff.keySet()) {
            result.append(formatLine(key, diff.get(key)));
            result.append("\n");
        }

        if (result.length() == 2) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.append("}").toString();
    }

    public static String formatLine(String key, Map<String, Object> valueMap) {

        var map = new TreeMap<>(valueMap);

        if (map.size() == 1 && map.firstKey().equals("1")) {
            return "  - " + key + ": " + map.get(map.firstKey());
        } else if (map.size() == 1 && map.firstKey().equals("2"))  {
            return "  + " + key + ": " + map.get(map.firstKey());
        } else if (!Objects.equals(map.get(map.firstKey()), map.get(map.lastKey()))) {
            return "  - " + key + ": " + map.get(map.firstKey())
                    + "\n  + " + key + ": " + map.get(map.lastKey());
        } else {
            return "    " + key + ": " + map.get(map.firstKey());
        }
    }
}
