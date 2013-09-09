package agentes.jade.Monitor;

public class Afericao {
	private Object dado;
	/*Possiveis valores
	 * EDados. Dados ::= "Temperatura de" Quantidade "C" ;
	 * EDados1. Dados ::= "Bilirrubina" Quantidade "g/dL" ;
	 * EDados2. Dados ::= "Hemoglobina" Quantidade "mg/dL";
	 * EDados3. Dados ::= "Pressao Arterial" Quantidade ":" Quantidade "mmHg";
	 * */
	private Integer quantidade1 = null;
	private Integer quantidade2 = null;
	
	private Integer hora1 = null;
	private Integer hora2 = null;
	
	public Object getDado() {
		return dado;
	}
	public void setDado(Object dado) {
		this.dado = dado;
	}
	public Integer getQuantidade1() {
		return quantidade1;
	}
	public void setQuantidade1(Integer quantidade) {
		this.quantidade1 = quantidade;
	}
	public Integer getQuantidade2() {
		return quantidade2;
	}
	public void setQuantidade2(Integer quantidade2) {
		this.quantidade2 = quantidade2;
	}
	public Integer getHora2() {
		return hora2;
	}
	public void setHora2(Integer hora2) {
		this.hora2 = hora2;
	}
	public Integer getHora1() {
		return hora1;
	}
	public void setHora1(Integer hora1) {
		this.hora1 = hora1;
	}
	
	public String getHora() {
		return ("as " +String.valueOf(this.hora1)+"h"+String.valueOf(this.hora2)+"m");
	}
	
	

}
