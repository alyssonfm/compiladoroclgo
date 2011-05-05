package cvslogic;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import util.Constants;
import util.ConstantsXML;

import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.CompositeAcceptor;
import com.pavelvlasov.uml.Element;

import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.Package;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Parameter;
import com.pavelvlasov.uml.eval.PrefixedCompositeEvaluator;
import com.pavelvlasov.uml.xmi.ModelBuilder;
import com.pavelvlasov.uml.xmi.PrintStreamLogger;

public class XMIParser {
	private Model model;
	private ConstraintPackage constraintPackage;
	private ConstraintBody constraintBody = new ConstraintBody();
	private ConstraintContext cc;
	private String errorActual = "";
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
		ParserAuxiliar.setClasses(model);
	}

	public Model getModel() {
		return model;
	}

	public void setContext(String context) {
		cc = new ConstraintContext();
		cc.setContext(context);
	}

	public static void main(String[] args) {
		XMIParser parser = new XMIParser();
		parser.loadModel("Modelos/profe.xml");
		parser.setPackage("");
		System.out.println(parser.existsPackage());
		System.out.println(parser.getError());
		parser.setContext("Cliente::idade():Integer");
		System.out.println(parser.existsContext());
		System.out.println(parser.getError());
		parser.getAttributeType("ProgramaFidelidade", "socio");
		parser.getOperationType("ProgramaFidelidade", "obtemServicos");
		// parser.getOperationType("getMulta");
		System.out.println(parser.getParametersType("ProgramaFidelidade",
				"obtemServicos"));
		// parser.getSuperType("Transacao", "Cheque");

	}

	public Package getPackageActual() {
		return this.constraintPackage.getPack();
	}

	public boolean existsPackage() {
		Collection<Object> packages = this.model.getPackages();
		Package pack = null;

		for (String path : this.constraintPackage.getPackage()) {
			pack = getPackage(path, packages);
			if (pack != null) {
				packages = pack.getElements(Constants.PACKAGE);
			} else {
				setError(ParserAuxiliar.packageError
						+ constraintPackage.toString());
				return false;
			}
		}
		this.constraintPackage.setPackage(pack);
		return true;
	}

	private Package getPackage(String name, Collection<Object> packs) {
		for (Object pack : packs) {
			if (((Package) pack).getName().equals(name)) {
				return (Package) pack;
			}
		}
		return null;
	}

	private void setPackage(String string) {
		constraintPackage = new ConstraintPackage();
		constraintPackage.setCaminho(string);

	}

	public String getSuperType(String type, String typeReduz) {
		return ParserAuxiliar.getSuperType(type, typeReduz);
	}

	public static Classifier getBodyClass() {
		return bodyClass;
	}

	public String getError() {
		return errorActual;
	}

	public void setError(String error) {
		this.errorActual = error + " !";
	}

	private boolean notExistPackage() {
		return this.constraintPackage.getPackage().size() == 0;
	}

	public boolean existsContext() {

		if (existsPackage() || notExistPackage()) {
			Classifier classActual;
			if (notExistPackage()) {
				classActual = ParserAuxiliar.getClassByName(cc
						.getContextClass());
			} else {
				classActual = ParserAuxiliar.getClassByName(cc
						.getContextClass(), getPackageActual());
			}

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

	public List<String> getParametersType(String operation) {
		if (getOperationType(operation) == null) {
			return null;
		}
		List<String> parameters = new LinkedList<String>();
		String[] idPath = operation.split(ConstantsXML.DOUBLE_DOT_DOT);
		if (idPath.length == 1) {
			parameters = ParserAuxiliar.getOperationParameters(operation,
					getBodyClass().getOperations());
		}
		return parameters;

	}

	public List<String> getParametersType(String className, String operation) {
		if (getOperationType(className, operation) == null) {
			return null;
		}
		return ParserAuxiliar.getOperationParameters(operation, className,
				this.constraintPackage.getPack());

	}

	public String getOperationType(String className, String operation) {
		return ParserAuxiliar.getOperationType(operation, className,
				this.constraintPackage.getPack());
	}

	public String getOperationType(String identifier) {
		String[] idPath = identifier.split(ConstantsXML.DOUBLE_DOT_DOT);
		if (idPath.length == 1) {
			return ParserAuxiliar.getOperationType(identifier, getBodyClass());
		}
		return null;
	}

	public String getAttributeType(String className, String att) {
		return ParserAuxiliar.getAttributeType(att, className,
				this.constraintPackage.getPack());
	}

	public String getAttributeType(String identifier) {
		String[] idPath = identifier.split(ConstantsXML.DOUBLE_DOT_DOT);
		String idName;
		String idClass;
		List<String> caminho = new LinkedList<String>();
		idName = (idPath[0]).replace("self.", "");

		if (idPath.length == 1) {
			if (idName.equals("self")) {
				return getBodyClass().getName();
			}
			idName = idName.replace("self.", "");
			return ParserAuxiliar.getAttributeType(idName, getBodyClass());
		} else if (idPath.length == 2) {
			idClass = idPath[1];

		} else if (idPath.length > 2) {

		}
		return null;
	}
}
