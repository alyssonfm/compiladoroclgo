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
package com.pavelvlasov.uml;

import java.util.Collection;

/**
 * Classifier (Actor, UseCase, Class, ...).
 * @author Pavel Vlasov
 * @version $Revision$
 */
public interface Classifier extends ModelElement {
	/**
	 * @return Dependencies where current classifier is a client
	 */
	Collection getClientDependencies();

	/**
	 * @return Dependencies where current classifier is a client
	 */
	Collection getSupplierDependencies();
	
	Collection getGeneralizations();

	Collection getRealizations();

	Collection getOperations();

	Collection getAttributes();

	Collection getAssociationEnds();
	
	/**
	 * Convenient method for code generation
	 * @return First supertype of the same type as element 
	 */
	Collection getExtends();
		
	/**
	 * All supertypes except one returned in getExtends();
	 * @return
	 */	
	Collection getImplements();
	
	/**
	 * Convenient method 
	 * @return collection of classifiers used by this classifier. 
	 * It includes attributes, operation parameters and return 
	 * types, client dependencies and outbound associations.
	 * associations, parameters, ...
	 */
	Collection getImports();
	
	boolean isLeaf();
	
	boolean isRoot();
	
	boolean isActive();
	
	boolean isAbstract();
	
	Collection getRequirements();
	
	Collection getScenarios();
	
	Collection getFiles();
	
	Collection getDiagrams();
	
	/**
	 * Checks whether current classifier is subtype/subclass of another
	 * classifier.
	 * 
	 * @param base
	 *            Base classifier
	 * @return true if this classifier is subtype/subclass of the base
	 *         classifier
	 */
	boolean isTypeOf(Classifier base);
	
	/**
	 * @param aspect Aspect
	 * @return true if the Classifier is aspected by the Aspect
	 */
	boolean isAspectedBy(Aspect aspect);
		
	/**
	 * @return aspects of this type.
	 */
	Collection getAspectInstances();

	/**
	 * Equals to elements(null, null)
	 * @return Owned elements
	 */
	Collection getElements();

	/**
	 * @param type Element type. Any type if null. 
	 * @param stereotype Element stereotype. Any stereotype if null.
	 * @return Owned elements filtered by type and stereotype
	 */
	Collection getElements(String type);	
	
	/**
	 * Use this method to get advices for dynamically generated 
	 * operations. For example for getters and setters generated
	 * from properties.
	 * @param operationName
	 * @return Advices matching operation <CODE>operationName</CODE> with 
	 * no parameters.
	 */
	Collection getAdvices(String operationName);
	
	/**
	 * Use this method to get advices for dynamically generated 
	 * operations. For example for getters and setters generated
	 * from properties.
	 * @param operationName
	 * @param parameter1Classifier Classifier for the first parameter.
	 * @return Advices matching operation <CODE>operationName</CODE> with 
	 * one parameters.
	 */
	Collection getAdvices(String operationName, Classifier parameter1Classifier);
	
	/**
	 * Use this method to get advices for dynamically generated 
	 * operations. For example for getters and setters generated
	 * from properties.
	 * @param operationName
	 * @param parameter1Classifier Classifier for the first parameter.
	 * @param parameter1Classifier Classifier for the second parameter.
	 * @return Advices matching operation <CODE>operationName</CODE> with 
	 * two parameters.
	 */
	Collection getAdvices(String operationName, Classifier parameter1Classifier, Classifier parameter2Classifier);
	
	/**
	 * Use this method to get advices for dynamically generated 
	 * operations. For example for getters and setters generated
	 * from properties.
	 * @param operationName
	 * @param parameterClassifiers collection parameter classifiers.
	 * @return Advices matching operation <CODE>operationName</CODE> with 
	 * two parameters.
	 */
	Collection getAdvices(String operationName, Collection parameterClassifiers);

	Collection getIncludes();
	
	Collection getExtensionPoints();
	
	
}
