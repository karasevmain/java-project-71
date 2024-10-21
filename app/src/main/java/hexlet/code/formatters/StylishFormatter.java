package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> diffs) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Map<String, Object> diff: diffs) {
            String key = diff.get("key").toString();
            String status = diff.get("status").toString();
            Object oldValue = diff.get("oldValue");
            Object newValue = diff.get("newValue");
            switch (status) {
                case "unchanged":
                    result.append("    ").append(key).append(": ").append(formatToString(diff.get("value")))
                            .append("\n");
                    break;
                case "added":
                    result.append("  + ").append(key).append(": ").append(formatToString(newValue))
                            .append("\n");
                    break;
                case "updated":
                    result.append("  - ").append(key).append(": ").append(formatToString(oldValue))
                            .append("\n");
                    result.append("  + ").append(key).append(": ").append(formatToString(newValue))
                            .append("\n");
                    break;
                case "removed":
                    result.append("  - ").append(key).append(": ").append(formatToString(oldValue))
                            .append("\n");
                    break;
                default: break;
            }
        }
        result.append("}");
        return result.toString();
    }
    public String formatToString(Object object) {
        if (object == null) {
            return "null";
        } else {
            return object.toString();
        }
    }
}
