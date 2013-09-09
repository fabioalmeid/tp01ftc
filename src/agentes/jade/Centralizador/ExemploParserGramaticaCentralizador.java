package agentes.jade.Centralizador;

import gramatica.Centralizador.Absyn.*;
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
			System.out.println("mensagem recebida do centralizador: " + s);
			try { // simula o monitor recebendo e validando a mensagem
				TarefaCentralizador tc = GrammarParserCentralizador.getCentralizadorMessageObject(s);
				if (tc.getAcao() instanceof ECollect1) { // veja na gramatica o que significa ECollect1
					System.out.print("Iniciando a medicacao");
					List<Object> dados = tc.getDados();
					ImprimeDados(dados);
				}
				else if (tc.getAcao() instanceof ECollect2) {
					System.out.print("Parando medicao");
					List<Object> dados = tc.getDados();
					ImprimeDados(dados);
				}
				else if (tc.getAcao() instanceof EApply1) {
					System.out.print("Liberando medicacao");
					List<Medicamento> med = tc.getMedicacao();
					ImprimeMedicacao(med);
				}
				else if (tc.getAcao() instanceof EApply2){
					System.out.print("Cessando liberacao da medicacao");
					List<Medicamento> med = tc.getMedicacao();
					ImprimeMedicacao(med);
					}
				System.out.println("\n-------------------");	
			} catch (Exception e){
				System.out.println(e.getMessage());
			}						
		}

		System.out.println("acabou");

	}
	
	public static void ImprimeDados(List<Object> dados) {
		for (Object o : dados){
			if (o instanceof EData1)
				System.out.print(" Temperatura");
			else if (o instanceof EData2)
				System.out.print(" Hemoglobina");
			else if (o instanceof EData3)
				System.out.print(" bilirrubina");
			else if (o instanceof EData4)
				System.out.print(" Pressao Arterial");
		}
	}
	
	public static void ImprimeMedicacao(List<Medicamento> med) {
		for (Medicamento m : med) {
			if (m.remedio instanceof ERemedy1) {
				if (m.quantidade != null)
					System.out.print(" " + String.valueOf(m.quantidade) + " Dipirona");
				else
					System.out.print(" Dipirona");
			} else if (m.remedio instanceof ERemedy2) {
				if (m.quantidade != null)
					System.out.print(" " + String.valueOf(m.quantidade)+ " Paracetamol");
				else
					System.out.print(" Paracetamol");
			}
		}
	}

}
