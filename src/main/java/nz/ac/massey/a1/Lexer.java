package nz.ac.massey.a1;

public class Lexer {
    private final String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public Token nextToken() {
        skipWhitespaceOutsideLiteral();
        if (isAtEnd()) {
            return new Token(TokenType.EOF, "");
        }
        char ch = currentChar();
        if (Character.isLetter(ch)) {
            return readIdentifierOrKeyword();
        }
        if (ch == '"') {
            return readStringLiteral();
        }
        if (ch == '+') {
            advance();
            return new Token(TokenType.PLUS, "+");
        }
        if (ch == ';') {
            advance();
            return new Token(TokenType.END, ";");
        }
        throw new RuntimeException("Unexpected character: " + ch);
    }

    private boolean isAtEnd() {
        return pos >= input.length();
    }

    private char currentChar() {
        return input.charAt(pos);
    }

    private void advance() {
        pos++;
    }

    public void skipWhitespaceOutsideLiteral() {
        while (!isAtEnd() && Character.isWhitespace(currentChar())) {
            advance();
        }
    }

    public Token readIdentifierOrKeyword() {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && (Character.isLetterOrDigit(currentChar()) || currentChar() == '_')) {
            sb.append(currentChar());
            advance();
        }
        String word = sb.toString();

        switch (word.toLowerCase()) {
            case "append":
                return new Token(TokenType.APPEND, word);
            case "list":
                return new Token(TokenType.LIST, word);
            case "print":
                return new Token(TokenType.PRINT, word);
            case "exit":
                return new Token(TokenType.EXIT, word);
            case "printlength":
                return new Token(TokenType.PRINTLENGTH, word);
            case "printwords":
                return new Token(TokenType.PRINTWORDS, word);
            case "printwordcount":
                return new Token(TokenType.PRINTWORDCOUNT, word);
            case "set":
                return new Token(TokenType.SET, word);
            case "reverse":
                return new Token(TokenType.REVERSE, word);
            default:
                return new Token(TokenType.ID, word);
        }

    }

    public Token readStringLiteral() {
        advance();// skip first quote

        StringBuilder sb = new StringBuilder();

        while (!isAtEnd() && currentChar() != '"') {
            sb.append(currentChar());
            advance();
        }
        if(!isAtEnd()){
            advance();//skip closing quote
        }
        return new Token(TokenType.LITERAL, sb.toString());
    }
}
