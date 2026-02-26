package Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Objects;


/** Reference https://sv.wikipedia.org/wiki/Fordon for our defenition of vehicle */

public abstract class Vehicle {

    protected Movement movement;
    protected VehicleAttribute attribute;


    public Vehicle(Point coordinates, double direction, int nrDoors, String modelName, Color color) {
        this.attribute = new VehicleAttribute(nrDoors, modelName, color);
        this.movement = new Movement(coordinates, direction);
    }

    /** Get current speed */
    public double getCurrentSpeed() {return Math.round(this.movement.getCurrentSpeed());}


    /** Get color */
    public Color getColor() {return this.attribute.getColor();}

    /** Set a new color for the vehicle */
    protected void setColor(Color clr){this.attribute.setColor(clr);}

    protected void setDirection(double direction) {this.movement.setDirection(direction);
    }

    /** Set the new x and y position based on the direction and current speed */
    public void move(){
        this.movement.getCoordinates().x += (int) (Math.cos(this.movement.getDirection()) * getCurrentSpeed());
        this.movement.getCoordinates().y += (int) (Math.sin(this.movement.getDirection()) * getCurrentSpeed());
    }

    /** Force move the vehicle forward by a certain distance */
    protected void forceMove(double distance){
        this.movement.getCoordinates().x += (int) (Math.cos(this.movement.getDirection()) * distance);
        this.movement.getCoordinates().y += (int) (Math.sin(this.movement.getDirection()) * distance);
    }

    //** Force position of the vehicle */
    public void forcePosition(Point inputCords){
        this.movement.getCoordinates().x = inputCords.x;
        this.movement.getCoordinates().y = inputCords.y;
    }

    public void stopVehicle() {this.movement.setCurrentSpeed(0);}

    /** Calculate the distance between two vehicles */
    public double getGeoDistance(Vehicle v1, Vehicle v2){
        double distX = Math.abs(v1.movement.getCoordinates().getX() - v2.movement.getCoordinates().getX());
        double distY = Math.abs(v1.movement.getCoordinates().getY() - v2.movement.getCoordinates().getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    /** Rotate the vehicle left via adding 30° */
    public void turnLeft(){
        // rotate the vehicle left via adding 30°
        this.movement.setDirection((this.movement.getDirection() - Math.toRadians(30)) % (2*Math.PI));
    }

    /** Rotate the vehicle right via subtracting 30° */
    public void turnRight(){this.movement.setDirection(this.movement.getDirection() + Math.toRadians(30)) % (2*Math.PI)); // Gnäller i @override funktioner
    }

    public void checkBounds(int maxX, int maxY) {
        if (this.movement.getCoordinates().x < 0 || this.movement.getCoordinates().x > maxX) {
            this.movement.setDirection(Math.PI - this.movement.getDirection());
        }

        if (this.movement.getCoordinates().y < 0 || this.movement.getCoordinates().y > maxY) {
            this.movement.setDirection(2*Math.PI - this.movement.getDirection());
        }
    }


    public Point getCoordinates(){
        return this.movement.getCoordinates();
    }
}
