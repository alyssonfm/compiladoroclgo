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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Accepts element if all included acceptors accept.
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class CompositeAcceptor implements Acceptor {
	private Collection acceptors=new LinkedList();

	/**
	 * Default constructor. 
	 */
	public CompositeAcceptor() {
	}
	
	/**
	 * 
	 */
	public CompositeAcceptor(Collection acceptors) {
		if (acceptors!=null) {
			this.acceptors.addAll(acceptors);
		}
	}
	
	/**
	 * 
	 */
	public CompositeAcceptor(Acceptor[] acceptors) {
		if (acceptors!=null) {
			this.acceptors.addAll(Arrays.asList(acceptors));
		}
	}
	
	public void addAcceptor(Acceptor acceptor) {
		if (acceptor!=null) {
			acceptors.add(acceptor);
		}
	}
	
	public void removeAcceptor(Acceptor acceptor) {
		acceptors.remove(acceptor);
	}
	
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Acceptor#accept(com.pavelvlasov.uml.ModelElement)
	 */
	public boolean accept(ModelElement element) {
		Iterator it=acceptors.iterator();
		while (it.hasNext()) {
			Acceptor acceptor=(Acceptor) it.next();
			if (!acceptor.accept(element))  {
				return false;
			}
		}
		return true;
	}
}
