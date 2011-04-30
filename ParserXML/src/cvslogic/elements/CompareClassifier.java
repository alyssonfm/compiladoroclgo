package cvslogic.elements;

import util.AuxiliaryFunctions;
import util.AuxiliaryFunctionsXML;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Classifier;

import cvslogic.generatedxmi.VersionComparator;

public class CompareClassifier {

	public boolean equalsRoot(Classifier newClassifier, Classifier oldClassifier) {
		if (newClassifier.isRoot() != oldClassifier.isRoot()) {
			VersionComparator.putElement(Constants.ROOT.toLowerCase(),
					newClassifier.getType(), newClassifier.getId(),
					newClassifier.isRoot() + "", oldClassifier.isRoot() + "");
		}

		return newClassifier.isRoot() == oldClassifier.isRoot();
	}

	public boolean equalsAbstract(Classifier newClassifier,
			Classifier oldClassifier) {
		if (newClassifier.isAbstract() != oldClassifier.isAbstract()) {
			VersionComparator.putElement(Constants.ABSTRACT.toLowerCase(),
					newClassifier.getType(), newClassifier.getId(),
					newClassifier.isAbstract() + "", oldClassifier.isAbstract()
							+ "");
		}
		return newClassifier.isAbstract() == oldClassifier.isAbstract();
	}

	public boolean equalsLeaf(Classifier newClassifier, Classifier oldClassifier) {

		if (newClassifier.isLeaf() != oldClassifier.isLeaf()) {
			VersionComparator.putElement(Constants.LEAF.toLowerCase(),
					newClassifier.getType(), newClassifier.getId(),
					newClassifier.isLeaf() + "", oldClassifier.isLeaf() + "");
		}
		return newClassifier.isLeaf() == oldClassifier.isLeaf();
	}

	public boolean equalsActive(Classifier newClassifier,
			Classifier oldClassifier) {
		if (newClassifier.isActive() != oldClassifier.isActive()) {
			VersionComparator.putElement(Constants.ACTIVE.toLowerCase(),
					newClassifier.getType(), newClassifier.getId(),
					newClassifier.isActive() + "", oldClassifier.isActive()
							+ "");
		}
		return newClassifier.isActive() == oldClassifier.isActive();
	}

	public boolean equalsExtends(Classifier newClassifier,
			Classifier oldClassifier) {
		Boolean out = true;
		Object[] newIncludes = newClassifier.getExtends().toArray();
		Object[] oldIncludes = oldClassifier.getExtends().toArray();

		for (Object include : newIncludes) {
			if (!oldClassifier.getExtends().contains(include)) {
				out = false;
			}
		}

		for (Object include : oldIncludes) {
			if (!newClassifier.getExtends().contains(include)) {
				out = false;
			}
		}
		if (out == false) {
			VersionComparator.putExtends(Constants.EXTEND, newClassifier
					.getId(), AuxiliaryFunctionsXML
					.collectionToList(newClassifier.getExtends()),
					AuxiliaryFunctionsXML.collectionToList(oldClassifier
							.getExtends()));
		}
		return out;

	}

	public boolean equalsIncludes(Classifier newClassifier,
			Classifier oldClassifier) {
		Boolean out = true;
		Object[] newIncludes = newClassifier.getIncludes().toArray();
		Object[] oldIncludes = oldClassifier.getIncludes().toArray();

		for (Object include : newIncludes) {
			if (!oldClassifier.getIncludes().contains(include)) {
				out = false;
			}
		}

		for (Object include : oldIncludes) {
			if (!newClassifier.getIncludes().contains(include)) {
				out = false;
			}
		}
		if (out == false) {
			VersionComparator.putIncludes(Constants.INCLUDE, newClassifier
					.getId(), AuxiliaryFunctionsXML
					.collectionToList(newClassifier.getIncludes()),
					AuxiliaryFunctionsXML.collectionToList(oldClassifier
							.getIncludes()));
		}
		return out;

	}

