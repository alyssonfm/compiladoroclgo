package cvslogic;

public class AtributoComp {

	private String nome;
	private String tipo;

	public AtributoComp(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public String getTipo() {
		return this.tipo;
	}

	public String toString() {
		return "(Nome: " + this.nome + " - Tipo: " + this.tipo +")";
	}

}
