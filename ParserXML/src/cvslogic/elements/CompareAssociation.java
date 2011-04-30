package cvslogic.elements;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.AssociationEnd;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

public class CompareAssociation {

	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();
	private CompareAssociationEnd compareAssociationEnd = new CompareAssociationEnd();

	public void compareAssociations(Object[] newAssociations,
			Object[] oldAssociations) {

		for (Object newAssociation : newAssociations) {
			Element oldAssociation = AuxiliaryFunctions.getByID(
					oldAssociations, newAssociation);

			if (oldAssociation != null
					&& !((ModelElement) oldAssociation).getType()
							.equalsIgnoreCase(Constants.INCLUDE)) {

				HTMLFileManager.getInstance().buildElement(Tag.H3.toString(),
						null, "Comparison - Association");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");

				System.out.println("Association: " + oldAssociation.getName());
				compareElement.compareProperties((Element) oldAssociation,
						(Element) newAssociation);
				compareModelElement.compareProperties(
						(ModelElement) oldAssociation,
						(ModelElement) newAssociation);
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println(Constants.EOF + "AssociationEnds Source: ");
				HTMLFileManager.getInstance().buildElement(Tag.H5.toString(),
						null, "(Source)");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");
				compareAssociationEnd.compareProperties(
						((Association) oldAssociation).getSource(),
						((Association) newAssociation).getSource());
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println(Constants.EOF + "AssociationEnds Target: ");
				HTMLFileManager.getInstance().buildElement(Tag.H5.toString(),
						null, "(Target)");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");
				compareAssociationEnd.compareProperties(
						((Association) oldAssociation).getTarget(),
						((Association) newAssociation).getTarget());
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());

				System.out.println("---");
			}

		}

	}

	public void extractAssociationEnd(AssociationEnd associationEnd) {

	}

}
