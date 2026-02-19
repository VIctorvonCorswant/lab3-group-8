import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    protected final String modelName;

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

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, String modelName) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.modelName = modelName;
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Components.Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.

            String img = "pics/$modelName.jpg".replace("$modelName", modelName);
            carImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(img)));
            //workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public String getModelName() {
        return  modelName;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("X" + objectPoint.x + " Y" + objectPoint.y);
        g.drawImage(carImage, objectPoint.x, objectPoint.y, null);
        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        //g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
