package utils;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.MissingEnvironmentVariableException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class PropertyReader {

    private final Map<String, Object> properties;

    public PropertyReader() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = PropertyReader.class
                .getClassLoader()
                .getResourceAsStream("application.yml")
        ) {
            Map<String, Object> appProperties = yaml.load(inputStream);
            properties = ImmutableMap.copyOf(appProperties);
        } catch (IOException e) {
            log.error("Ошибка при чтении конфига: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            String[] keys = key.split("\\.");
            Object value = properties;

            for (String k : keys) {
                if (!(value instanceof Map)) {
                    throw new MissingEnvironmentVariableException("Неверный путь переменной или переменная отсутствует");
                }
                value = ((Map<?, ?>) value).get(k);
            }

            return clazz.cast(value);
        } catch (ClassCastException e) {
            log.error("Ошибка при получении переменной из файла: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
