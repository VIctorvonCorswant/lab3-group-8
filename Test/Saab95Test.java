package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import Components.Saab95;

class Saab95Test {

    private Saab95 saab95;

    @BeforeEach
    public void setUp() {
        saab95 = new Saab95(Color.red, (double) 125);
    }

    @Test
    void setTurboOn() {
        assertFalse(saab95.getTurboOn());
        saab95.setTurboOn();
        assertTrue(saab95.getTurboOn());
    }

    @Test
    void setTurboOff() {
        assertFalse(saab95.getTurboOn());
        saab95.setTurboOn();
        assertTrue(saab95.getTurboOn());
        saab95.setTurboOff();
        assertFalse(saab95.getTurboOn());
    }

    @Test
    void speedFactor() {
        // Turbo off
        saab95.setTurboOff();
        double expectedSpeedFactorOff = 125 * 0.01 * 1;
        assertEquals(expectedSpeedFactorOff, saab95.speedFactor(), 0.0001);

        // Turbo on
        saab95.setTurboOn();
        double expectedSpeedFactorOn = 125 * 0.01 * 1.3;
        assertEquals(expectedSpeedFactorOn, saab95.speedFactor(), 0.0001);
    }

    @Test
    void cannotGasWhenEngineOff() {
        saab95.setTurboOn();
        saab95.gas(0.5);
        assertEquals(0, saab95.getCurrentSpeed(), 0.0001);
    }
}