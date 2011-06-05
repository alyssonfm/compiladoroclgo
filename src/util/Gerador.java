package util;

import java.util.HashMap;
import java.util.LinkedList;


public class Gerador {
	
	private String code;
	private static Gerador instance;
	private static String funcao;
	private HashMap<String, Funcao> funcoes;
	private Funcao funcaoAtual;
	private int temp;
	
	public static Gerador getNewInstance(){
		instance = new Gerador();
		instance.code = cabecalho();
		instance.funcoes = new HashMap<String, Funcao>();
		instance.temp = 0;
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
		funcaoAtual.corpo += s;
	}
	
	private String addFUNCAOCONDICIONAL(String nome, String condicao, String verdade, String falso, String tipo){
		Funcao f = new Funcao(nome + "Temp" + temp++, tipo);
		f.corpo = TemplateCode.IF("condicao", "verdadeiro", "falso");
		f.parametros.add(new Parametro("condicao", "bool"));
		f.parametros.add(new Parametro("verdadeiro", tipo));
		f.parametros.add(new Parametro("falso", tipo));
		funcoes.put(f.nome, f);
		return f.nome + "(" + condicao + "," + verdade + "," + falso +")";
	}
	
	public String addIF(String condicao, String verdade, String falso, String tipo){
		return addFUNCAOCONDICIONAL("IF", condicao, verdade, falso, tipo);
	}
	
	public String addIMPLIES(String condicao, String resultado){
		return addFUNCAOCONDICIONAL("IMPLIES", condicao, resultado, "!" + resultado, "bool");
	}
	
	public void addFuncao(String classe, String nome, LinkedList<Parametro> parametros, String retornoTipo){
		Funcao f = new Funcao(nome, retornoTipo);
		funcoes.put(nome, f);
		f.parametros = parametros;
		f.corpo = "return ";
		funcaoAtual = f;
	}
	
	
	public void print(){
		System.out.println(code);
	}
	
	private void gerarTudo(){
		for(Funcao f : funcoes.values()){
			code += f.code() + "\n";
		}
	}

	public String getCode(){
		gerarTudo();
		if(funcao == null)
			return code;
		return code + mainExp();
	}
	
	public static String type(String t){
		if(t.equalsIgnoreCase("Integer"))
			return "int";
		if(t.equalsIgnoreCase("Real"))
			return "float";
		if(t.equalsIgnoreCase("String"))
			return "string";
		if(t.equalsIgnoreCase("Boolean"))
			return "bool";
		return t;
	}
	
}
