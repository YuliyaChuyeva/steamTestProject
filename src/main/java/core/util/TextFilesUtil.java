package core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFilesUtil {
    public static String readFileName(String fileName) {
        try {
            String value = Files.readString(Path.of(fileName)).trim();
            if (value.isEmpty()) {
                throw new RuntimeException(fileName + " is empty");
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read from file: " + fileName +
                            ". File must exist and must NOT be committed to git.",
                    e
            );
        }
    }
}