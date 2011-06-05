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
import com.pavelvlasov.uml.Realization;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class RealizationImpl extends ModelElementImpl implements Realization {

	/**
	 * @param owner
	 * @param holder
	 */
	public RealizationImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
	}

	/**
	 * @param model
	 * @param holder
	 */
	public RealizationImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}
	
	private Classifier subtype;
	private Classifier supertype;

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Generalization#getSubtype()
	 */
	public Classifier getSubtype() {
		if (subtype==null && holder.hasAttribute("client"))  {
			subtype=(Classifier) getModel().findElement(holder.getAttribute("client"));
		}
		return subtype;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Generalization#getSupertype()
	 */
	public Classifier getSupertype() {
		if (supertype==null && holder.hasAttribute("supplier"))  {
			supertype=(Classifier) getModel().findElement(holder.getAttribute("supplier"));
		}
		return supertype;
	}
}
