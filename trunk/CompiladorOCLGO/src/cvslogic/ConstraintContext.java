package cvslogic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import util.xmi.ConstantsXML;

public class ConstraintContext {
	private List<String> caminho = new LinkedList<String>();
	private String nomeOperacao = null;
	private TreeMap<String, String> parametros = new TreeMap<String, String>();
	private List<String> keys = new LinkedList<String>();
	private String tipoRetorno = null;
	private String contextClass = null;

	public void setContext(String pack) {
		setCaminho(pack);
		setContextClass(pack);
		this.tipoRetorno = findReturnType(pack);
		this.nomeOperacao = findOperacao(pack);
		this.parametros = findParameters(pack);
	}

	private void setCaminho(String pack) {
		if (findCaminho(pack).size() > 1) {
			this.caminho = findCaminho(pack).subList(0,
					findCaminho(pack).size() - 2);
		}
	}

	private void setContextClass(String pack) {
		if (findCaminho(pack).size() > 1) {
			this.contextClass = findCaminho(pack).get(
					findCaminho(pack).size() - 2);
		}
	}

	public List<String> getCaminho() {
		return this.caminho;
	}

	private List<String> findCaminho(String pack) {
		if (pack.indexOf("(") != -1) {
			return Arrays.asList(pack.substring(0, pack.indexOf("(")).split(
					ConstantsXML.DOUBLE_DOT_DOT));
		}
		return new LinkedList<String>();
	}

	/*
	 * private int getIndexOperation(String pack) {
	 * System.out.println(findCaminho(pack)); String lastElement =
	 * findCaminho(pack) .get(findCaminho(pack).size() - 1) +
	 * ConstantsXML.DOUBLE_DOT_DOT; return pack.indexOf(lastElement) +
	 * lastElement.length(); }
	 */

	private String findOperacao(String pack) {
		return findCaminho(pack).get(findCaminho(pack).size() - 1);
	}

	private String findReturnType(String pack) {
		if (pack.lastIndexOf(ConstantsXML.DOT_DOT) != -1) {
			if (pack.charAt(pack.lastIndexOf(ConstantsXML.DOT_DOT) - 1) == ')') {
				return pack
						.substring(pack.lastIndexOf(ConstantsXML.DOT_DOT) + 1);
			}
		}
		return "";

	}

	public String getReturnType() {
		return this.tipoRetorno;
	}

	public String getOperacao() {
		return this.nomeOperacao;
	}

	public TreeMap<String, String> findParameters(String pack) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		int rightIndex = pack.length() - 1;
		if (!this.tipoRetorno.equals("")) {
			rightIndex = pack.length()
					- (new StringBuffer(pack).reverse().toString())
							.indexOf(ConstantsXML.DOT_DOT) - 2;
		}
		String aux = pack.substring(pack.indexOf(this.nomeOperacao)
				+ this.nomeOperacao.length() + 1, rightIndex);
		if (aux.split(",").length > 0) {
			for (String parameter : aux.split(",")) {
				if (!parameter.replace(" ", "").equals("")) {
					String name = parameter
							.substring(0, parameter.indexOf(":"));
					String type = parameter
							.substring(parameter.indexOf(":") + 1);
					keys.add(name);
					map.put(name, type);
				}
			}
		}
		return map;
	}

	public TreeMap<String, String> getParameters() {
		return this.parametros;
	}

	public String getContextClass() {
		return this.contextClass;
	}

	public String pathToString() {
		String out = "";
		if (this.caminho.size() == 1) {
			out += caminho.get(0);
		}
		if (caminho.size() > 1) {
			out += caminho.get(0);
			for (int i = 1; i < caminho.size(); i++) {
				out += "::" + caminho.get(i);
			}
		}
		return out;
	}

	public static void main(String[] args) {
		ConstraintContext cc = new ConstraintContext();
		String identifier = "torada";
		if (!identifier.contains("(")) {
			identifier += "()";
		}
		cc.setContext("PacoteDaTorada::getTorada(torada:Cheque):Torada");
		System.out.println("caminho: " + cc.getCaminho());
		System.out.println("class: " + cc.getContextClass());
		System.out.println("operacao: " + cc.getOperacao());
		System.out.println("param: " + cc.getParameters());
		System.out.println("return: " + cc.getReturnType());

	}
}
