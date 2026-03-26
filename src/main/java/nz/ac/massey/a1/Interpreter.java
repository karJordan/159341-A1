package nz.ac.massey.a1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {
    private final Map<String, String> variables = new HashMap<>();
    private boolean running = true;

    public Interpreter() {
    }

    public void execute(List<Statement> program) {
        for (Statement stmt : program) {
            if (!running) {
                break;
            }
            executeStatement(stmt);
        }
    }

    public boolean isRunning() {
        return running;
    }

    private void executeStatement(Statement stmt) {
        if (stmt instanceof AppendStatement appendStatement) {
            String oldValue = variables.getOrDefault(appendStatement.getId(), "");
            String appendValue = appendStatement.getExpression().toString();
            variables.put(appendStatement.getId(), oldValue + appendValue);
            return;
        }
        if (stmt instanceof ExitStatement exitStatement) {
            running = false;
            return;
        }
        if (stmt instanceof ListStatement listStatement) {
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getKey());
            }
            return;
        }
        if (stmt instanceof PrintWordCountStatement printWordCountStatement) {
            String value = evaluateExpression(printWordCountStatement.getExpression());
            String trimmed = value.trim();

            if (trimmed.isEmpty()) {
                System.out.println(0);
            } else {
                String[] words = trimmed.split("\\s+");
                System.out.println(words.length);
            }
            return;
        }
        if (stmt instanceof PrintStatement printStatement) {
            String value = evaluateExpression(printStatement.getExpression());
            System.out.println(value);
            return;
        }
        if (stmt instanceof PrintWordsStatement printWordsStatement) {
            String value = evaluateExpression(printWordsStatement.getExpression());
            String trimmed = value.trim();
            if (trimmed.isEmpty()) {
                return;
            }
            String[] words = trimmed.split("\\s+");
            for (String word : words) {
                System.out.println(word);
            }
            return;
        }

        if (stmt instanceof PrintLengthStatement printLengthStatement) {
            String value = evaluateExpression(printLengthStatement.getExpression());
            System.out.println(value.length());
            return;
        }
        if (stmt instanceof SetStatement setStatement) {
            String value = evaluateExpression(setStatement.getExpression());
            variables.put(setStatement.getId(), value);
            return;
        }
        if (stmt instanceof ReverseStatement reverseStatement) {
            String value = variables.get(reverseStatement.getId());
            if (value == null) {
                throw new RuntimeException("undefined variable: " + reverseStatement.getId());
            }
            String reversed = new StringBuilder(value).reverse().toString();
            variables.put(reverseStatement.getId(), reversed);
            return;
        }
        throw new RuntimeException("Invalid Statement: " + stmt.getClass().getSimpleName());
    }

    private String evaluateExpression(Expression expression) {
        StringBuilder result = new StringBuilder();

        for (Token token : expression.getTokens()) {
            TokenType type = token.getType();

            if (type == TokenType.ID) {
                String value = variables.get(token.getText());
                if (value == null) {
                    throw new RuntimeException("Undefined variable: " + token.getText());
                }
                result.append(value);
            } else if (type == TokenType.LITERAL) {
                result.append(token.getText());
            } else if (type == TokenType.CONSTANT) {
                result.append(resolveConstant(token.getText()));
            } else {
                throw new RuntimeException("Invalid token in expression: " + type);
            }
        }
        return result.toString();
    }

    private String resolveConstant(String text) {
        switch (text.toLowerCase()) {
            case "space":
                return " ";
            case "tab":
                return "\t";
            case "newline":
                return "\n";
            default:
                throw new RuntimeException("Unknown constant: " + text);
        }
    }
}
