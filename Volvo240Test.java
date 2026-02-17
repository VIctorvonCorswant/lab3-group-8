import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    private Volvo240 volvo;

    /** Sets up a normal Volvo 240 */
    @BeforeEach
    public void setUp() {volvo = new Volvo240(Color.green,(double)100);}

    /** Checks if engine power is correct */
    @Test
    public void getEnginePower() {
        assertEquals(100, volvo.getEnginePower());
    }

    /** Checks if speed factor is correct */
    @Test
    public void speedFactor(){
        double expectedSpeedFactor = 100 * 0.01 * 1.25;
        assertEquals(expectedSpeedFactor, volvo.speedFactor(), 0.0001);
    }

    /** Checks if the gas pedal goes out of bounds */
    @Test
    public void throttleOutOfBounds() {
        volvo.engineOn = true;
        volvo.gas(1.5);
        assertEquals(0, volvo.getCurrentSpeed());

        volvo.gas(-0.5);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    /** Checks if the brake pedal goes out of bounds */
    @Test
    public void brakeOutOfBounds() {
        volvo.engineOn = true;
        volvo.currentSpeed = 40;

        volvo.brake(1.5);
        assertEquals(40, volvo.getCurrentSpeed());
        volvo.brake(-0.5);
        assertEquals(40, volvo.getCurrentSpeed());
    }

    /** Accelerates and decelerates to check if
     * speed gets out of intended range */
    @Test
    public void carSpeedOutOfRange() {
        volvo.engineOn = true;

        for(int i=0; i<300; i++) {volvo.gas(1);}
        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed());

        for(int i=0; i<300; i++) {volvo.brake(1);}
        assertEquals(0, volvo.getCurrentSpeed());
    }

    /** Checks if car cannot gas when engine is off */
    @Test
    public void cannotGasWhenEngineOff() {
        volvo.engineOn = false;
        volvo.gas(0.5);
        assertEquals(0, volvo.getCurrentSpeed(), 0.0001);
    }
}
