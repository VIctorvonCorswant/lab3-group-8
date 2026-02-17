package FinalObject;

import Components.Car;

import java.util.ArrayList;
import java.util.List;

public class Workshop<M extends Car> {

    List<M> facility = new ArrayList<>();
    protected int facilitySize = 5;


    public Workshop(int facilitySize) {this.facilitySize = facilitySize;}

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
