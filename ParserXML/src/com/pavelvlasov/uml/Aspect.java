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
 * Represents aspect.
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public interface Aspect extends Classifier {
	/**
	 * @return Collection of AspectInstances. 
	 */
	Collection getInstances();
	
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
}
