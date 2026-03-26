package nz.ac.massey.a1;

public class PrintLengthStatement implements Statement{
    private final Expression expression;

    public PrintLengthStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression(){
        return expression;
    }
}
