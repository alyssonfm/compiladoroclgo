package util;


public class Gerador {
	
	private String code;
	private static Gerador instance;
	private static String funcao;
	
	public static Gerador getNewInstance(){
		instance = new Gerador();
		instance.code = cabecalho();
		return instance;
	}
	
	public void setFuncao(String nome){
		funcao = nome;
	}
	
	public static Gerador getInstance(){
		if(instance == null)
			return getNewInstance();
		return instance;
	}
	
	private static String cabecalho(){
		return "package main\n\n" +
				"import \"fmt\"\n\n";
	}
	
	private static String mainExp(){
		return "\nfunc main() {\n\t" +
				"fmt.Println(" + funcao +"())\n}";
	}
	
	public void gen(String s) {
		code += s;
	}
	
	public void print(){
		System.out.println(code);
	}
	
	public String getCode(){
		if(funcao == null)
			return code;
		return code + mainExp();
	}
	
}
