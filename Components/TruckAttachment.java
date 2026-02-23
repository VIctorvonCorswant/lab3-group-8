package Components;

import java.util.ArrayList;
import java.util.List;


public class TruckAttachment<T> {
    List<T> occupation;
    private int occupationSize;
    // boolean trailerSafe = true;
    private double trailerAngle = 0;
    private double trailerMaxAngle;


    // Trailer
    public TruckAttachment(int size) {
        this.occupation = new ArrayList<>(size);
        this.occupationSize = size;
        this.trailerMaxAngle = 90;
    }

    // Bed
    public TruckAttachment() {
        this.occupation = new ArrayList<>(1);
        this.occupationSize = 1;
        this.trailerMaxAngle = 1;
    }

    private int getTrailerSize() {
        return this.occupation.size();
    }

    private boolean getTrailerSafe() {
        return (this.trailerAngle == this.trailerMaxAngle);
    }

    public double getTrailerAngle() {
        return this.trailerAngle;
    }
    
    /** Raise the trailer */
    public void raiseTrailer(double value){
        if(!this.getTrailerSafe() && this.getTrailerSize() > this.occupationSize) {
            this.trailerAngle += Math.min(value, this.trailerMaxAngle);
        }
    }

    /** Lower the trailer */
    public void lowerTrailer(double value) {
        if(!this.getTrailerSafe() && this.getTrailerSize() > this.occupationSize) {
            this.trailerAngle -= Math.max(value, 0);
        }
    }
    
    /** Load object on attachment */
    public void loadObject(Object object){
        occupation.add((T) object);
    }
    
    /** Unload ob   ject from Attachment */
    public Object unloadObject() {
        if (!occupation.isEmpty() && !this.getTrailerSafe()) {
            return occupation.removeLast();
        }
        return null;
    }
}