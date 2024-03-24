package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void testException() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                Parser.parse("src/test/resources/file.js"));
        assertEquals("Unknown file extension: js", exception.getMessage());
    }
}
