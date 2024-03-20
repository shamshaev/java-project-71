package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import picocli.CommandLine;

public class AppTest {
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testNestedJson() {
        var expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}\n";
        String[] args = "src/test/resources/fileNested1.json src/test/resources/fileNested2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testNestedYml() {
        var expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}\n";
        String[] args = "src/test/resources/fileNested1.yml src/test/resources/fileNested2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testEmptyJson() {
        var expected = "{}\n";
        String[] args = "src/test/resources/fileEmpty1.json src/test/resources/fileEmpty2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testEmptyYml() {
        var expected = "{}\n";
        String[] args = "src/test/resources/fileEmpty1.yml src/test/resources/fileEmpty2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

}
