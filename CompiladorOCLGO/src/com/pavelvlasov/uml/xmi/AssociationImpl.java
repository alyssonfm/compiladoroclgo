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

import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.AssociationEnd;
import com.pavelvlasov.uml.UmlException;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class AssociationImpl extends ClassifierImpl implements Association {
	private AssociationEnd source;
	private AssociationEnd target;

	/**
	 * @param owner
	 * @param holder
	 */
	public AssociationImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
		try {
			NodeList ownedEnds = holder.getElementsByTagName("ownedEnd");
			source = new AssociationEndImpl(this, (Element) ownedEnds.item(0),
					this, true);
			target = new AssociationEndImpl(this, (Element) ownedEnds.item(1),
					this, false);
		} catch (UmlException e) {
			throw e;
		} catch (Exception e) {
			throw new UmlException("Can't load association " + getId() + ": "
					+ e, e, getId());
		}
	}

	/**
	 * @param model
	 * @param holder
	 */
	public AssociationImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}

	public AssociationEnd getSource() {
		return source;
	}

	public AssociationEnd getTarget() {
		return target;
	}

}
