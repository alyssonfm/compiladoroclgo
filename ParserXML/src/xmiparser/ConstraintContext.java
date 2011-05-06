package xmiparser;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

import util.ConstantsXML;

public class ConstraintContext {
	private List<String> caminho = new LinkedList<String>();
	private String nomeOperacao;
	private TreeMap<String, String> parametros = new TreeMap<String, String>();
	private List<String> keys = new LinkedList<String>();
	private String tipoRetorno;
	private String contextClass;

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

	public static void main(String[] args) {
		ConstraintContext cc = new ConstraintContext();
		cc
				.setContext("ProgramaFidelidade::getOperacao(torada:Person, oi:String, op:Integer):Sequence(Servicos)");
		System.out.println(cc.getCaminho());
		System.out.println(cc.getContextClass());
		System.out.println(cc.getOperacao());
		System.out.println(cc.getParameters());
		System.out.println(cc.getReturnType());

	}
}
