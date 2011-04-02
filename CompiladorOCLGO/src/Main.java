import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import java_cup.sym;
import java_cup.runtime.Symbol;
import util.Logger;
import util.Util;
import analise_lexica.AnaliseLexica;
import analise_sintatica.AnaliseSintatica;


public class Main {

	private static AnaliseLexica createScanner(String fileName) {
		AnaliseLexica scanner = null;
		try {
			scanner = new AnaliseLexica(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.printf("Arquivo %s nao encontrado.\n", fileName);
		} catch (Exception e) {
			System.out.printf(
					"Erro nao esperado durante a leitura do arquivo:\n%s\n", e
							.getMessage());
		}
		return scanner;
	}
	
	private static void analiseLexica(String fileName){
		AnaliseLexica scanner = null;
		Logger logger = new Logger();
		scanner = createScanner(fileName);
		
		System.out.println("########  Iniciando Analise Lexica  ########");
		
		Symbol s;
		while(true){
			try{
				s = scanner.next_token();
				
				if(s.sym == sym.EOF){
					break;
				}
				
				logger.addMessage("Token: " + Util.changeTokenNames(s.toString()) + " - " + "\"" + s.value + "\"");
			
			
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (Error e) {
				logger.addError(e.getMessage());
			}
		}
		System.out.print(logger);
		System.out.println("########  Finalizada Analise Lexica ########");
	}

	private static void analiseSintatica(String fileName, boolean debug) {
		System.out.println("########  Iniciando Analise Sintatica  ########");
		try {
			AnaliseLexica l = createScanner(fileName);
			AnaliseSintatica g = new AnaliseSintatica(l);
			if(debug){
				g.debug_parse();
			}else{
				g.parse();
			}
			System.out.println("Sem erros.");
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado!");
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro sintatico");
		}catch (Error e) {
			System.out.print(e.getMessage());
			System.out.println("Ocorreu algum erro lexico");
		}
		System.out.println("########  Finalizada Analise Sintatica ########");
	}

	public static void main(String[] args) {
		
		if(args.length < 1){
			System.out.println("OCL -> GO");
			System.out.println("1 - Analise Lexica");
			System.out.println("2 - Analise Sintatica");
			System.out.print("Arquivo? ");
			Scanner in = new Scanner(System.in);
			analiseSintatica(in.next(), false);
		}else{
			System.out.println("OCL -> GO");
			System.out.println("1 - Analise Lexica");
			System.out.println("2 - Analise Sintatica");
			System.out.println("3 - Analise Sintatica Avançado");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			if(choice == 1){
				analiseLexica(args[0]);
			}else if(choice == 2){
				analiseSintatica(args[0], false);
			}else if(choice == 3){
				analiseSintatica(args[0], true);
			}
			System.out.println("FIM");
		}
	}

}
