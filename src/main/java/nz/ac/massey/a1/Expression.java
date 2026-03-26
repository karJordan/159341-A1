package nz.ac.massey.a1;

import java.util.List;

public class Expression {
    private final List<Token> tokens;

    public Expression(List<Token> tokens){
        this.tokens = tokens;
    }

    public List<Token> getTokens(){
        return tokens;
    }
}
