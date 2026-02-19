package Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Workshop<M extends Car> {

    List<M> facility = new ArrayList<>();
    protected int facilitySize = 5;
    public String workshopName;
    public Point coordinates = new Point(300, 300);


    public Workshop(int facilitySize, String workshopName, Point coords) {
        this.facilitySize = facilitySize;
        this.workshopName = workshopName;
        this.coordinates.x = coords.x;
        this.coordinates.y = coords.y;
    }

    public String getModelName() {
        return workshopName;
    }

    public boolean addCarToWorkshop(M car) {
        // Compile-time safety: only cars typed with the workshop's model type M can be passed
        if (facility.size() < facilitySize) {
            facility.add(car);
            return true;
        }
        return false;
    }

    public Image getImage()  {
        try {
            return ImageIO.read(new File("pics/workshopName.jpg".replace("workshopName", this.workshopName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeCarFromWorkshop(M car) {
        return facility.remove(car);
    }

    public List<M> getFacility() {return facility;}

    public Point getCoordinates() {return coordinates;}
}
