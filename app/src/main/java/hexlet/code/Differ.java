package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var map1 = Parser.parse(filePath1);
        var map2 = Parser.parse(filePath2);

        var diffMap = generateDiffMap(map1, map2);

        return Formatter.format(diffMap, formatName);
    }

    public static Map<String, Map<String, Object>> generateDiffMap(Map<String, Object> map1, Map<String, Object> map2) {
        var keysList = Stream.of(map1, map2)
                .flatMap(map -> map.keySet().stream())
                .distinct()
                .toList();

        var diffMap = new TreeMap<String, Map<String, Object>>();

        for (var key : keysList) {
            var valueMap = new TreeMap<String, Object>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                valueMap.put("1", map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                valueMap.put("2", map2.get(key));
            } else {
                valueMap.put("1", map1.get(key));
                valueMap.put("2", map2.get(key));
            }
            diffMap.put(key, valueMap);
        }

        return diffMap;
    }
}
