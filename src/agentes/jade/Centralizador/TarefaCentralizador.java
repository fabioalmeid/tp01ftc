package agentes.jade.Centralizador;

import gramatica.Centralizador.Absyn.Dados;
import gramatica.Centralizador.Absyn.EApply1;
import gramatica.Centralizador.Absyn.EApply2;
import gramatica.Centralizador.Absyn.ECollect1;
import gramatica.Centralizador.Absyn.ECollect2;
import gramatica.Centralizador.Absyn.EData1;
import gramatica.Centralizador.Absyn.EData2;
import gramatica.Centralizador.Absyn.EData3;
import gramatica.Centralizador.Absyn.EData4;
import gramatica.Centralizador.Absyn.ERemedy1;
import gramatica.Centralizador.Absyn.ERemedy2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TarefaCentralizador {
	private final int DATA_QUANTITY = 5;
	
	private Object acao;	
	/*possíveis valores
	 * ECollect1. Coletar ::= "Iniciar Medicao";
	 * ECollect2. Coletar ::= "Parar medicao";
	 * EApply1. Aplicar ::= "Liberar";
	 * EApply2. Aplicar ::= "Cessar Liberacao"; 
	 * */
	
	private List<Object> dados = new ArrayList<Object>();
	/*possíveis valores
	 *  EData1. Dados ::= "Temperatura";
	 *  EData2. Dados ::= "Hemoglobina";
	 *  EData3. Dados ::= "bilirrubina";
	 *  EData4. Dados ::= "Pressao Arterial";
	 *  EData5. Dados ::= Dados Operador Dados;
	 * */
	
	private List<Medicamento> medicacao = new ArrayList<Medicamento>();
	/*possíveis valores
	 * EQtde1. Quantidade ::= Integer;
	 * ERemedy1. Remedio ::= "Dipirona";
	 * ERemedy2. Remedio ::= "Paracetamol";
	*/
	
	public Object getAcao() {
		return acao;
	}
	public void setAcao(Object acao) {
		this.acao = acao;
	}
	public List<Object> getDados() {
		return dados;
	}
	public void setDados(Object dados) {
		this.dados.add(dados);
	}
	public List<Medicamento> getMedicacao() {
		return medicacao;
	}
	public void setMedicacao(Medicamento medicacao) {
		this.medicacao.add(medicacao);
	}
	
	public String prettyPrinterTarefa() {
		StringBuilder pretty = new StringBuilder();
		
		if ((getAcao() instanceof ECollect1) || (getAcao() instanceof ECollect2)) {
			String action = (getAcao() instanceof ECollect1) ? "Iniciar Medicao " : "Parar medicao ";
			pretty.append(action);
			pretty.append(prettyPrinterDados(getDados()));
		} else if ((getAcao() instanceof EApply1) || (getAcao() instanceof EApply2)) {
			String action = (getAcao() instanceof EApply1) ? "Liberar " : "Cessar Liberacao "; 
			pretty.append(action);
			pretty.append(prettyPrinterMedicacao(getMedicacao()));
		}
		
		return pretty.toString();
	}
	
	private String prettyPrinterDados(List<Object> dados) {
		int cont = 0;
		StringBuilder pretty = new StringBuilder();
		for (Object o : dados){
			if (o instanceof EData1)
				pretty.append((cont > 0) ? " e Temperatura" : "Temperatura");
			else if (o instanceof EData2)
				pretty.append((cont > 0) ? " e Hemoglobina" : "Hemoglobina");
			else if (o instanceof EData3)
				pretty.append((cont > 0) ? " e bilirrubina" : "bilirrubina");
			else if (o instanceof EData4)
				pretty.append((cont > 0) ? " e Pressao Arterial" : "Pressao Arterial");
			cont++;
		}
		return pretty.toString();
	}
	
	private String prettyPrinterMedicacao(List<Medicamento> med) {
		StringBuilder pretty = new StringBuilder();
		for (Medicamento m : med) {
			if (m.remedio instanceof ERemedy1) {
				if (m.quantidade != null)
					pretty.append(String.valueOf(m.quantidade) + " Dipirona");
				else
					pretty.append("Dipirona");
			} else if (m.remedio instanceof ERemedy2) {
				if (m.quantidade != null)
					pretty.append(String.valueOf(m.quantidade)+ " Paracetamol");
				else
					pretty.append("Paracetamol");
			}
		}
		return pretty.toString();
	}
	
	public String getRandomStartMeasure() {
		TarefaCentralizador tc = new TarefaCentralizador();
		
		// seta iniciar medicao
		tc.setAcao(new ECollect1());

		int iRandom = (int) (Math.random() * (DATA_QUANTITY));
		switch (iRandom) {
		case 0:
			tc.setDados(new EData1());
			break;
		case 1:
			tc.setDados(new EData2());
			break;
		case 2:
			tc.setDados(new EData3());
			break;
		case 3:
			tc.setDados(new EData4());
			break;
		case 4:
			int iRandom2 = (int) (Math.random() * (DATA_QUANTITY-1));
			int iRandom3 = (int) (Math.random() * (DATA_QUANTITY-1));
			
			Map <Integer, Dados> hm = new HashMap<Integer, Dados>();
			hm.put(0, new EData1());
			hm.put(1, new EData2());
			hm.put(2, new EData3());
			hm.put(3, new EData4());
			
			tc.setDados(hm.get(iRandom2));
			if (iRandom3 != iRandom2)
				tc.setDados(hm.get(iRandom3));
							
			break;
		default:
			System.out.println("Error: Index out of range on TarefaCentralizador");
			break;
		}		
		return tc.prettyPrinterTarefa();
	}

}
