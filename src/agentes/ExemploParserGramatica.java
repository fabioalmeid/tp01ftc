package agentes;

import gramatica.Centralizador.Interpreter;
import gramatica.Centralizador.TransformIntoObject;
import gramatica.Centralizador.VisitSkel;
import gramatica.Centralizador.Yylex;
import gramatica.Centralizador.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ExemploParserGramatica {
	public static void main(String args[]) throws Exception {
		//String str = "Iniciar Medicao Temperatura";
		//String str = "Iniciar Monitoramento Temperatura";
		String str = "Liberar Dipirona";

		// convert String into InputStream
		InputStream is = new ByteArrayInputStream(str.getBytes());
		// read it with BufferedReader
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//		String line;
//		System.out.print("String de entrada: ");
//		while ((line = br.readLine()) != null) {
//			System.out.println(line);
//		}
//
//		br.close();

		Yylex linguagem = new Yylex(is);

		parser p = new parser(linguagem);
		
//		TransformIntoObject obj = new TransformIntoObject();
//		obj.teste(p.pTarefa());
	
		String resultado = Interpreter.interpret(p.pTarefa());
	    System.out.println("Resultado: " + resultado);

//		Tarefa parse_tree = null;
//		try {
//			parse_tree = p.pTarefa();
//		} catch (Error e) {
//			throw new Exception("String '" + str + "' nao pertence a linguagem. Erro: " + e.getMessage());
//		}
//		
//		System.out.println(PrettyPrinter.print(parse_tree));

		System.out.println("acabou");

	}

}
