package site.iyangiiiii.Utils;

import java.nio.file.Paths;

public class Global {
    public static String RESOURCES_PATH = getAbsolutePath("src/main/resources/");
    public static String PACKAGE_PATH = getAbsolutePath("src/main/java/site/iyangiiiii/");

    public static String MYBATIS_CONFIG_PATH = RESOURCES_PATH + "configs/hibernate/hibernate.cfg.xml";

    public static String IMAGE_PATH = RESOURCES_PATH + "/Image/";

    public static String getAbsolutePath(String relativePath) {
        return Paths.get(relativePath).toAbsolutePath().toString();
    }

    public static String getImgPath(String imgName) {
        return IMAGE_PATH + imgName;
    }
}