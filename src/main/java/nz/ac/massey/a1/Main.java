package nz.ac.massey.a1;

import javax.management.relation.RoleUnresolved;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("159.341 Assignment 1 Semester 1 2026");
        System.out.println("Submitted by: Kareem Harvey, ");
        System.out.println("-----------------------------");

        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();

        while (interpreter.isRunning()){
            System.out.println(">");
            String line = scanner.nextLine();
            if(line.trim().isEmpty()){
                continue;
            }
            try{
                Lexer lexer = new Lexer(line);
                Parser parser = new Parser(lexer);

                List<Statement> program = parser.parseProgram();
                interpreter.execute(program);
            }
            catch (RuntimeException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
