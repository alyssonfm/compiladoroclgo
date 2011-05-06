package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java_cup.runtime.Symbol;
import util.ErroFatal;
import util.Logger;
import util.LoggerSemantico;
import util.Util;
import util.sym;
import analise_lexica.AnaliseLexica;
import analise_semantica.AnaliseSemantica;
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
        
        public static Logger analiseLexica(String fileName){
                AnaliseLexica scanner = null;
                Logger logger = new Logger();
                scanner = createScanner(fileName);
                if(scanner != null){
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
                }
                return logger;
        }

        public static Logger analiseSintatica(String fileName, boolean debug) {
        	Logger log = new Logger();
        	Util.setLog(log);
            try {
            	AnaliseLexica l = createScanner(fileName);
                AnaliseSintatica g = new AnaliseSintatica(l);
                if(debug){
                	g.debug_parse();
                }else{
                	g.parse();
                }
                } catch (FileNotFoundException e) {
                	log.addError("Arquivo nao encontrado!");
                } catch (Exception e) {
                	log.addMessage("Ocorreu algum erro sintatico");
                }catch (Error e) {
                        log.addError(e.getMessage());
                        log.addMessage("Ocorreu algum erro lexico");
                }
                return log;
        }
        
        public static Logger analiseSemantica(String fileName, boolean debug) {
        	Logger log = LoggerSemantico.getNewInstance();
            Util.setLog(log);
                try {
                        AnaliseLexica l = createScanner(fileName);
                        AnaliseSemantica g = new AnaliseSemantica(l);
                        if(debug){
                                g.debug_parse();
                        }else{
                                g.parse();
                        }
                } catch (FileNotFoundException e) {
                	log.addError("Arquivo nao encontrado!");
                } catch (ErroFatal e) {
        			log.addMessage("Erro na analise Semantica.");
                } catch (Exception e) {
                	log.addError(e.getStackTrace().toString());
                	log.addMessage("Ocorreu algum erro sintatico");
                }catch (Error e) {
                        log.addError(e.getMessage());
                        log.addMessage("Ocorreu algum erro lexico");
                }
                return log;
        }
        public static void main(String[] args) {
			MainFrame mf = new MainFrame();
		}
}