package cvslogic.elements;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.AssociationEnd;

public class CompareAssociationEnd {

	public boolean equalsMultiplicity(AssociationEnd newEnd,
			AssociationEnd oldEnd) {
		if (oldEnd.getMultiplicity().equals("")
				&& newEnd.getMultiplicity().equals("1")
				|| oldEnd.getMultiplicity().equals("1")
				&& newEnd.getMultiplicity().equals("")) {
			return true;
		} else if (oldEnd.getMultiplicity().equals("*")
				&& newEnd.getMultiplicity().equals("0..*")
				|| oldEnd.getMultiplicity().equals("0..*")
				&& newEnd.getMultiplicity().equals("*")) {
			return true;
		}
		return newEnd.getMultiplicity().equals(oldEnd.getMultiplicity());
	}

	/*public boolean equalsInbound(AssociationEnd newEnd, AssociationEnd oldEnd) {
		return newEnd.isInbound() == oldEnd.isInbound();
	}*/

	public boolean equalsClassifier(AssociationEnd newEnd, AssociationEnd oldEnd) {
		return newEnd.getClassifier().getName().equals(
				oldEnd.getClassifier().getName());
	}

	public boolean equalsTarget(AssociationEnd newEnd, AssociationEnd oldEnd) {
		return newEnd.isTarget() == oldEnd.isTarget();
	}

	public boolean equalsSource(AssociationEnd newEnd, AssociationEnd oldEnd) {
		return newEnd.isSource() == oldEnd.isSource();
	}

	public void compareProperties(AssociationEnd oldAssociation,
			AssociationEnd newAssociation) {

		HTMLFileManager.getInstance().buildElement(Tag.H4.toString(), null,
				"Comparison - AssociationEnd");

		AuxiliaryFunctions.print(Constants.MULTIPLICITY, equalsMultiplicity(
				newAssociation, oldAssociation), getMultiplicity(
				newAssociation, oldAssociation));

		HTMLFileManager.getInstance().setTableLine(
				"Associated Element",
				oldAssociation.getClassifier().getType() + " - "
						+ oldAssociation.getClassifier().getName(),
				newAssociation.getClassifier().getType() + " - "
						+ newAssociation.getClassifier().getName());

		System.out.println("Associated Element: "
				+ oldAssociation.getClassifier().getType() + " - "
				+ oldAssociation.getClassifier().getName());

	}

	public String getMultiplicity(AssociationEnd newEnd, AssociationEnd oldEnd) {
		String outOld = oldEnd.getMultiplicity();
		String outNew = newEnd.getMultiplicity();
		if (outOld.equals("")) {
			outOld = "1";
		}
		if (outNew.equals("")) {
			outNew = "1";
		}
		HTMLFileManager.getInstance().setTableLine(Constants.MULTIPLICITY,
				outOld, outNew);
		return "Old: " + (outOld) + " - New: " + (outNew);
	}

	/*public String getInbound(AssociationEnd newEnd, AssociationEnd oldEnd) {
		return "Old: " + (oldEnd.isInbound()) + " - New: "
				+ (newEnd.isInbound());
	}*/

}
