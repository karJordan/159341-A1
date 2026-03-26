package nz.ac.massey.a1;

public class Token {
    private final TokenType type;
    private final String text;

    public Token(TokenType type, String text){
        this.type = type;
        this.text = text;
    }

    //getters
    public TokenType getType(){
        return type;
    }
    public String getText(){
        return text;
    }

    @Override
    public String toString(){
        return type + "(\""+ text +"\")";
    }
}
