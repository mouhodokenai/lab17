import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    static Stack<Double> operands = new Stack<>();
    static Stack<Character> operators = new Stack<>();
    public static double conversion(String expression) {
        Pattern pattern = Pattern.compile("(\\d+\\.?\\d*)|([+\\-*/()])");
        Matcher matcher = pattern.matcher(expression);


        while (matcher.find()) {
            String token = matcher.group();
            char Char = token.charAt(0);
            if (Character.isDigit(Char)) {

                double number = Double.parseDouble(token);
                operands.push(number);

            } else if (Char == '(') {
                operators.push(Char);

            } else if (Char == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    Operations(operands, operators);
                }
                operators.pop();
            } else {

                while (!operators.isEmpty() && Priority(Char) <= Priority(operators.peek())) {
                    Operations(operands, operators);
                }
                operators.push(Char);
            }
            //System.out.println(operands);
            //System.out.println(operators);
        }

        while (!(operators.isEmpty())) {
            Operations(operands, operators);
        }

        return operands.pop();
    }

    private static int Priority(char operator) {
        if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    private static void Operations(Stack<Double> operands, Stack<Character> operators) {
        double operand2 = operands.pop();
        double operand1 = operands.pop();
        char operator = operators.pop();
        double result = switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            default -> 0;
        };

        operands.push(result);
    }
}
