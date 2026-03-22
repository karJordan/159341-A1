package nz.ac.massey.a1;

public class Main {
    static void main(String[] args) {
        Lexer lexer = new Lexer("append \"hello world\" + test;" );

        Token token;
        do{
            token = lexer.nextToken();
            System.out.println(token);
        }while(token.getType() != TokenType.EOF);
    }
}
