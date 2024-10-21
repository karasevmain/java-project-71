package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diffs) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diff : diffs) {
            String key = diff.get("key").toString();
            String status = diff.get("status").toString();
            Object oldValue = diff.get("oldValue");
            Object newValue = diff.get("newValue");
            switch (status) {
                case "unchanged":
                        // Не используем для plain
                    break;
                case "added": //Property 'key2' was added with value: 'value2'
                    result.append("Property '").append(key).append("' was added with value: ")
                            .append(formatToString(newValue))
                            .append("\n");
                    break;
                case "updated": //Property 'numbers2' was updated. From [complex value] to [complex value]
                    result.append("Property '").append(key).append("' was updated. From ")
                            .append(formatToString(oldValue)).append(" to ").append(formatToString(newValue))
                            .append("\n");
                    break;
                case "removed": //Property 'numbers3' was removed
                    result.append("Property '").append(key).append("' was removed").append("\n");
                    break;
                default: break;
            }
        }
        return result.toString().trim();
    }

    public String formatToString(Object object) {
        if (object == null) {
            return "null";
        } else if (object instanceof Map || object instanceof List) {
            return "[complex value]";
        } else if (object instanceof String) {
            return "'" + object + "'";
        } else {
            return object.toString();
        }
    }
}
