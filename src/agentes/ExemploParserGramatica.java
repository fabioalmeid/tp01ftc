package agentes;

import gramatica.Centralizador.PrettyPrinter;
import gramatica.Centralizador.Yylex;
import gramatica.Centralizador.parser;
import gramatica.Centralizador.Absyn.Tarefa;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExemploParserGramatica {
	public static void main(String args[]) throws Exception {
		String str = "Iniciar Medicao Temperatura";
		//String str = "Iniciar Monitoramento Temperatura";
		 
		// convert String into InputStream
		InputStream is = new ByteArrayInputStream(str.getBytes());
		
		Yylex linguagem = new Yylex(is);
		
		parser p = new parser(linguagem);
		
		Tarefa parse_tree = null;
		try {
			parse_tree = p.pTarefa();
		} catch (Error e) {
			System.out.println("String '" + str + "' nao pertence a linguagem.");
			throw new Exception("String '" + str + "' pertence a linguagem.");
		}

		System.out.println(PrettyPrinter.print(parse_tree));


		
		// read it with BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
	 
		String line;
		System.out.print("String de entrada: ");
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	 
		br.close();
		
		System.out.println("acabou");
		
	}

}
