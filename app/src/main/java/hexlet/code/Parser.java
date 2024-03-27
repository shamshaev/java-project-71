package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {

        return switch (dataFormat) {
            case "yml", "yaml" -> parseYaml(content);
            case "json" -> parseJson(content);
            default -> throw new Exception("Unknown format: '" + dataFormat + "'");
        };
    }

    public static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

    public static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }
}
