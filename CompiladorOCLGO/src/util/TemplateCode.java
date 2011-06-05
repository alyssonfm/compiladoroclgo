package util;

public class TemplateCode {
	
	public static String IF(String exp, String verdadeiro, String falso){
		return "if " + exp + "{\n" + "\t return " + verdadeiro + "\n}\n" + "return " + falso;
	}
	
	public static String FUNCTION(String classe, String nome, String parametro, String type){
		return "func " + nome + "(" + parametro + ") (" + type + ") {\n";
	}
	
	public static String RELATIONALEXPRESSION(String left, String operador, String right){
		return "(" + left + " " + operador + " " + right + ")";		
	}
	
}
