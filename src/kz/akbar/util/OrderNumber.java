package kz.akbar.util;

public class OrderNumber {
    private static OrderNumber ourInstance = new OrderNumber();

    public static OrderNumber getInstance() {
        return ourInstance;
    }

    private static int orderCount = 0;

    private OrderNumber() {
    }

    public int getOrderNumber() {
        if (orderCount == 99_999) {
            orderCount = 0;
        }
        return ++orderCount;
    }
}
