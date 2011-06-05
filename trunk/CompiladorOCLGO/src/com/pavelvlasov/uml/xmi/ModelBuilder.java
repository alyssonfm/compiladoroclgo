/*
 * umlapi 1.1.2
 * UML API 
 * Copyright (C) 2003  Pavel Vlasov
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * URL: http://www.pavelvlasov.com/Products/UmlApi/index.html
 * e-Mail: vlasov@pavelvlasov.com
 
 */
package com.pavelvlasov.uml.xmi;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.app.Velocity;
import org.apache.xpath.CachedXPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.pavelvlasov.uml.Acceptor;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.UmlException;
import com.pavelvlasov.uml.eval.Evaluator;
import com.pavelvlasov.uml.eval.VelocityEvaluator;
import com.pavelvlasov.uml.eval.WrapperFactory;

/**
 * Builds model from XMI file.
 * Uses aspect resolver class specified in com.pavelvlasov.uml.xmi.ModelBuilder:AspectResolver
 * system property. Defaults to com.pavelvlasov.uml.xmi.StereotypeGlobAspectResolver. 
 * @author Pavel Vlasov
 * @version $Revision$
 */
public class ModelBuilder {
	
	/**
	 * Loads model from XMI file
	 * @param file
	 * @return model
	 * @throws UmlException
	 */
	public static Model loadModel(File file, Acceptor acceptor, Logger logger, Evaluator evaluator) {
		
		if (!(file.exists() && file.isFile())) {
			throw new UmlException("File "+file.getAbsolutePath()+" does not exist or not a file.");
		}
		
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document=builder.parse(file);
			CachedXPathAPI cxpa=new CachedXPathAPI();
			
			Element modelElement=(Element) cxpa.selectSingleNode(document.getDocumentElement(),"Model");			
			Model model=new ModelImpl(modelElement, cxpa, acceptor, logger);
			
			if (evaluator==null) {
				return model;
			} else {
				WrapperFactory wrapperFactory=new WrapperFactory(evaluator);
				return (Model) wrapperFactory.wrap(model);
			}
		} catch (UmlException e) {
			throw e;
		} catch (Exception e) {
			
			throw new UmlException("Can't load "+ file.getAbsolutePath()+": "+ e, e);
		}		
	}

	private static void listPackageContent(com.pavelvlasov.uml.Package pkg, int level) {
		for (int i=0; i<level; i++) {
			System.out.print("\t");
		}
		System.out.println(pkg.getName());
		Iterator eit=pkg.getElements().iterator();
		while (eit.hasNext()) {
			ModelElement element=(ModelElement) eit.next();
			if (element instanceof com.pavelvlasov.uml.Package) {
				listPackageContent((com.pavelvlasov.uml.Package) element, level+1);				
			} else {
				for (int i=0; i<level; i++) {
					System.out.print("\t");
				}
				System.out.println("\t"+element.getType()+": "+element.getName() + "("+element.getAbsoluteName()+")");
			}
		}		
	}

	/**
	 * Test method
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Velocity.init();
		VelocityEvaluator evaluator=new VelocityEvaluator(null);
		Model model=loadModel(new File(args[0]), null, new PrintStreamLogger(), evaluator);				
		System.out.println(model.getName());
		Classifier mySimpleBean=(Classifier) model.findElement(args[1]);		
		System.out.println(mySimpleBean.getName());
		Object o=mySimpleBean.navigate("id='EAID_E97A5B57_131A_4d35_820D_FB7B5868DE6F'");
		System.out.println(o.getClass().getName());
		System.out.println(o);
		
//		Visitor visitor=new Visitor() {
//
//			public void visit(Model model) {
//				System.out.println(model.getName());
//				System.out.println(model.getClass().getName());
//			}
//
//			public void visit(ModelElement modelElement) {
//				System.out.println(modelElement.getName());
//				System.out.println(modelElement.getClass().getName());
//			}			
//		};
//		
//		model.accept(visitor);
	}	
}
