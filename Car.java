public abstract class Car extends Vehicle {
    // Initiate variables
    public int nrDoors;
    public String modelName;
    public boolean engineOn = false;

    /** Get the number of doors a car has */
    public int getNrDoors(){
        return nrDoors;
    }

    /** Get the model name of a car */
    public String getModelName(){
        return modelName;
    }

    /** Check if the engine is on */
    public boolean getEngineOn(){
        return engineOn;
    }

    /** Start the engine */
    public void startEngine() {if (!this.engineOn) {this.engineOn = true;}}

    /** Stop the engine */
    public void stopEngine() {if (this.engineOn) {this.engineOn = false;}}

    /** Accelerate by pressing the gas pedal the car */
    public void gas(double amount){
        if (amount >= 0 && amount <= 1){
            if(this.getCurrentSpeed() < this.getEnginePower()){
                incrementSpeed(amount, this.engineOn);
                this.move();
            }
        }
        else {System.out.println("Can't push down pedal because it's already on the floor (Throttle is out of range.)");}
    }

    /** Decelerate by braking the car */
    public void brake(double amount){
        if (amount >= 0 && amount <= 1){
            if(this.getCurrentSpeed() > 0){
                decrementSpeed(amount, this.engineOn);
                this.move();

            }
        }
        else {System.out.println("It's either through the floor or  (Brake is out of range.)");}
    }

    /** Does something different for each car type */
    protected abstract double speedFactor();
}