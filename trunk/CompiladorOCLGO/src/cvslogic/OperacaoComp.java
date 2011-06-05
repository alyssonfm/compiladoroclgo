package cvslogic;

import java.util.LinkedList;
import java.util.List;

public class OperacaoComp {

	private String nome;
	private String tipo;
	private List<AtributoComp> parametros = new LinkedList<AtributoComp>();

	public OperacaoComp(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public String getTipo() {
		return this.tipo;
	}

	public List<AtributoComp> getParametros() {
		return parametros;
	}

	public void addParametro(AtributoComp att) {
		this.parametros.add(att);
	}

	public String toString() {
		return "(Nome: " + this.nome + " Tipo: " + this.tipo + " Parametros: "
				+ this.parametros + ")";
	}
}
