package agentes.jade.Monitor;

import java.util.ArrayList;
import java.util.List;

import agentes.jade.Centralizador.Medicamento;

public class TarefaMonitor {
	private List<Afericao> afericoes = new ArrayList<Afericao>(); 
	/*possíveis valores
	 * EAcao. Acao ::= Dados "as" Hora;
	 * EAcao2. Acao ::= Acao  Operador Acao;
	 * EDados. Dados ::= "Temperatura de" Quantidade "C" ;
	 * EDados1. Dados ::= "Bilirrubina" Quantidade "g/dL" ;
	 * EDados2. Dados ::= "Hemoglobina" Quantidade "mg/dL";
	 * EDados3. Dados ::= "Pressao Arterial" Quantidade ":" Quantidade "mmHg";
	 * */

	public List<Afericao> getAfericoes() {
		return afericoes;
	}

	public void setAfericoes(Afericao afericoes) {
		this.afericoes.add(afericoes);
	}
	
	public void setAfericoes(List<Afericao> afericoes) {
		this.afericoes = afericoes;
	}
	
}
