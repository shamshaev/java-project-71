package hexlet.code;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var map1 = Parser.parse(filePath1);
        var map2 = Parser.parse(filePath2);

        var diffMap = Mapper.generateDiffMap(map1, map2);

        return Formatter.format(diffMap, formatName);
    }

    // Непонятно почему, но тесты Хекслета требуют наличие еще реализации фунцкции generate
    // c 2-мя параметрами, вместо 3-х. Хотя версия с 3-мя параметрами работает хорошо
    // и дефолтный вариант формата закрывается прописыванием в классе App дефолтной опции.
    // Эта реализация по сути никогда не будет вызвана из App, поэтому вдвойне непонятно.
    // Тем не менее реализацию добавил, наверное я что-то непонимаю концептуально.
    public static String generate(String filePath1, String filePath2) throws Exception {
        var formatName = "stylish";

        return generate(filePath1, filePath2, formatName);
    }
}
