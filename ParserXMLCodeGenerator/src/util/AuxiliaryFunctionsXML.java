package util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.Generalization;
import com.pavelvlasov.uml.Model;

public class AuxiliaryFunctionsXML {

	public static String filterType(String type) {
		return type.replace(ConstantsXML.umlDotDot, "");
	}

	public static Boolean equalsNodeType(Element element, String type) {
		// System.out.println(element.getAttributeNode("xmi:type").getNodeValue());
		return element.getAttributeNode("xmi:type").getNodeValue()
				.equalsIgnoreCase(ConstantsXML.umlDotDot + type);
	}

	public static String getNodeType(Element element) {
		// System.out.println(element.getAttributeNode("xmi:type").getNodeValue());
		return filterType(element.getAttributeNode(ConstantsXML.xmiType)
				.getNodeValue());
	}

	public static String getNodeID(Element element) {
		// System.out.println(element.getAttributeNode("xmi:type").getNodeValue());
		return filterType(element.getAttributeNode(ConstantsXML.xmiID)
				.getNodeValue());
	}

	public static String getNodeName(Element element) {
		// System.out.println(element.getAttributeNode("xmi:type").getNodeValue());
		if (element.getAttributeNode(ConstantsXML.xmiName) != null) {
			return element.getAttributeNode(ConstantsXML.xmiName)
					.getNodeValue();
		} else {
			return "";
		}

	}

	public static String getNameByID(String id, Model model) {
		// System.out.println(element.getAttributeNode("xmi:type").getNodeValue());
		return model.findElement(id).getName();
	}

	public static List<String> collectionToList(Collection<Object> coll) {
		List<String> list = new LinkedList<String>();
		for (Object o : coll) {
			list.add(o.toString());
		}

		return list;
	}

	/*
	 * public static List<Generalization>
	 * collectionToList(Collection<Generalization> coll) { List<String> list =
	 * new LinkedList<String>(); for (Generalization o : coll) {
	 * list.add(o.toString()); }
	 * 
	 * return list; }
	 */

	public static String getTypeByClass(com.pavelvlasov.uml.Element element) {
		if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		} else if (element instanceof Association) {
			return Constants.ASSOCIATION;
		}
		return "";
	}

	public static String filterAttributeType(String type) {
		return type.replace("href=", "").replace(
				"http://schema.omg.org/spec/UML/2.0/uml.xml#", "").replace(
				"\"", "").replace(
				"http://schema.omg.org/spec/UML/2.2/uml.xml#", "");
	}
}
