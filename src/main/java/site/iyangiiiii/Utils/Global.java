package site.iyangiiiii.Utils;

import site.iyangiiiii.Entities.User;

import java.nio.file.Paths;

public class Global {
    public static User curUser = null;

    public static String RESOURCES_PATH = getAbsolutePath("src/main/resources/");
    public static String PACKAGE_PATH = getAbsolutePath("src/main/java/site/iyangiiiii/");

    public static String MYBATIS_CONFIG_PATH = RESOURCES_PATH + "configs/hibernate/hibernate.cfg.xml";

    public static String IMAGE_PATH = RESOURCES_PATH + "/Image/";

    /**
     * 将相对路径转换为绝对路径
     * @param relativePath 相对路径
     * @return 绝对路径
     */
    public static String getAbsolutePath(String relativePath) {
        return Paths.get(relativePath).toAbsolutePath().toString();
    }

    /**
     * 根据图片名获取图片路径
     * @param imgName 图片名
     * @return 图片路径
     */
    public static String getImgPath(String imgName) {
        return IMAGE_PATH + imgName;
    }
}