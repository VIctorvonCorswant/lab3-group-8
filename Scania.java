import java.awt.*;

// Yes the Scania is towable (https://www.agg-net.com/sites/default/files/styles/large_compact_teaser/public/images/news/volvo_fh16.jpg?h=bb31b4aa&itok=WxqahyC0)
public class Scania extends Truck implements Movable, Towable{
    /** Initiate variables */
    private double bedAngle = 0.0;
    private boolean turboOn;

    /** Scania constructor */
    protected Scania(Color color, double EnginePower, int nrDoors, String modelName) {
         super(color, EnginePower, nrDoors, modelName);
         this.trailerSafe = true; // The trailer is safe to drive with by default
    }

    public double getBedAngle() {return bedAngle;}

    /** Lower the bed */
    public void raiseBed(double amount){
        if(amount <= 0) return; // Prevent negative input
        if (this.getCurrentSpeed() > 0) return;
        this.trailerSafe = false; // The trailer is not safe to drive with when the bed is raised
        if(this.getBedAngle() + amount >= 70){
            bedAngle = 70;
        }
        else if((this.getBedAngle() + amount) > 0 && this.getCurrentSpeed() == 0 && amount > 0){
            bedAngle = getBedAngle() + amount;
        }
    }

    /** Lower the bed */
    public void lowerBed(double amount){
        if(this.getBedAngle() - amount <= 0){
            //We skip the moving-check, since it shouldn't occur,
            //and if we're moving want to lower the bed
            bedAngle = 0;
            this.trailerSafe = true; // The trailer is safe to drive with when the bed is fully lowered
        }
        else if((this.getBedAngle() - amount) >= 0 && amount > 0){
            bedAngle = getBedAngle() - amount;
        }
    }

    /** turbo get/set methods from saab, because the scania has a turbo */
    public boolean getTurboOn(){return turboOn;}

    public void setTurboOn(){turboOn = true;}

    public void setTurboOff(){turboOn = false;}

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
