package cvslogic;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import util.ConstantsXML;

import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.CompositeAcceptor;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Parameter;
import com.pavelvlasov.uml.eval.PrefixedCompositeEvaluator;
import com.pavelvlasov.uml.xmi.ModelBuilder;
import com.pavelvlasov.uml.xmi.PrintStreamLogger;

public class XMIParser {
	private static Model model;
	private static ConstraintPackage constraintPackage = new ConstraintPackage();
	private static ConstraintContext constraintContext = new ConstraintContext();
	private static ConstraintBody constraintBody = new ConstraintBody();
	private static String errorActual = "";
	// private static Package bodyPack = null;
	private static Classifier bodyClass = null;

	// private static Operation bodyOperation = null;

	private File getModelFile(String path) {
		File xmiFile = new File(path);
		return xmiFile;
	}

	public void loadModel(String path) {
		this.model = ModelBuilder.loadModel(getModelFile(path),
				new CompositeAcceptor(), new PrintStreamLogger(),
				new PrefixedCompositeEvaluator());

	}

	public Model getModel() {
		return model;
	}

	public static void main(String[] args) {
		XMIParser parser = new XMIParser();
		parser.loadModel("Modelos/Profe.xml");
		ParserAuxiliar.setClasses(model);
		ConstraintContext cc = new ConstraintContext();
		cc.setContext("ProgramaFidelidade::obtemServicos():Set(Servicos)");
		parser.exists(cc);
		if (parser.getType("socio") != null) {
			System.out.println(ParserAuxiliar.filterType(parser
					.getType("socio")));
		}
		// parser.getModel().getPackages()
		/*
		 * for (Object o : parser.getModel().getClasses().toArray()) {
		 * Classifier classifier = (Classifier) o; System.out.println("Class: "
		 * + classifier.getName()); if (classifier.getOperations().size() > 0) {
		 * for (Object ob : classifier.getOperations().toArray()) { Operation
		 * att = (Operation) ob; String out = att.getName() + " " +
		 * att.getType(); System.out.println(out); }
		 * 
		 * }
		 * 
		 * }
		 */

		// Classifier classifier = ((Classifier) parser.getModel().getClasses()
		// .toArray()[0]);
		// classifier.getAbsoluteName();

	}

	public static Classifier getBodyClass() {
		return bodyClass;
	}

	public static String getError() {
		return errorActual;
	}

	public void setError(String error) {
		this.errorActual = error + " !";
	}

	public boolean exists(ConstraintContext cc) {

		if (cc.getCaminho().size() == 0) {
			Classifier classActual = ParserAuxiliar.getClassByName(model
					.getClasses(), cc.getContextClass());
			if (classActual == null) {
				setError(ParserAuxiliar.contexClassError + cc.getContextClass());
				return false;
			} else {
				setBodyClass(classActual);
				Operation operation = ParserAuxiliar.getOperationByName(
						classActual, cc.getOperacao());
				if (operation == null) {
					setError(ParserAuxiliar.operationError + cc.getOperacao());
					return false;
				} else {
					if (cc.getParameters().size() > 0) {
						for (String param : cc.getParameters().keySet()) {
							Parameter parameter = ParserAuxiliar.getParameter(
									operation, param, cc.getParameters().get(
											param));
							if (parameter == null) {
								setError(ParserAuxiliar.parameterError + param);
								return false;
							}
						}
					}
					if (!cc.getReturnType().equals(
							ParserAuxiliar
									.filterOperationType((Element) operation))) {
						setError(ParserAuxiliar.returnTypeError
								+ cc.getReturnType());
						return false;
					}
				}
			}
		}

		return true;
	}

	private void setBodyClass(Classifier classActual) {
		this.bodyClass = classActual;
	}

	public static String getType(String identifier) {
		String[] idPath = identifier.split(ConstantsXML.DOUBLE_DOT_DOT);
		String idName;
		String idClass;
		List<String> caminho = new LinkedList<String>();
		if (idPath.length == 1) {
			idName = (idPath[0]).replace("self.", "");
			return ParserAuxiliar.getAttributeType(idName, getBodyClass()
					.getAttributes());
		}
		return null;
	}
}
