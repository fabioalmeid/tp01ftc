package gramatica.Monitor.Absyn; // Java Package generated by the BNF Converter.

public abstract class Dados implements java.io.Serializable {
  public abstract <R,A> R accept(Dados.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(gramatica.Monitor.Absyn.EDados p, A arg);
    public R visit(gramatica.Monitor.Absyn.EDados1 p, A arg);
    public R visit(gramatica.Monitor.Absyn.EDados2 p, A arg);
    public R visit(gramatica.Monitor.Absyn.EDados3 p, A arg);

  }

}
