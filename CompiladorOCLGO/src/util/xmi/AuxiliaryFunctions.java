package util.xmi;

import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Generalization;
import com.pavelvlasov.uml.xmi.ExtendsImpl;
import com.pavelvlasov.uml.xmi.ExtensionPointImpl;
import com.pavelvlasov.uml.xmi.IncludeImpl;

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

	public static String getIncludedElements(Object[] includes) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < includes.length; i++) {
			out.append(((IncludeImpl) includes[i]).toString() + "; ");
		}
		return out.toString();
	}

	public static String getExcludedElements(Object[] excludes) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < excludes.length; i++) {
			out.append(((ExtendsImpl) excludes[i]).getId().toString() + "; ");
		}
		return out.toString();
	}

	public static String getExtensionPoints(Object[] points) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < points.length; i++) {
			out.append(((ExtensionPointImpl) points[i]).toString() + "; ");
		}
		return out.toString();
	}
}
