package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String content1, String content2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(content1, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> map2 = mapper.readValue(content2, new TypeReference<Map<String, Object>>() { });

        if (map1.isEmpty() && map2.isEmpty()) {
            return "{}";
        }

        return Stream.of(map1, map2)
                .flatMap(map -> map.keySet().stream())
                .distinct()
                .sorted()
                .map(key -> generateStylish(key, map1, map2))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    public static String generateStylish(String key, Map<String, Object> map1, Map<String, Object> map2) {
        if (map1.containsKey(key) && !map2.containsKey(key)) {
            return "  - " + key + ": " + map1.get(key);
        }
        if (!map1.containsKey(key) && map2.containsKey(key)) {
            return "  + " + key + ": " + map2.get(key);
        }
        if (map1.get(key).equals(map2.get(key))) {
            return "    " + key + ": " + map1.get(key);
        }
        return "  - " + key + ": " + map1.get(key) + "\n" + "  + " + key + ": " + map2.get(key);
    }
}
