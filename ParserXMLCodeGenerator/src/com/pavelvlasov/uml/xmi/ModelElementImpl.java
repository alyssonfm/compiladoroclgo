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
import java.util.LinkedList;

import javax.xml.transform.TransformerException;

import org.apache.xpath.CachedXPathAPI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.UmlException;
import com.pavelvlasov.uml.Visitor;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
abstract class ModelElementImpl extends ElementImpl implements ModelElement {
	ModelElementImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
	}

	ModelElementImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}

	private String stereotype;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#stereotype()
	 */
	public String getStereotype() {
		if (stereotype == null) {
			try {
				stereotype = getElementStereotype(holder, getModel()
						.getCachedXPathAPI());
			} catch (Exception e) {
				throw new UmlException("Unable to load stereotype for element "
						+ getId() + ": " + e, e, getId());
			}
		}
		return stereotype;
	}

	static String getElementStereotype(Element holder, CachedXPathAPI cxpa)
			throws TransformerException {
		Node node = cxpa.selectSingleNode(holder,
				"ModelElement.stereotype/Stereotype");
		if (node instanceof Element) {
			return ((Element) node).getAttribute("name");
		} else {
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#getVisibility()
	 */
	public String getVisibility() {
		return holder.getAttribute("visibility");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#accept(com.pavelvlasov.uml.Visitor)
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#getAbsoluteName()
	 */
	public String getAbsoluteName() {
		return getAbsoluteName(".");
	}

	private Collection constraints;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#getConstraints()
	 */
	public Collection getConstraints() {
		if (constraints == null) {
			try {
				constraints = new LinkedList();
				NodeIterator nit = getModel().getCachedXPathAPI()
						.selectNodeIterator(holder,
								"ModelElement.constraint/Constraint");
				Element ce;
				while ((ce = (Element) nit.nextNode()) != null) {
					constraints.add(new ConstraintImpl(this, ce));
				}
			} catch (Exception e) {
				throw new UmlException(
						"Can't load tagged constraints for element " + getId()
								+ ": " + e, e, getId());
			}

		}

		return constraints;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#getAbsoluteName(java.lang.String)
	 */
	public String getAbsoluteName(String separator) {
		if (getOwner() == null) {
			return getName();
		} else {
			String ownerName = ((ModelElementImpl) getOwner())
					.getAbsoluteName(separator);
			return ownerName.length() == 0 ? getName() : ownerName + separator
					+ getName();
		}
	}

	/*
	 * private boolean isNamespace(ModelElement e) { return
	 * "1".equals(e.getToolTaggedValues().getValue("isnamespace","")); }
	 */

	public String getRootPath(String separator) {
		if (getOwner() == null) {
			return "";
		} else {
			return getOwner().getRootPath(separator) + ".." + separator;
		}
	}

	public boolean isStatic() {
		if (holder.hasAttribute("isStatic")) {
			return holder.getAttribute("isStatic").equals("true");
		}
		return false;
	}

}
