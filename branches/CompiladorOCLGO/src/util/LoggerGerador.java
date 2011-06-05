package util;

public class LoggerGerador{
	
	private static LoggerGerador instancia;
	private static LoggerSemantico semantico;
	
	public static LoggerGerador getInstance(){
		if (instancia == null)
			return getNewInstance();
		return instancia;
	}
	public static LoggerGerador getNewInstance(){
		semantico = LoggerSemantico.getNewInstance();
		instancia = new LoggerGerador();
		return instancia;
	}
	
	public static LoggerSemantico getLogger(){
		return semantico;
	}
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		if(semantico.hasError())
			return semantico.toString();
		return code;
	}
	public void addError(String error) {
		semantico.addError(error);
	}
	public void addMessage(String message) {
		semantico.addMessage(message);
	}
	

}
