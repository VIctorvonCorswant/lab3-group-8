import Components.Car;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    // BufferedImage workshopImage;

    // To keep track of a single car's position
    Point objectPoint = new Point();

    // BufferedImage volvoWorkshopImage;
    // Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        objectPoint.x = x;
        objectPoint.y = y;
    }

    public DrawPanel(int x, int y, CarController cc){
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x,y));
        this.setBackground(Color.green);
        this.carC = cc;

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Components.Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.

            carImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            //workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("X" + objectPoint.x + " Y" + objectPoint.y);
        //Flytta For loopen för bilarnas ritning hit
        /*
        for (Car car : this.carsList.cars) {
            g.drawImage(car.getImage(), objectPoint.x, objectPoint.y, null);
        }

         */

        //for (Car car : this.carC.getCarList()) {
        //    g.drawImage(car.getImage(), car.getCoordinates().x, car.getCoordinates().y, null);
        //}
        for (int i=0; i<10; i++) {
            System.out.println(("Bamse"));
        }



        g.drawImage(carImage, objectPoint.x, objectPoint.y, null);



        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        //g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
