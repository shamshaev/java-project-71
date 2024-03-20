package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Differ {
    public static Map<String, Object> generate(Map<String, Object> map1, Map<String, Object> map2) {
        var keys = Stream.of(map1, map2)
                .flatMap(map -> map.keySet().stream())
                .distinct()
                .toList();

        Map<String, Object> diff = new TreeMap<>();

        for (var key : keys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                diff.put(key + " 1", map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                diff.put(key + " 2", map2.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                diff.put(key + " 0", map1.get(key));
            } else {
                diff.put(key + " 1", map1.get(key));
                diff.put(key + " 2", map2.get(key));
            }
        }

        return diff;
    }
}
