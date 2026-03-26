package nz.ac.massey.a1;

public class ReverseStatement implements Statement{
    private final  String id;

    public ReverseStatement(String id){
        this.id=id;
    }

    public String getId(){
        return id;
    }
}