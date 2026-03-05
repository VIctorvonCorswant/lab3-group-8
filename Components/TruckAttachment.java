package Components;

import java.util.ArrayList;
import java.util.List;


public class TruckAttachment<T> {
    private double trailerAngle = 0;
    private double trailerMaxAngle;
    private Cargo occupation;


    // Trailer
    public TruckAttachment(int size) {
        this.occupation = new <T>Cargo(size);
        this.trailerMaxAngle = 90;
        this.trailerAngle = 0;
    }

    // Bed
    public TruckAttachment() {
        this.occupation = new <T>Cargo(1);
        this.trailerMaxAngle = 1;
        this.trailerAngle = 0;
    }

    protected ArrayList<T> getCargo() {return (ArrayList<T>) this.occupation.getCargo();}

    private int getTrailerSize() {
        return this.occupation.getOccupationSize();
    }

    public double getTrailerAngle() {
        return this.trailerAngle;
    }

    public boolean getTrailerSafe() {
        if (this.trailerAngle == 0) return true;
        else return false;
    }
    
    /** Raise the trailer */
    public void raiseTrailer(double value){
        if(this.trailerAngle < this.trailerMaxAngle && this.getTrailerSize() > this.occupation.getOccupationSize()) {
            this.trailerAngle += Math.min(value, this.trailerMaxAngle);
        }
        else this.trailerAngle = this.trailerMaxAngle;
    }

    /** Lower the trailer */
    public void lowerTrailer(double value) {
        if(this.trailerAngle > 0 && this.getTrailerSize() > this.occupation.getOccupationSize()) {
            this.trailerAngle -= Math.max(value, 0);
        }
        else this.trailerAngle = 0;
    }
    
    /** Load object on attachment */
    public void loadObject(Object object){
        this.occupation.loadObject((T) object);
    } //Hur funkar T här?
    
    /** Unload object from Attachment */
    public Object unloadObject() {
        if (!this.occupation.isListEmpty() && getTrailerSafe()) {
            return occupation.unloadObject();
        }
        return null;
    }
}