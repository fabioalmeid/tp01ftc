package gramatica.Centralizador.Absyn; // Java Package generated by the BNF Converter.

public abstract class Aplicar implements java.io.Serializable {
  public abstract <R,A> R accept(Aplicar.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(gramatica.Centralizador.Absyn.EApply1 p, A arg);
    public R visit(gramatica.Centralizador.Absyn.EApply2 p, A arg);

  }

}
