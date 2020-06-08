package neuro;

import model.Vector;

public abstract class Neuron {

    protected Vector vector;

    public Neuron(int size) {
        this.vector = new Vector(size);
        inc();
    }

    public Neuron(Vector vector) {
        this.vector = vector;
    }

    public double getWeight(int index) {
        return vector.getCoordinate(index);
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "vector=" + vector +
                '}';
    }

    public Vector getVector() {
        return vector;
    }

    protected abstract void inc();
}
