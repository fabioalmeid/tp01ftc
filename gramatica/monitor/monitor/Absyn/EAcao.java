package monitor.Absyn; // Java Package generated by the BNF Converter.

public class EAcao extends Acao {
  public final Dados dados_;
  public final Hora hora_;

  public EAcao(Dados p1, Hora p2) { dados_ = p1; hora_ = p2; }

  public <R,A> R accept(monitor.Absyn.Acao.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof monitor.Absyn.EAcao) {
      monitor.Absyn.EAcao x = (monitor.Absyn.EAcao)o;
      return this.dados_.equals(x.dados_) && this.hora_.equals(x.hora_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.dados_.hashCode())+this.hora_.hashCode();
  }


}
