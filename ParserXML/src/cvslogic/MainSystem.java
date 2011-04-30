package cvslogic;

import java.io.File;
import java.io.IOException;

import javax.swing.text.html.HTML.Tag;

import util.reports.HTMLFileManager;

import com.pavelvlasov.uml.CompositeAcceptor;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.eval.PrefixedCompositeEvaluator;
import com.pavelvlasov.uml.xmi.ModelBuilder;
import com.pavelvlasov.uml.xmi.PrintStreamLogger;

import cvslogic.elements.ComparePackage;
import cvslogic.generatedxmi.VersionComparator;
import cvslogic.generatedxmi.XMICreator;

public class MainSystem {

	private File xmiFileNew = null;
	private File xmiFileOld = null;
	private Model newVersion = null;
	private Model oldVersion = null;
	private ComparePackage comparePackage = new ComparePackage();

	public static void main(String[] args) {
		MainSystem teste = new MainSystem();

		teste.setXMIVersions("Modelos/TesteFerramenta2.xml",
				"Modelos/TesteFerramenta.xml");

		teste.loadModels();

		teste.compare(teste.getNewVersion(), teste.getOldVersion());

		XMICreator xmiCreator = new XMICreator("VersionController",
				"generatedXMI/comparison.xml");
		try {
			xmiCreator.getDoc(VersionComparator.ChangedElements);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(VersionComparator.ChangedElements);
		/*
		 * Model model = ModelBuilder.loadModel(new File(
		 * "Modelos/TesteFerramenta.xml"), new CompositeAcceptor(), new
		 * PrintStreamLogger(), new PrefixedCompositeEvaluator()); Package pack
		 * = (Package) (model.getPackages().toArray()[0]);
		 * 
		 * Collection list = (pack.getElements("UseCase")); Iterator it =
		 * list.iterator();
		 * 
		 * while (it.hasNext()) { Classifier c = (Classifier) it.next(); if
		 * (c.getExtends().size() > 0) { System.out.println(((ExtendsImpl)
		 * c.getExtends().toArray()[0]) .getExtendedElement()); } }
		 */
	}

	public Model getNewVersion() {
		return this.newVersion;
	}

	public Model getOldVersion() {
		return this.oldVersion;
	}

	public void setXMIVersions(String newPath, String oldPath) {
		this.xmiFileNew = new File(newPath);
		this.xmiFileOld = new File(oldPath);
	}

	public void loadModels() {
		this.newVersion = ModelBuilder.loadModel(this.xmiFileNew,
				new CompositeAcceptor(), new PrintStreamLogger(),
				new PrefixedCompositeEvaluator());
		this.oldVersion = ModelBuilder.loadModel(xmiFileOld,
				new CompositeAcceptor(), new PrintStreamLogger(),
				new PrefixedCompositeEvaluator());
	}

	public void compare(Model newModel, Model oldModel) {
		/*
		 * for (Object e : ((Package)
		 * model.getPackages().toArray()[0]).getAssociationEnds().toArray()) {
		 * System.out.println(((Element) e).getName()); }
		 */
		HTMLFileManager.getInstance().openTag(Tag.HTML.toString());
		HTMLFileManager.getInstance().openTag(Tag.HEAD.toString());
		HTMLFileManager.getInstance().buildElement(Tag.TITLE.toString(), null,
				"Version Control System to UML Models");
		HTMLFileManager.getInstance().closeTag(Tag.HEAD.toString());
		HTMLFileManager.getInstance().openTag(Tag.BODY.toString());

		comparePackage.compareVersions(newModel, oldModel);

		HTMLFileManager.getInstance().closeTag(Tag.BODY.toString());
		HTMLFileManager.getInstance().closeTag(Tag.HTML.toString());
		try {
			HTMLFileManager.getInstance().writeHTMLFile(
					HTMLFileManager.getInstance().getContent(),
					"report/reportComparison.html");
			// System.out.println(HTMLFileManager.getInstance().getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
