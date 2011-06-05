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
import com.pavelvlasov.uml.Dependency;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class DependencyImpl extends ModelElementImpl implements Dependency {

	/**
	 * @param owner
	 * @param holder
	 */
	public DependencyImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
	}

	/**
	 * @param model
	 * @param holder
	 */
	public DependencyImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}
	
	private Classifier client;
	private Classifier supplier;

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Dependency#getClient()
	 */
	public Classifier getClient() {
		if (client==null && holder.hasAttribute("client"))  {
			client=(Classifier) getModel().findElement(holder.getAttribute("client"));
		}
		return client;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Dependency#getSuppier()
	 */
	public Classifier getSupplier() {
		if (supplier==null && holder.hasAttribute("supplier"))  {
			supplier=(Classifier) getModel().findElement(holder.getAttribute("supplier"));
		}		
		return supplier;
	}

}
