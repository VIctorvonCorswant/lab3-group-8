import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {

    private Workshop<Volvo240> workshop;
    private Workshop allWorkshop;

    /** Sets up a normal Volvo240 specific Workshop*/
    @BeforeEach
    void setUp() {
        workshop = new Workshop<Volvo240>(2);
    }

    @Test
    void getFacility() {
        assertNotNull(workshop.getFacility());
    }

    /** Tests if you can add car to workshop*/
    @Test
    void addCarToWorkshop() {
        Volvo240 car1 = new Volvo240(Color.BLACK, 128.0);
        Volvo240 car2 = new Volvo240(Color.BLUE, 150.0);
        workshop.addCarToWorkshop(car1);
        workshop.addCarToWorkshop(car2);
        assertTrue(workshop.getFacility().contains(car1));
        assertTrue(workshop.getFacility().contains(car2));
    }

    /** Tests if you can add cars after the workshop is full */
    @Test
    void addCarToFullWorkshop() {
        Volvo240 car1 = new Volvo240(Color.BLACK, 128.0);
        Volvo240 car2 = new Volvo240(Color.BLUE, 150.0);
        Volvo240 car3 = new Volvo240(Color.RED, 200.0);
        workshop.addCarToWorkshop(car1);
        workshop.addCarToWorkshop(car2);
        workshop.addCarToWorkshop(car3);
        assertFalse(workshop.getFacility().contains(car3));
    }

    /** Tests if you can remove car from workshop */
    @Test
    void removeCarFromWorkshop() {
        Volvo240 car1 = new Volvo240(Color.BLACK, 128.0);
        workshop.addCarToWorkshop(car1);
        workshop.removeCarFromWorkshop(car1);
        assertFalse(workshop.getFacility().contains(car1));
    }

    /** Proves in syntax code that you cannot add wrong vehicle type, static error */
    @Test
    void onlyAcceptSpecificVehicle() {
        Volvo240 car1 = new Volvo240(Color.BLACK, 128.0);
        Scania truck = new Scania(Color.BLUE, 300, 2, "Scania");

        workshop.addCarToWorkshop(car1);
        //workshop.addCarToWorkshop(truck); //It does not even compile.
                                            //"Men att den säger ifrån är ett bevis att den fungerar" - Ballong.
    }

    /** Tests if you can add any car to a "tag-less" workshop */
    @Test
    void allAcceptingWorkshop() {
        allWorkshop = new Workshop(2);

        Volvo240 car1 = new Volvo240(Color.BLACK, 128.0);
        Scania truck = new Scania(Color.BLUE, 300, 2, "Scania");

        allWorkshop.addCarToWorkshop(car1);
        allWorkshop.addCarToWorkshop(truck);

        assertEquals(2, allWorkshop.facilitySize);
    }
}