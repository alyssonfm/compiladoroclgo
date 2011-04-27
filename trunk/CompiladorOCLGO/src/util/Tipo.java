package util;

public class Tipo{
	
	private String nome;
	private Tipo pai;
	
	public Tipo(String nome, Tipo pai){
		this.nome = nome;
		this.pai = pai;
	}

	public Tipo(String name) {
		this.nome = name;
		this.pai = null;
	}
	
	public boolean hasPai(){
		return this.pai != null;
	}
	
	public Tipo getPai() {
		return pai;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPai(Tipo pai) {
		this.pai = pai;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Tipo){
			if( (this.nome == ((Tipo) obj).getNome()) && this.pai.equals(((Tipo) obj).getPai())){
				return true;
			}
		}
		return false;
	}
}
