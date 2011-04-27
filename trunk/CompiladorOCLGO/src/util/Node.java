package util;

public class Node {

	private Object valor;
	private Tipo tipo;
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	public Object getValor() {
		return valor;
	}
	
	
}
