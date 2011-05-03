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
 * 
 * Modified by Natã Melo. SPLab
 
 */
package com.pavelvlasov.uml.xmi;

import java.util.Iterator;

import org.apache.commons.jxpath.JXPathContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.AuxiliaryFunctionsXML;
import util.ConstantsXML;

import com.pavelvlasov.uml.ModelElement;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class ElementImpl implements com.pavelvlasov.uml.Element {

	ElementImpl(ModelElementImpl owner, Element holder) {
		this.owner = owner;
		this.holder = holder;
		this.model = owner.getModel();
		NodeList nodeList = holder.getElementsByTagName("type");
		if (nodeList.getLength() > 0) {
			setType(AuxiliaryFunctionsXML.filterAttributeType(nodeList.item(0)
					.getAttributes().item(0).toString()));
		} else {
			setType(holder.getAttribute(ConstantsXML.TYPE_ONLY));
		}
	}

	// Changed here!
	ElementImpl(ModelImpl model, Element holder) {
		this.model = model;
		this.holder = holder;
		NodeList nodeList = holder.getElementsByTagName("type");
		if (nodeList.getLength() > 0) {
			setType(AuxiliaryFunctionsXML.filterAttributeType(nodeList.item(0)
					.getAttributes().item(0).toString()));
		} else {
			setType(holder.getAttribute(ConstantsXML.TYPE_ONLY));
		}
	}

	protected final Element holder;
	private JXPathContext jxPathContext;
	private ModelElement owner;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#id()
	 */
	public String getId() {
		if (holder.hasAttribute(ConstantsXML.xmiID)) {
			return AuxiliaryFunctionsXML.getNodeID(holder);
		} else if (getOwner() != null) {
			return getOwner().getId() + "." + getName();
		} else {
			return "(undefined)";
		}
	}

	private JXPathContext getJXPathContext() {
		if (jxPathContext == null) {
			if (getOwner() == null) {
				jxPathContext = JXPathContext.newContext(this);
			} else {
				jxPathContext = JXPathContext.newContext(
						((ElementImpl) getOwner()).getJXPathContext(), this);
			}
			jxPathContext.setLenient(true);
		}
		return jxPathContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#getOwner()
	 */
	public ModelElement getOwner() {
		return owner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#iterate(java.lang.String)
	 */
	public Iterator iterate(String xpath) {
		return getJXPathContext().iterate(xpath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#navigate(java.lang.String)
	 */
	public Object navigate(String xpath) {
		return getJXPathContext().getValue(xpath);
	}

	private ModelImpl model;

	ModelImpl getModel() {
		return model;
	}

	private String type;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#name()
	 */
	public String getName() {
		return AuxiliaryFunctionsXML.getNodeName(holder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#name()
	 */
	void setName(String name) {
		holder.setAttribute("name", name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#getType()
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return (obj instanceof com.pavelvlasov.uml.Element)
				&& getId().equals(((com.pavelvlasov.uml.Element) obj).getId());
	}

	// /**
	// * @return
	// */
	// Element getHolder() {
	// return holder;
	// }

}
