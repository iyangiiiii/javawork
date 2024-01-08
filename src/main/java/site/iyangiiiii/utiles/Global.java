package site.iyangiiiii.utiles;

import java.nio.file.Paths;

public class Global {
    public static String PACKAGE_PATH = getAbsolutePath("src/main/java/site/iyangiiiii/");

    public static String getAbsolutePath(String relativePath) {
        return Paths.get(relativePath).toAbsolutePath().toString();
    }
}