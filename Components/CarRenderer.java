package Components;
import java.awt.*;

public final class CarRenderer {
    private CarRenderer() {} // utility class

    //this is the Car rendering function
    public static void render(Graphics g, Car car) {
        if (car == null) return;
        Point p = car.getCoordinates();
        Image img = car.getImage();
        if (img != null && p != null) {
            g.drawImage(img, p.x, p.y, null);
        }
    }

    //this is the Workshop rendering function
    public static void render(Graphics g, Workshop workshop) {
        if  (workshop == null) return;
        Point p = workshop.getCoordinates();
        Image img = workshop.getImage();
        if (img != null && p != null) {
            g.drawImage(img, p.x, p.y, null);
        }
    }
}