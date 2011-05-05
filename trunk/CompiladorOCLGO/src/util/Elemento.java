package util;

import java.util.HashMap;

public class Elemento {
	
	public String valor;
	public int left;
	public int right;
	private HashMap<String, String> map;
	
	public Elemento() {
		map = new HashMap<String, String>();
	}
	
	public Elemento(String valor){
		this();
		this.valor = valor;
	}
	
	public Elemento(String valor, int left, int right) {
		this(valor);
		this.left = left + 1;
		this.right = right + 1;
	}
	
	public void setAtributo(String atributo, String valor) {
		map.put(atributo, valor);
	}
	
	public String getAtributo(String atributo){
		return map.get(atributo);
	}
	
	@Override
	public String toString() {
		return valor + "(" + left + "," + right + ")";
	}
	
	public static boolean tiposEquivalentes(String esperado, String passado){
		
		
		if(esperado.equalsIgnoreCase(passado)){
			return true;
		}
		
		if(ehCollection(esperado) && ehCollection(passado)){
			if(tiposEquivalentes(tipoCollection(esperado), tipoCollection(passado)))
				return true;
		}
		
		if(esperado.equalsIgnoreCase("Real") || passado.equalsIgnoreCase("Integer")){
			if(esperado.equalsIgnoreCase("Integer") || passado.equalsIgnoreCase("Real")){
				return true;
			}
		}
		
		return false;		
	}
	
	public static String tipoCollection(String collection){
		return collection.substring(collection.indexOf("(") + 1, collection.indexOf(")"));
	}
	
	public static boolean ehCollection(String id){
		if(id.contains("(")){
			id = id.substring(0, id.indexOf("("));
		}
		if(id.equalsIgnoreCase("Collection") || id.equalsIgnoreCase("Set") || id.equalsIgnoreCase("Bag") ||
				id.equalsIgnoreCase("Sequence") || id.equalsIgnoreCase("OrderedSet")){
			return true;
		}
		return false;
	}
	
	public static boolean ehOperacaoColecao(String id){
		if(id.equalsIgnoreCase("select") || id.equalsIgnoreCase("reject") ||
				id.equalsIgnoreCase("forAll") || id.equalsIgnoreCase("exists") ||
				id.equalsIgnoreCase("any") || id.equalsIgnoreCase("collect")){
			return true;
		}
		return false;
	}
	
	public static String max(String esperado, String passado) {
		
		if(tiposEquivalentes(esperado, passado)){
			
			if(esperado.equalsIgnoreCase(passado)){
				return esperado;
			}
			
			if(esperado == "Real" || passado == "Integer"){
				return "Real";
			}
			if(esperado == "Integer" || passado == "Real"){
				return "Integer";
			}
			
		}
		return null;
	}
	
	public static String coercao(String valor, String tipo, String novoTipo){
		if(tipo == "Integer" && novoTipo == "Real"){
			Integer temp = Integer.parseInt(valor);
			Double temp2 = temp.doubleValue();
			return temp2.toString();			
		}
		if(tipo == "Real" && novoTipo == "Integer"){
			Double temp = Double.parseDouble(valor);
			Integer temp2 = temp.intValue();
			return temp2.toString();			
		}
		return valor;
	}

}
