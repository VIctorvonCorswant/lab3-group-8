import Components.Car;

import java.awt.*;
import java.awt.image.BufferedImage;
import Components.Workshop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    CarController carC;

    // Just a single image, TODO: Generalize
    BufferedImage carImage;
    BufferedImage workshopImage;

    // To keep track of a single car's position
    Point objectPoint = new Point();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        objectPoint.x = x;
        objectPoint.y = y;
    }

    public DrawPanel(int x, int y, CarController cc) {
        this.carC = cc;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x,y));
        this.setBackground(Color.DARK_GRAY);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : carC.getCarList()) {
            Point p = car.getCoordinates();
            g.drawImage(car.getImage(), p.x, p.y, null);
        }

        for (Workshop w : carC.getWorkshops()) {
            System.out.println("workshop" + w);
            Point p = w.getCoordinates();
            g.drawImage(w.getImage(), p.x, p.y, null);
        }
    }

        //g.drawImage(carImage, objectPoint.x, objectPoint.y, null);

        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        // g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
}

