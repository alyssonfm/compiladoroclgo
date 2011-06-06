package util;

public class Parametro {
	
	public String nome;
	public String tipo;
	
	public Parametro(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return nome + " " + tipo;
	}

}
