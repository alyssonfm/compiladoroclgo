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
	
	public static boolean tiposEquivalentes(String tipo1, String tipo2){
		
		if(tipo1.equalsIgnoreCase(tipo2)){
			return true;
		}
		
		if(tipo1 == "Real" || tipo1 == "Integer"){
			if(tipo2 == "Real" || tipo2 == "Integer"){
				return true;
			}
		}
		
		return false;		
	}
	
	public static String max(String tipo1, String tipo2) {
		
		if(tiposEquivalentes(tipo1, tipo2)){
			
			if(tipo1.equalsIgnoreCase(tipo2)){
				return tipo1;
			}
			
			if(tipo1 == "Real" || tipo2 == "Real"){
				return "Real";
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
		return valor;
	}

}
