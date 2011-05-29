package util;

public class LoggerSemantico extends Logger {
	
	private static LoggerSemantico instancia;
	
	public static LoggerSemantico getInstance(){
		if (instancia == null){
			instancia = new LoggerSemantico();
		}
		return instancia;
	}
	public static LoggerSemantico getNewInstance(){
		instancia = new LoggerSemantico();
		return instancia;
	}
	
}
