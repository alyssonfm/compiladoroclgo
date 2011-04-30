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

}
