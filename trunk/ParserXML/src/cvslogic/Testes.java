package cvslogic;

import java.io.File;

import com.pavelvlasov.uml.CompositeAcceptor;
import com.pavelvlasov.uml.Element;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.eval.PrefixedCompositeEvaluator;
import com.pavelvlasov.uml.xmi.ModelBuilder;
import com.pavelvlasov.uml.xmi.PrintStreamLogger;

public class Testes {
	
	public static void main(String[] args) {
		
		File xmiFileNew = new File("Modelos/Estudo_de_Caso.xml");
		Model newVersion = ModelBuilder.loadModel(xmiFileNew,
				new CompositeAcceptor(), new PrintStreamLogger(),
				new PrefixedCompositeEvaluator());
		System.out.println(newVersion.getName());
		for (Object element : (newVersion.getPackages().toArray())){
			System.out.println(((Element) element).getName());
		}
	
	}

}
