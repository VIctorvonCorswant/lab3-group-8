package Components;

import java.util.ArrayList;
import java.util.List;

public class Cargo<T> {
    protected List<T> occupation;
    private int occupationSize;




    public Cargo(int occupationSize) {
        this.occupationSize = occupationSize;
        this.occupation = new ArrayList<>(occupationSize);
    }

    public Cargo() {this.occupation = new  ArrayList<>(1);}

    public int getOccupationSize() {return occupationSize;}

    protected void setOccupationSize(int occupationSize) {this.occupationSize = occupationSize;}

    public ArrayList<T> getOccupation() {return (ArrayList<T>) this.occupation;}

    public void loadObject(Object object) {
        this.occupation.add((T) object);
    }

    public Object unloadObject(Object object) {
        this.occupation.remove(object);
        return object;
    }

}
