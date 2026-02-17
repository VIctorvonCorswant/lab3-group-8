package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

import Components.Volvo240;
import Components.Car;

class VehicleTest {

    private Car kraschIT;

    /** Sets up a normal kraschIT 240 */
    @BeforeEach
    public void setUp() {kraschIT = new Volvo240(Color.green,(double)100);}

    /** Checks if current speed is correct */
    @Test
    void getCurrentSpeed() {
        assertEquals(0, kraschIT.getCurrentSpeed(), 0.0001);
    }

    /** Checks if color is correct */
    @Test
    public void getColor() {assertEquals(Color.green, kraschIT.getColor());}

    /** Checks if you can change color is correct */
    @Test
    public void setColor() {
        kraschIT.setColor(Color.BLUE);
        assertEquals(Color.BLUE, kraschIT.getColor());
    }

    /** Checks if speed factor is correct */
    @Test
    void speedFactor() {
        double expectedSpeedFactor = 100 * 0.01;
        assertEquals(expectedSpeedFactor, kraschIT.speedFactor(kraschIT.getEnginePower()), 0.0001);
    }

    /** Checks if increment speed works correctly */
    @Test
    void incrementSpeed() {
        kraschIT.engineOn = true;
        double speed = kraschIT.getCurrentSpeed();
        kraschIT.incrementSpeed(1, kraschIT.getEngineOn());
        assertEquals(speed + kraschIT.speedFactor(kraschIT.getEnginePower()), kraschIT.getCurrentSpeed(), 0.0001);
    }

    /** Checks if decrement speed works correctly */
    @Test
    void decrementSpeed() {
        kraschIT.engineOn = true;
        kraschIT.incrementSpeed(2, kraschIT.getEngineOn()); // First increase speed to avoid negative speed
        double speed = kraschIT.getCurrentSpeed();
        kraschIT.decrementSpeed(1, kraschIT.getEngineOn());
        assertEquals(speed - kraschIT.speedFactor(kraschIT.getEnginePower()), kraschIT.getCurrentSpeed(), 0.0001);

        //Test for negative speed
        kraschIT.decrementSpeed(3, kraschIT.getEngineOn());
        assertEquals(0, kraschIT.getCurrentSpeed(), 0.0001);
    }

    /** Checks if engine power is correct */
    @Test
    void getEnginePower() {
        assertEquals(100, kraschIT.getEnginePower(), 0.0001);
    }
    
    /** Checks if move moves a vehicle correctly */
    @Test
    public void move() {
        kraschIT.engineOn = true;
        kraschIT.gas(1);
        assertEquals(1, kraschIT.coordinates.getX());
        assertEquals(0, kraschIT.coordinates.getY());

        kraschIT.turnLeft();
        kraschIT.turnLeft();
        kraschIT.turnLeft();
        kraschIT.gas(1);

        assertEquals(1, kraschIT.coordinates.getX());
        assertEquals(2, kraschIT.coordinates.getY());

        kraschIT.turnRight();
        kraschIT.turnRight();
        kraschIT.turnRight();

        kraschIT.brake(1);
        assertEquals(2, kraschIT.coordinates.getX());
        assertEquals(2, kraschIT.coordinates.getY());
    }

    /** Checks if turn left turns the vehicle correctly */
    @Test
    void turnLeft() {
        kraschIT.turnLeft();
         assertEquals(Math.toRadians(30), kraschIT.direction, 0.0001);
    }

    /** Checks if turn right turns the vehicle correctly */
    @Test
    void turnRight() {
        kraschIT.turnRight();
        assertEquals(-Math.toRadians(30), kraschIT.direction, 0.0001);
    }

    /** Checks if decrement speed does not go below zero */
    @Test
    void decrementSpeedDoesNotGoBelowZero() {
        kraschIT.decrementSpeed(100, kraschIT.getEngineOn());
        assertEquals(0, kraschIT.getCurrentSpeed(), 0.0001);
    }

    /** Checks if gas input outside valid range is ignored */
    @Test
    void gasInputOutOfRange() {
        kraschIT.engineOn = true;
        kraschIT.gas(1.5);
        assertEquals(0, kraschIT.getCurrentSpeed(), 0.0001);
    }
}