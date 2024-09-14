package hexlet.code;

import hexlet.code.Utils.Parser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

    public static void generate(String firstFilePath, String secondFilePath, String format) throws Exception{
        String firstFileData = Files.readString(Paths.get(firstFilePath));
        String secondFileData = Files.readString(Paths.get(secondFilePath));

        System.out.println("firstFileData = " + firstFileData);
        System.out.println("SecondFileData = " + secondFileData);

        Map<String, Object> result1 = Parser.parse(firstFileData);
        Map<String, Object> result2 = Parser.parse(secondFileData);

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        
        List<Map<String, Object>> diffs = new ArrayList<>();
        
        SortedSet<String> keys = new TreeSet<>(result1.keySet());
        keys.addAll(result2.keySet());
        System.out.println("sortedFirstData = " + keys);

        for (String key : keys)  {
            Map<String, Object> diff = new HashMap<>();
            Object firstValue = result1.get(key);
            Object secondValue = result2.get(key);

            if (!result2.containsKey(key)) {
                diff.put("key", key);
                diff.put("value", firstValue);
                diff.put("status", "removed");
            }
            else if (!result1.containsKey(key)) {
                diff.put("key", key);
                diff.put("value", secondValue);
                diff.put("status", "added");
            }
            else if (!firstValue.equals(secondValue)) {
                diff.put("key", key);
                diff.put("value", secondValue);
                diff.put("status", "updated");
            } else {
                diff.put("key", key);
                diff.put("value", firstValue);
                diff.put("status", "unchanged");
            }
            diffs.add(diff);
        }
        System.out.println("diffs = " + diffs);
    }
}
