package Centralizador.Absyn; // Java Package generated by the BNF Converter.

public abstract class Medicacao implements java.io.Serializable {
  public abstract <R,A> R accept(Medicacao.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(Centralizador.Absyn.EMedic1 p, A arg);
    public R visit(Centralizador.Absyn.EMedic2 p, A arg);

  }

}
