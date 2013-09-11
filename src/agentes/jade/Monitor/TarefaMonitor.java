package agentes.jade.Monitor;

import java.util.ArrayList;

public class TarefaMonitor {
	//private List<Afericao> afericoes = new ArrayList<Afericao>();
	private ArrayList<ArrayList<Afericao>> listaDeListaAfericoes = new ArrayList<ArrayList<Afericao>>();
	/*possíveis valores
	 * EAcao. Acao ::= Dados "as" Hora;
	 * EAcao2. Acao ::= Acao  Operador Acao;
	 * EDados. Dados ::= "Temperatura de" Quantidade "C" ;
	 * EDados1. Dados ::= "Bilirrubina" Quantidade "g/dL" ;
	 * EDados2. Dados ::= "Hemoglobina" Quantidade "mg/dL";
	 * EDados3. Dados ::= "Pressao Arterial" Quantidade ":" Quantidade "mmHg";
	 * E qualquer concatenação de duas ou mais ações
	 * */

	public ArrayList<ArrayList<Afericao>> getListaDeListaAfericoes() {
		return listaDeListaAfericoes;
	}

	public void setListaDeListaAfericoes(ArrayList<ArrayList<Afericao>> listaDeListaAfericoes) {
		this.listaDeListaAfericoes = listaDeListaAfericoes;
	}
	
	public String prettyPrinterListaAfericoes() {
		StringBuilder pretty = new StringBuilder();
		
		int cont = 0;
		for (ArrayList<Afericao> singleList : getListaDeListaAfericoes()) {
			if (cont > 0) pretty.append("\n");
			pretty.append(prettyPrinterAfericoes(singleList));
			cont++;
		}
		
		return pretty.toString();
	}
	
	public String prettyPrinterAfericoes(ArrayList<Afericao> afericoes) {
		StringBuilder pretty = new StringBuilder();

		int cont = 0;
		for (Afericao af : afericoes) {
			if (cont > 0) pretty.append(" e ");
			pretty.append(af.prettyPrinter());
			cont++;
		}
		
		return pretty.toString();
	}
}
