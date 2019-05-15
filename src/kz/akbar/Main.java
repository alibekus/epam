package kz.akbar;

import kz.akbar.model.Ingredient;
import kz.akbar.model.Order;
import kz.akbar.model.Pizza;
import kz.akbar.model.PizzaType;
import kz.akbar.util.NameLimiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static void changePizzaNumber(BufferedReader br, Order order) throws IOException {
        for (; ; ) {
            System.out.println("Want to change the number of pizzas?? y/any key");
            String change = br.readLine();
            if (change.equalsIgnoreCase("y")) {
                List<Pizza> pizzaList = order.getPizzaList();
                System.out.println(pizzaList);
                String pizzaToChange = null;
                if (pizzaList.size() > 1) {
                    System.out.print("Which pizza you want to change count/remove? Enter name (case sensitive).");
                    pizzaToChange = br.readLine();
                } else {
                    pizzaToChange = pizzaList.get(0).getName();
                }
                System.out.print("Enter count (if you enter zero count, it will remove pizza): ");
                int countToChange = Integer.parseInt(br.readLine());
                order.changePizzaCount(pizzaToChange, countToChange);
            } else {
                break;
            }
        }
    }

    static List<Ingredient> chooseIngredient(BufferedReader br) throws IOException {
        Ingredient[] ingredients = Ingredient.values();
        List<Ingredient> chosenIngredients = new ArrayList<>();
        for (int i = 1; i <= ingredients.length; i++) {
            System.out.println(i + " - " + ingredients[i - 1].getTitle());
        }
        System.out.println("Which ingredients would you like to add to pizza?\nType numbers with space delimiter.");
        String ingredientNumbersString = br.readLine();
        String[] ingredientNumberStringArray = ingredientNumbersString.split(" ");
        int ingredientNumber;
        for (int i = 0; i < ingredientNumberStringArray.length; i++) {
            ingredientNumber = Integer.parseInt(ingredientNumberStringArray[i]);
            for (int j = 0; j < ingredients.length; j++) {
                if ((ingredientNumber - 1) == ingredients[j].ordinal()) {
                    chosenIngredients.add(ingredients[j]);
                }
            }
        }
        return chosenIngredients;
    }

    private static int inputNumberChoice(BufferedReader br) throws IOException {
        int choiceNumber = 0;
        for (; ; ) {
            try {
                choiceNumber = Integer.parseInt(br.readLine());
                if (choiceNumber == 0) {
                    System.exit(0);
                }
                return choiceNumber;
            } catch (NumberFormatException exp) {
                System.out.println("It needs integer number! Try again");
                continue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int pizzaAmount = 0;
        int pizzaNumber = 0;
        Pizza margarita = new Pizza("Margarita", PizzaType.CALZONE, 2);
        margarita.addIngredients(Ingredient.TOMATO_PASTE, Ingredient.PEPPERONI, Ingredient.GARLIC, Ingredient.BACON);
        Pizza pepperoniOro = new Pizza("PepperoniOro", PizzaType.OPEN, 3);
        pepperoniOro.addIngredients(Ingredient.TOMATO_PASTE, Ingredient.CHEESE, Ingredient.PEPPERONI, Ingredient.OLIVES);
        Pizza basePZZ = new Pizza("BasePZZ", PizzaType.OPEN, 12);
        basePZZ.addIngredients(Ingredient.TOMATO_PASTE, Ingredient.OLIVES, Ingredient.BACON);
        Order order_7717 = new Order(7717, "7717");
        Order order_4372 = new Order(4372, "4372");
        order_7717.addPizza(margarita, pepperoniOro);
        order_4372.addPizza(basePZZ);
        System.out.println(order_7717.toString());
        System.out.println(order_4372.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! Welcome to out pizzeria 'Palmetto'\nYou can order a maximum 10 pizza.\nTo quick exit " +
                "press 'q' in any step.");
        System.out.print("What's your name: ");
        String clientName = br.readLine();
        if (clientName.equalsIgnoreCase("q")) {
            System.exit(0);
        }
        Order order = new Order(clientName);
        for (; ; ) {
            System.out.print("Which type of pizza would you like? 1 - open or 2 - calzone (0 for exit): ");
            int typeCode = inputNumberChoice(br);
            PizzaType type = null;
            switch (typeCode) {
                case 1:
                    type = PizzaType.OPEN;
                    break;
                case 2:
                    type = PizzaType.CALZONE;
                    break;
                default:
                    System.exit(0);
            }
            System.out.print("How do you name pizza? ");
            String pizzaName = br.readLine();
            if (pizzaName.equalsIgnoreCase("q")) {
                System.exit(0);
            }
            System.out.print("How many pizza would you like? (0 for exit): ");
            int count = inputNumberChoice(br);
            pizzaAmount += count;
            pizzaName = NameLimiter.checkName(pizzaName, clientName, ++pizzaNumber);
            List<Ingredient> ingredients = chooseIngredient(br);
            Pizza pizza = new Pizza(pizzaName, type, count);
            for (Ingredient ingredient : ingredients) {
                pizza.addIngredients(ingredient);
            }
            order.addPizza(pizza);
            if (pizzaAmount + 1 < 10) {
                System.out.println("You can order " + (10 - pizzaAmount) + " more pizza.");
                System.out.println("If you want to order more pizza, press 'y', else press any key.");
                String answer = br.readLine();
                if (answer.equalsIgnoreCase("y")) {
                    continue;
                } else {
                    break;
                }
            } else {
                System.out.println("You have reached the limit, sorry...");
                break;
            }
        }
        changePizzaNumber(br, order);
        System.out.print("Your order: \n" + order.toString());
    }
}
