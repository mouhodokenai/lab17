import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.next();
        double result = Calculator.conversion(expression);
        System.out.println("Результат: " + result);
    }
}
