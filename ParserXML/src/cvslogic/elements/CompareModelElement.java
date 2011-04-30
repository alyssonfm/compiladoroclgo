package cvslogic.elements;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

import cvslogic.generatedxmi.VersionComparator;

public class CompareModelElement {

	// Falta accept, getConstraints, getRootPath,

	public boolean equalsAbsoluteName(ModelElement newME, ModelElement oldME) {
		return newME.getAbsoluteName().equals(oldME.getAbsoluteName());
	}

	public boolean equalsStereotype(ModelElement newME, ModelElement oldME) {
		return newME.getType().equals(oldME.getType());
	}

	public boolean equalsType(ModelElement newME, ModelElement oldME) {
		return newME.getType().equals(oldME.getType());
	}

	public boolean equalsVisibility(ModelElement newME, ModelElement oldME) {
		if (!newME.getVisibility().equals(oldME.getVisibility())) {
			VersionComparator.putElement(Constants.VISIBILITY.toLowerCase(),
					newME.getType(), newME.getId(), newME.getVisibility()
							.toString(), oldME.getVisibility().toString());
		}

		return newME.getVisibility().equals(oldME.getVisibility());
	}

	public String getStereotypes(ModelElement newME, ModelElement oldME) {
		String out = "Old: ";
		if (oldME.getOwner() != null) {
			out += oldME.getType().toString();
		}
		out += " - New: ";
		if (newME.getOwner() != null) {
			out += newME.getType().toString();
		}

		return out;
	}

	public String getTypes(ModelElement newME, ModelElement oldME) {
		return "Old: " + oldME.getType() + " - New: " + newME.getType();
	}

	public String getVisibilities(ModelElement newME, ModelElement oldME) {
		HTMLFileManager.getInstance().setTableLine(Constants.VISIBILITY,
				oldME.getVisibility(), newME.getVisibility());
		return "Old: " + oldME.getVisibility() + " - New: "
				+ newME.getVisibility();
	}

	public void compareProperties(ModelElement oldModelElement,
			ModelElement newModelElement) {
		/*
		 * AuxiliaryFunctions.print(Constants.STEREOTYPE,
		 * equalsStereotype((ModelElement) newModelElement, (ModelElement)
		 * oldModelElement), getStereotypes( (ModelElement) newModelElement,
		 * (ModelElement) oldModelElement));
		 */
		/*
		 * AuxiliaryFunctions.print(Constants.TYPE, equalsType((ModelElement)
		 * newModelElement, (ModelElement) oldModelElement), getTypes(
		 * (ModelElement) newModelElement, (ModelElement) oldModelElement));
		 */
		AuxiliaryFunctions.print(Constants.VISIBILITY,
				equalsVisibility((ModelElement) newModelElement,
						(ModelElement) oldModelElement), getVisibilities(
						(ModelElement) newModelElement,
						(ModelElement) oldModelElement));
	}

	public Boolean changed(ModelElement newElement, ModelElement oldElement) {
		if (!equalsVisibility(newElement, oldElement)) {
			return false;
		}
		return true;
	}

}
