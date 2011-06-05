package cvslogic;

import java.util.LinkedList;
import java.util.List;

public class ClasseComp {

	private String nome;
	private List<OperacaoComp> operacoes = new LinkedList<OperacaoComp>();
	private List<AtributoComp> atributos = new LinkedList<AtributoComp>();

	public ClasseComp(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public List<OperacaoComp> getOperacoes() {
		return this.operacoes;
	}

	public List<AtributoComp> getAtt() {
		return this.atributos;
	}

	public void addOperacao(OperacaoComp operacao) {
		this.operacoes.add(operacao);
	}

	public void addAtributo(AtributoComp atributo) {
		this.atributos.add(atributo);
	}

	public String toString() {
		return "Classe -> Nome: " + this.nome + " ## Atributos: " + this.atributos
				+ " ## Operacoes: " + this.operacoes + "\n";
	}

	public boolean temAtributo(String nome) {
		for (AtributoComp att : this.atributos) {
			if (att.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean temOperacao(String nome) {
		for (OperacaoComp att : this.operacoes) {
			if (att.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
}
