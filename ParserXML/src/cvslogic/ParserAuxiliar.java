package cvslogic;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import util.Constants;

import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Generalization;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Package;
import com.pavelvlasov.uml.Parameter;

public class ParserAuxiliar {

	public static String packageError = "Não existe o caminho especificado: ";
	public static String contexClassError = "Não existe a classe especificada: ";
	public static String operationError = "Não existe a operação especificada: ";
	public static String parameterError = "Não existe o parâmetro especificado: ";
	public static String returnTypeError = "Não existe o tipo de retorno especificado: ";
	public static Collection<Classifier> classes = new LinkedList<Classifier>();
	private static Collection<String> generalizations = new LinkedList<String>();

	public static Classifier getClassByName(String className) {
		for (Object o : classes) {
			if (((Element) o).getOwner() != null) {
				if (!((Element) o).getOwner().getName().equals("")) {
					if (((Element) o).getName().equals(className)) {
						return (Classifier) o;
					}
				}
			}
		}
		return null;
	}

	public static Classifier getClassByName(String className, Package pack) {
		for (Object o : classes) {
			if (pack != null) {
				if (!pack.getName().equals("")) {
					if (((Element) o).getName().equals(className)
							&& ((Element) o).getOwner().getName().equals(
									pack.getName())) {
						return (Classifier) o;
					}
				} else if (((Element) o).getName().equals(className)) {
					return (Classifier) o;
				}
			}
		}
		return null;
	}

	public static Classifier getClassById(String id) {
		for (Object o : classes) {
			if (((Element) o).getId().equals(id)) {
				return (Classifier) o;
			}
		}
		return null;
	}

	public static Operation getOperationByName(Classifier classActual,
			String name) {
		Collection<Operation> operations = classActual.getOperations();
		Operation op = getOperation(name, operations);
		if (op == null) {
			operations = new LinkedList<Operation>();
			for (String classe : getGeneralizations(classActual)) {
				Classifier classAux = getClassByName(classe);
				operations.addAll(classAux.getOperations());
			}
			op = getOperation(name, operations);
			if (op != null) {
				if (!op.getVisibility().equals("private")
						&& !op.getVisibility().equals("package")) {
					return op;
				}
			}
		} else if (op != null) {
			return op;
		}
		return null;
	}

	public static Parameter getParameter(Operation operation, String name,
			String type) {
		for (Object ob : operation.getParameters()) {
			if (((Parameter) ob).getName().equals(name)
					&& filterType(((Element) ob).getType()).equals(
							filterType(type))) {
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
		classes.clear();
		for (Object p : model.getPackages()) {
			Package pack = ((Package) p);
			classes.addAll(pack.getClasses());
		}
		classes.addAll(model.getClasses());
	}

	public static List<String> getOperationParameters(String name,
			Collection<Operation> operations) {
		Operation op = getOperation(name, operations);
		List<String> out = new LinkedList<String>();
		if (op != null) {
			for (Object param : op.getParameters()) {
				out.add(formatType(((Element) param)));
			}
		}
		return out;
	}

	private static Operation getOperation(String name,
			Collection<Operation> operations) {
		for (Object op : operations) {
			if (((Operation) op).getName().equals(name)) {
				return (Operation) op;
			}
		}
		return null;
	}

	private static String formatType(Element element) {
		if (((Element) element).getType().startsWith("Collection")) {
			return "Collection("
					+ filterType(((Element) element).getType().substring(0,
							((Element) element).getType().length() - 1)
							.replace("Collection(", "")) + ")";

		} else {
			return filterType(((Element) element).getType());
		}
	}

	public static List<String> getOperationParameters(String name,
			String classeName, Package pack) {
		Classifier classe = getClassByName(classeName, pack);
		if (classe != null) {
			return getOperationParameters(name, classe.getOperations());
		}

		return null;
	}

	public static String getOperationType(String name, String classeName,
			Package pack) {
		Classifier classe = getClassByName(classeName, pack);
		if (classe != null) {
			Operation op = getOperationByName(classe, name);
			if (op != null) {
				return formatType((Element) op);
			}
		}
		return null;
	}

	public static String getAttributeType(String name, String classeName,
			Package pack) {
		Classifier classe = getClassByName(classeName, pack);
		if (classe != null) {
			Attribute att = getAttribute(name, classe.getAttributes());
			if (att != null) {
				return formatType((Element) att);
			}
		}
		return null;
	}

	public static String getOperationType(String name, Classifier classe) {
		Operation op = getOperationByName(classe, name);
		if (op != null) {
			return formatType((Element) op);
		}
		return null;
	}

	private static Attribute getAttribute(String name,
			Collection<Attribute> attributes) {
		for (Object att : attributes) {
			// System.out.println("aqui: " + ((Attribute)
			// att).getName().equals(""));
			if (((Attribute) att).getName().equals(name)) {
				return (Attribute) att;
			} else if (((Attribute) att).getName().equals("")) {
				Classifier classAux = getClassById(((Attribute) att)
						.getType());
				if (classAux != null) {
					if (classAux.getName().equals(name)) {
						return (Attribute) att;
					}
				}
			}
		}
		return null;
	}

	public static String getAttributeType(String name, Classifier classifier) {
		Collection<Attribute> attributes = classifier.getAttributes();
		Attribute att = getAttribute(name, attributes);
		if (att == null) {
			attributes = new LinkedList<Attribute>();
			for (String classe : getGeneralizations(classifier)) {
				Classifier classAux = getClassByName(classe);
				attributes.addAll(classAux.getAttributes());
			}
			att = getAttribute(name, attributes);
			if (att != null) {
				if (!att.getVisibility().equals("private")
						&& !att.getVisibility().equals("package")) {
					return formatType((Element) att);
				}
			}
		} else if (att != null) {
			return formatType((Element) att);
		}
		return null;
	}

	public static String getSuperType(String type, String typeReduz) {
		Classifier classReduz = getClassByName(typeReduz);

		if (classReduz != null) {
			if (classReduz.getName().equals(type)) {
				return classReduz.getName();
			} else if (getGeneralizations(classReduz).size() > 0) {
				for (String classAux : getGeneralizations(classReduz)) {
					if (classAux.equals(type)) {
						return classAux;
					}
				}
			}
		}
		return null;

	}

	public static Collection<String> getGeneralizations(Classifier classAux) {
		generalizations.clear();
		addSuperType(classAux);
		return generalizations;

	}

	private static void addSuperType(Classifier classAux) {
		if (classAux != null) {
			for (Object ob : classAux.getGeneralizations()) {
				if (!generalizations.contains(((Generalization) ob)
						.getSupertype())) {
					Classifier aux = getClassById(((Generalization) ob)
							.getSupertype());
					generalizations.add(aux.getName());
					addSuperType(aux);
				}
			}
		}
	}
}