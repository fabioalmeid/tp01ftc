package gramatica.Centralizador.Absyn; // Java Package generated by the BNF Converter.

public abstract class Coletar implements java.io.Serializable {
  public abstract <R,A> R accept(Coletar.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(gramatica.Centralizador.Absyn.ECollect1 p, A arg);
    public R visit(gramatica.Centralizador.Absyn.ECollect2 p, A arg);

  }

}