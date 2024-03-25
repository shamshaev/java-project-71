package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;
import java.util.function.Function;

public class Parser {
    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {

        Function<String, ObjectMapper> mapper = str -> {
            if (str.equals("json")) {
                return new ObjectMapper();
            } else if (str.equals("yml")) {
                return new YAMLMapper();
            } else {
                throw new RuntimeException("Unknown data format: " + dataFormat);
            }
        };

        return mapper.apply(dataFormat).readValue(content, new TypeReference<Map<String, Object>>() { });
    }
}
