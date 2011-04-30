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

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

import util.AuxiliaryFunctionsXML;
import util.ConstantsXML;

import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.UmlException;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class AttributeImpl extends ModelElementImpl implements Attribute {
	private String initialValue;
	private Classifier classifier;
	private String classifierId;
	private boolean isChangeable;

	/**
	 * @param owner
	 * @param holder
	 */
	public AttributeImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
		loadClassifierId();
		
		try {
			String type = "Property";
			Element eType = (Element) getModel().getCachedXPathAPI().selectSingleNode(
					holder, "type");
			if (eType != null) {
				setType(AuxiliaryFunctionsXML.filterAttributeType(eType.getAttribute(ConstantsXML.HREF)));
			}else{
				setType(holder.getAttribute(ConstantsXML.TYPE_ONLY));
			}
		} catch (TransformerException e1) {
			e1.printStackTrace();
		}
		
		try {
			Element e = (Element) getModel().getCachedXPathAPI()
					.selectSingleNode(holder, "defaultValue");
			if (e != null && e.hasAttribute("value")) {
				initialValue = e.getAttribute("value");
			}
		} catch (Exception e) {
			throw new UmlException("Can't load classifier id: " + e, e, getId());
		}
		isChangeable = !"frozen".equals(holder.getAttribute("changeable"));
	}

	public void setType(String type) {
		super.setType(type);

	}

	/**
	 * @param model
	 * @param holder
	 */
	public AttributeImpl(ModelImpl model, Element holder) {
		super(model, holder);
		loadClassifierId();
	}

	private void loadClassifierId() {
		try {
			Element e = (Element) getModel().getCachedXPathAPI()
					.selectSingleNode(holder, "ownedAttribute");
			if (e != null && e.hasAttribute(ConstantsXML.xmiID)) {
				classifierId = e.getAttribute(ConstantsXML.xmiID);
			}
		} catch (Exception e) {
			throw new UmlException("Can't load classifier id: " + e, e, getId());
		}
	}

	String getClassifierId() {
		return classifierId;
	}

	public Classifier getClassifier() {
		if (classifier == null && classifierId != null) {
			classifier = (Classifier) getModel().findElement(classifierId);
			if (classifier == null) {
				getModel().getLogger().debug(this, "Classifier not found");
			}
		}

		return classifier;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public boolean isChangeable() {
		return isChangeable;
	}

	private Collection facets;

	public Collection getFacets() {
		if (facets == null) {
			facets = getModel().getAspectResolver().getFacets(this);
		}
		return facets;
	}

}
