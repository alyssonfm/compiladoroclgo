package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cvslogic.AtributoComp;
import cvslogic.ClasseComp;
import cvslogic.OperacaoComp;


public class Gerador {
	
	private String code;
	private static Gerador instance;
	private static String funcao;
	private HashMap<String, Classe> classes;
	private Funcao funcaoAtual;
	private Funcao funcaoConsultaAtual;
	private boolean consulta;
	private LinkedList<String> consultaValores;
	private int temp;
	private HashMap<String, Funcao> funcoes;
	
	public static Gerador getNewInstance(){
		instance = new Gerador();
		return instance;
	}
	
	private Gerador() {
		code = cabecalho();
		funcoes = new HashMap<String, Funcao>();
		classes = new HashMap<String, Classe>();
		temp = 0;
		criarClasses();
	}
	
	public void setFuncaoConsulta(){
		funcaoConsultaAtual = new Funcao("", "");
		consulta = true;
		consultaValores = new LinkedList<String>();
	}
	
	public void desativarConsulta(){
		consulta = false;
	}
	
	public void addParametroConsulta(String nome, String tipo, String code){
		if(consulta){
			if(!code.contains("atual.valor"))
				funcaoConsultaAtual.parametros.add(new Parametro(nome, type(tipo)));
			consultaValores.add(code);
		}
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
		String code = "\nfunc main() {\n" +
				"\ttemp := new(" + instance.funcaoAtual.tipoAssociado + ")\n" +
				"\tfmt.Println(temp." + funcao +"(";
		code += instance.funcaoAtual.getLiteralParametros();
		code += "))\n";
		code += "}";
		return code;
	}
	
	public void gen(String s) {
		funcaoAtual.corpo += s;
	}
	
