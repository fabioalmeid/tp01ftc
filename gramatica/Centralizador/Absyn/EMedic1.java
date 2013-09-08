package Centralizador.Absyn; // Java Package generated by the BNF Converter.

public class EMedic1 extends Medicacao {
  public final Quantidade quantidade_;
  public final Remedio remedio_;

  public EMedic1(Quantidade p1, Remedio p2) { quantidade_ = p1; remedio_ = p2; }

  public <R,A> R accept(Centralizador.Absyn.Medicacao.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Centralizador.Absyn.EMedic1) {
      Centralizador.Absyn.EMedic1 x = (Centralizador.Absyn.EMedic1)o;
      return this.quantidade_.equals(x.quantidade_) && this.remedio_.equals(x.remedio_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.quantidade_.hashCode())+this.remedio_.hashCode();
  }


}