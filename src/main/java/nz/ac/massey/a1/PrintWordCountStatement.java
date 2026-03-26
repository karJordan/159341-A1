package nz.ac.massey.a1;

public class PrintWordCountStatement implements Statement{
    private final Expression expression;

    public PrintWordCountStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression(){
        return expression;
    }
}