package util;

public class LoggerSemantico extends Logger {
	
	private static LoggerSemantico instancia;
	
	public static LoggerSemantico getInstance(){
		if (instancia == null){
			instancia = new LoggerSemantico();
		}
		return instancia;
	}

}
