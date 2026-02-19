package Components;

import java.awt.*;
import java.util.List;

public class VolvoFH16 extends Truck implements Movable {

    /** Initiate variables */
    private boolean turboOn;
    TruckAttachment<Car> trailer;
    protected int trailerSize = 3;
    public boolean engineOn = false;

    /** Initialize the constructor */
    public VolvoFH16(Color color, double enginePower, int trailerSize, Point coordinates ) {
        super(color, enginePower, 2, "VolvoFH16", coordinates);
        this.trailer = new TruckAttachment<>(trailerSize);
        //this.trailerSize = trailerSize;
        //this.trailerSafe = true; // The trailer is safe to drive with by default
    }

    /** Raise the trailer */
    public void raiseTrailer(double value){
        if(getCurrentSpeed() == 0){
            trailer.raiseTrailer(value);
        }
    }

    /** Lower the trailer */
    public void lowerTrailer(double value) {
        if (getCurrentSpeed() == 0) {
            trailer.lowerTrailer(value);
        }
    }

    public List getTrailer() {return trailer.occupation;}

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
            Car car = (Car) trailer.unloadObject();
            // Unload the car behind the truck, based on the current direction of the truck
            car.direction = (this.direction + Math.toRadians(180)) %  (2*Math.PI);
            car.forceMove(5);
            //car.coordinates = new Point(this.coordinates.x, this.coordinates.y);
            return (Car) car;
        }
        return null;
    }

    /** turbo from saab, because the Components.VolvoFH16 has a turbo */
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
            for (Car car : trailer.occupation) {
                car.coordinates = this.getCoordinates();
            }
        }
    }

    public void turnLeft(){
        if(this.trailerSafe) {
            super.turnLeft();
            // Turn all cars on the trailer with the truck
            for (Car car : trailer.occupation) {
                car.direction = this.direction;
            }
        }
    }

    public void turnRight(){
        if(this.trailerSafe) {
            super.turnRight();
            // Turn all cars on the trailer with the truck
            for (Car car : trailer.occupation) {

                car.setDirection(this.direction);
            }
        }
    }
}
