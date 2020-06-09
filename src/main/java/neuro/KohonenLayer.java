package neuro;

import app.AppSettings;
import model.Sprite;
import model.Vector;
import model.support.IncorrectLengthException;
import model.support.NotDetermineSystemException;

import java.util.*;

public class KohonenLayer {

    List<KohonenNeuron> layer = new LinkedList<>();
    HashMap<Integer, Sprite> initList = new HashMap<>();
    double alpha;

    public KohonenLayer(List<Sprite> initialList) throws IncorrectLengthException {
        alpha = 0.7;
        List<Integer> workList = new LinkedList<>();
        do {
            workList.clear();
            layer.clear();
            for (int i = 0; i < initialList.size(); ++i) {
                layer.add(new KohonenNeuron());
            }
            for (Sprite s : initialList) {
                Vector v = new Vector(s);
                Vector net = new Vector(initialList.size(), 0);
                int i = 0;
                for (Neuron n : layer) {
                    net.setCoordinatesValue(v.scalarMultiplication(n.getVector()), i);
                    ++i;
                }
                int index = net.getIndexOfMaxValue();
                workList.add(index);
                System.out.println("Index = " + index);
            }
        } while (new HashSet(workList).size() != initialList.size());

        for (int i=0; i<workList.size(); ++i) {
            initList.put(workList.get(i), initialList.get(i));
        }

        System.out.println("OK");
        System.out.println(this);

    }

    public int getGroupIndex(Sprite baseImage) {
        for (Map.Entry<Integer, Sprite> s: initList.entrySet()) {
            if (s.getValue().equals(baseImage))
                return s.getKey();
        }
        return -1;
    }

    public int getRandomOtherGroupIndex(Sprite sprite) {
        int index = getGroupIndex(sprite);
        int result;
        do {
            result = (int) Math.round(Math.random()*initList.size());
        } while (result != index);
        return result;
    }

    public void calcTheta(List<Sprite> rightSet, List<Sprite> wrongSet) throws IncorrectLengthException, NotDetermineSystemException {
        int index = getNeuronIndex(rightSet.get(0));
        List<Double> list = new LinkedList<>();
        for (Sprite s : rightSet) {
            list.add(new Vector(s).scalarMultiplication(layer.get(index).getVector()));
        }
        double min = list.stream().min(Double::compare).get();
        System.out.println("Min = " + min);

        list.clear();
        for (Sprite s : wrongSet) {
            list.add(new Vector(s).scalarMultiplication(layer.get(index).getVector()));
        }
        double max = list.stream().max(Double::compare).get();
        System.out.println("Max = " + max);

        if (min < max) {
            throw new NotDetermineSystemException();
        } else {
            layer.get(index).setTheta((min + max * AppSettings.LAMBDA) / (1 + AppSettings.LAMBDA));
        }

        System.out.println(layer.get(index).getTheta());
    }

    public void learn(Sprite sprite) {
        Vector v = new Vector(sprite);
        Vector net = new Vector(AppSettings.KOHONEN_NEURON_COUNT, 0);
        try {
            for (int i = 0; i < AppSettings.KOHONEN_NEURON_COUNT; ++i) {
                net.setCoordinatesValue(v.scalarMultiplication(layer.get(i).getVector()), i);
            }
            KohonenNeuron winner = layer.get(net.getIndexOfMaxValue());
            config(v, winner);
        } catch (IncorrectLengthException e) {
            e.printStackTrace();
        }
    }

    public void batchLearn(List<Sprite> learningSet) {
        alpha = 0.7;
        for (Sprite s : learningSet) {
            learn(s);
            alpha *= 0.9;
        }

    }

    public Vector getRecognizedVector(Sprite sprite) {
        Vector v = new Vector(sprite);
        Vector net = new Vector(layer.size(), 0);
        try {
            for (int i = 0; i < layer.size(); ++i) {
                net.setCoordinatesValue(v.scalarMultiplication(layer.get(i).getVector()), i);
            }
            return net;
        } catch (IncorrectLengthException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector getTriggeredRecognizedVector(Sprite sprite) {
        Vector v = getRecognizedVector(sprite);

        int maxIndex = 0;
        for (int i = 1; i < v.getLength(); ++i) {
            if (v.getCoordinate(i) > v.getCoordinate(maxIndex))
                maxIndex = i;
        }

        for (int i = 0; i < v.getLength(); ++i) {
            if (v.getCoordinate(i) < layer.get(i).getTheta())
                v.setCoordinatesValue(0, i);
        }

        for (int i=0; i<v.getLength(); ++i) {
            v.setCoordinatesValue((i == maxIndex) ? 1 : 0, i);
        }


        return v;
    }

    public int getNeuronIndex(Sprite sprite) {
        Vector v = new Vector(sprite);
        Vector net = new Vector(layer.size(), 0);
        try {
            for (int i = 0; i < layer.size(); ++i) {
                net.setCoordinatesValue(v.scalarMultiplication(layer.get(i).getVector()), i);
            }
            return net.getIndexOfMaxValue();
        } catch (IncorrectLengthException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (KohonenNeuron k : layer) {
            sb.append(k.toString());
            sb.append('\n');
        }

        return sb.toString();
    }

    private void config(Vector vector, KohonenNeuron neuron) {
        for (int i = 0; i < AppSettings.INPUT_CHANEL_COUNT; ++i) {
            double w = neuron.vector.getCoordinate(i);
            w = w + alpha * (vector.getCoordinate(i) - w);
            neuron.vector.setCoordinatesValue(w, i);
        }
    }

    public int[] getDeterminativePositions(int size, int group) {
        return layer.get(group).getVector().getDeterminativePositions(size);
    }
}
