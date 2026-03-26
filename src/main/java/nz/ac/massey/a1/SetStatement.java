package nz.ac.massey.a1;

public class SetStatement implements Statement{
    private final String id;
    private final Expression expression;

    public SetStatement(String id, Expression expression){
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
