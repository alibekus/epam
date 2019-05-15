package kz.akbar.model;

public enum PizzaType {
    OPEN("Pizza Base",1),
    CALZONE("Pizza Base (Calzone)",1.5);

    private String type;
    private double cost;

    PizzaType(String type, double cost) {
        this.type = type;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return type + ": " + cost;
    }
}
