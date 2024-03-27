package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.Differ.getContent;
import static hexlet.code.Differ.getDataFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    // без этих тестов падает % покрытия на Code Climate
    @Test
    void testException() throws Exception {
        var filePath = String.join("/", "src", "test", "resources", "fixtures", "file.js");
        var content = getContent(filePath);
        var dataFormat = getDataFormat(filePath);

        Exception exception = assertThrows(Exception.class, () ->
                Parser.parse(content, dataFormat));
        assertEquals("Unknown format: '" + dataFormat + "'", exception.getMessage());
    }
}
