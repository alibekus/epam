package kz.akbar.model;

public enum Ingredient {
    TOMATO_PASTE("Tomato Paste",1),
    CHEESE("Cheese",1),
    SALAMI("Salami",1.5),
    BACON("Bacon",1.2),
    GARLIC("Garlic",0.3),
    CORN("Corn",0.7),
    PEPPERONI("Pepperoni",0.6),
    OLIVES("Olives",0.5);

    private String title;
    private double cost;

    Ingredient(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return title + ": " + cost;
    }
}
