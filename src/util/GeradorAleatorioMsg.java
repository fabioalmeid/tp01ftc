package util;

import java.util.ArrayList;
import java.util.List;

public class GeradorAleatorioMsg {
	
	public static String getRandomMessageCentralizador(){
		List<String> mensagens = new ArrayList<String>();
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
		// @TODO Adicionar mensagens invalidas
		
		int indexMsg = (int) (Math.random() * (mensagens.size()-1));
		return mensagens.get(indexMsg);
	}
	
	public static String getRandomMessageCentralizadorToMonitor(){
		List<String> mensagens = new ArrayList<String>();
		mensagens.add("Iniciar Medicao Temperatura");
		mensagens.add("Iniciar Medicao Hemoglobina");
		mensagens.add("Iniciar Medicao bilirrubina");
		mensagens.add("Iniciar Medicao Temperatura e Hemoglobina");
		mensagens.add("Iniciar Medicao Hemoglobina e bilirrubina e Temperatura");
		mensagens.add("Parar medicao Temperatura");
		mensagens.add("Parar medicao bilirrubina");
		mensagens.add("Parar medicao Temperatura e bilirrubina");
		// @TODO Adicionar mensagens invalidas
		
		int indexMsg = (int) (Math.random() * (mensagens.size()-1));
		return mensagens.get(indexMsg);
	}
	
	public static String getRandomMessageCentralizadorToAtuador(){
		List<String> mensagens = new ArrayList<String>();
		mensagens.add("Liberar Dipirona");
		mensagens.add("Liberar Paracetamol");
		mensagens.add("Liberar 8 Dipirona");
		mensagens.add("Liberar 6 Paracetamol");
		mensagens.add("Cessar Liberacao Dipirona");
		mensagens.add("Cessar Liberacao Paracetamol");
		// @TODO Adicionar mensagens invalidas
		
		int indexMsg = (int) (Math.random() * (mensagens.size()-1));
		return mensagens.get(indexMsg);
	}
	
	
	public static String getRandomMessageMonitor(){
		List<String> mensagens = new ArrayList<String>();
		mensagens.add("Temperatura de 39 C as 19 h: 59 m");
		mensagens.add("Bilirrubina 1000 g/dL as 16 h: 12 m");
		mensagens.add("Hemoglobina 900 mg/dL as 22 h: 36 m");
		mensagens.add("Pressao Arterial 15 : 9 mmHg as 00 h: 12 m");
		mensagens.add("Bilirrubina 1000 g/dL as 14 h: 01 m e Hemoglobina 900 mg/dL as 14 h: 01 m");
		mensagens.add("Temperatura de 39 C as 19 h: 59 m e Pressao Arterial 15 : 9 mmHg as 19 h: 18 m");
		//@TODO Adicionar mensagens invalidas
		
		int indexMsg = (int) (Math.random() * (mensagens.size()-1));
		return mensagens.get(indexMsg);
	}
	
}
