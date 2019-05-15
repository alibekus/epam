package kz.akbar.model;

import java.util.HashSet;
import java.util.Set;

public class Pizza {
    private String name;
    private PizzaType type;
    private int count;
    private Set<Ingredient> ingredients = new HashSet<>();

    public Pizza(String name, PizzaType type, int count) {
        this.name = name;
        this.type = type;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addIngredients(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (this.ingredients.contains(ingredient)) {
                System.out.println("Pizza already contains " + ingredient.getTitle() + "\nPlease, check your order.");
            }
            if (this.ingredients.size() == Ingredient.values().length) {
                System.out.println("Your pizza is full of ingredients!");
            } else {
                this.ingredients.add(ingredient);
            }
        }
    }

    public double calculatePizzaPrice() {
        double price = 0;
        for (Ingredient ingredient : ingredients) {
            price += ingredient.getCost();
        }
        return price += type.getCost();
    }

    @Override
    public String toString() {
        StringBuilder pizzaCompositionBuilder = new StringBuilder();
        pizzaCompositionBuilder.append("Name: " + name + "\n");
        pizzaCompositionBuilder.append("-----------------------------------\n");
        pizzaCompositionBuilder.append(type + Order.EURO + '\n');
        for (Ingredient ingredient : ingredients) {
            pizzaCompositionBuilder.append(ingredient + Order.EURO + '\n');
        }
        pizzaCompositionBuilder.append("-----------------------------------\n");
        return pizzaCompositionBuilder.toString();
    }
}
