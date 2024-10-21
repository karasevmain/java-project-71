package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface Formatter {
    String format(List<Map<String, Object>> diffs) throws Exception;
    static Formatter chooseFormat(String formatName) {
        if (formatName == null || formatName.isEmpty()) {
            formatName = "stylish";
        }
        switch (formatName) {
            case "stylish":
                return new StylishFormatter();
            case "plain":
                return new PlainFormatter();
            case "json":
                return new JsonFormatter();
            default: throw new IllegalArgumentException("Неподдерживаемый формат " + formatName);
        }
    }
}
