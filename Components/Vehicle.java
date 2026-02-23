package Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Objects;

/** Reference https://sv.wikipedia.org/wiki/Fordon for our defenition of vehicle */

public abstract class Vehicle {
    protected double currentSpeed;
    public Color color;

    protected Point coordinates = new Point(0, 0);
    protected double direction = 0;

    public Vehicle(Color color, Point coordinates, double direction) {
        this.color = color;
        this.coordinates = coordinates;
        this.direction = direction;
    }

    /** Get current speed */
    public double getCurrentSpeed() {return Math.round(currentSpeed);}


    /** Get color */
    public Color getColor() {return color;}

    /** Set a new color for the vehicle */
    protected void setColor(Color clr){color = clr;}

    protected void setDirection(double direction) {
        this.direction = direction;
    }

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

    //** Force position of the vehicle */
    public void forcePosition(Point inputCords){
        coordinates.x = inputCords.x;
        coordinates.y = inputCords.y;
    }

    public void stopVehicle() {this.currentSpeed = 0;}

    /** Calculate the distance between two vehicles */
    public double getGeoDistance(Vehicle v1, Vehicle v2){
        double distX = Math.abs(v1.coordinates.getX() - v2.coordinates.getX());
        double distY = Math.abs(v1.coordinates.getY() - v2.coordinates.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    /** Rotate the vehicle left via adding 30° */
    public void turnLeft(){
        // rotate the vehicle left via adding 30°
        direction = (direction - Math.toRadians(30)) % (2*Math.PI);
    }

    /** Rotate the vehicle right via subtracting 30° */
    public void turnRight(){
        direction = (direction + Math.toRadians(30)) % (2*Math.PI);
    }

    public void checkBounds(int maxX, int maxY) {
        if (coordinates.x < 0 || coordinates.x > maxX) {
            direction = Math.PI - direction;
        }

        if (coordinates.y < 0 || coordinates.y > maxY) {
            direction = 2*Math.PI - direction;
        }
    }


    public Point getCoordinates(){
        return coordinates;
    }
}
