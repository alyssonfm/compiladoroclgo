package cvslogic;

import java.util.Collection;
import java.util.LinkedList;

import util.Constants;

import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Package;
import com.pavelvlasov.uml.Parameter;

public class ParserAuxiliar {

	public static String packageError = "Não existe o pacote especificado: ";
	public static String contexClassError = "Não existe a classe especificada: ";
	public static String operationError = "Não existe a operação especificada: ";
	public static String parameterError = "Não existe o parâmetro especificado: ";
	public static String returnTypeError = "Não existe o tipo de retorno especificado: ";
	public static Collection<Classifier> classes = new LinkedList<Classifier>();

	public static Classifier getClassByName(Collection<Object> classes,
			String className) {
		for (Object o : classes) {
			if (((Element) o).getName().equals(className)) {
				return (Classifier) o;
			}
		}
		return null;
	}

	public static Operation getOperationByName(Classifier classActual,
			String name) {
		for (Object o : classActual.getOperations()) {
			if (((Operation) o).getName().equals(name)) {
				return (Operation) o;
			}
		}

		return null;

	}

	public static Parameter getParameter(Operation operation, String name,
			String type) {
		for (Object ob : operation.getParameters()) {
			if (((Parameter) ob).getName().equals(name)
					&& ((Parameter) ob).getType().equals(type)) {
				return (Parameter) ob;
			}
		}
		return null;
	}

	public static String filterOperationType(Element element) {
		if (element.getType().startsWith("_")) {
			for (Object ob : classes) {
				if (((Classifier) ob).getId().equals(element.getType())) {
					return ((Classifier) ob).getName().replace("<", "(")
							.replace(">", ")");
				}
			}
		}
		return element.getType().replace("<", "(").replace(">", ")");
	}

	public static String filterType(String type) {
		if (type.startsWith("_")) {
			for (Object ob : classes) {
				if (((Classifier) ob).getId().equals(type)) {

					return ((Classifier) ob).getName().replace("<", "(")
							.replace(">", ")");
				}
			}
		}
		return type.replace("<", "(").replace(">", ")");
	}

	public static void setClasses(Model model) {
		for (Object p : model.getPackages()) {
			Package pack = ((Package) p);
			classes.addAll(pack.getElements(Constants.CLASS));
		}
		classes.addAll(model.getClasses());
	}

	public static String getAttributeType(String name,
			Collection<Attribute> attributes) {
		for (Object att : attributes) {
			if (((Attribute) att).getName().equals(name)) {
				if (((Attribute) att).getType().startsWith("Collection")) {
					return "Collection("
							+ filterType(((Attribute) att).getType()
									.substring(
											0,
											((Attribute) att).getType()
													.length() - 1).replace(
											"Collection(", "")) + ")";
				} else {
					return ((Attribute) att).getType();
				}

			}
		}

		return null;
	}
}
