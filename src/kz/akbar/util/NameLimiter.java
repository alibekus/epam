package kz.akbar.util;

public class NameLimiter {

    public static String checkName(String name, String alternative, int count) {
        if (name.length() < 4 || name.length() > 20) {
            name = alternative + '_' + count;
            System.out.println("The name of your pizza is too short or too long.\nSo the name will be " + name);
        }
        return name;
    }
}
