package monitor.Absyn; // Java Package generated by the BNF Converter.

public class EDados2 extends Dados {
  public final Quantidade quantidade_;

  public EDados2(Quantidade p1) { quantidade_ = p1; }

  public <R,A> R accept(monitor.Absyn.Dados.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof monitor.Absyn.EDados2) {
      monitor.Absyn.EDados2 x = (monitor.Absyn.EDados2)o;
      return this.quantidade_.equals(x.quantidade_);
    }
    return false;
  }

  public int hashCode() {
    return this.quantidade_.hashCode();
  }


}
