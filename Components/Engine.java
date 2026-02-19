package Components;

public class Engine {
    private double enginePower;
    private boolean engineOn = false;

    private double trimFactor = 0;
    private double turboFactor = 0;
    private boolean turboOn;

    public Engine(double enginePower) {
        this.enginePower = enginePower;
    }

    /** Calculate the speed factor of the vehicle */
    protected double speedFactor(double enginePower) {
        if (trimFactor > 0) {
            return getEnginePower() * 0.01 * trimFactor;
        }
        else if (turboFactor > 0) {
            double turbo = 1;
            if(turboOn) {turbo = turboFactor;}
            return getEnginePower() * 0.01 * turbo;
        }
        else {
            return enginePower * 0.01;
        }
    }

    public void setTurboOn(){turboOn = true;}

    public void setTurboOff(){turboOn = false;}

    public void setTrimFactor(double trimFactor) {this.trimFactor = trimFactor;}

    public void setTurboFactor(double turboFactor) {this.turboFactor = turboFactor;}

    /** Get engine power */
    public double getEnginePower(){return this.enginePower;}

    /** Set engine power */
    public void setEnginePower(double power){this.enginePower = power;}

    /** Start the engine */
    public void startEngine() {if (!this.engineOn) {this.engineOn = true;}}

    /** Stop the engine */
    public void stopEngine() {if (this.engineOn) {this.engineOn = false;}}

    /** Check if the engine is on */
    public boolean isEngineOn() {return this.engineOn;}

    public double calculateSpeed(double value) {
        if (this.isEngineOn()) {
            return this.speedFactor(this.getEnginePower()) * value;
        }
        return 0;

    }
}
