package util;

public class StringErro {
	
	public static String tipoInvalido(String token, String tipo){
		return "Tipo de " + token + " deveria ser " + tipo;
	}
	
	public static String tipoDiferentes(String token1, String token2){
		return "Tipo de " + token1 + " eh diferente do tipo de " + token2;
	}

}
