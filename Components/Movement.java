package Components;
import java.awt.*;

public class Movement {
    protected Point coordinates;
    protected double direction;
    protected double currentSpeed;

    public Movement(Point coordinates, double direction) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.currentSpeed = 0;
    }

    public double getCurrentSpeed() {return currentSpeed;}

    protected double setCurrentSpeed(double speed) {;return this.currentSpeed = speed;}

    public Point getCoordinates() {return this.coordinates;}

    protected void setCoordinates(Point coordinates) {}

    public  double getDirection() {return direction;}

    protected void setDirection(double direction) {this.direction = direction;}


}
