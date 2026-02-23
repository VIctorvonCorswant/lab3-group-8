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
    private String workshopName;
    private Point coordinates = new Point(300, 300);
    private double catchRadius = 80.0;


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
        if (facility.size() < facilitySize && !facility.contains(car)) {
            facility.add(car);
            car.stopVehicle();
            car.engine.stopEngine();
            return true;
        }
        return false;
    }

    public void checkAddVehicleToWorkshop(ArrayList<M> cars) {
        for (M car : cars) {
            double distX = Math.abs(this.coordinates.getX() - car.getCoordinates().getX());
            double distY = Math.abs(this.coordinates.getY() - car.getCoordinates().getY());

            if (distX < catchRadius && distY < catchRadius) {
                boolean added = this.addCarToWorkshop(car);
                if (added) {
                    car.forcePosition(new Point(
                            (int)this.coordinates.getX(),
                            (int)this.coordinates.getY()
                    ));
                    System.out.println(this.getModelName() + " is now in the workshop " + workshopName);
                }
            }
        }
    }

    public void checkRemoveVehicleFromWorkshop(ArrayList<M> cars) {
        for (M car : cars) {
            if(facility.contains(car)){
                double distX = Math.abs(this.coordinates.getX() - car.getCoordinates().getX());
                double distY = Math.abs(this.coordinates.getY() - car.getCoordinates().getY());

                if (distX > 2 && distY < 2) {
                    this.removeCarFromWorkshop(car);
                    System.out.println("begone: " + this.getModelName());
                }
            }
        }
    }

    public Image getImage()  {
        try {
            return ImageIO.read(new File("pics/workshopName.jpg".replace("workshopName", this.workshopName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeCarFromWorkshop(M car) {
        car.forceMove(catchRadius + 1);
        return facility.remove(car);
    }

    public List<M> getFacility() {return facility;}

    public Point getCoordinates() {return coordinates;}
}
