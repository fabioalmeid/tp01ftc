package gramatica.Centralizador.Absyn; // Java Package generated by the BNF Converter.

public class EMedic2 extends Medicacao {
  public final Remedio remedio_;

  public EMedic2(Remedio p1) { remedio_ = p1; }

  public <R,A> R accept(gramatica.Centralizador.Absyn.Medicacao.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof gramatica.Centralizador.Absyn.EMedic2) {
      gramatica.Centralizador.Absyn.EMedic2 x = (gramatica.Centralizador.Absyn.EMedic2)o;
      return this.remedio_.equals(x.remedio_);
    }
    return false;
  }

  public int hashCode() {
    return this.remedio_.hashCode();
  }


}
