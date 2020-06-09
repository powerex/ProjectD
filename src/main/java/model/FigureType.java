package model;

public enum FigureType {
    UP("UP"), DOWN("DOWN"), LEFT("LEFT"), RIGHT("RIGHT");

    private String name;

    FigureType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
