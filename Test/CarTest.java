package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car kraschIT2;

    /** Sets up a normal kraschIT as a volvo240 */
    @BeforeEach
    public void setUp() {kraschIT2 = new Volvo240(Color.black,(double)100);}


    /** Checks if the car has correct number of doors */
    @Test
    public void getNrDoors() {assertEquals(4, kraschIT2.getNrDoors());}


    /** Checks if model name is correct */
    @Test
    public void getModelName() {
        assertEquals("FinalObject.Volvo240", kraschIT2.getModelName());
    }

    /** Checks if car actually starts */
    @Test
    public void getEngineOn() {
        //test if engine can power on
        assertFalse(kraschIT2.getEngineOn());
        kraschIT2.engineOn = true;
        assertTrue(kraschIT2.getEngineOn());

        kraschIT2.engineOn = false;
        assertFalse(kraschIT2.getEngineOn());
    }

    /** Checks if starting engine works */
    @Test
    public void startEngine(){
        kraschIT2.startEngine();
        assertTrue(kraschIT2.engineOn);
        kraschIT2.startEngine();
        assertTrue(kraschIT2.engineOn);
    }

    /** Checks if stopping engine works */
    @Test
    public void stopEngine(){
        kraschIT2.engineOn = true;
        kraschIT2.stopEngine();
        assertFalse(kraschIT2.engineOn);
        kraschIT2.stopEngine();
        assertFalse(kraschIT2.engineOn);
    }

    /** Tests if the gas pedal decreases speed */
    @Test
    public void gasPedal(){
        kraschIT2.engineOn=true;

        double temp = kraschIT2.getCurrentSpeed();
        kraschIT2.gas(1);
        assertFalse(kraschIT2.getCurrentSpeed() < temp);
    }

    /** Tests if the brake pedal increases speed */
    @Test
    public void brakePedal(){
        kraschIT2.engineOn=true;

        kraschIT2.gas(1);
        double temp = kraschIT2.getCurrentSpeed();
        kraschIT2.brake(1);
        assertFalse(kraschIT2.getCurrentSpeed() > temp);
    }

    /** Checks if speed factor is correct */
    @Test
    void speedFactor() {
        double expectedSpeedFactor = 100 * 0.01 * 1.25;
        assertEquals(expectedSpeedFactor, kraschIT2.speedFactor(), 0.0001);
    }

    /** Checks if car cannot move when engine is off */
    @Test
    public void cannotMoveWhenEngineOff() {
        kraschIT2.engineOn = false;
        kraschIT2.incrementSpeed(1, kraschIT2.getEngineOn());
        assertEquals(0, kraschIT2.getCurrentSpeed());
    }

    /** Checks if car speed does not exceed maximum speed */
    @Test
    public void doesNotExceedMaxSpeed() {
        kraschIT2.engineOn = true;
        kraschIT2.incrementSpeed(1000, kraschIT2.getEngineOn());
        assertEquals(kraschIT2.getEnginePower(), kraschIT2.getCurrentSpeed(), 0.0001);
    }
}