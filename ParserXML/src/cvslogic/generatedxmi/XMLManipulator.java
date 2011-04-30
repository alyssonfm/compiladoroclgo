package cvslogic.generatedxmi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.Constants;
import util.ConstantsXML;

public class XMLManipulator {

	public static Element createElement(Document doc, String elementName,
			String id, HashMap<String, String> map) {
		Element element = doc.createElement(elementName);
		element.setAttribute(Constants.ID.toLowerCase(), id);
		for (String key : map.keySet()) {
			element.setAttribute(key, map.get(key));
		}
		return element;
	}

	public static Element createElement(Document doc, String elementName,
			HashMap<String, String> map) {
		Element element = doc.createElement(elementName);
		for (String key : map.keySet()) {
			element.setAttribute(key, map.get(key));
		}
		return element;
	}

	public static Element setElementsList(Element element,
			List<Element> elementsList) {
		Element elementAux = element;
		for (Element e : elementsList) {
			elementAux.appendChild(e);
		}
		return elementAux;
	}

	public static List<Element> addElements(Document doc, String elementName,
			List<String> elementsList) {
		List<Element> elements = new LinkedList<Element>();
		for (String supertype : elementsList) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(Constants.SUPER_TYPE.toLowerCase(), supertype);
			Element element = createElement(doc, elementName, map);
			elements.add(element);
		}
		return elements;

	}

	public static Document buildElements(Document doc,
			List<ComparedElement> elementsList) {
		Document docAux = doc;
		Element changementsAux = (Element) doc.getElementsByTagName(
				ConstantsXML.CHANGEMENTS).item(0);
		for (ComparedElement CE : elementsList) {
			List<Element> listAux = new LinkedList<Element>();
			Element dad = createElement(doc, CE.getElementType(), CE
					.getElementID(), new HashMap<String, String>());
			Element oldAtts = createElement(doc, ConstantsXML.OLD, CE
					.getOldAttributes());
			Element newAtts = createElement(doc, ConstantsXML.NEW, CE
					.getNewAttributes());
			listAux.add(oldAtts);
			listAux.add(newAtts);
			dad = setElementsList(dad, listAux);

			List<Element> generalizations = addElements(doc,
					Constants.GENERALIZATION.toString(), CE
							.getGeneralizations());
			dad = setElementsList(dad, generalizations);

			List<Element> includes = addElements(doc, Constants.INCLUDE
					.toString(), CE.getIncludes());
			dad = setElementsList(dad, includes);

			List<Element> Extends = addElements(doc, Constants.EXTEND
					.toString(), CE.getExtends());
			dad = setElementsList(dad, Extends);

			List<Element> extensions = addElements(doc,
					Constants.EXTENSION_POINT.toString(), CE
							.getExtensionPoints());
			dad = setElementsList(dad, extensions);

			changementsAux.appendChild(dad);
		}
		// docAux.replaceChild(changementsAux, doc.getElementsByTagName(
		// ConstantsXML.CHANGEMENTS).item(0));
		return docAux;
	}
}
