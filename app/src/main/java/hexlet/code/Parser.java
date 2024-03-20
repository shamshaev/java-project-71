package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filepath, String content) throws Exception {
        var fileExtension = filepath.substring(filepath.indexOf('.') + 1);

        if (fileExtension.equals("json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } else if (fileExtension.equals("yml")) {
            ObjectMapper mapper = new YAMLMapper();
            return mapper.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } else {
            throw new Exception("unknown file extension" + filepath);
        }
    }
}
