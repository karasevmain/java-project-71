package hexlet.code.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper();

    public static Map<String, Object> parse(String data) throws Exception {
        return JSON_MAPPER.readValue(data, new TypeReference<>() { });
    }
}
