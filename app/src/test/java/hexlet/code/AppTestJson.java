package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTestJson {

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
    public void testJsonNested() {
        var expected = "{\"chars1\":{\"1\":[\"a\",\"b\",\"c\"],\"2\":[\"a\",\"b\",\"c\"]},"
            + "\"chars2\":{\"1\":[\"d\",\"e\",\"f\"],\"2\":false},\"checked\":{\"1\":false,\"2\":true},"
            + "\"default\":{\"1\":null,\"2\":[\"value1\",\"value2\"]},\"id\":{\"1\":45,\"2\":null},"
            + "\"key1\":{\"1\":\"value1\"},\"key2\":{\"2\":\"value2\"},\"numbers1\":{\"1\":[1,2,3,4],"
            + "\"2\":[1,2,3,4]},\"numbers2\":{\"1\":[2,3,4,5],\"2\":[22,33,44,55]},"
            + "\"numbers3\":{\"1\":[3,4,5]},\"numbers4\":{\"2\":[4,5,6]},"
            + "\"obj1\":{\"2\":{\"nestedKey\":\"value\",\"isNested\":true}},"
            + "\"setting1\":{\"1\":\"Some value\",\"2\":\"Another value\"},\"setting2\":{\"1\":200,"
            + "\"2\":300},\"setting3\":{\"1\":true,\"2\":\"none\"}}\n";
        String[] args = "-f json src/test/resources/fileNested1.json src/test/resources/fileNested2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlNested() {
        var expected = "{\"chars1\":{\"1\":[\"a\",\"b\",\"c\"],\"2\":[\"a\",\"b\",\"c\"]},"
                + "\"chars2\":{\"1\":[\"d\",\"e\",\"f\"],\"2\":false},\"checked\":{\"1\":false,\"2\":true},"
                + "\"default\":{\"1\":null,\"2\":[\"value1\",\"value2\"]},\"id\":{\"1\":45,\"2\":null},"
                + "\"key1\":{\"1\":\"value1\"},\"key2\":{\"2\":\"value2\"},\"numbers1\":{\"1\":[1,2,3,4],"
                + "\"2\":[1,2,3,4]},\"numbers2\":{\"1\":[2,3,4,5],\"2\":[22,33,44,55]},"
                + "\"numbers3\":{\"1\":[3,4,5]},\"numbers4\":{\"2\":[4,5,6]},"
                + "\"obj1\":{\"2\":{\"nestedKey\":\"value\",\"isNested\":true}},"
                + "\"setting1\":{\"1\":\"Some value\",\"2\":\"Another value\"},\"setting2\":{\"1\":200,"
                + "\"2\":300},\"setting3\":{\"1\":true,\"2\":\"none\"}}\n";
        String[] args = "-f json src/test/resources/fileNested1.yml src/test/resources/fileNested2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testJsonEmpty() {
        var expected = "{}\n";
        String[] args = "-f json src/test/resources/fileEmpty1.json src/test/resources/fileEmpty2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlEmpty() {
        var expected = "{}\n";
        String[] args = "-f json src/test/resources/fileEmpty1.yml src/test/resources/fileEmpty2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }
}
