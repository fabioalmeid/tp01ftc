package gramatica.Centralizador;

import gramatica.Centralizador.Absyn.*;

public class Interpreter {
	public static String interpret(Tarefa e) {
		Tarefa tarefa  = (Tarefa) e;
		return interpretExp(tarefa, null);
	}

	private static String interpretExp(Tarefa e, Object o) {
		return e.accept(new Int_Tarefa(), null).toString();
	}
	
	private static class Int_Coletar implements Coletar.Visitor<String,Object> {
		@Override
		public String visit(ECollect1 p, Object arg) {
			String resultado = p.toString();
			return resultado;
		}

		@Override
		public String visit(ECollect2 p, Object arg) {
			String resultado = p.toString();
			return resultado;
		}
	}
	
	private static class Int_Coletar2 implements Aplicar.Visitor<String,Object> {
		@Override
		public String visit(EApply1 p, Object arg) {
			String resultado = p.toString();
			return resultado;
		}

		@Override
		public String visit(EApply2 p, Object arg) {
			String resultado = p.toString();
			return resultado;
		}
	}
	
	private static class Int_Acao implements Acao.Visitor<String,Object> {
		@Override
		public String visit(EAction1 p, Object arg) {
			String acao = p.coletar_.accept(new Int_Coletar(), arg);
			return acao;
		}
		@Override
		public String visit(EAction2 p, Object arg) {
			String acao2 = p.aplicar_.accept(new Int_Coletar2(), arg);
			return acao2;
		}
	}

	private static class Int_Tarefa implements Tarefa.Visitor<String,Object> {
		@Override
		public String visit(ETask p, Object arg) {
			String tarefa = p.acao_.accept(new Int_Acao(), arg);
			return tarefa;
		}

	}
}
