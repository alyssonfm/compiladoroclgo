package cvslogic.elements;

import util.AuxiliaryFunctions;
import util.Constants;

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

public class CompareClassifierRole {

	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();
	private CompareAssociationEnd compareAssociationEnd = new CompareAssociationEnd();

	public void compareClassifierRoles(Classifier newPackage, Classifier oldPackage) {

		Object[] newClassifierRoles = ((Classifier) newPackage).getElements(
				"ClassifierRole").toArray();
		Object[] oldClassifierRoles = ((Classifier) newPackage).getElements(
				"ClassifierRole").toArray();

		for (Object newClassifierRole : newClassifierRoles) {
			Element oldClassifierRole = AuxiliaryFunctions.getByID(oldClassifierRoles,
					newClassifierRole);

			if (oldClassifierRole != null) {
				System.out.println(Constants.CLASSIFIERROLE + ": "
						+ oldClassifierRole.getName());
				compareElement.compareProperties((Element) oldClassifierRole,
						(Element) newClassifierRole);
				compareModelElement.compareProperties(
						(ModelElement) oldClassifierRole, (ModelElement) newClassifierRole);
			}
			System.out.println(Constants.EOF + "AssociationEnds Source: ");
			compareAssociationEnd.compareProperties(((Association) oldClassifierRole)
					.getSource(), ((Association) newClassifierRole).getSource());
			System.out.println(Constants.EOF + "AssociationEnds Target: ");
			compareAssociationEnd.compareProperties(((Association) oldClassifierRole)
					.getTarget(), ((Association) newClassifierRole).getTarget());

			System.out.println("---");

		}
	}
}
