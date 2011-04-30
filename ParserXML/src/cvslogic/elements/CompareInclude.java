package cvslogic.elements;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

public class CompareInclude {

	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();
	private CompareAssociationEnd compareAssociationEnd = new CompareAssociationEnd();

	public void compareIncludes(Classifier newPackage, Classifier oldPackage) {

		Object[] newIncludes = ((Classifier) newPackage).getElements(
				"include").toArray();
		Object[] oldIncludes = ((Classifier) newPackage).getElements(
				"include").toArray();

		for (Object newInclude : newIncludes) {
			Element oldInclude = AuxiliaryFunctions.getByID(oldIncludes,
					newInclude);

			HTMLFileManager.getInstance().buildElement(Tag.H3.toString(), null,
					"Comparison - Include");

			if (oldInclude != null) {
				System.out.println(Constants.INCLUDE + ": "
						+ oldInclude.getName());
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");

				compareElement.compareProperties((Element) oldInclude,
						(Element) newInclude);
				compareModelElement.compareProperties(
						(ModelElement) oldInclude, (ModelElement) newInclude);

				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println(Constants.EOF + "AssociationEnds Source: ");
				HTMLFileManager.getInstance().buildElement(Tag.H5.toString(),
						null, "(Source)");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");
				compareAssociationEnd.compareProperties(
						((Association) oldInclude).getSource(),
						((Association) newInclude).getSource());
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println(Constants.EOF + "AssociationEnds Target: ");
				HTMLFileManager.getInstance().buildElement(Tag.H5.toString(),
						null, "(Target)");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");
				compareAssociationEnd.compareProperties(
						((Association) oldInclude).getTarget(),
						((Association) newInclude).getTarget());
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println("---");

			}

		}
	}
}
