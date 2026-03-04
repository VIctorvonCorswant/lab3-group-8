import Components.Car;
import Components.Volvo240;
import Components.Workshop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationEngine {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private static Timer timer = new Timer(delay, new TimerListener());

    private static CarController cc;
    private static CarView frame;

    public static void main(String[] args) {
        // Instance of this class

        cc = new CarController();

        frame = new CarView("Bamses lekstuga",cc);

        cc.frame = frame;

        // Start the timer
        timer.start();
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private static class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Point panelDim = cc.getPanelDim();
            for (Car car : cc.getCarList()) {
                car.move();
                car.checkBounds(panelDim.x-(100), panelDim.y-(60));

                int x = (int) Math.round(car.getCoordinates().x);
                int y = (int) Math.round(car.getCoordinates().y);

                cc.moveObject(x,y);
                //cc.frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                cc.frame.drawPanel.repaint(); // TODO: this is weird
            }
            for (Workshop workshop : cc.getWorkshops()) {
                workshop.checkAddVehicleToWorkshop(cc.getCarList());
                workshop.checkRemoveVehicleFromWorkshop(cc.getCarList());
            }
        }
    }

    private boolean checkAddCarToWorkshop(){
        return true;
    }
}