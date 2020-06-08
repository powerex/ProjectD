package neuro;

import app.AppSettings;
import model.Vector;

public class GrosbergNeuron extends Neuron {

    private static long id = 0;
    private double level = 0.5;

    @Override
    protected void inc() {
        id++;
    }

    public GrosbergNeuron() {
        super(AppSettings.GROSBERG_NEURON_COUNT);
        this.vector = new Vector(AppSettings.KOHONEN_NEURON_COUNT, 0, false);
    }

    public static long getId() {
        return id;
    }
}
