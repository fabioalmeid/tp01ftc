package Centralizador;
import Centralizador.Absyn.*;
/*** BNFC-Generated Visitor Design Pattern Skeleton. ***/
/* This implements the common visitor design pattern.
   Tests show it to be slightly less efficient than the
   instanceof method, but easier to use. 
   Replace the R and A parameters with the desired return
   and context types.*/

public class VisitSkel
{
  public class TarefaVisitor<R,A> implements Tarefa.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.ETask p, A arg)
    {
      /* Code For ETask Goes Here */

      p.acao_.accept(new AcaoVisitor<R,A>(), arg);

      return null;
    }

  }
  public class AcaoVisitor<R,A> implements Acao.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.EAction1 p, A arg)
    {
      /* Code For EAction1 Goes Here */

      p.coletar_.accept(new ColetarVisitor<R,A>(), arg);
      p.dados_.accept(new DadosVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Centralizador.Absyn.EAction2 p, A arg)
    {
      /* Code For EAction2 Goes Here */

      p.aplicar_.accept(new AplicarVisitor<R,A>(), arg);
      p.medicacao_.accept(new MedicacaoVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Centralizador.Absyn.EAction3 p, A arg)
    {
      /* Code For EAction3 Goes Here */


      return null;
    }

  }
  public class ColetarVisitor<R,A> implements Coletar.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.ECollect1 p, A arg)
    {
      /* Code For ECollect1 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.ECollect2 p, A arg)
    {
      /* Code For ECollect2 Goes Here */


      return null;
    }

  }
  public class AplicarVisitor<R,A> implements Aplicar.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.EApply1 p, A arg)
    {
      /* Code For EApply1 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.EApply2 p, A arg)
    {
      /* Code For EApply2 Goes Here */


      return null;
    }

  }
  public class DadosVisitor<R,A> implements Dados.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.EData1 p, A arg)
    {
      /* Code For EData1 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.EData2 p, A arg)
    {
      /* Code For EData2 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.EData3 p, A arg)
    {
      /* Code For EData3 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.EData4 p, A arg)
    {
      /* Code For EData4 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.EData5 p, A arg)
    {
      /* Code For EData5 Goes Here */

      p.dados_1.accept(new DadosVisitor<R,A>(), arg);
      p.operador_.accept(new OperadorVisitor<R,A>(), arg);
      p.dados_2.accept(new DadosVisitor<R,A>(), arg);

      return null;
    }

  }
  public class OperadorVisitor<R,A> implements Operador.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.EOp p, A arg)
    {
      /* Code For EOp Goes Here */


      return null;
    }

  }
  public class MedicacaoVisitor<R,A> implements Medicacao.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.EMedic1 p, A arg)
    {
      /* Code For EMedic1 Goes Here */

      p.quantidade_.accept(new QuantidadeVisitor<R,A>(), arg);
      p.remedio_.accept(new RemedioVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Centralizador.Absyn.EMedic2 p, A arg)
    {
      /* Code For EMedic2 Goes Here */

      p.remedio_.accept(new RemedioVisitor<R,A>(), arg);

      return null;
    }

  }
  public class QuantidadeVisitor<R,A> implements Quantidade.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.EQtde1 p, A arg)
    {
      /* Code For EQtde1 Goes Here */

      //p.integer_;

      return null;
    }

  }
  public class RemedioVisitor<R,A> implements Remedio.Visitor<R,A>
  {
    public R visit(Centralizador.Absyn.ERemedy1 p, A arg)
    {
      /* Code For ERemedy1 Goes Here */


      return null;
    }
    public R visit(Centralizador.Absyn.ERemedy2 p, A arg)
    {
      /* Code For ERemedy2 Goes Here */


      return null;
    }

  }
}