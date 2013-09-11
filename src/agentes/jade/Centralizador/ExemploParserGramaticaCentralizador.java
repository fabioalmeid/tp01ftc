package agentes.jade.Centralizador;

import java.util.ArrayList;
import java.util.List;

public class ExemploParserGramaticaCentralizador {
	public static void main(String args[]) {
		List<String> mensagens = new ArrayList<String>();
		
		// todas mensagens corretas - Simulacao de envio do centralizador
		mensagens.add("Iniciar Medicao Temperatura");
		mensagens.add("Iniciar Medicao Hemoglobina");
		mensagens.add("Iniciar Medicao bilirrubina");
		mensagens.add("Iniciar Medicao Temperatura e Hemoglobina");
		mensagens.add("Iniciar Medicao Hemoglobina e bilirrubina e Temperatura");
		mensagens.add("Parar medicao Temperatura");
		mensagens.add("Parar medicao bilirrubina");
		mensagens.add("Parar medicao Temperatura e bilirrubina");
		mensagens.add("Liberar Dipirona");
		mensagens.add("Liberar Paracetamol");
		mensagens.add("Liberar 8 Dipirona");
		mensagens.add("Liberar 6 Paracetamol");
		mensagens.add("Cessar Liberacao Dipirona");
		mensagens.add("Cessar Liberacao Paracetamol");		

		for (String s : mensagens) {
			System.out.println("mensagem recebida do centralizador:\n" + s);
			try { // simula o monitor recebendo e validando a mensagem
				TarefaCentralizador tc = GrammarParserCentralizador.getCentralizadorMessageObject(s);
				System.out.println(tc.prettyPrinterTarefa());
				System.out.println("\n-------------------");	
			} catch (Exception e){
				System.out.println(e.getMessage());
			}						
		}

		System.out.println("acabou");

	}
}
