package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, Map<String, Object>> diff, String formatName) {
        return switch (formatName) {
            case "stylish" -> Stylish.format(diff);
            case "plain" -> Plain.format(diff);
            //case "json" -> Json.format(diff);
            default -> "Unknown output format: " + formatName;
        };
    }
}
