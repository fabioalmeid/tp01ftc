package atuador.Absyn; // Java Package generated by the BNF Converter.

public class EAcao1 extends Acao {
  public final Remedio remedio_;

  public EAcao1(Remedio p1) { remedio_ = p1; }

  public <R,A> R accept(atuador.Absyn.Acao.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof atuador.Absyn.EAcao1) {
      atuador.Absyn.EAcao1 x = (atuador.Absyn.EAcao1)o;
      return this.remedio_.equals(x.remedio_);
    }
    return false;
  }

  public int hashCode() {
    return this.remedio_.hashCode();
  }


}
