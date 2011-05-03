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
        
        public static Logger analiseLexica(String fileName){
                AnaliseLexica scanner = null;
                Logger logger = new Logger();
                scanner = createScanner(fileName);
                if(scanner != null){
                	logger.addMessage("########  Iniciando Analise Lexica  ########");
                        
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
                        logger.addMessage("########  Finalizada Analise Lexica ########");
                }
                return logger;
        }

        public static Logger analiseSintatica(String fileName, boolean debug) {
        	Logger log = new Logger();
        	log.addMessage("########  Iniciando Analise Sintatica  ########");
            try {
            	AnaliseLexica l = createScanner(fileName);
                AnaliseSintatica g = new AnaliseSintatica(l);
                if(debug){
                	g.debug_parse();
                }else{
                	g.parse();
                }
                log.addMessage("Sem erros.");
                } catch (FileNotFoundException e) {
                	log.addMessage("Arquivo nao encontrado!");
                } catch (Exception e) {
                	log.addMessage("Ocorreu algum erro sintatico");
                }catch (Error e) {
                        log.addError(e.getMessage());
                        log.addMessage("Ocorreu algum erro lexico");
                }
                log.addMessage("########  Finalizada Analise Sintatica ########");
                return log;
        }
        
        public static Logger analiseSemantica(String fileName, boolean debug) {
        	Logger log = new Logger();
        	log.addMessage("########  Iniciando Analise Semantica  ########");
                
                try {
                        AnaliseLexica l = createScanner(fileName);
                        AnaliseSintatica g = new AnaliseSintatica(l);
                        if(debug){
                                g.debug_parse();
                        }else{
                                g.parse();
                        }
                        log.addMessage(LoggerSemantico.getInstance().toString());
                } catch (FileNotFoundException e) {
                	log.addError("Arquivo nao encontrado!");
                } catch (Exception e) {
                	log.addError("Ocorreu algum erro sintatico");
                }catch (Error e) {
                        log.addError(e.getMessage());
                        log.addMessage("Ocorreu algum erro lexico");
                }
                log.addMessage("########  Finalizada Analise Semantica ########");
                return log;
        }
        public static void main(String[] args) {
			MainFrame mf = new MainFrame();
		}
}