	public boolean equalsExtensionPoints(Classifier newClassifier,
			Classifier oldClassifier) {
		Boolean out = true;
		Object[] newExtensionPoints = newClassifier.getExtensionPoints()
				.toArray();
		Object[] oldExtensionPoints = oldClassifier.getExtensionPoints()
				.toArray();

		for (Object extensionPoint : newExtensionPoints) {
			if (!oldClassifier.getExtensionPoints().contains(extensionPoint)) {
				out = false;
			}
		}

		for (Object extensionPoint : oldExtensionPoints) {
			if (!newClassifier.getExtensionPoints().contains(extensionPoint)) {
				out = false;
			}
		}

		if (out == false) {
			VersionComparator.putExtensionPoints(Constants.EXTENSION_POINT,
					newClassifier.getId(), AuxiliaryFunctionsXML
							.collectionToList(newClassifier
									.getExtensionPoints()),
					AuxiliaryFunctionsXML.collectionToList(oldClassifier
							.getExtensionPoints()));
		}

		return out;

	}

	public boolean equalsGeneralizations(Classifier newClassifier,
			Classifier oldClassifier) {

		Boolean out = true;

		Object[] newGeneralizations = newClassifier.getGeneralizations()
				.toArray();
		Object[] oldGeneralizations = oldClassifier.getGeneralizations()
				.toArray();

		for (Object generalization : newGeneralizations) {
			if (!oldClassifier.getGeneralizations().contains(generalization)) {
				out = false;
			}
		}

		for (Object generalization : oldGeneralizations) {
			if (!newClassifier.getGeneralizations().contains(generalization)) {
				out = false;
			}
		}

		if (out == false) {
			VersionComparator.putGeneralizations(Constants.GENERALIZATION,
					newClassifier.getId(), AuxiliaryFunctionsXML
							.collectionToList(newClassifier
									.getGeneralizations()),
					AuxiliaryFunctionsXML.collectionToList(oldClassifier
							.getGeneralizations()));
		}

		return out;

	}

	// Observar se esse equals utilizando Object está correto!
	public boolean equalsImports(Classifier newClassifier,
			Classifier oldClassifier) {

		Object[] newGeneralizations = newClassifier.getImports().toArray();
		Object[] oldGeneralizations = oldClassifier.getImports().toArray();

		for (Object generalization : newGeneralizations) {
			if (!oldClassifier.getImports().contains(generalization)) {
				return false;
			}
		}

		for (Object generalization : oldGeneralizations) {
			if (!newClassifier.getImports().contains(generalization)) {
				return false;
			}
		}

		// getExtends : return the supertype;

		return true;

	}

	public String getAbstracts(Classifier newClassifier,
			Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(Constants.ABSTRACT,
				oldClassifier.isAbstract() + "",
				newClassifier.isAbstract() + "");
		return "Old: " + oldClassifier.isAbstract() + " - New: "
				+ newClassifier.isAbstract();
	}

	public String getRoots(Classifier newClassifier, Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(Constants.ABSTRACT,
				oldClassifier.isRoot() + "", newClassifier.isRoot() + "");
		return "Old: " + oldClassifier.isRoot() + " - New: "
				+ newClassifier.isRoot();
	}

	public String getLeafs(Classifier newClassifier, Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(Constants.LEAF,
				oldClassifier.isLeaf() + "", newClassifier.isLeaf() + "");
		return "Old: " + oldClassifier.isLeaf() + " - New: "
				+ newClassifier.isLeaf();
	}

	public String getActives(Classifier newClassifier, Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(Constants.ACTIVE,
				oldClassifier.isActive() + "", newClassifier.isActive() + "");
		return "Old: " + oldClassifier.isActive() + " - New: "
				+ newClassifier.isActive();
	}

