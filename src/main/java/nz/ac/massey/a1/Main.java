package nz.ac.massey.a1;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("159.341 Assignment 1 Semester 1 2026");
        System.out.println("Submitted by: Kareem Harvey, ");
        System.out.println("-----------------------------");

        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();

        while (true) {
            try {
                String line = scanner.nextLine();

                Lexer lexer = new Lexer(line);
                Parser parser = new Parser(lexer);
                Statement stmt = parser.parseStatement();

                boolean shouldExit = interpreter.execute(stmt);
                if (shouldExit) {
                    break;}
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