	private String addFUNCAOCONDICIONAL(String nome, String condicao, String verdade, String falso, String tipo){
		String t = tipo;
		if(!typePrimitiveGO(t)){
			t = "*";
			t += Elemento.ehCollection(tipo) ? "List" + tipo : tipo; 
		}
		Funcao f = new Funcao(nome + "Temp" + temp++, t);
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
	
	private String addFuncaoEXISTS(String condicao, String tipoLista, String listaNome){
		Funcao f = funcaoConsultaAtual;
		f.nome = "existsTemp" + temp++;
		f.retornoTipo = "bool";
		f.parametros.add(new Parametro("lista", tipoLista));
		
		String code = f.nome + "(";
		for(String s : consultaValores){
			if(s.contains("atual.valor")){
				String id = s.substring(s.lastIndexOf(".") + 1);
				condicao = condicao.replace(id, s);
			}else{
				code += s + ",";
			}
		}
		code += "self." + listaNome + ")";
		
		
		f.corpo = "atual := lista.head\n";
		f.corpo += "for atual != nil {\n";
		f.corpo += "\tif(" + condicao + "){\n";
		f.corpo += "\t\treturn true\n";
		f.corpo += "\t}\n";
		f.corpo += "\tatual = atual.next\n";
		f.corpo += "}\n";
		f.corpo += "return false";
		funcoes.put(f.nome, f);
		
		return code;
	}
	
	private String addFuncaoFORALL(String condicao, String tipoLista, String listaNome){
		Funcao f = funcaoConsultaAtual;
		f.nome = "forAllTemp" + temp++;
		f.retornoTipo = "bool";
		f.parametros.add(new Parametro("lista", tipoLista));
		
		String code = f.nome + "(";
		for(String s : consultaValores){
			if(s.contains("atual.valor")){
				String id = s.substring(s.lastIndexOf(".") + 1);
				condicao = condicao.replace(id, s);
			}else{
				code += s + ",";
			}
		}
		code += "self." + listaNome + ")";
		
		
		f.corpo = "atual := lista.head\n";
		f.corpo += "for atual != nil {\n";
		f.corpo += "\tif !(" + condicao + "){\n";
		f.corpo += "\t\treturn false\n";
		f.corpo += "\t}\n";
		f.corpo += "\tatual = atual.next\n";
		f.corpo += "}\n";
		f.corpo += "return true";
		funcoes.put(f.nome, f);
		
		return code;
	}
	
	private String addFuncaoANY(String condicao, String tipoLista, String listaNome){
		Funcao f = funcaoConsultaAtual;
		f.nome = "anyTemp" + temp++;
		//t pois tem que ser depois de *List
		f.retornoTipo = type(tipoLista.substring(tipoLista.indexOf("t") + 1));
		f.parametros.add(new Parametro("lista", tipoLista));

		String code = f.nome + "(";
		for(String s : consultaValores){
			if(s.contains("atual.valor")){
				String id = s.substring(s.lastIndexOf(".") + 1);
				condicao = condicao.replace(id, s);
			}else{
				code += s + ",";
			}
		}
		code += "self." + listaNome + ")";
		
		
		f.corpo = "atual := lista.head\n";
		f.corpo += "for atual != nil {\n";
		f.corpo += "\tif !(" + condicao + "){\n";
		f.corpo += "\t\treturn atual.valor\n";
		f.corpo += "\t}\n";
		f.corpo += "\tatual = atual.next\n";
		f.corpo += "}\n";
		f.corpo += "return nil";
		funcoes.put(f.nome, f);
		
		return code;
	}
	
	
	public String addFuncaoCONSULTA(String funcao, Elemento parametro, Elemento lista){
		String code = "";
		if(funcao.equalsIgnoreCase("exists")){
			code = addFuncaoEXISTS(parametro.getAtributo("code"), type(lista.getAtributo("tipo")), lista.valor);
		}
		if(funcao.equalsIgnoreCase("forAll")){
			code = addFuncaoFORALL(parametro.getAtributo("code"), type(lista.getAtributo("tipo")), lista.valor);
		}
		if(funcao.equalsIgnoreCase("any")){
			code = addFuncaoANY(parametro.getAtributo("code"), type(lista.getAtributo("tipo")), lista.valor);
		}
		funcaoConsultaAtual = null;
		return code;
	}
	
	public void addFuncao(String classe, String nome, LinkedList<Parametro> parametros, String retornoTipo){
		Funcao f = new Funcao(nome, retornoTipo);
		classes.get(classe).funcoes.put(nome, f);
		f.parametros = new LinkedList<Parametro>();
		for(Parametro p : parametros){
			f.parametros.add(new Parametro(p.nome, typeGO(p.tipo) ? p.tipo : type(p.tipo)));
		}
		f.tipoAssociado = classe;
		f.corpo = "return ";
		funcaoAtual = f;
	}
	
	
	public void print(){
		System.out.println(code);
	}
	
	private void gerarTudo(){
		for(Classe c : classes.values()){
			code += c.code() + "\n";
		}
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
	
	public static String literal(String t){
		if(t.length() > 1 && t.charAt(0) == '*'){
			t = t.substring(1);
		}
		if(t.equalsIgnoreCase("int"))
			return "0";
		if(t.equalsIgnoreCase("float"))
			return "0.0";
		if(t.equalsIgnoreCase("string"))
			return "\"\"";
		if(t.equalsIgnoreCase("bool"))
			return "false";
		if(t.isEmpty()){
			return "nil";
		}
		return "new (" + t + ")";
	}
	
	public static boolean typePrimitiveGO(String t){
		if(t.equalsIgnoreCase("int"))
			return true;
		if(t.equalsIgnoreCase("float"))
			return true;
		if(t.equalsIgnoreCase("string"))
			return true;
		if(t.equalsIgnoreCase("bool"))
			return true;
		return false;
	}
	
	public static boolean typeGO(String t){
		if(t.charAt(0) == '*'){
			return true;
		}
		return typePrimitiveGO(t);
	}
	
	public static boolean typePrimitiveOCL(String t){
		if(t.equalsIgnoreCase("Integer"))
			return true;
		if(t.equalsIgnoreCase("Real"))
			return true;
		if(t.equalsIgnoreCase("String"))
			return true;
		if(t.equalsIgnoreCase("Boolean"))
			return true;
		return false;
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
		if(Elemento.ehCollection(t)){
			return "*List" + Elemento.tipoCollection(t);
		}
		return "*" + t;
	}
	
	private LinkedList<Parametro> criarParametros(List<AtributoComp> att){
		LinkedList<Parametro> parametros = new LinkedList<Parametro>();
		for(AtributoComp a : att){
			String nome = a.getNome();
			String tipo = a.getTipo();
			if(Elemento.ehCollection(nome)){
				nome = Elemento.tipoCollection(nome) + "s";
			}
			parametros.add(new Parametro(nome, type(tipo)));
		}
		return parametros;
	}
	
	private void criarFuncoes(List<OperacaoComp> ope, String classe){
		for(OperacaoComp o : ope){
			LinkedList<Parametro> parametros = criarParametros(o.getParametros());
			String tipo = o.getTipo(); 
			tipo = tipo.isEmpty() ? "" : type(tipo);
			addFuncao(classe, o.getNome(), parametros, tipo);
		}
	}
	
	private void criarClasses(){
		List<ClasseComp> classes = XMIParserBasic.loadAllClassesComp();
		for(ClasseComp c : classes){
			Classe classe = new Classe(c.getNome());
			classe.atributos = criarParametros(c.getAtt());
			this.classes.put(classe.nome, classe);
			criarFuncoes(c.getOperacoes(), classe.nome);
		}
	}
	
}
