package atuador.Absyn; // Java Package generated by the BNF Converter.

public class EAcao extends Acao {
  public final Remedio remedio_;

  public EAcao(Remedio p1) { remedio_ = p1; }

  public <R,A> R accept(atuador.Absyn.Acao.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof atuador.Absyn.EAcao) {
      atuador.Absyn.EAcao x = (atuador.Absyn.EAcao)o;
      return this.remedio_.equals(x.remedio_);
    }
    return false;
  }

  public int hashCode() {
    return this.remedio_.hashCode();
  }


}
