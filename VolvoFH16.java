import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class VolvoFH16 extends Truck implements Movable{

    /** Initiate variables */
    private boolean turboOn;
    TruckAttachment trailer;
    protected int trailerSize = 3;
    public boolean engineOn = false;

    /** Initialize the constructor */
    public VolvoFH16(Color color, double EnginePower, int nrDoors, int trailerSize, String modelName) {
        super(color, EnginePower, nrDoors, modelName);
        this.trailer = new TruckAttachment(trailerSize);
        //this.trailerSize = trailerSize;
        //this.trailerSafe = true; // The trailer is safe to drive with by default
    }

    /** Raise the trailer */
    public void raiseTrailer(){
        if(!this.trailerSafe && getCurrentSpeed() == 0){
            this.trailerSafe=true;
        }
    }

    /** Lower the trailer */
    public void lowerTrailer() {
        if (this.trailerSafe && getCurrentSpeed() == 0) {
            this.trailerSafe = false;
        }
    }

    public List<Object> getTrailer() {return trailer.occupation;}

    /** Load car to trailer */
    public <T extends Car & Towable> T loadCar(T car){
        if(getCurrentSpeed() == 0){
            if (this.getGeoDistance(car, this) > 5) return car; // Prevent loading cars that are too far away
            trailer.loadObject(car);
            return null;
        }
        return car;
    }


     /** Unload car from trailer */
    public Car unloadCar(){
        if(getCurrentSpeed() == 0){
            Object car = trailer.unloadObject();
            // Unload the car behind the truck, based on the current direction of the truck
            car.direction = (this.direction + Math.toRadians(180)) %  (2*Math.PI);
            car.forceMove(5);
            //car.coordinates = new Point(this.coordinates.x, this.coordinates.y);
            return (Car) car;
        }
        return null;
    }

    /** turbo from saab, because the VolvoFH16 has a turbo */
    public boolean getTurboOn(){return turboOn;}

    public void setTurboOn(){turboOn = true;}

    public void setTurboOff(){turboOn = false;}

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return engine.getEnginePower() * 0.01 * turbo;
    }

    @Override
    public void move(){
        if(this.trailerSafe) {
            super.move();
            // Move all cars on the trailer with the truck
            for (Car car : trailer) {
                car.coordinates = this.coordinates;
            }
        }
    }

    public void turnLeft(){
        if(this.trailerSafe) {
            super.turnLeft();
            // Turn all cars on the trailer with the truck
            for (Car car : trailer) {
                car.direction = this.direction;
            }
        }
    }

    public void turnRight(){
        if(this.trailerSafe) {
            super.turnRight();
            // Turn all cars on the trailer with the truck
            for (Car car : trailer) {
                car.direction = this.direction;
            }
        }
    }
}
