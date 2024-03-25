package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Objects;

public class Mapper {
    public static Map<String, List<Object>> generateDiffMap(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        var diffMap = new TreeMap<String, List<Object>>();

        for (var key : keys) {
            diffMap.put(key, generateValueList(key, map1, map2));
        }
        return diffMap;
    }

    public static List<Object> generateValueList(String key, Map<String, Object> map1, Map<String, Object> map2) {
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
        return valueList;
    }
}
