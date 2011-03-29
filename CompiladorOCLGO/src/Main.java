import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import analise_lexica.Lex;
import analise_sintatica.parser;

public class Main {
	
	private static Lex createScanner(String fileName){
		Lex scanner = null;
		try {
			scanner = new Lex(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.printf("Arquivo %s nao encontrado.\n", fileName);
		} catch (Exception e) {
			System.out.printf("Erro nao esperado durante a leitura do arquivo:\n%s\n", e.getMessage());
		}
		return scanner;
	}
	
	private static void analiseSintatica(String fileName) {
		try {
			Lex l = createScanner(fileName);
		    parser g = new parser(l);
		    g.parse();
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		System.out.println("OCL -> GO");
		System.out.println("1 - Analise Lexica");
		System.out.println("2 - Analise Sintatica");		
		System.out.print("Arquivo? ");
		Scanner in = new Scanner(System.in);
		analiseSintatica(in.next());
	}

}
