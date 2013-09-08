package agentes;

import java.util.List;

import gramatica.Centralizador.Absyn.Dados;
import gramatica.Centralizador.Absyn.Medicacao;

public class TarefaCentralizador {
	private Object acao; 
	/*possíveis valores
	 * ECollect1. Coletar ::= "Iniciar Medicao";
	 * ECollect2. Coletar ::= "Parar medicao";
	 * EApply1. Aplicar ::= "Liberar";
	 * EApply2. Aplicar ::= "Cessar Liberacao"; 
	 * */
	
	private List<Object> dados;
	private List<Object> medicacao;
	
	public Object getAcao() {
		return acao;
	}
	public void setAcao(Object acao) {
		this.acao = acao;
	}
	


}
