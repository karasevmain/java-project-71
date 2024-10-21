package hexlet.code.Utils;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.HashMap;
import java.util.Objects;
public class FileDataProcessor {
    public static List<Map<String, Object>> process(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<Map<String, Object>> diffs = new ArrayList<>();
        SortedSet<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        for (String key : keys) {
            Map<String, Object> diff = new HashMap<>();
            if (!secondMap.containsKey(key)) {
                diff.put("key", key);
                diff.put("oldValue", firstMap.get(key));
                diff.put("status", "removed");
            } else if (!firstMap.containsKey(key)) {
                diff.put("key", key);
                diff.put("newValue", secondMap.get(key));
                diff.put("status", "added");
            } else if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                diff.put("key", key);
                diff.put("oldValue", firstMap.get(key));
                diff.put("newValue", secondMap.get(key));
                diff.put("status", "updated");
            } else {
                diff.put("key", key);
                diff.put("value", firstMap.get(key));
                diff.put("status", "unchanged");
            }
            diffs.add(diff);
        }
        return diffs;
    }
}
