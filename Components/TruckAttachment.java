package Components;

import java.util.ArrayList;
import java.util.List;


public class TruckAttachment<T> {
    // boolean trailerSafe = true;
    private double trailerAngle = 0;
    private double trailerMaxAngle;
    private Cargo occupation;


    // Trailer
    public TruckAttachment(int size) {
        this.occupation = new <T>Cargo(size);
        this.trailerMaxAngle = 90;
    }

    // Bed
    public TruckAttachment() {
        this.occupation = new <T>Cargo(1);
        this.trailerMaxAngle = 1;
    }

    protected ArrayList<T> getCargo() {return (ArrayList<T>) this.occupation.getCargo();}

    private int getTrailerSize() {
        return this.occupation.getOccupationSize();
    }

    private boolean getTrailerSafe() {
        return (this.trailerAngle == this.trailerMaxAngle);
    }

    public double getTrailerAngle() {
        return this.trailerAngle;
    }
    
    /** Raise the trailer */
    public void raiseTrailer(double value){
        if(!this.getTrailerSafe() && this.getTrailerSize() > this.occupation.getOccupationSize()) {
            this.trailerAngle += Math.min(value, this.trailerMaxAngle);
        }
    }

    /** Lower the trailer */
    public void lowerTrailer(double value) {
        if(!this.getTrailerSafe() && this.getTrailerSize() > this.occupation.getOccupationSize()) {
            this.trailerAngle -= Math.max(value, 0);
        }
    }
    
    /** Load object on attachment */
    public void loadObject(Object object){
        this.occupation.loadObject((T) object);
    } //Hur funkar T här?
    
    /** Unload object from Attachment */
    public Object unloadObject() {
        if (!this.occupation.isListEmpty() && !this.getTrailerSafe()) {
            return occupation.unloadObject();
        }
        return null;
    }
}