package nz.ac.massey.a1;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------------------------------------");
        System.out.println("159.341 Assignment 1 Semester 1 2026");
        System.out.println("Submitted by: Kareem Harvey, 24013544");
        System.out.println("-------------------------------------");

        //read from stdin using scanner
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();

        while (interpreter.isRunning()){
            System.out.print(">");
            //read each line of input
            String line = scanner.nextLine();
            if(line.trim().isEmpty()){//restart if nothing entered
                continue;
            }
            try{//takes input then lexer->parser->interpreter
                Lexer lexer = new Lexer(line);
                Parser parser = new Parser(lexer);

                List<Statement> program = parser.parseProgram();
                interpreter.execute(program);
            }
            catch (RuntimeException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
