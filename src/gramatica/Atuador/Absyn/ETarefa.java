package gramatica.Atuador.Absyn; // Java Package generated by the BNF Converter.

public class ETarefa extends Tarefa {
  public final Acao acao_;

  public ETarefa(Acao p1) { acao_ = p1; }

  public <R,A> R accept(gramatica.Atuador.Absyn.Tarefa.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof gramatica.Atuador.Absyn.ETarefa) {
      gramatica.Atuador.Absyn.ETarefa x = (gramatica.Atuador.Absyn.ETarefa)o;
      return this.acao_.equals(x.acao_);
    }
    return false;
  }

  public int hashCode() {
    return this.acao_.hashCode();
  }


}