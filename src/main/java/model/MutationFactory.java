package model;

import model.Figure;
import model.Sprite;
import model.support.Position;

import java.util.List;
import java.util.Random;

public class MutationFactory {

    private static Random gen = new Random();

    public static Sprite getMutantSprite(Sprite source, Position position) {
        boolean[][] table = source.getTable().clone();
        for (int i=0; i<table.length; ++i) {
            table[i] = table[i].clone();
        }
        table[position.row][position.col] = !table[position.row][position.col];
        return new Figure(table);
    }

    public static Sprite getMutantSprite(Sprite source, int randomMutationCount) {
        boolean[][] table = source.getTable().clone();
        int size = table.length;
        for (int i=0; i<size; ++i) {
            table[i] = table[i].clone();
        }

        for (int i=0; i<randomMutationCount; ++i) {
            int row = gen.nextInt(size);
            int col = gen.nextInt(size);
            table[row][col] = !table[row][col];
        }

        return new Figure(table);
    }

    public static Sprite getMutantSprite(Sprite source, List<Position> positionList) {
        boolean[][] table = source.getTable().clone();
        int size = table.length;
        for (int i=0; i<size; ++i) {
            table[i] = table[i].clone();
        }

        for (Position pos: positionList) {
            table[pos.row][pos.col] = !table[pos.row][pos.col];
        }

        return new Figure(table);
    }

}
