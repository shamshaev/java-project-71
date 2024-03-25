package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine;

public class AppTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    private static String filePathNestedJson1;
    private static String filePathNestedJson2;
    private static String filePathNestedYml1;
    private static String filePathNestedYml2;
    private static String filePathEmptyJson;
    private static String filePathEmptyYml;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    private static String getPathString(String fileName) {
        return String.join("/", "src", "test", "resources", "fixtures", fileName);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
        filePathNestedJson1 = getPathString("nested1.json");
        filePathNestedJson2 = getPathString("nested2.json");
        filePathNestedYml1 = getPathString("nested1.yml");
        filePathNestedYml2 = getPathString("nested2.yml");
        filePathEmptyJson = getPathString("empty.json");
        filePathEmptyYml = getPathString("empty.yml");
    }

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
    public void testInNestedJsonOutStylish() {
        var expected = resultStylish + "\n";

        var commandLineString = "-f stylish " + filePathNestedJson1 + " " + filePathNestedJson2;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInNestedYmlOutStylish() {
        var expected = resultStylish + "\n";

        var commandLineString = "-f stylish " + filePathNestedYml1 + " " + filePathNestedYml2;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInEmptyJsonOutStylish() {
        var expected = "{}\n";

        var commandLineString = "-f stylish " + filePathEmptyJson + " " + filePathEmptyJson;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInEmptyYmlOutStylish() {
        var expected = "{}\n";

        var commandLineString = "-f stylish " + filePathEmptyYml + " " + filePathEmptyYml;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInNestedJsonOutPlain() {
        var expected = resultPlain + "\n";

        var commandLineString = "-f plain " + filePathNestedJson1 + " " + filePathNestedJson2;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInNestedYmlOutPlain() {
        var expected = resultPlain + "\n";

        var commandLineString = "-f plain " + filePathNestedYml1 + " " + filePathNestedYml2;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInEmptyJsonOutPlain() {
        var expected = "\n";

        var commandLineString = "-f plain " + filePathEmptyJson + " " + filePathEmptyJson;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInEmptyYmlOutPlain() {
        var expected = "\n";

        var commandLineString = "-f plain " + filePathEmptyYml + " " + filePathEmptyYml;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);
        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInNestedJsonOutJson() {
        var expected = resultJson + "\n";

        var commandLineString = "-f json " + filePathNestedJson1 + " " + filePathNestedJson2;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInNestedYmlOutJson() {
        var expected = resultJson + "\n";

        var commandLineString = "-f json " + filePathNestedYml1 + " " + filePathNestedYml2;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInEmptyJsonOutJson() {
        var expected = "{}\n";

        var commandLineString = "-f json " + filePathEmptyJson + " " + filePathEmptyJson;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }

    @Test
    public void testInEmptyYmlOutJson() {
        var expected = "{}\n";

        var commandLineString = "-f json " + filePathEmptyYml + " " + filePathEmptyYml;
        String[] args = commandLineString.split(" ");
        new CommandLine(new App()).execute(args);
        var actual = out.toString();
        System.out.println(actual);

        assertEquals(expected, actual);
        assertEquals("", err.toString());
    }
}
