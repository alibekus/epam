package kz.akbar.util;

public class NameLimiter {

    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 20;

    public static String checkName(String name, String alternative, int count) {
        if (name.length() <= MIN_NAME_LENGTH || name.length() >= MAX_NAME_LENGTH) {
            name = alternative + '_' + count;
            System.out.println("The name of your pizza is too short or too long.\nSo the name will be " + name);
        }
        return name;
    }
}
