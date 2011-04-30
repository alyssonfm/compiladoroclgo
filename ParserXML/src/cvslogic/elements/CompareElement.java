package cvslogic.elements;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Element;

import cvslogic.generatedxmi.VersionComparator;

public class CompareElement {

	public boolean equalsID(Element newElement, Element oldElement) {
		return newElement.getId().equals(oldElement.getId());
	}

	public boolean equalsName(Element newElement, Element oldElement) {
		if (!newElement.getName().equals(oldElement.getName())) {
			VersionComparator.putElement(Constants.NAME.toLowerCase(),
					newElement.getType(), newElement.getId(), newElement
							.getName(), oldElement.getName());
		}
		return newElement.getName().equals(oldElement.getName());
	}

	// Prestar atenção se esta comparação está correta!
	public boolean equalsOwner(Element newElement, Element oldElement) {
		if (newElement.getOwner() == null && oldElement.getOwner() == null) {
			return true;
		}
		if (newElement.getOwner() == null || oldElement.getOwner() == null) {
			if (newElement.getOwner() == null) {
				VersionComparator.putElement(Constants.OWNER.toLowerCase(),
						newElement.getType(), newElement.getId(), "",
						oldElement.getOwner().toString());
			} else {
				VersionComparator.putElement(Constants.OWNER.toLowerCase(),
						newElement.getType(), newElement.getId(), newElement
								.getOwner().toString(), "");
			}
			return false;
		}
		if (!newElement.getOwner().getName().toString().equals(
				oldElement.getOwner().getName().toString())) {
			VersionComparator.putElement(Constants.OWNER.toLowerCase(),
					newElement.getType(), newElement.getId(), newElement
							.getOwner().toString(), oldElement.getOwner()
							.toString());
		}
		return newElement.getOwner().getName().toString().equals(
				oldElement.getOwner().getName().toString());
	}

	public String getIDs(Element newPackage, Element oldPackage) {
		HTMLFileManager.getInstance().setTableLine(Constants.ID,
				oldPackage.getId(), newPackage.getId());
		return "Old: " + oldPackage.getId() + " - New: " + newPackage.getId();
	}

	public String getNames(Element newPackage, Element oldPackage) {
		HTMLFileManager.getInstance().setTableLine(Constants.NAME,
				oldPackage.getName(), newPackage.getName());
		return "Old: " + oldPackage.getName() + " - New: "
				+ newPackage.getName();
	}

	public String getOwners(Element newPackage, Element oldPackage) {
		String out = "Old: ";
		String newValue = "";
		String oldValue = "";
		if (oldPackage.getOwner() != null) {
			out += oldPackage.getOwner().getName();
			oldValue = oldPackage.getOwner().getName();
		}
		out += " - New: ";
		if (newPackage.getOwner() != null) {
			out += newPackage.getOwner().getName();
			newValue = newPackage.getOwner().getName();
		}
		HTMLFileManager.getInstance().setTableLine(Constants.OWNER, oldValue,
				newValue);
		return out;
	}

	public void compareProperties(Element oldElement, Element newElement) {
		/*
		 * AuxiliaryFunctions.print(Constants.ID, equalsID((Element) newElement,
		 * oldElement), getIDs((Element) newElement, oldElement));
		 */
		System.out.println(Constants.ID + ": " + oldElement.getId());
		AuxiliaryFunctions.print(Constants.ID, equalsID((Element) newElement,
				oldElement), getIDs((Element) newElement, oldElement));
		AuxiliaryFunctions.print(Constants.OWNER, equalsOwner(
				(Element) newElement, oldElement), getOwners(
				(Element) newElement, oldElement));
		AuxiliaryFunctions.print(Constants.NAME, equalsName(
				(Element) newElement, oldElement), getNames(
				(Element) newElement, oldElement));
	}

	// Faltam iterate e navigate

	public Boolean changed(Element newElement, Element oldElement) {
		if (!equalsName(newElement, oldElement)
				|| !equalsOwner(newElement, oldElement)) {
			return false;
		}
		return true;
	}

}
