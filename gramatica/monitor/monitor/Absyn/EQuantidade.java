package monitor.Absyn; // Java Package generated by the BNF Converter.

public class EQuantidade extends Quantidade {
  public final Integer integer_;

  public EQuantidade(Integer p1) { integer_ = p1; }

  public <R,A> R accept(monitor.Absyn.Quantidade.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof monitor.Absyn.EQuantidade) {
      monitor.Absyn.EQuantidade x = (monitor.Absyn.EQuantidade)o;
      return this.integer_.equals(x.integer_);
    }
    return false;
  }

  public int hashCode() {
    return this.integer_.hashCode();
  }


}
