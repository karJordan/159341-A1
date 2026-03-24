package nz.ac.massey.a1;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer){
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }
}
