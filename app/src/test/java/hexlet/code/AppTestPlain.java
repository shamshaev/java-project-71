package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTestPlain {
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
    public void testYmlNested() {
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
    public void testJsonEmpty() {
        var expected = "\n";
        String[] args = "-f plain src/test/resources/fileEmpty1.json src/test/resources/fileEmpty2.json".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testYmlEmpty() {
        var expected = "\n";
        String[] args = "-f plain src/test/resources/fileEmpty1.yml src/test/resources/fileEmpty2.yml".split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }
}
