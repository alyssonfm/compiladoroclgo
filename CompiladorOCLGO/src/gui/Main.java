package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import core.sym;

import analise_lexica.AnaliseLexica;
import analise_sintatica.AnaliseSintatica;

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
        
        public static void analiseLexica(String fileName){
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

        public static void analiseSintatica(String fileName, boolean debug) {
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
        
        public static void analiseSemantica(String fileName, boolean debug) {
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
                        System.out.println("Ocorreu algum erro sintatico");
                }catch (Error e) {
                        System.out.print(e.getMessage());
                        System.out.println("Ocorreu algum erro lexico");
                }
                System.out.println("########  Finalizada Analise Semantica ########");
        }
        public static void main(String[] args) {
			MainFrame mf = new MainFrame();
		}
}