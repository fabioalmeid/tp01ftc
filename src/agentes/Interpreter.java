//package agentes;
//
//import gramatica.Centralizador.Absyn.*;
//
//public class Interpreter {
//	public static Integer interpret(Tarefa e) {
//		Tarefa tarefa = (Tarefa) e;
//		return interpretExp(tarefa, null);
//	}
//
//	private static Integer interpretExp(Tarefa e, Object o) {
//		return e.accept(new kct(), null);
//	}
//
//	private static class kct implements Tarefa.Visitor<ETask,Object> {
//
//		public Acao.Visitor<ETask, Object> visit(EAction1 p, Object o) {
//
//			Integer a = p.acao_.accept(this, null);
//
//			return a + b;
//		}
//
//		public Integer visit(Calc.Absyn.EDiv p, Object o) {
//
//			Integer a = p.exp_1.accept(this, null);
//			Integer b = p.exp_2.accept(this, null);
//
//			return a / b;
//		}
//
//		public Integer visit(Calc.Absyn.ESub p, Object o) {
//
//			Integer a = p.exp_1.accept(this, null);
//			Integer b = p.exp_2.accept(this, null);
//
//			return a - b;
//		}
//
//		public Integer visit(Calc.Absyn.EMul p, Object o) {
//
//			Integer a = p.exp_1.accept(this, null);
//			Integer b = p.exp_2.accept(this, null);
//
//			return a * b;
//		}
//
//		public Integer visit(Calc.Absyn.EInt p, Object o) {
//			return p.integer_;
//		}
//
//	}
//}
