package neuro;

import app.AppSettings;
import model.Figure;
import model.Sprite;
import model.Vector;

import java.util.LinkedList;
import java.util.List;

public class GrosbergLayer {

    List<GrosbergNeuron> layer = new LinkedList<>();
    double beta = 1;

    public GrosbergLayer() {
        for (int i = 0; i < AppSettings.GROSBERG_NEURON_COUNT; ++i) {
            layer.add(new GrosbergNeuron());
        }
    }

    public void study(Vector kohonenVector, Sprite associatedSprite) {
        Vector v = new Vector(associatedSprite, false);

        System.out.print("Kohonen vector");
        System.out.println(kohonenVector);

        System.out.print("Associated: ");
        System.out.println(v);

        for (int j = 0; j < layer.size(); ++j) {
            for (int i=0; i<kohonenVector.getLength(); ++i) {
                double vji = layer.get(j).getVector().getCoordinate(i);
                layer.get(j).getVector().setCoordinatesValue(vji +
                        beta * (v.getCoordinate(j) - vji) * kohonenVector.getCoordinate(i), i);
            }
        }
    }

    public void info() {
        for (GrosbergNeuron n: layer) {
            System.out.println(n);
        }
    }

    public Sprite getSprite(Vector kohonenVector) {
        boolean[][] table = new boolean[AppSettings.OUT_SIZE][AppSettings.OUT_SIZE];
        double[] v = new double[AppSettings.GROSBERG_NEURON_COUNT];
        for (int i=0; i<v.length; ++i) {
            v[i] = 0;
            for (int j=0; j<AppSettings.KOHONEN_NEURON_COUNT; ++j) {
                v[i] += layer.get(i).getVector().getCoordinate(j) * kohonenVector.getCoordinate(j);
            }
        }


        for (int i=0; i<v.length; ++i) {
            table[i / AppSettings.OUT_SIZE][i % AppSettings.OUT_SIZE] = v[i] >= 0.6 ;
        }

        return new Figure(table);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (GrosbergNeuron k : layer) {
            sb.append(k.toString());
            sb.append('\n');
        }

        return sb.toString();
    }
}
