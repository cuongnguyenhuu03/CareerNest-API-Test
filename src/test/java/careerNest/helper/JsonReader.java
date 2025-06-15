package careerNest.helper;



import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonReader {
    public static String readJsonFile(String path) {
        try (InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Json not found: " + path);
            }
            return IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }
}
