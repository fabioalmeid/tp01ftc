package gramatica.Centralizador.Absyn; // Java Package generated by the BNF Converter.

public class ERemedy1 extends Remedio {

  public ERemedy1() { }

  public <R,A> R accept(gramatica.Centralizador.Absyn.Remedio.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof gramatica.Centralizador.Absyn.ERemedy1) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return 37;
  }


}
