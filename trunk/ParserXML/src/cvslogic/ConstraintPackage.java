package cvslogic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import util.ConstantsXML;

public class ConstraintPackage {
	//Cada indice representará os pacotes em sequência do package;
	private List<String> caminho = new LinkedList<String>();
	
	public void setCaminho (String pack){
		this.caminho = filterContext (pack);
	}
	
	private List<String> filterContext(String pack){
		return Arrays.asList(pack.split(ConstantsXML.DOUBLE_DOT_DOT));
	}
	
	public List<String> getPackage (){
		return this.caminho;
	}
	
	public static void main(String[] args) {
		ConstraintPackage cp = new ConstraintPackage();
		cp.setCaminho("Behavioral::Activity::Torada");
		System.out.println(cp.getPackage());
	}
	
	
	
}
