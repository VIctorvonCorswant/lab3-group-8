import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class VolvoFH16Test {

    private VolvoFH16 fh16;

    /** Sets up a VolvoFH16 */
    @BeforeEach
    public void setUp() {
        fh16 = new VolvoFH16(Color.GRAY, 300.0, 2, 3, "FH16");
    }

    /** Tests if trailer lowers */
    @Test
    public void lowerTrailer() {
        fh16.trailerSafe = true;
        fh16.lowerTrailer();
        assertFalse(fh16.trailerSafe);
    }

    /** Tests if trailer raises */
    @Test
    public void raiseTrailer() {
        fh16.trailerSafe = false;
        fh16.raiseTrailer();
        assertTrue(fh16.trailerSafe);
    }

    /** Tests if trailer loads more than max capacity */
    @Test
    public void loadMaxCapacityCar() {
        fh16.lowerTrailer();
        Volvo240 bil1 = new Volvo240(Color.green, 180.0);
        Volvo240 bil2 = new Volvo240(Color.yellow, 180.0);
        Volvo240 bil3 = new Volvo240(Color.blue, 180.0);
        Volvo240 bil4 = new Volvo240(Color.pink, 180.0);
        fh16.loadCar(bil1);
        fh16.loadCar(bil2);
        fh16.loadCar(bil3);
        fh16.loadCar(bil4);

        assertEquals(fh16.trailerSize, fh16.trailer.size());
    }

    /** Tests both if cars can only be loaded when trailer is down
     * and if trucks can not be loaded */
    @Test
    public void loadCarOnlyWhenTrailerDown() {

        Volvo240 bil1 = new Volvo240(Color.green, 180.0);
        Volvo240 bil2 = new Volvo240(Color.yellow, 180.0);
        Scania lastbil = new Scania(Color.red, 280.0, 2, "lastbil");
        VolvoFH16 lastbil1 = new VolvoFH16(Color.blue, 300.0, 2, 1, "lastbil1");
        fh16.loadCar(bil1);
        fh16.lowerTrailer();
        fh16.loadCar(bil2);
        fh16.loadCar(lastbil);
        // fh16.loadCar(lastbil1);  // This line is commented out because it would cause an error
                                    // because VolvoFH16 can't be towed

        assertEquals(2, fh16.trailer.size());
    }

    /** Tests if it is the last loaded car that gets unloaded */
    @Test
    public void unloadLastCar() {
        Volvo240 bil1 = new Volvo240(Color.green, 180.0);
        Volvo240 bil2 = new Volvo240(Color.yellow, 180.0);
        fh16.lowerTrailer();
        fh16.loadCar(bil1);
        fh16.loadCar(bil2);
        fh16.raiseTrailer();
        fh16.unloadCar();
        fh16.lowerTrailer();
        fh16.unloadCar();

        assertEquals(1, fh16.trailer.size());
        assertEquals(new ArrayList<Car>(Collections.singleton(bil1)), fh16.getTrailer());
    }

    /** Tests if you can gas when trailer is not safe (e.g. trailer is lowered) */
    @Test
    public void gasNotWhenTrailerUnsafe() {
        fh16.trailerSafe = false;
        fh16.lowerTrailer();
        fh16.gas(1);

        assertEquals(0, fh16.getCurrentSpeed());
    }

    /** Checks if the car only loads when it is close enough
     * and unloads the car at a reasonable distance (5 units) */
    @Test
    public void loadingAndUnloadingDistance() {
        Volvo240 bil1 = new Volvo240(Color.green, 180.0);
        fh16.lowerTrailer();
        bil1.forceMove(200.0);
        fh16.loadCar(bil1);
        assertEquals(0, fh16.trailer.size());

        bil1.forceMove(-200.0);
        fh16.loadCar(bil1);
        fh16.unloadCar();
        assertEquals(5, fh16.getGeoDistance(fh16, bil1));
    }

    /** Checks if the car's coordinates is the same as the truck it's loaded on */
    @Test
    public void carFollowsTruck() {
        fh16.startEngine();
        Volvo240 bil1 = new Volvo240(Color.green, 180.0);
        fh16.lowerTrailer();
        assertEquals(0, fh16.getGeoDistance(fh16, bil1));
        fh16.loadCar(bil1);
        fh16.raiseTrailer();
        bil1.forceMove(300.0);
        fh16.gas(1);
        assertEquals(0, fh16.getGeoDistance(fh16, bil1));
    }
}