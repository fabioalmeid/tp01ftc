package gramatica.Centralizador.Absyn; // Java Package generated by the BNF Converter.

public class EQtde1 extends Quantidade {
  public final Integer integer_;

  public EQtde1(Integer p1) { integer_ = p1; }

  public <R,A> R accept(gramatica.Centralizador.Absyn.Quantidade.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof gramatica.Centralizador.Absyn.EQtde1) {
      gramatica.Centralizador.Absyn.EQtde1 x = (gramatica.Centralizador.Absyn.EQtde1)o;
      return this.integer_.equals(x.integer_);
    }
    return false;
  }

  public int hashCode() {
    return this.integer_.hashCode();
  }


}
