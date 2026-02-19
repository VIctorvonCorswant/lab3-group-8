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

//    void addVehicleToWorkshop(ArrayList cars) {
//        for (Car car : cars) {
//            double distX = Math.abs(workshop.coordinates.getX() - car.getCoordinates().getX());
//            double distY = Math.abs(workshop.coordinates.getY() - car.getCoordinates().getY());
//
//            if (distX < 100 && distY < 100) {
//                boolean added = workshop.addCarToWorkshop(car);
//                if (added) {
//                    car.forcePosition(new Point(
//                            (int)workshop.coordinates.getX(),
//                            (int)workshop.coordinates.getY()
//                    ));
//                    System.out.println("RAHHHHH: " + workshop.getModelName());
//                }
//            }
//        }
//    }

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
