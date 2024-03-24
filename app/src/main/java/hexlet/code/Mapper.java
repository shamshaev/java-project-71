package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;
import java.util.stream.Stream;

public class Mapper {
    public static Map<String, List<Object>> generateDiffMap(Map<String, Object> map1, Map<String, Object> map2) {

        var keyList = Stream.of(map1, map2)
                .flatMap(map -> map.keySet().stream())
                .distinct()
                .toList();

        var diffMap = new TreeMap<String, List<Object>>();

        for (var key : keyList) {
            var valueList = new ArrayList<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                valueList.add("deleted");
                valueList.add(map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                valueList.add("added");
                valueList.add(map2.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                valueList.add("not changed");
                valueList.add(map1.get(key));
            } else {
                valueList.add("changed");
                valueList.add(map1.get(key));
                valueList.add(map2.get(key));
            }
            diffMap.put(key, valueList);
        }
        return diffMap;
    }
}
