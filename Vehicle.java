import java.awt.*;

/** Reference https://sv.wikipedia.org/wiki/Fordon for our defenition of vehicle */

public abstract class Vehicle {
    public double currentSpeed;
    public Color color;
    public double enginePower;
    public Point coordinates = new Point(0, 0);
    public double direction = 0;

    /** Get current speed */
    public double getCurrentSpeed() {return currentSpeed;}

    /** Get color */
    public Color getColor() {return color;}

    /** Set a new color for the vehicle */
    protected void setColor(Color clr){color = clr;}

    /** Calculate the speed factor of the vehicle */
    protected double speedFactor(double enginePower) {return enginePower * 0.01;}

    /** Increase the speed of the vehicle */
    protected void incrementSpeed(double amount, boolean engineOn){
        if (amount < 0 || !engineOn)
            return;
        currentSpeed = Math.min(getCurrentSpeed() + (speedFactor(enginePower) * amount), enginePower);
    }

    /** Decrease the speed of the vehicle */
    protected void decrementSpeed(double amount, boolean engineOn){
        if (amount < 0 || !engineOn)
            return;
        currentSpeed = Math.max(getCurrentSpeed() - (speedFactor(enginePower) * amount), 0);
    }

    /** Get engine power */
    public double getEnginePower(){return this.enginePower;}

    /** Set the new x and y position based on the direction and current speed */
    public void move(){
        coordinates.x += (int) (Math.cos(direction) * getCurrentSpeed());
        coordinates.y += (int) (Math.sin(direction) * getCurrentSpeed());
    }

    /** Force move the vehicle forward by a certain distance */
    protected void forceMove(double distance){
        coordinates.x += (int) (Math.cos(direction) * distance);
        coordinates.y += (int) (Math.sin(direction) * distance);
    }

    /** Calculate the distance between two vehicles */
    public double getGeoDistance(Vehicle v1, Vehicle v2){
        double distX = Math.abs(v1.coordinates.getX() - v2.coordinates.getX());
        double distY = Math.abs(v1.coordinates.getY() - v2.coordinates.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    /** Rotate the vehicle left via adding 30° */
    public void turnLeft(){
        // rotate the vehicle left via adding 30°
        direction = (direction + Math.toRadians(30)) % (2*Math.PI);
    }

    /** Rotate the vehicle right via subtracting 30° */
    public void turnRight(){
        direction = (direction - Math.toRadians(30)) % (2*Math.PI);
    }
}
