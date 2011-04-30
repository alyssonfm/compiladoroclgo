package cvslogic.elements;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

public class CompareExtend {

	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();
	private CompareAssociationEnd compareAssociationEnd = new CompareAssociationEnd();

	public void compareextends(Classifier newPackage, Classifier oldPackage) {

		
		Object[] newextends = ((Classifier) newPackage).getElements(
				"extend").toArray();
		Object[] oldextends = ((Classifier) newPackage).getElements(
				"extend").toArray();

		for (Object newextend : newextends) {

			Element oldextend = AuxiliaryFunctions.getByID(oldextends,
					newextend);
			
			

			if (oldextend != null) {

				HTMLFileManager.getInstance().buildElement(Tag.H3.toString(),
						null, "Comparison - Extend");

				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");

				System.out.println(Constants.EXTEND + ": "
						+ oldextend.getName());
				compareElement.compareProperties((Element) oldextend,
						(Element) newextend);
				compareModelElement.compareProperties((ModelElement) oldextend,
						(ModelElement) newextend);

				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());

				System.out.println(Constants.EOF + "AssociationEnds Source: ");
				HTMLFileManager.getInstance().buildElement(Tag.H5.toString(),
						null, "(Source)");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
				"border=\"1\"");
				compareAssociationEnd.compareProperties(
						((Association) oldextend).getSource(),
						((Association) newextend).getSource());
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println(Constants.EOF + "AssociationEnds Target: ");
				HTMLFileManager.getInstance().buildElement(Tag.H5.toString(),
						null, "(Target)");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
				"border=\"1\"");
				compareAssociationEnd.compareProperties(
						((Association) oldextend).getTarget(),
						((Association) newextend).getTarget());
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				System.out.println("---");

			}

		}
	}
}
