package Components;

import java.util.ArrayList;
import java.util.List;

public class Cargo<T> {
    protected List<T> occupation;
    private int occupationSize;




    public Cargo(int occupationSize) {
        this.occupationSize = occupationSize;
        this.occupation = new ArrayList<T>(occupationSize);
    }

    public Cargo() {this.occupation = new  ArrayList<>(1);}

    public ArrayList<T> getCargo() {return (ArrayList<T>) this.occupation;}

    public int getOccupationSize() {return occupationSize;}

    protected void setOccupationSize(int occupationSize) {this.occupationSize = occupationSize;}

    public ArrayList<T> getOccupation() {return (ArrayList<T>) this.occupation;}

    public void loadObject(Object object) {
        this.occupation.add((T) object);
    }

    public Object unloadObject() {
        return this.occupation.removeLast();
    }

    public boolean isListEmpty() {
        return this.occupation.isEmpty();
    }

}
