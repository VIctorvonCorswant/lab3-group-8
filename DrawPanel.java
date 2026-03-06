import Components.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements Observer {

    CarController carC;

    // To keep track of a single car's position
    Point objectPoint = new Point();

    void moveit(int x, int y){
        objectPoint.x = x;
        objectPoint.y = y;
    }

    public DrawPanel(int x, int y, CarController cc) {
        this.carC = cc;
        for (Car car : cc.getCarList()){
            car.registerObserver((Observer) this);
        }
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x,y));
        this.setBackground(Color.DARK_GRAY);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : carC.getCarList()) {
            CarRenderer.render(g, car);
        }

        for (Workshop w : carC.getWorkshops()) {
            CarRenderer.render(g, w);
        }
    }

    @Override
    public void update(Subject s) {
        repaint();
    }
}

