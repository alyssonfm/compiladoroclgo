package core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



import java_cup.runtime.Symbol;
import util.Logger;
import util.LoggerSemantico;
import util.Util;


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
		if(scanner != null){
			System.out.println("########  Iniciando Analise Lexica  ########");
			
			Symbol s;
			while(true){
				try{
					s = scanner.next_token();
					
					if(s.sym == sym.EOF){
						break;
					}
					
					if(s.sym == sym.STRING){
						logger.addMessage("Token: " + Util.changeTokenNames(s.toString()) + " - " + "\"" + "'" + s.value + "'" + "\"");
					}else{
						logger.addMessage("Token: " + Util.changeTokenNames(s.toString()) + " - " + "\"" + s.value + "\"");
					}
				
				
				}
				catch (IOException e) {
					System.out.printf(
							"Erro nao esperado durante a leitura do arquivo:\n%s\n", e
									.getMessage());
				}
				catch (Error e) {
					logger.addError(e.getMessage());
				}
			}
			System.out.print(logger);
			System.out.println("########  Finalizada Analise Lexica ########");
		}
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
	
	private static void analiseSemantica(String fileName, boolean debug) {
		System.out.println("########  Iniciando Analise Semantica  ########");
		try {
			AnaliseLexica l = createScanner(fileName);
			AnaliseSintatica g = new AnaliseSintatica(l);
			if(debug){
				g.debug_parse();
			}else{
				g.parse();
			}
			System.out.print(LoggerSemantico.getInstance());
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu algum erro sintatico");
		}catch (Error e) {
			System.out.print(e.getMessage());
			System.out.println("Ocorreu algum erro lexico");
		}
		System.out.println("########  Finalizada Analise Semantica ########");
	}

	public static void main(String[] args) {
		
		String filePath = "";
		int tipo = 0;
		
		System.out.println("COMPILADOR OCL -> GO");
		System.out.println("Autores: Alysson, Delano e Nata");
		
		if(args.length < 2){
			Scanner in;
			
			if(args.length < 1){
				System.out.print("Arquivo com consultas OCL: ");
				in = new Scanner(System.in);
				filePath = in.nextLine();
				
				System.out.println("1 - Analise Lexica");
				System.out.println("2 - Analise Sintatica");
				System.out.println("3 - Analise Semantica");
				in = new Scanner(System.in);
				tipo = in.nextInt();
			}else{
				System.out.println("1 - Analise Lexica");
				System.out.println("2 - Analise Sintatica");
				System.out.println("3 - Analise Semantica");
				in = new Scanner(System.in);
				tipo = in.nextInt();
				filePath = args[0];
			}
		}else{
			filePath = args[0];
			try{
				tipo = Integer.parseInt(args[1]);
			}catch (Exception e){
				System.out.println("Entrada deve ser: <string> <integer>");
				System.exit(0);
			}
		}
		
		if(tipo == 1){
			analiseLexica(filePath);
		}else if(tipo == 2){
			analiseSintatica(filePath, false);
		}else if(tipo == 3){
			analiseSemantica(filePath, false);
		}else{
			System.out.println("Deve escolher entre 1, 2 ou 3.");
		}
		
		System.out.println("Obrigado por usar nosso compilador!");
		System.out.println("Pressione ENTER para sair.");
		//Scanner in = new Scanner(System.in);
		//in.nextLine();
		
	}

}
