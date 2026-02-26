package Components;

import java.awt.*;

public class VehicleAttribute {
   public int nrDoors;
   public String modelName;
   public Color color;

   public VehicleAttribute(int nrDoors, String modelName, Color color) {
      this.nrDoors = nrDoors;
      this.modelName = modelName;
      this.color = color;
   }

   public int getNrDoors() {return nrDoors;}

   protected void setNrDoors(int nrDoors) {this.nrDoors = nrDoors;}

   public String getModelName() {return modelName;}

   protected void setModelName(String modelName) {this.modelName = modelName;}

   public Color getColor() {return color;}

   protected void setColor(Color color) {this.color = color;}




}
