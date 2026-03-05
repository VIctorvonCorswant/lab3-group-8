package Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Workshop<M extends Car> {
    private final Class<M> supportedModel;
    List<M> facility = new ArrayList<>();
    protected int facilitySize = 5;
    private String workshopName;
    private Point coordinates = new Point(300, 300);
    private double catchRadius = 80.0;


    public Workshop(int facilitySize, String workshopName, Point coords) {
        this.supportedModel = (Class<M>) Car.class; // Default to Car if no specific model is provided
        this.facilitySize = facilitySize;
        this.workshopName = workshopName;
        this.coordinates.x = coords.x;
        this.coordinates.y = coords.y;
    }

    // Overloaded constructor to specify the supported model type
    public Workshop(Class<M> supportedModel, int facilitySize, String workshopName, Point coords) {
        this.supportedModel = supportedModel;
        this.facilitySize = facilitySize;
        this.workshopName = workshopName;
        this.coordinates.x = coords.x;
        this.coordinates.y = coords.y;
    }

    public String getModelName() {
        return workshopName;
    }

    public double getCatchRadius() {
        return catchRadius;
    }

    public ArrayList getCarsInWorkshop() {
        return new ArrayList<>(facility);
    }

    public boolean addCarToWorkshop(M car) {
        if (!supportedModel.isInstance(car)) {
            return false;
        }
        // Compile-time safety: only cars typed with the workshop's model type M can be passed
        if (facility.size() < facilitySize && !facility.contains(car)) {
            facility.add(car);
            car.stopVehicle();
            car.engine.stopEngine();
            System.out.println(car.getModelName() + " is now in the workshop " + this.getModelName());
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
        car.forceMove(catchRadius + 10);
        System.out.println(car.getModelName() + " has left " + this.getModelName());
        return facility.remove(car);
    }

    public List<M> getFacility() {return facility;}

    public Point getCoordinates() {return coordinates;}
}
