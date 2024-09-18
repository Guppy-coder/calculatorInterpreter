import java.util.Scanner;

public class main {

    public static void main(String[] args) {


        while (true) {
            System.out.print("calc>");
            String input = new main().getInput();

            Interpreter interpreter = new Interpreter(input);
            int result = interpreter.expr();
            System.out.println(result);
        }

    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (input.isEmpty()) {
            System.out.print("calc>");
            input = scanner.nextLine();
        }
        return input;
    }
}
