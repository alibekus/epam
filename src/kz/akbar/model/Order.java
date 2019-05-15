package kz.akbar.model;

import kz.akbar.util.OrderNumber;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private static int clientNumber;
    private String clientName;
    private int pizzaAmount;
    private LocalTime orderTime;
    private List<Pizza> pizzaList = new ArrayList<>();
    public static final int COUNT_LIMIT = 10;
    public static final String EURO = "\u20ac";
    private OrderNumber orderNumber = OrderNumber.getInstance();

    public Order(String clientName) {
        orderId = orderNumber.getOrderNumber();
        this.clientName = clientName;
        clientNumber++;
        orderTime = LocalTime.now();
    }

    public Order(int clientNumber, String clientName) {
        this.orderId = orderNumber.getOrderNumber();
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        orderTime = LocalTime.now();
    }

    public boolean addPizza(Pizza... pizza) {
        if (pizza.length > 0) {
            for (int i = 0; i < pizza.length; i++) {
                pizzaAmount += pizza[i].getCount();
            }
            if (pizzaAmount > COUNT_LIMIT) {
                System.out.println("In your order more than 10 pizza!\nOnly 10 first pizza will be prepared.");
            }
            pizzaAmount = 0;
            for (int i = 0; i < pizza.length; i++) {
                int pizzaCount = pizza[i].getCount();
                int countDiffer = COUNT_LIMIT - pizzaAmount;
                if (countDiffer == 0) {
                    break;
                }
                if (pizzaCount >= countDiffer ) {
                    pizza[i].setCount(countDiffer);
                }
                pizzaList.add(pizza[i]);
                pizzaAmount += pizza[i].getCount();
            }
            return true;
        } else {
            return false;
        }
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public boolean removePizza(String pizzaName) {
        for (Pizza pizza : pizzaList) {
            if (pizza.getName().equals(pizzaName)) {
                return pizzaList.remove(pizza);
            }
        }
        return false;
    }

    public boolean changePizzaCount(String name, int count) {
        for (Pizza pizza : pizzaList) {
            if (pizza.getName().equals(name)) {
                if (count == 0) {
                    removePizza(name);
                } else {
                    pizza.setCount(count);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        double totalCost = 0;
        StringBuilder orderBillBuilder = new StringBuilder();
        orderBillBuilder.append("***********************************\n");
        orderBillBuilder.append("Order: " + orderId + '\n');
        orderBillBuilder.append("Client: " + clientNumber + '\n');
        orderBillBuilder.append("Order time: " + orderTime + '\n');
        for (Pizza pizza : pizzaList) {
            orderBillBuilder.append(pizza.toString());
            orderBillBuilder.append("Altogether: " + df.format(pizza.calculatePizzaPrice()) + Order.EURO + '\n');
            orderBillBuilder.append("Quantity: " + pizza.getCount() + '\n');
            orderBillBuilder.append("-----------------------------------\n");
            totalCost += pizza.calculatePizzaPrice() * pizza.getCount();
        }
        orderBillBuilder.append("Total sum: " + df.format(totalCost) + Order.EURO + '\n');
        orderBillBuilder.append("***********************************\n");
        return orderBillBuilder.toString();
    }
}
