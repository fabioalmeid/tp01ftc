
//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Wed Sep 11 20:53:32 AMT 2013
//----------------------------------------------------

package atuador;


/** CUP v0.10k generated parser.
  * @version Wed Sep 11 20:53:32 AMT 2013
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\006\000\002\002\004\000\002\003\003\000\002\004" +
    "\004\000\002\004\004\000\002\005\003\000\002\005\003" +
    "" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\012\000\006\004\006\006\004\001\002\000\006\005" +
    "\011\007\010\001\002\000\004\002\013\001\002\000\006" +
    "\005\011\007\010\001\002\000\004\002\000\001\002\000" +
    "\004\002\ufffc\001\002\000\004\002\ufffd\001\002\000\004" +
    "\002\ufffe\001\002\000\004\002\001\001\002\000\004\002" +
    "\uffff\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\012\000\006\003\004\004\006\001\001\000\004\005" +
    "\013\001\001\000\002\001\001\000\004\005\011\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



  public atuador.Absyn.Tarefa pTarefa() throws Exception
  {
	java_cup.runtime.Symbol res = parse();
	return (atuador.Absyn.Tarefa) res.value;
  }

public <B,A extends java.util.LinkedList<? super B>> A cons_(B x, A xs) { xs.addFirst(x); return xs; }

public void syntax_error(java_cup.runtime.Symbol cur_token)
{
	report_error("Syntax Error, trying to recover and continue parse...", cur_token);
}

public void unrecovered_syntax_error(java_cup.runtime.Symbol cur_token) throws java.lang.Exception
{
	throw new Exception("Unrecoverable Syntax Error");
}


}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Remedio ::= _SYMB_3 
            {
              atuador.Absyn.Remedio RESULT = null;
		 RESULT = new atuador.Absyn.ERemedio1(); 
              CUP$parser$result = new java_cup.runtime.Symbol(3/*Remedio*/, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Remedio ::= _SYMB_1 
            {
              atuador.Absyn.Remedio RESULT = null;
		 RESULT = new atuador.Absyn.ERemedio(); 
              CUP$parser$result = new java_cup.runtime.Symbol(3/*Remedio*/, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Acao ::= _SYMB_0 Remedio 
            {
              atuador.Absyn.Acao RESULT = null;
		atuador.Absyn.Remedio p_2 = (atuador.Absyn.Remedio)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new atuador.Absyn.EAcao1(p_2); 
              CUP$parser$result = new java_cup.runtime.Symbol(2/*Acao*/, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Acao ::= _SYMB_2 Remedio 
            {
              atuador.Absyn.Acao RESULT = null;
		atuador.Absyn.Remedio p_2 = (atuador.Absyn.Remedio)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new atuador.Absyn.EAcao(p_2); 
              CUP$parser$result = new java_cup.runtime.Symbol(2/*Acao*/, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Tarefa ::= Acao 
            {
              atuador.Absyn.Tarefa RESULT = null;
		atuador.Absyn.Acao p_1 = (atuador.Absyn.Acao)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new atuador.Absyn.ETarefa(p_1); 
              CUP$parser$result = new java_cup.runtime.Symbol(1/*Tarefa*/, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Tarefa EOF 
            {
              Object RESULT = null;
		atuador.Absyn.Tarefa start_val = (atuador.Absyn.Tarefa)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = new java_cup.runtime.Symbol(0/*$START*/, RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

