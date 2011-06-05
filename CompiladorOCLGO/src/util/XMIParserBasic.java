package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import cvslogic.XMIParser;

public class XMIParserBasic extends XMIParser {
	
	private static XMIParserBasic instancia;
	public Stack<String> context;
	private boolean navegando;
	private static String path;
	private HashMap<String, String> variaveis;
	
	private XMIParserBasic() {
		super();
		context = new Stack<String>();
		navegando = false;
		variaveis = new HashMap<String, String>();
	}
	
	public static void setPath(String path){
		XMIParserBasic.path = path;
	}
	
	public static String getPath() {
		return path;
	}
	
	public static XMIParserBasic getInstancia(){
		if(instancia == null){
			instancia = new XMIParserBasic();
			instancia.loadModel(path);
		}
		return instancia;
	}
	
	public static XMIParserBasic getNewInstancia(){
		instancia = null;
		return getInstancia();
	}
	
	public void setTempContext(String context){
		if(super.getSuperType(context, context) != null){
			this.context.push(context);
		}
	}
	
	public void ativarNavegando(){
		if(!context.empty())
			navegando = true;
	}
	
	public void desativarNavegando(String classe){
		if(!context.empty() && context.lastElement().equalsIgnoreCase(classe)){
			context.pop();
		}if(context.empty())
			navegando = false;
	}
	
	public boolean isNavegando(){
		return navegando;
	}
	
	public boolean addVariavel(String id, String tipo){
		if(!variaveis.containsKey(id)){
			variaveis.put(id, tipo);
			return true;
		}
		return false;
	}
	
	public boolean addVariavel(LinkedList<String> lista, String tipo){
		boolean retorno = true;
		for(String i : lista){
			if(!addVariavel(i, tipo)){
				retorno = false;
			}
		}
		return retorno;
	}
	
	public boolean containsVariavel(String id){
		return variaveis.containsKey(id);
	}
	
	public void deleteVariavel(String id){
		variaveis.remove(id);
	}
	
	public void deleteAllVariaveis(){
		variaveis = new HashMap<String, String>();
	}
	
	public void deleteVariavel(LinkedList<String> lista){
		for(String i : lista){
			deleteVariavel(i);
		}
	}
	
	@Override
	public String getAttributeType(String identifier) {
		if(variaveis.containsKey(identifier)){
			return variaveis.get(identifier);
		}
		if(isNavegando()){
			return super.getAttributeType(context.lastElement(), identifier);
		}
		return super.getAttributeType(identifier);
	}
	
	@Override
	public String getOperationType(String identifier) {
		if(identifier.equalsIgnoreCase("select") || identifier.equalsIgnoreCase("reject")){
			return "Collection(" + context.lastElement() + ")";
		}
		if(identifier.equalsIgnoreCase("forAll") || identifier.equalsIgnoreCase("exists")){
			return "Boolean";
		}
		if(identifier.equalsIgnoreCase("any")){
			return context.lastElement();
		}
		if(isNavegando()){
			return super.getOperationType(context.lastElement(), identifier);
		}
		return super.getOperationType(identifier);
	}
	
	@Override
	public List<String> getParametersType(String operation) {
		LinkedList<String> par = new LinkedList<String>();
		if(operation.equalsIgnoreCase("select") || operation.equalsIgnoreCase("reject") ||
				operation.equalsIgnoreCase("forAll") || operation.equalsIgnoreCase("exists") ||
				operation.equalsIgnoreCase("any")){
			par.add("Boolean");
			return par;
		}
		if(isNavegando()){
			return super.getParametersType(context.lastElement(), operation);
		}
		return super.getParametersType(operation);
	}
	
}
