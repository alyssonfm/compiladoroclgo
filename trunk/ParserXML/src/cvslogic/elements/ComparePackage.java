package cvslogic.elements;

import java.io.IOException;
import java.util.Collection;

import javax.swing.text.html.HTML.Tag;

import util.AuxiliaryFunctions;
import util.Constants;
import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.ModelElement;

public class ComparePackage {

	private CompareElement compareElement = new CompareElement();
	private CompareModelElement compareModelElement = new CompareModelElement();
	private CompareActor compareActor = new CompareActor();
	private CompareUseCase compareUseCase = new CompareUseCase();
	private CompareAssociation compareAssociation = new CompareAssociation();
	private CompareInclude compareInclude = new CompareInclude();
	private CompareExtend compareExtend = new CompareExtend();

	public void compareVersions(Model newVersion, Model oldVersion) {
		Object[] newPackages = newVersion.getPackages().toArray();
		Object[] oldPackages = oldVersion.getPackages().toArray();

		comparePackages(newPackages, oldPackages);
	}

	public void comparePackages(Object[] newPackages, Object[] oldPackages) {

		for (Object newPackage : newPackages) {
			Element oldPackage = AuxiliaryFunctions.getByID(oldPackages,
					newPackage);

			if (oldPackage != null) {

				HTMLFileManager.getInstance().buildElement(Tag.H2.toString(),
						null, "Comparison - Package");

				System.out.println(" Package: " + oldPackage.getName());

				HTMLFileManager.getInstance().openTag(Tag.TABLE.toString(),
						"border=\"1\"");
				HTMLFileManager.getInstance().setTableTop();
				
				compareElement.compareProperties((Element) oldPackage,
						(Element) newPackage);
				compareModelElement.compareProperties(
						(ModelElement) oldPackage, (ModelElement) newPackage);
				HTMLFileManager.getInstance().closeTag(Tag.TABLE.toString());
				
				System.out.println(Constants.EOF + " Actors");
				compareActor.compareActors(((Classifier) newPackage)
						.getElements(Constants.ACTOR).toArray(),
						((Classifier) oldPackage).getElements(Constants.ACTOR).toArray());
				compareUseCase.compareUseCases(((Classifier) newPackage)
						.getElements(Constants.USECASE).toArray(),
						((Classifier) oldPackage).getElements(
								Constants.USECASE).toArray());
				compareAssociation.compareAssociations(
						((Classifier) newPackage).getElements(
								Constants.ASSOCIATION).toArray(),
						((Classifier) oldPackage).getElements(
								Constants.ASSOCIATION).toArray());

				System.out.println();

			}
			System.out.println("---");
			System.out.println();
			System.out.println();
			Collection newPackagesList = (((Classifier) newPackage)
					.getElements(Constants.PACKAGE));
			Collection oldPackagesList = (((Classifier) oldPackage)
					.getElements(Constants.PACKAGE));

			Object[] newPackagesAux;
			Object[] oldPackagesAux;
			if (newPackagesList.size() == 0) {
				newPackagesAux = new Object[0];
			} else {
				newPackagesAux = newPackagesList.toArray();
			}
			if (oldPackagesList.size() == 0) {
				oldPackagesAux = new Object[0];
			} else {
				oldPackagesAux = oldPackagesList.toArray();
			}

			if (oldPackagesAux.length > 0 && newPackagesAux.length > 0) {
				comparePackages(newPackagesAux, oldPackagesAux);
			}

			HTMLFileManager.getInstance().openTag(Tag.TABLE.toString());
		}

	}
}
