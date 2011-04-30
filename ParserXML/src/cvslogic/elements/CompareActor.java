package cvslogic.elements;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

public class CompareActor {

	private CompareClassifier compareClassifier = new CompareClassifier();
	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();

	public void compareActors(Object[] newActors, Object[] oldActors) {
		
		for (Object newActor : newActors) {
			Element oldActor = AuxiliaryFunctions.getByID(oldActors, newActor);
			if (oldActor != null) {

				HTMLFileManager.getInstance().buildElement(Tag.H3.toString(),
						null, "Comparison - Actor");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");
				

				System.out.println(Constants.ACTOR + ": " + oldActor.getName());
				compareElement.compareProperties((Element) oldActor,
						(Element) newActor);
				compareModelElement.compareProperties((ModelElement) oldActor,
						(ModelElement) newActor);
				compareClassifier.compareProperties((Classifier) oldActor,
						(Classifier) newActor);

				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
			}
			System.out.println("---");
		}

	}
}
