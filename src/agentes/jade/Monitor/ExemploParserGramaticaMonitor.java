package agentes.jade.Monitor;

import gramatica.Monitor.Absyn.EDados;
import gramatica.Monitor.Absyn.EDados1;
import gramatica.Monitor.Absyn.EDados2;
import gramatica.Monitor.Absyn.EDados3;

import java.util.ArrayList;
import java.util.List;


public class ExemploParserGramaticaMonitor {
	
	public static void main(String args[]) throws Exception {
		String mens = "Temperatura de 37 C as 05 h: 30 m";
		
		// exemplo de como realizar get nos dados
		TarefaMonitor tm = new TarefaMonitor();
		try {
			tm = GrammarParserMonitor.getMonitorMessageObject(mens);
			List<Afericao> listaAF = tm.getAfericoes(); 
			for (Afericao af : listaAF) {
				if (af.getDado() instanceof EDados){ // veja gramatica para EDados
					System.out.print("Temperatura ");
					System.out.print(String.valueOf(af.getQuantidade1()) + " C");
				}
			}
			System.out.println("\n-------------------");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		// agora testando com mais mensagens
		List<String> mensagens = new ArrayList<String>();
		mensagens.add("Temperatura de 39 C as 19 h: 59 m");
		mensagens.add("Bilirrubina 1000 g/dL as 16 h: 12 m");
		mensagens.add("Hemoglobina 900 mg/dL as 22 h: 36 m");
		mensagens.add("Pressao Arterial 15 : 9 mmHg as 00 h: 12 m");
		mensagens.add("Bilirrubina 1000 g/dL as 14 h: 01 m e Hemoglobina 900 mg/dL as 14 h: 01 m");
		mensagens.add("Temperatura de 39 C as 19 h: 59 m e Pressao Arterial 15 : 9 mmHg as 19 h: 18 m");
		
		for (String s : mensagens) {
			System.out.println("mensagem recebida do monitor: " + s);
			TarefaMonitor t = new TarefaMonitor();
			try {
				t = GrammarParserMonitor.getMonitorMessageObject(s);
				Imprime(t.getAfericoes());
				System.out.println("\n-------------------");
			} catch (Exception e){
				System.out.println(e.getMessage());
			}			
		}
		
	}
	
	public static void Imprime(List<Afericao> listaAF) {
		for (Afericao af : listaAF) {
			if (af.getDado() instanceof EDados) { // veja gramatica para EDados
				System.out.print(" Temperatura ");
				System.out.print(String.valueOf(af.getQuantidade1()) + " C " + af.getHora());
			} else if (af.getDado() instanceof EDados1) {
				System.out.print(" Bilirrubina ");
				System.out.print(String.valueOf(af.getQuantidade1()) + " g/dL " + af.getHora());
			} else if (af.getDado() instanceof EDados2) {
				System.out.print(" Hemoglobina ");
				System.out.print(String.valueOf(af.getQuantidade1()) + " mg/dL " + af.getHora());
			} else if (af.getDado() instanceof EDados3) {
				System.out.print(" Pressao Arterial ");
				System.out.print(String.valueOf(af.getQuantidade1()) + ":");
				System.out.print(String.valueOf(af.getQuantidade2()) + " mg/dL " + af.getHora());
			}
		}
	}
}
