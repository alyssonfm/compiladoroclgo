package cvslogic;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import util.Constants;
import util.xmi.ConstantsXML;

import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.CompositeAcceptor;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Package;
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
	private static List<ClasseComp> classesComp = new LinkedList<ClasseComp>();

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
		loadAllClassesComp();
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
		//System.out.println("Aqui:" + parser.getClassesComp());
		parser.setPackage("PacoteSistema");
		System.out.println(parser.existsPackage());
		System.out.println(parser.getError());
		// parser.setContext("Torada::getTorada(torada:Cheque):Torada");
		// parser.setContext("Conta::debitar(pts:Integer):Boolean");
		// parser.setContext("Cheque::programa():ProgramaFidelidade");
		parser.setContext("Cheque::programa():ProgramaFidelidade");
		//System.out.println(parser.existsContext());
		//System.out.println(parser.getError());
		//System.out.println("oi " + parser.getAttributeType("servico"));
		// System.out
		// .println(parser
		// .getOperationType("getTorada"));
		// parser.getOperationType("getMulta");
		// System.out
		// .println(parser
		// .getParametersType("PacoteDaTorada::Torada::getTorada(torada : PacoteSistema::Cheque)"));
		// System.out.println(parser.getSuperType("Transacao", "Cheque"));

	}

	public List<ClasseComp> getClassesComp() {
		return classesComp;
	}

	public static List<ClasseComp> loadAllClassesComp() {
		for (Classifier classe : ParserAuxiliar.classes) {
			if (classe.getName().equals("") || classe.getName().contains("<")) {
				continue;
			}
			ClasseComp classeComp = new ClasseComp(classe.getName());

			for (Object att : classe.getAttributes()) {
				AtributoComp attr = new AtributoComp(((Element) att).getName(),
						ParserAuxiliar.filterOperationCollection(ParserAuxiliar
								.filterType(((Element) att).getType())));
				classeComp.addAtributo(attr);
			}

			for (Object opp : classe.getOperations()) {
				OperacaoComp opComp = new OperacaoComp(((Element) opp)
						.getName(), ParserAuxiliar
						.filterOperationCollection((ParserAuxiliar
								.filterOperationType(((Element) opp)))));

				for (Object att : ((Operation) opp).getParameters()) {
					AtributoComp attr = new AtributoComp(((Parameter) att)
							.getName(), ParserAuxiliar
							.filterOperationCollection(ParserAuxiliar
									.filterType(((Element) att).getType())));
					opComp.addParametro(attr);
				}

				classeComp.addOperacao(opComp);
			}
			classesComp.add(classeComp);
		}
		ajustarHerancas();
		return classesComp;
	}

	private static ClasseComp getClasseComp(String nome) {
		for (ClasseComp classeComp : classesComp) {
			if (classeComp.getNome().equals(nome)) {
				return classeComp;
			}
		}
		return null;
	}

	private static void removerClasseComp(String nome) {
		for (int i = 0; i < classesComp.size(); i++) {
			if (classesComp.get(i).getNome().equals(nome)) {
				classesComp.remove(i);
			}
		}
	}

	private static void ajustarHerancas() {
		for (Object classe : ParserAuxiliar.classes) {
			if (((Classifier) classe).getName().equals("")
					|| ((Classifier) classe).getName().contains("<")) {
				continue;
			}
			Collection<String> generalizations = ParserAuxiliar
					.getGeneralizations((Classifier) classe);
			if (generalizations != null) {
				for (String superType : generalizations) {
					if (superType.equals("") || superType.contains("<")) {
						continue;
					}
					mergeClasses(superType, ((Classifier) classe).getName());
				}
			}
		}
	}

	private static void mergeClasses(String classePai, String classeFilha) {
		ClasseComp pai = getClasseComp(classePai);
		ClasseComp filha = getClasseComp(classeFilha);

		for (AtributoComp attr : pai.getAtt()) {
			if (!filha.temAtributo(attr.getNome())) {
				filha.addAtributo(attr);
			}
		}
		for (OperacaoComp attr : pai.getOperacoes()) {
			if (!filha.temOperacao(attr.getNome())) {
				filha.addOperacao((attr));
			}
		}
		removerClasseComp(filha.getNome());
		classesComp.add(filha);
	}

	public Package getPackageActual() {
		return this.constraintPackage.getPack();
	}

	public Package getPackage(ConstraintPackage cp) {
		// Collection<Object> packages = getPackageActual().getElements(
		// Constants.PACKAGE);
		Collection<Object> packages = model.getPackages();
		Package packAux = null;
		for (String path : cp.getPackage()) {
			packAux = getPackage(path, packages);
			if (packAux != null) {
				packages = packAux.getElements(Constants.PACKAGE);
			}
		}
		return packAux;
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
		if (packs.size() > 0) {
			for (Object pack : packs) {
				if (((Package) pack).getName().equals(name)) {
					return (Package) pack;
				} else {
					return getPackage(name, ((Package) pack)
							.getElements(Constants.PACKAGE));
				}
			}
		}
		return null;
	}

	public void setPackage(String string) {
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
			Classifier classActual = null;
			if (notExistPackage()) {
				if (cc.getCaminho().size() == 0) {
					classActual = ParserAuxiliar.getClassByName(cc
							.getContextClass(), null);
				} else {
					ConstraintPackage cpAux = new ConstraintPackage();
					cpAux.setCaminhoList(cc.getCaminho());
					Package packa = getPackage(cpAux);
					if (packa != null) {
						this.constraintPackage.setPackage(packa);
						classActual = ParserAuxiliar.getClassByName(cc
								.getContextClass(), getPackageActual());
					}
				}
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
			return true;
		}

		return false;
	}

	private void setBodyClass(Classifier classActual) {
		this.bodyClass = classActual;
	}

	public List<String> getParametersType(String operation) {
		if (getOperationType(operation) == null) {
			return null;
		}
		ConstraintContext cc = new ConstraintContext();
		cc.setContext(operation);
		List<String> parameters = new LinkedList<String>();
		Classifier classe = null;
		if (cc.getContextClass() == null) {
			classe = getBodyClass();
		} else if (cc.getContextClass() != null && cc.getCaminho().size() == 0) {
			classe = ParserAuxiliar.getClassByName(cc.getContextClass());
		} else if (cc.getCaminho().size() > 0) {
			ConstraintPackage cp = new ConstraintPackage();
			cp.setCaminhoList(cc.getCaminho());
			Package packAux = getPackage(cp);
			classe = ParserAuxiliar.getClassByName(cc.getContextClass(),
					packAux);
		}
		return ParserAuxiliar.getOperationParameters(cc.getOperacao(), classe
				.getOperations());
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
		ConstraintContext cc = new ConstraintContext();
		if (!identifier.contains("(")) {
			identifier += "()";
		}
		cc.setContext(identifier);
		if (cc.getContextClass() == null) {
			return ParserAuxiliar.getOperationType(cc.getOperacao(),
					getBodyClass());
		} else if (cc.getContextClass() != null && cc.getCaminho().size() == 0) {
			Classifier classe = ParserAuxiliar.getClassByName(cc
					.getContextClass());
			if (classe != null) {
				if (!cc.getContextClass().equals(getBodyClass().getName())) {
					// return ParserAuxiliar.getOperationType(cc.getOperacao(),
					// classe, true);
				} else {
					return ParserAuxiliar.getOperationType(cc.getOperacao(),
							classe);
				}
			}
		} else if (cc.getCaminho().size() > 0) {
			ConstraintPackage cp = new ConstraintPackage();
			cp.setCaminhoList(cc.getCaminho());
			Package packAux = getPackage(cp);
			if (packAux != null) {
				Classifier classe = ParserAuxiliar.getClassByName(cc
						.getContextClass(), packAux);
				if (classe != null) {
					if (!cc.getContextClass().equals(getBodyClass().getName())) {
						// return ParserAuxiliar.getOperationType(
						// cc.getOperacao(), classe, true);
					} else {
						return ParserAuxiliar.getOperationType(
								cc.getOperacao(), classe);
					}
				}
			}
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
		List<String> caminho = Arrays.asList(idPath);
		idName = (idPath[idPath.length - 1]).replace("self.", "");

		if (idPath.length == 1) {
			if (idName.equals("self")) {
				return getBodyClass().getName();
			}
			idName = idName.replace("self.", "");
			return ParserAuxiliar.getAttributeType(idName, getBodyClass());
		} else if (idPath.length > 1) {
			idClass = idPath[idPath.length - 2];
			if (idPath.length == 2) {
				Classifier classe = ParserAuxiliar.getClassByName(idClass,
						getPackageActual());
				if (classe != null) {
					// return ParserAuxiliar
					// .getAttributeType(idName, classe, true);
				}
			} else if (idPath.length > 2) {
				ConstraintPackage cp = new ConstraintPackage();
				cp.setCaminhoList(caminho.subList(0, idPath.length - 2));
				Package pack = getPackage(cp);
				if (pack != null) {
					Classifier classe = ParserAuxiliar.getClassByName(idClass,
							pack);
					if (classe != null) {
						// return ParserAuxiliar.getAttributeType(idName,
						// classe,
						// true);
					}

				}
			}
		}
		return null;
	}
}
