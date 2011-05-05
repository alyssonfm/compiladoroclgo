package util;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import cvslogic.XMIParser;

public class XMIParserBasic extends XMIParser {
	
	private static XMIParserBasic instancia;
	private Stack<String> context;
	private boolean navegando;
	
	private XMIParserBasic() {
		super();
		context = new Stack<String>();
		navegando = false;
	}
	
	public static XMIParserBasic getInstancia(){
		if(instancia == null){
			instancia = new XMIParserBasic();
			instancia.loadModel("files//in//Profe2.xml");
		}
		return instancia;
	}
	
	public void setTempContext(String context){
		if(super.getSuperType(context, context) != null){
			this.context.push(context);
		}
	}
	
	public void ativarNavegando(){
		if(!context.empty())
			navegando = true;
	}
	
	public void desativarNavegando(){
		if(!context.empty()){
			context.pop();
		}
		navegando = false;
	}
	
	public boolean isNavegando(){
		return navegando;
	}
	
	@Override
	public String getAttributeType(String identifier) {
		if(isNavegando()){
			return super.getAttributeType(context.lastElement(), identifier);
		}
		return super.getAttributeType(identifier);
	}
	
	@Override
	public String getOperationType(String identifier) {
		if(identifier.equalsIgnoreCase("select") || identifier.equalsIgnoreCase("reject")){
			return "Collection(Source)";
		}
		if(identifier.equalsIgnoreCase("forAll") || identifier.equalsIgnoreCase("exists")){
			return "Boolean";
		}
		if(identifier.equalsIgnoreCase("any")){
			return "Source";
		}
		return super.getOperationType(identifier);
	}
	
	@Override
	public List<String> getParametersType(String operation) {
		LinkedList<String> par = new LinkedList<String>();
		if(operation.equalsIgnoreCase("select") || operation.equalsIgnoreCase("reject") ||
				operation.equalsIgnoreCase("forAll") || operation.equalsIgnoreCase("exists") ||
				operation.equalsIgnoreCase("any")){
			par.add("Boolean");
			return par;
		}
		return super.getParametersType(operation);
	}

}