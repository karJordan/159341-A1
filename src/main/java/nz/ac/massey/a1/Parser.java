package nz.ac.massey.a1;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }
//match() runs first and when it is true in parsestatement() it will update current token
// to the next token then expect() will make sure current token is the expected token
    private boolean match(TokenType type) {//moves current token to next token
        if (currentToken.getType() == type){
            currentToken = lexer.nextToken();
            return true;
        }
        return false;
    }
    private Token expect(TokenType type) {//checks if expected token matches current token
        if (currentToken.getType() == type) {
            Token token = currentToken;
            currentToken = lexer.nextToken();
            return token;
        }
        throw new RuntimeException("Expected " + type + " but found " + currentToken.getType());
    }

    private Token expectValue(){
        if (currentToken.getType() == TokenType.ID ||
        currentToken.getType() == TokenType.CONSTANT ||
        currentToken.getType() == TokenType.LITERAL){
            Token token = currentToken;
            currentToken = lexer.nextToken();
            return token;
        }
        throw new RuntimeException("Expected VALUE (id | constant | literal) but found " + currentToken.getType());
    }

    public Statement parseStatement() {//Confirms grammar
        if(match(TokenType.APPEND)){//confirms APPEND ID EXPR END
            Token id = expect(TokenType.ID);
            Expression expression = parseExpression();
            expect(TokenType.END);
            return new AppendStatement(id.getText(),expression);
        }
        if(match(TokenType.EXIT)){
            expect(TokenType.END);
            return new ExitStatement();
        }
        if(match(TokenType.LIST)){
            expect(TokenType.END);
            return new ListStatement();
        }
        if(match(TokenType.PRINTWORDCOUNT)){
            Expression expression = parseExpression();
            expect(TokenType.END);
            return new PrintWordCountStatement(expression);
        }
        if(match(TokenType.PRINT)){
            Expression expression = parseExpression();
            expect(TokenType.END);
            return new PrintStatement(expression);
        }
        if(match(TokenType.PRINTWORDS)){
            Expression expression = parseExpression();
            expect(TokenType.END);
            return new PrintWordsStatement(expression);
        }
        if(match(TokenType.PRINTLENGTH)){
            Expression expression = parseExpression();
            expect(TokenType.END);
            return new PrintLengthStatement(expression);
        }
        if(match(TokenType.SET)){//confirms grammar for set statement SET ID EXP END
            Token id = expect(TokenType.ID);
            Expression expr = parseExpression();
            expect(TokenType.END);
            return new SetStatement(id.getText(),expr);//returns set statement
        }
        if(match(TokenType.REVERSE)){
            Token id = expect(TokenType.ID);
            expect(TokenType.END);
            return new ReverseStatement(id.getText());
        }
        throw new RuntimeException("Invalid Statement");
    }

    public Expression parseExpression(){//Grammar rule for expression
        List<Token> tokens = new ArrayList<>();

        tokens.add(expectValue());
        while(match(TokenType.PLUS)){
            tokens.add(expectValue());
        }
        return new Expression(tokens);
    }

    public List<Statement> parseProgram(){
        List<Statement> statements = new ArrayList<>();
        while (currentToken.getType() != TokenType.EOF) {
            statements.add(parseStatement());
        }
        return statements;
    }
}
