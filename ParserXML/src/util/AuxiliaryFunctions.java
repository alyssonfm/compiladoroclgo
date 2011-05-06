package util;

import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Generalization;

public class AuxiliaryFunctions {

	public static boolean containsEntity(Element[] array, Element element) {
		for (Element e : array) {
			if (e.getId() == element.getId()) {
				return true;
			}
		}
		return false;
	}

	public static Element getByID(Object[] array, Object element) {
		for (Object e : array) {
			if (((Element) e).getId().equals(((Element) element).getId())) {
				return (Element) e;
			}
		}
		return null;
	}

	public static void print(String type, Boolean value, String string) {
		System.out.println("Attribute " + type + " - Equals: " + value + " - "
				+ string);
	}

	public static String concatenates(Object[] elements) {
		StringBuilder out = new StringBuilder();
		for (Object object : elements) {
			out.append(((Element) object).getName() + "; ");
		}
		return out.toString();
	}

	public static String getSupertypes(Object[] generalizations) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < generalizations.length; i++) {
			out.append(((Generalization) generalizations[i]).toString() + "; ");
		}
		return out.toString();
	}


}
