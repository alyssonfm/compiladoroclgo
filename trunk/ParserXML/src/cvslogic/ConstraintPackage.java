package cvslogic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.pavelvlasov.uml.Package;

import util.ConstantsXML;

public class ConstraintPackage {
	// Cada indice representará os pacotes em sequência do package;
	private List<String> caminho = new LinkedList<String>();
	private Package pack;

	public void setCaminho(String pack) {
		if (pack != null) {
			if (!pack.equals("")) {
				this.caminho = filterContext(pack);
			}
		}

	}

	public Package getPack() {
		return this.pack;
	}

	public String toString() {
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

	private List<String> filterContext(String pack) {
		return Arrays.asList(pack.split(ConstantsXML.DOUBLE_DOT_DOT));
	}

	public List<String> getPackage() {
		return this.caminho;
	}

	public static void main(String[] args) {
		ConstraintPackage cp = new ConstraintPackage();
		cp.setCaminho("Behavioral::Activity::Torada");
		System.out.println(cp.getPackage());
	}

	public void setPackage(Package pack) {
		this.pack = pack;

	}

}
