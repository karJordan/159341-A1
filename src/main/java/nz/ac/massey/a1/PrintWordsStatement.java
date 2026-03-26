package nz.ac.massey.a1;

public class PrintWordsStatement implements Statement{
    private final Expression expression;

    public PrintWordsStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression(){
        return expression;
    }
}