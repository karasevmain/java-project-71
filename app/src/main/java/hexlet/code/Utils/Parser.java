package hexlet.code.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());
    public static Map<String, Object> parse(String data, String formatFile) throws Exception {
        if (formatFile.equals("json")) {
            return JSON_MAPPER.readValue(data, new TypeReference<Map<String, Object>>() { });
        } else  if (formatFile.equals("yaml")) {
            return YAML_MAPPER.readValue(data, new TypeReference<Map<String, Object>>() { });
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат: " + formatFile);
        }
    }
}
