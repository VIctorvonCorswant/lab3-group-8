package Components;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomCar {
    private ArrayList<Color> allColors;
    private Point panelDim;

    public RandomCar(Point panelDim) {
        this.allColors = new ArrayList<>();
        this.allColors.add(Color.red);
        this.allColors.add(Color.blue);
        this.allColors.add(Color.green);
        this.allColors.add(Color.yellow);
        this.allColors.add(Color.black);
        this.allColors.add(Color.white);
        this.allColors.add(Color.cyan);
        this.allColors.add(Color.magenta);
        this.allColors.add(Color.orange);
        this.allColors.add(Color.pink);

        this.panelDim = panelDim;

    }

    public Car randomizeCar() {
        Random rand = new Random();
        int colorNum = rand.nextInt(allColors.size()-1);
        double engineNum = rand.nextDouble(100)+100;
        Point startPoint = new Point(rand.nextInt(panelDim.x-260)+80,rand.nextInt(panelDim.y-460)+80);
        int carNum = rand.nextInt(4);

        switch (carNum) {
            case 0:
                return new Volvo240(allColors.get(colorNum), engineNum, startPoint);

            case 1:
                return new Saab95(allColors.get(colorNum), engineNum, startPoint);

            case 2:
                return new Scania(allColors.get(colorNum), engineNum, 1, startPoint);

            case 3:
                return new VolvoFH16(allColors.get(colorNum), engineNum, 5, startPoint);

        }
        return null;
    }
}
