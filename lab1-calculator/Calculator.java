import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double current =0.0;
        double stored =0.0;
        char operator = '+';
        System.out.println("Enter a number or an operator. Type exit to quit");
        while (true) {
            String input = scan.nextLine();
            if (input.equals("exit")) {
                System.out.println("Exited");
                break;
            }
            if ("+-*/=".contains(input)) {
                operator = input.charAt(0);
                if (operator == '=') {
                    current = stored;
                }
                System.out.println(current);
            }
            else {
                double number = Double.parseDouble(input);
                switch (operator) {
                    case '+':
                        stored += number;
                        break;
                    case '-':
                        stored -= number;
                        break;
                    case '*':
                        stored *= number;
                        break;
                    case '/':
                        stored /= number;
                        break;
                    default:
                        stored = number;
                }
                current = stored;
                System.out.println(current);
            }
        }
    }
}
