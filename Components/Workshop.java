package Components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Workshop<M extends Car> {

    List<M> facility = new ArrayList<>();
    protected int facilitySize = 5;
    public String modelName;
    public Point coordinates = new Point(300, 300);


    public Workshop(int facilitySize, String modelName) {
        this.facilitySize = facilitySize;
        this.modelName  =modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public boolean addCarToWorkshop(M car) {
        // Compile-time safety: only cars typed with the workshop's model type M can be passed
        if (facility.size() < facilitySize) {
            facility.add(car);
            return true;
        }
        return false;
    }

    public boolean removeCarFromWorkshop(M car) {
        return facility.remove(car);
    }

    public List<M> getFacility() {return facility;}
}
