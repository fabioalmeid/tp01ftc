package Centralizador.Absyn; // Java Package generated by the BNF Converter.

public abstract class Dados implements java.io.Serializable {
  public abstract <R,A> R accept(Dados.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(Centralizador.Absyn.EData1 p, A arg);
    public R visit(Centralizador.Absyn.EData2 p, A arg);
    public R visit(Centralizador.Absyn.EData3 p, A arg);
    public R visit(Centralizador.Absyn.EData4 p, A arg);
    public R visit(Centralizador.Absyn.EData5 p, A arg);

  }

}
