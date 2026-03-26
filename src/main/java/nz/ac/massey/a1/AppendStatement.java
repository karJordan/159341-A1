package nz.ac.massey.a1;

public class AppendStatement implements Statement{
    private final String id;
    private final Expression expression;

    public AppendStatement(String id, Expression expression){
        this.id = id;
        this.expression = expression;
    }

    public String getId(){
        return id;
    }

    public Expression getExpression(){
        return expression;
    }
}