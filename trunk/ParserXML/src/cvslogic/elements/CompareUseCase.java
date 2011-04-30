package cvslogic.elements;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.ModelElement;

public class CompareUseCase {

	private CompareClassifier compareClassifier = new CompareClassifier();
	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();
	private CompareInclude compareInclude = new CompareInclude();

	public void compareUseCases(Object[] newUseCases, Object[] oldUseCases) {

		for (Object newUseCase : newUseCases) {
			Element oldUseCase = AuxiliaryFunctions.getByID(oldUseCases,
					newUseCase);

			if (oldUseCase != null) {

				HTMLFileManager.getInstance().buildElement(Tag.H3.toString(),
						null, "Comparison - Use Case");
				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");

				System.out.println(Constants.USECASE + ": "
						+ oldUseCase.getName());
				compareElement.compareProperties((Element) oldUseCase,
						(Element) newUseCase);
				compareModelElement.compareProperties(
						(ModelElement) oldUseCase, (ModelElement) newUseCase);
				compareClassifier.compareProperties((Classifier) oldUseCase,
						(Classifier) newUseCase);
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				// compareInclude.compareIncludes((Classifier) newUseCase,
				// (Classifier) oldUseCase);
			}
			System.out.println("---");
		}

	}

}
