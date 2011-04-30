package cvslogic.generatedxmi;

import java.util.LinkedList;
import java.util.List;

public class VersionComparator {
	public static List<ComparedElement> ChangedElements = new LinkedList<ComparedElement>();

	public static ComparedElement getComparedElemetByID(String elementID,
			Boolean exclude) {
		ComparedElement comparedElement = null;
		for (int i = 0; i < ChangedElements.size(); i++) {
			if (ChangedElements.get(i).getElementID().equalsIgnoreCase(
					elementID)) {
				comparedElement = ChangedElements.get(i);
				if (exclude) {
					ChangedElements.remove(i);
				}
			}
		}
		if (comparedElement == null) {
			comparedElement = new ComparedElement(elementID);
		}
		return comparedElement;
	}

	public static void putType(String type, String id) {
		ComparedElement comparedElement = getComparedElemetByID(id, true);
		comparedElement.setElementType(type);
		ChangedElements.add(comparedElement);

	}

	public static void putGeneralizations(String attribute, String id,
			List<String> newValue, List<String> oldValue) {
		ComparedElement comparedElement = getComparedElemetByID(id, true);
		comparedElement.setNewGeneralizations(newValue);
		comparedElement.setOldGeneralizations(oldValue);
		ChangedElements.add(comparedElement);
	}

	public static void putIncludes(String attribute, String id,
			List<String> newValue, List<String> oldValue) {
		ComparedElement comparedElement = getComparedElemetByID(id, true);
		comparedElement.setNewIncludes(newValue);
		comparedElement.setOldIncludes(oldValue);
		ChangedElements.add(comparedElement);
	}

	public static void putExtends(String attribute, String id,
			List<String> newValue, List<String> oldValue) {
		ComparedElement comparedElement = getComparedElemetByID(id, true);
		comparedElement.setNewExtends(newValue);
		comparedElement.setOldExtends(oldValue);
		ChangedElements.add(comparedElement);
	}

	public static void putExtensionPoints(String attribute, String id,
			List<String> newValue, List<String> oldValue) {
		ComparedElement comparedElement = getComparedElemetByID(id, true);
		comparedElement.setNewExtensionPoints(newValue);
		comparedElement.setOldExtensionPoints(oldValue);
		ChangedElements.add(comparedElement);
	}

	public static void putElement(String attribute, String type, String id,
			String newValue, String oldValue) {
		ComparedElement comparedElement = getComparedElemetByID(id, true);
		comparedElement.setElementType(type);
		comparedElement.addAttribute(attribute, newValue, oldValue);
		ChangedElements.add(comparedElement);
	}

}
