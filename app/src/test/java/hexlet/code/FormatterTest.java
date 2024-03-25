package hexlet.code;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FormatterTest {

    // без этих тестов падает % покрытия на Code Climate
    @Test
    void testException() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                Formatter.format(Map.of(), "strait"));
        assertEquals("Unknown output format: strait", exception.getMessage());
    }
}
