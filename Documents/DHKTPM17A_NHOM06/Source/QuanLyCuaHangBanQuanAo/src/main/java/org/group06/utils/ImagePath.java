package org.group06.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImagePath {

    // Icon path
    public static final String ICON_USER = "user.png";
    public static final String ICON_CHART = "chart.png";
    public static final String ICON_EMPLOYEE = "employee.png";
    public static final String ICON_CUSTOMER = "customer.png";
    public static final String ICON_BILL = "bill.png";
    public static final String ICON_CLOTHES = "clothes.png";
    public static final String ICON_CALENDAR = "calendar.png";

    // Thumbnail path
    public static final URL THUMBNAIL_LOGIN = ImagePath.class.getResource("/images/thumbnail/login.jpg");
    public static final URL THUMBNAIL_MAIN = ImagePath.class.getResource("/images/thumbnail/gdChinh.jpg");
    public static final URL THUMBNAIL_ICON = ImagePath.class.getResource("/images/thumbnail/icon.png");

//    Product path
    public static final URL PRODUCT_EXAMPLE = ImagePath.class.getResource("/images/product/example.jpg");
    public static final URL UPLOAD_IMG = ImagePath.class.getResource("/images/product/uploadImg.jpg");
    private ImagePath() {
        // Để ngăn việc tạo đối tượng instance của lớp này
    }

    public static Image loadImage(URL iconLogo) {
        try {
            return ImageIO.read(iconLogo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Icon loadIcon(String iconPath) {
        return new ImageIcon(loadImage(ImagePath.class.getResource("/images/icon/black/" + iconPath)));
    }

}
