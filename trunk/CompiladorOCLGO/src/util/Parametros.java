package util;

import java.util.LinkedList;

public class Parametros {
	
	public LinkedList<String> lista;
	
	public Parametros() {
		lista = new LinkedList<String>();
	}
	
	public void push(String tipo){
		lista.addFirst(tipo);
	}
	
	public boolean parametrosValidos(LinkedList<String> lista2){
		if(lista.size() != lista2.size()){
			return false;
		}
		int tamanho = lista.size();
		for(int i = 0; i < tamanho; i++){
			if(!Elemento.tiposEquivalentes(lista.get(i), lista2.get(i))){
				return false;
			}
		}
		return true;
	}

}
