package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    // В этом классе тестируется только вывод при вводе без указания формата, т.е. дефолтный.
    // Остальные форматы тестируются в AppTest на верхнем уровне ввода-вывода.
    // В принципе там можно без проблем было бы протестировать и дефолтный вариант
    // без создания метода generate(String, String) и функционал приложения никак бы не пострадал,
    // но тесты Хекслета требуют generate(String, String), а не только generate(String, String, String).
    private static String resultStylish;
    private static String filePathNestedJson1;
    private static String filePathNestedJson2;
    private static String filePathNestedYml1;
    private static String filePathNestedYml2;

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
    public static void beforeAll()  throws Exception {
        resultStylish = readFixture("result_stylish.txt");
        filePathNestedJson1 = getPathString("nested1.json");
        filePathNestedJson2 = getPathString("nested2.json");
        filePathNestedYml1 = getPathString("nested1.yml");
        filePathNestedYml2 = getPathString("nested2.yml");
    }

    @Test
    public void testInNestedJsonOutDefault() throws Exception {
        var expected = resultStylish;
        var actual = Differ.generate(filePathNestedJson1, filePathNestedJson2);

        assertEquals(expected, actual);
    }

    @Test
    public void testInNestedYmlOutDefault() throws Exception {
        var expected = resultStylish;
        var actual = Differ.generate(filePathNestedYml1, filePathNestedYml2);

        assertEquals(expected, actual);
    }
}
