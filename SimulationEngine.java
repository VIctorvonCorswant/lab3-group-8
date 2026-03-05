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
                //cc.frame.drawPanel.repaint(); // TODO: this is weird
            }
            for (Workshop workshop : cc.getWorkshops()) {
                checkAddCarToWorkshop(workshop);
                checkRemoveCarFromWorkshop(workshop);
            }
        }
    }

    private static void checkAddCarToWorkshop(Workshop workshop) {
        for (Car car : cc.getCarList()){
            double distX = Math.abs(workshop.getCoordinates().getX() - car.getCoordinates().getX());
            double distY = Math.abs(workshop.getCoordinates().getY() - car.getCoordinates().getY());

            if (distX < workshop.getCatchRadius() && distY < workshop.getCatchRadius()) {
                boolean added = workshop.addCarToWorkshop(car);
                if (added) {
                    car.forcePosition(new Point(
                            (int)workshop.getCoordinates().getX(),
                            (int)workshop.getCoordinates().getY()
                    ));
                    System.out.println(workshop.getModelName() + " is now in the workshop " + workshop.getModelName());
                }
            }
        }
    }

    private static void checkRemoveCarFromWorkshop(Workshop workshop) {
        for (Car car : cc.getCarList()){
            if(workshop.getCarsInWorkshop().contains(car)){
                double distX = Math.abs(workshop.getCoordinates().getX() - car.getCoordinates().getX());
                double distY = Math.abs(workshop.getCoordinates().getY() - car.getCoordinates().getY());

                if (distX > 2 || distY > 2) {
                    workshop.removeCarFromWorkshop(car);
                    System.out.println(workshop.getModelName() + " is now removed from " + workshop.getModelName());
                }
            }
        }
    }

}