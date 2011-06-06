package util;

import java.util.LinkedList;

public class Parametros {
	
	public LinkedList<String> lista;
	public LinkedList<Elemento> elementos;
	
	public Parametros() {
		lista = new LinkedList<String>();
		elementos = new LinkedList<Elemento>();
	}
	
	public Elemento getFirstElemento(){
		return elementos.get(0);
	}
	
	public void addElemento(Elemento e){
		elementos.addFirst(e);
	}
	
	public void push(String tipo){
		lista.addFirst(tipo);
	}
	
	public String tipoParametrosIguais(){
		int tamanho = lista.size();
		for(int i = 0; i < tamanho - 1; i++){
			if(!lista.get(i).equalsIgnoreCase(lista.get(i + 1))){
				return null;
			}
		}
		return lista.get(0);
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
