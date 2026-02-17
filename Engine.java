public class Engine {
    private double enginePower;
    private boolean engineOn = false;

    public Engine(double enginePower) {
        this.enginePower = enginePower;
    }

    /** Calculate the speed factor of the vehicle */
    protected double speedFactor(double enginePower) {return enginePower * 0.01;}

    /** Get engine power */
    public double getEnginePower(){return this.enginePower;}

    /** Set engine power */
    public void setEnginePower(double power){this.enginePower = power;}

    /** Start the engine */
    public void startEngine() {if (!this.engineOn) {this.engineOn = true;}}

    /** Stop the engine */
    public void stopEngine() {if (this.engineOn) {this.engineOn = false;}}

    public boolean isEngineOn() {return this.engineOn;}
}
