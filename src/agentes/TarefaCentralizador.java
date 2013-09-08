package agentes;

import java.util.ArrayList;
import java.util.List;

public class TarefaCentralizador {
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
	


}
