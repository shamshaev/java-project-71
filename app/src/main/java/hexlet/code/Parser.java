package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;

public class Parser {
    public static Map<String, Object> parse(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        var content = Files.readString(path);
        var fileExtension = filePath.substring(filePath.indexOf('.', 1) + 1);

        Function<String, ObjectMapper> mapper = str -> {
            if (str.equals("json")) {
                return new ObjectMapper();
            } else if (str.equals("yml")) {
                return new YAMLMapper();
            } else {
                throw new RuntimeException("Unknown file extension: " + fileExtension);
            }
        };

        return mapper.apply(fileExtension).readValue(content, new TypeReference<Map<String, Object>>() { });
    }
}
