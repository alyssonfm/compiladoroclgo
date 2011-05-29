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
import org.w3c.dom.NodeList;

import util.Constants;
import util.ConstantsXML;

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.AssociationEnd;
import com.pavelvlasov.uml.Classifier;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class AssociationEndImpl extends ModelElementImpl implements AssociationEnd {
	private AssociationImpl association;
	private boolean isSource;

	/**
	 * @param owner
	 * @param holder
	 */
	public AssociationEndImpl(ModelElementImpl owner, Element holder,
			AssociationImpl association, boolean isSource) {
		super(owner, holder);
		this.association = association;
		this.isSource = isSource;
	}

	/**
	 * @param model
	 * @param holder
	 */
	public AssociationEndImpl(ModelImpl model, Element holder,
			AssociationImpl association, boolean isSource) {
		super(model, holder);
		this.association = association;
		this.isSource = isSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#getMultiplicity()
	 */
	public String getMultiplicity() {
		String multiplicity = "1";

		NodeList upperValue = holder
				.getElementsByTagName(ConstantsXML.UPPER_VALUE);
		NodeList lowerValue = holder
				.getElementsByTagName(ConstantsXML.LOWER_VALUE);

		if (upperValue.getLength() > 0 && lowerValue.getLength() > 0) {
			String upper = null;
			String lower = null;

			if (upperValue.item(0).getAttributes().getNamedItem(
					ConstantsXML.VALUE) != null) {
				upper = upperValue.item(0).getAttributes().getNamedItem(
						ConstantsXML.VALUE).getNodeValue();

			}
			if (lowerValue.item(0).getAttributes().getNamedItem(
					ConstantsXML.VALUE) != null) {
				lower = lowerValue.item(0).getAttributes().getNamedItem(
						ConstantsXML.VALUE).getNodeValue();
			}
			if (upper != null && lower != (null)) {
				multiplicity = lower + ".." + upper;
			} else if (upper != (null) && lower == (null)) {
				multiplicity = "0.." + upper;
			}
		}
		return multiplicity;
	}

	private Classifier classifier;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#getElement()
	 */
	public Classifier getClassifier() {
		if (classifier == null) {
			classifier = (Classifier) getModel().findElement(
					holder.getAttribute("type"));
			if (classifier == null) {
				getModel().getLogger().debug(this, "Classifier not found");
			}
		}

		return classifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#getAssociation()
	 */
	public Association getAssociation() {
		return association;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#getOtherEnd()
	 */
	public AssociationEnd getOtherEnd() {
		return isSource ? association.getTarget() : association.getSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#isInbound()
	 */
	/*
	 * public boolean isInbound() { String
	 * direction=getToolTaggedValues().getValue("direction", "Unspecified"); if
	 * ("Unspecified".equals(direction)) { return true; } else if
	 * ("Source -> Destination".equals(direction)) { return isTarget(); } else
	 * if ("Destination -> Source".equals(direction)) { return isSource(); }
	 * else { return false; } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#isSource()
	 */
	public boolean isSource() {
		return isSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.AssociationEnd#isTarget()
	 */
	public boolean isTarget() {
		return !isSource;
	}
}
