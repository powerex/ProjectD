package model;

import javafx.util.Pair;
import model.support.IncorrectLengthException;
import model.support.Position;
import org.omg.CORBA.INTERNAL;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vector {

    private double[] coordinates;

    private void normalize() {
        double sum = 0.;
        for (int i = 0; i < coordinates.length; ++i) {
            sum += coordinates[i] * coordinates[i];
        }
        sum = Math.sqrt(sum);
        for (int i = 0; i < coordinates.length; ++i) {
            coordinates[i] = coordinates[i] / sum;
        }
    }

    public Vector(int size) {
        coordinates = new double[size];
        for (int i = 0; i < size; ++i) {
            coordinates[i] = Math.random();
        }
        normalize();
    }

    public Vector(int size, int def) {
        this(size, def, true);
    }

    public Vector(int size, int def, boolean normalize) {
        coordinates = new double[size];
        for (int i = 0; i < size; ++i) {
            coordinates[i] = def;
        }
        if (normalize)
            normalize();
    }

    public Vector(Sprite sprite) {
        this(sprite, true);
    }

    public Vector(Sprite sprite, boolean normalize) {
        int size = sprite.getSize();
        coordinates = new double[size * size];
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                Position p = new Position(row, col);
                coordinates[p.getFlatPosition(size)] = (sprite.getPixel(p)) ? 1 : 0;
            }
        }
        if (normalize)
            normalize();
    }

    public void setCoordinatesValue(double value, int index) {
        coordinates[index] = value;
    }

    public int getIndexOfMaxValue() {
        int maxIndex = 0;
        for (int i=1; i<coordinates.length; ++i) {
            if (coordinates[i] > coordinates[maxIndex])
                maxIndex = i;
        }
        return maxIndex;
    }

    public int getLength() {
        return coordinates.length;
    }

    public double scalarMultiplication(Vector that) throws IncorrectLengthException {
        if (this.coordinates.length != that.coordinates.length)
            throw new IncorrectLengthException();

        double result = 0.;

        for (int i=0; i<coordinates.length; ++i) {
            result += coordinates[i] * that.coordinates[i];
        }

        return result;
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (double c: coordinates) {
            sb.append(String.format("%.3f", c)).append("; ");
        }
        sb.append('}');
        return sb.toString();
    }

    public int[] getDeterminativePositions(int size) {

        List<Pair<Integer, Double>> list = new LinkedList<>();
        for (int i=0; i<coordinates.length; ++i)
            list.add(new Pair<>(i, coordinates[i]));
        list.sort(Comparator.comparing(Pair::getValue));

        List<Integer> indexes = list.stream().skip(coordinates.length - size - 1).map(Pair::getKey).collect(Collectors.toList());

        indexes.stream().forEach(System.err::println);

        int[] result = new int[size];

        return result;
    }
}
