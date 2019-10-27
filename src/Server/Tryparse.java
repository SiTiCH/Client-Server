package Server;

public class Tryparse {
    public static boolean tryParseDouble(String someText) {
        try {
            Double.parseDouble(someText);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    public static boolean tryParseInteger(String someText) {
        try {
            Integer.parseInt(someText);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    public static boolean tryParseLong(String someText) {
        try {
            Long.parseLong(someText);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    public static boolean tryParseFloat(String someText) {
        try {
            Float.parseFloat(someText);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
