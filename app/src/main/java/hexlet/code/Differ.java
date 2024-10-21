package hexlet.code;

import hexlet.code.Utils.Parser;
import hexlet.code.Utils.FileDataProcessor;
import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

import static hexlet.code.formatters.Formatter.chooseFormat;


public class Differ {

    public static String generate(String firstFilePath, String secondFilePath, String format) throws Exception {

        String firstFileData = Files.readString(Paths.get(firstFilePath));
        String secondFileData = Files.readString(Paths.get(secondFilePath));

        Map<String, Object> firstFileMap = Parser.parse(firstFileData, getFileFormat(firstFilePath));
        Map<String, Object> secondFileMap = Parser.parse(secondFileData, getFileFormat(secondFilePath));

        List<Map<String, Object>> diffs = FileDataProcessor.process(firstFileMap, secondFileMap);

        Formatter formatter = chooseFormat(format);
        return formatter.format(diffs);
    }
    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    public static String getFileFormat(String filePath) {
        if (filePath.endsWith(".json")) {
            return "json";
        } else if (filePath.endsWith(".yml")) {
            return "yaml";
        } else {
            throw new IllegalArgumentException("Не поддерживаемый формат файла " + filePath);
        }
    }
}
