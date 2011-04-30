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

import java.util.Collection;

import org.w3c.dom.Element;

import com.pavelvlasov.uml.Aspect;
import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Operation;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public interface AspectResolver {

	/**
	 * @param classifier
	 * @return Aspect instances for this Classifier.
	 */
	Collection getAspectInstances(Classifier classifier);

	/**
	 * @param classifier
	 * @return Instances of this aspect.
	 */
	Collection getAspectInstances(AspectImpl aspectImpl);
	
	/**
	 * @param operation
	 * @return
	 */
	Collection getAdvices(Operation operation);
	
	/**
	 * @param holder
	 * @return
	 */
	boolean isAspect(Element holder);
	
	void setModel(ModelImpl model);

	/**
	 * @param impl
	 * @param operationName
	 * @param parameterClassifiers
	 * @return
	 */
	Collection getAdvices(Aspect aspect, String operationName, Collection parameterClassifiers);

	/**
	 * @param impl
	 * @return
	 */
	Collection getFacets(Attribute attribute);
	
	boolean isAdvice(Operation operation);
	
	boolean isFacet(Attribute attribute);
}
