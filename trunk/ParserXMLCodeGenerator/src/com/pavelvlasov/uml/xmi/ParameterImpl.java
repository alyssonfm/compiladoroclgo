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

import org.w3c.dom.Element;

import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Parameter;
import com.pavelvlasov.uml.UmlException;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class ParameterImpl extends ElementImpl implements Parameter {
	private Classifier classifier;
	private String classifierId;
	private String typeName;
	private String kind;
	private String defaultValue;

	/**
	 * @param owner
	 * @param holder
	 */
	public ParameterImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
		loadData();
	}

	/**
	 * @param model
	 * @param holder
	 */
	public ParameterImpl(ModelImpl model, Element holder) {
		super(model, holder);
		loadData();
	}
	
	private void loadData() {
		try {
			Element e=(Element) getModel().getCachedXPathAPI().selectSingleNode(holder, "Parameter.type/Classifier");
			if (e!=null && e.hasAttribute("xmi.idref")) {
				classifierId=e.getAttribute("xmi.idref");
			}
			
			kind=holder.getAttribute("kind");
			
			Element dve=(Element) getModel().getCachedXPathAPI().selectSingleNode(holder, "Parameter.defaultValue/Expression");
			if (dve!=null && dve.hasAttribute("body")) {
				defaultValue=e.getAttribute("body");
			}			
		} catch (Exception e) {
			throw new UmlException("Can't load parameter "+getId()+": "+e, e, getId());						
		}
		
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Parameter#getClassifier()
	 */
	public Classifier getClassifier() {
		if (classifier==null && classifierId!=null) {
			classifier=(Classifier) getModel().findElement(classifierId);
		}
		return classifier;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Parameter#getDefaultValue()
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Parameter#getParameterType()
	 */
	public String getKind() {
		return kind;
	}
}
