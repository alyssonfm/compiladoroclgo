package cvslogic;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import util.ConstantsXML;

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
	private Model model;
	private ConstraintPackage constraintPackage = new ConstraintPackage();
	private ConstraintContext constraintContext = new ConstraintContext();
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
		parser
				.setContext("Conta::estaVazia(nome:String,lista:Sequence<Cartao>):Boolean");
		System.out.println(parser.existsContext());
		System.out.println(parser.getError());
		parser.getOperationType("estaVazia");
		parser.getParametersType("estaVazia");
		parser.getSuperType("Transacao", "Cheque");
		parser.getAttributeType("pontos");

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

	public boolean existsContext() {

		if (cc.getCaminho().size() == 0) {
			Classifier classActual = ParserAuxiliar.getClassByName(cc
					.getContextClass());
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
		List<String> parameters = null;
		String[] idPath = operation.split(ConstantsXML.DOUBLE_DOT_DOT);
		if (idPath.length == 1) {
			parameters = ParserAuxiliar.getOperationParameters(operation,
					getBodyClass().getOperations());
		}
		return parameters;

	}

	public String getOperationType(String identifier) {
		String[] idPath = identifier.split(ConstantsXML.DOUBLE_DOT_DOT);
		if (idPath.length == 1) {
			return ParserAuxiliar.getOperationType(identifier, getBodyClass()
					.getOperations());
		}
		return null;
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
			return ParserAuxiliar.getAttributeType(idName, getBodyClass()
					.getAttributes());
		} else if (idPath.length == 2) {

		} else if (idPath.length > 2) {

		}
		return null;
	}
}
