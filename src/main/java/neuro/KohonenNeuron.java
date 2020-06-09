package neuro;

import app.AppSettings;

public class KohonenNeuron extends Neuron {

    private static long id = 0;
    private double theta = 0.5;

    @Override
    protected void inc() {
        id++;
    }

    public KohonenNeuron() {
        super(AppSettings.INPUT_CHANEL_COUNT);
    }

    public static long getId() {
        return id;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }
}