	public String getGeneralizations(Classifier newClassifier,
			Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(
				Constants.GENERALIZATIONS,
				AuxiliaryFunctions.getSupertypes(oldClassifier
						.getGeneralizations().toArray()),
				AuxiliaryFunctions.getSupertypes(newClassifier
						.getGeneralizations().toArray()));
		return "Old: "
				+ (AuxiliaryFunctions.getSupertypes(oldClassifier
						.getGeneralizations().toArray()))
				+ " - New: "
				+ (AuxiliaryFunctions.getSupertypes(newClassifier
						.getGeneralizations().toArray()));

	}

	public String getExtends(Classifier newClassifier, Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(
				Constants.EXTENDS,
				AuxiliaryFunctions.getExcludedElements(oldClassifier
						.getExtends().toArray()),
				AuxiliaryFunctions.getExcludedElements(newClassifier
						.getExtends().toArray()));
		return "Old: "
				+ (AuxiliaryFunctions.getExcludedElements(oldClassifier
						.getExtends().toArray()))
				+ " - New: "
				+ (AuxiliaryFunctions.getExcludedElements(newClassifier
						.getExtends().toArray()));

	}

	public String getExtensionPoints(Classifier newClassifier,
			Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(
				Constants.EXTENSION_POINTS,
				AuxiliaryFunctions.getExtensionPoints(oldClassifier
						.getExtensionPoints().toArray()),
				AuxiliaryFunctions.getExtensionPoints(newClassifier
						.getExtensionPoints().toArray()));
		return "Old: "
				+ (AuxiliaryFunctions.getExtensionPoints(oldClassifier
						.getExtensionPoints().toArray()))
				+ " - New: "
				+ (AuxiliaryFunctions.getExtensionPoints(newClassifier
						.getExtensionPoints().toArray()));

	}

	public String getIncludes(Classifier newClassifier, Classifier oldClassifier) {
		HTMLFileManager.getInstance().setTableLine(
				Constants.INCLUDES,
				AuxiliaryFunctions.getIncludedElements(oldClassifier
						.getIncludes().toArray()),
				AuxiliaryFunctions.getIncludedElements(newClassifier
						.getIncludes().toArray()));
		return "Old: "
				+ (AuxiliaryFunctions.getIncludedElements(oldClassifier
						.getIncludes().toArray()))
				+ " - New: "
				+ (AuxiliaryFunctions.getIncludedElements(newClassifier
						.getIncludes().toArray()));

	}

	public String getImports(Classifier newClassifier, Classifier oldClassifier) {
		return "Old: "
				+ (AuxiliaryFunctions.concatenates(oldClassifier.getImports()
						.toArray()))
				+ " - New: "
				+ (AuxiliaryFunctions.concatenates(newClassifier.getImports()
						.toArray()));

	}

	public void compareProperties(Classifier oldClass, Classifier newClass) {
		AuxiliaryFunctions.print(Constants.ABSTRACT, equalsAbstract(newClass,
				oldClass), getAbstracts(newClass, oldClass));
		AuxiliaryFunctions.print(Constants.ROOT,
				equalsRoot(newClass, oldClass), getRoots(newClass, oldClass));
		AuxiliaryFunctions.print(Constants.LEAF,
				equalsLeaf(newClass, oldClass), getLeafs(newClass, oldClass));
		AuxiliaryFunctions.print(Constants.ACTIVE, equalsActive(newClass,
				oldClass), getActives(newClass, oldClass));
		AuxiliaryFunctions.print(Constants.GENERALIZATIONS,
				equalsGeneralizations(newClass, oldClass), getGeneralizations(
						newClass, oldClass));
		AuxiliaryFunctions.print(Constants.INCLUDES, equalsIncludes(newClass,
				oldClass), getIncludes(newClass, oldClass));
		AuxiliaryFunctions.print(Constants.EXTENDS, equalsExtends(newClass,
				oldClass), getExtends(newClass, oldClass));
		AuxiliaryFunctions.print(Constants.EXTENSION_POINTS,
				equalsExtensionPoints(newClass, oldClass), getExtensionPoints(
						newClass, oldClass));

		// AuxiliaryFunctions.print(Constants.IMPORTS, equalsImports(newClass,
		// oldClass), getImports(newClass, oldClass));
	}

}
