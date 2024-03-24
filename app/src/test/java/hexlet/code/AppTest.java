package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import picocli.CommandLine;

public class AppTest {
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @BeforeEach
    public final void setUpStreams() {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public final void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testJsonNestedStylish() {
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
    public void testYmlNestedStylish() {
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
    public void testJsonEmptyStylish() {
        var expected = "{}\n";
        String[] args = "src/test/resources/fileEmpty1.json src/test/resources/fileEmpty2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlEmptyStylish() {
        var expected = "{}\n";
        String[] args = "src/test/resources/fileEmpty1.yml src/test/resources/fileEmpty2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testJsonNestedPlain() {
        var expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";
        String[] args = "-f plain src/test/resources/fileNested1.json src/test/resources/fileNested2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlNestedPlain() {
        var expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";
        String[] args = "-f plain src/test/resources/fileNested1.yml src/test/resources/fileNested2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testJsonEmptyPlain() {
        var expected = "\n";
        String[] args = "-f plain src/test/resources/fileEmpty1.json src/test/resources/fileEmpty2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlEmptyPlain() {
        var expected = "\n";
        String[] args = "-f plain src/test/resources/fileEmpty1.yml src/test/resources/fileEmpty2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testJsonNestedJson() {
        var expected = "{\"chars1\":[\"not changed\",[\"a\",\"b\",\"c\"]],\"chars2\":[\"changed\","
                + "[\"d\",\"e\",\"f\"],false],\"checked\":[\"changed\",false,true],\"default\":[\"changed\","
                + "null,[\"value1\",\"value2\"]],\"id\":[\"changed\",45,null],\"key1\":[\"deleted\","
                + "\"value1\"],\"key2\":[\"added\",\"value2\"],\"numbers1\":[\"not changed\",[1,2,3,4]],"
                + "\"numbers2\":[\"changed\",[2,3,4,5],[22,33,44,55]],\"numbers3\":[\"deleted\",[3,4,5]],"
                + "\"numbers4\":[\"added\",[4,5,6]],\"obj1\":[\"added\",{\"nestedKey\":\"value\","
                + "\"isNested\":true}],\"setting1\":[\"changed\",\"Some value\",\"Another value\"],"
                + "\"setting2\":[\"changed\",200,300],\"setting3\":[\"changed\",true,\"none\"]}\n";
        String[] args = "-f json src/test/resources/fileNested1.json src/test/resources/fileNested2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlNestedJson() {
        var expected = "{\"chars1\":[\"not changed\",[\"a\",\"b\",\"c\"]],\"chars2\":[\"changed\","
                + "[\"d\",\"e\",\"f\"],false],\"checked\":[\"changed\",false,true],\"default\":[\"changed\","
                + "null,[\"value1\",\"value2\"]],\"id\":[\"changed\",45,null],\"key1\":[\"deleted\","
                + "\"value1\"],\"key2\":[\"added\",\"value2\"],\"numbers1\":[\"not changed\",[1,2,3,4]],"
                + "\"numbers2\":[\"changed\",[2,3,4,5],[22,33,44,55]],\"numbers3\":[\"deleted\",[3,4,5]],"
                + "\"numbers4\":[\"added\",[4,5,6]],\"obj1\":[\"added\",{\"nestedKey\":\"value\","
                + "\"isNested\":true}],\"setting1\":[\"changed\",\"Some value\",\"Another value\"],"
                + "\"setting2\":[\"changed\",200,300],\"setting3\":[\"changed\",true,\"none\"]}\n";
        String[] args = "-f json src/test/resources/fileNested1.yml src/test/resources/fileNested2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testJsonEmptyJson() {
        var expected = "{}\n";
        String[] args = "-f json src/test/resources/fileEmpty1.json src/test/resources/fileEmpty2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlEmptyJson() {
        var expected = "{}\n";
        String[] args = "-f json src/test/resources/fileEmpty1.yml src/test/resources/fileEmpty2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testWrongFormat() {
        var expected = "Unknown output format: strait\n";
        String[] args = "-f strait src/test/resources/fileNested1.json src/test/resources/fileNested2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }
}
