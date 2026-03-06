package Components;

import java.awt.*;

// Yes the Components.Scania is towable (https://www.agg-net.com/sites/default/files/styles/large_compact_teaser/public/images/news/volvo_fh16.jpg?h=bb31b4aa&itok=WxqahyC0)
public class Scania extends Truck implements Towable, Turbo {
    /** Initiate variables */
    private boolean turboOn;
    private TruckAttachment<Object> bed;

    /** Components.Scania constructor */
    public Scania(Color color, double EnginePower, int trailerSize, Point coordinates) {
         super(color, EnginePower, 2, "Scania", coordinates);
         this.bed = new TruckAttachment<Object>(trailerSize);
         this.engine.setTurboFactor(1.3);
    }

    public double getBedAngle() {return this.bed.getTrailerAngle();}

    /** Lower the bed */
    public void raiseBed(double amount) {
        if(amount <= 0) return; // Prevent negative input
        if (this.getCurrentSpeed() > 0) return;
        this.bed.raiseTrailer(1);
    }

    /** Lower the bed */
    public void lowerBed(double amount) {
        this.bed.lowerTrailer(amount);

    }

    /** turbo get/set methods from saab, because the scania has a turbo */
    public void setTurboOn(){this.engine.setTurboOn();}

    public void setTurboOff(){this.engine.setTurboOff();}

    @Override
    public void move(){
        if(this.bed.getTrailerSafe()) super.move();
    }

    @Override
    public void turnLeft(){
        if(this.bed.getTrailerSafe()) super.turnLeft();
    }

    @Override
    public void turnRight(){
        if(this.bed.getTrailerSafe()) super.turnRight();
    }
}
