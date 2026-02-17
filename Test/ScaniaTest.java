package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import Components.Scania;

class ScaniaTest {

    private Scania scania;

    /** Sets up a normal Components.Scania */
    @BeforeEach
    public void setUp() {scania = new Scania(Color.GRAY, 280.0, 2, "Components.Scania");}

    /** Tests if we can gas whilst the bed is raised */
    @Test
    public void noGasWithRaisedBed() {
        System.out.println(scania.getColor());
        scania.raiseBed(40);
        scania.gas(1);
        assertEquals(0, scania.getCurrentSpeed());
    }

    @Test
    public void noRaiseBedWhilstMoving(){
        scania.engineOn = true;
        scania.gas(1);
        scania.raiseBed((55));
        assertEquals(0, scania.getBedAngle());
    }

    /** Tests if the bed cannot be raised above its maximum angle */
    @Test
    public void bedCannotExceedMaxAngle() {
        scania.raiseBed(100);
        assertEquals(70, scania.getBedAngle());
    }

    /** Tests if the bed cannot be lowered below zero */
    @Test
    public void bedCannotGoBelowZero() {
        scania.raiseBed(40);
        scania.lowerBed(50);
        assertEquals(0, scania.getBedAngle());
    }
}
