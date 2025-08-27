package Exercises.Encapsulation;

public class ValidationsUtils {
    public static boolean isValidEmployeeId(String id) {
        // not null, not empty
        id = id.trim();
        if (id == null || id.isEmpty()) {
            return false;
        }
        if (id.length() != 7) {
            return false;
        }
        if (id.substring(0, 3).equals("EMP")) {
            return false;
        }
        String numberPart = id.substring(3);
        try {
            Integer.parseInt(numberPart);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean isValidName(String name) {
        name = name.trim();
        if (name == null || name.isEmpty()) {
            return false;
        }

        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ')
                return false;

        }
        if (name.contains("  "))
            return false;

        return true;
    }

    public static boolean isValidSalary(double salary) {
        if (!(salary > 0 && salary <= 1000000)) {
            return false;
        }
        return true;
    }

    public static String formatName(String name) {
        name = name.trim();
        if (name == null) {
            return null;
        }
        if (name.isEmpty()) {
            return null;
        }
        return name;

    }

}
