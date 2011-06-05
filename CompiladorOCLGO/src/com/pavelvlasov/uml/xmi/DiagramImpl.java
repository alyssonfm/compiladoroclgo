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

import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;

import com.pavelvlasov.uml.Diagram;
import com.pavelvlasov.uml.UmlException;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class DiagramImpl extends TaggedImpl implements Diagram {

	/**
	 * @param owner
	 * @param holder
	 */
	public DiagramImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
	}

	private Collection elements;
	
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Diagram#getElements()
	 */
	public Collection getElements() {
		if (elements==null) {
			try {
				elements=new LinkedList();
				NodeIterator nit=getModel().getCachedXPathAPI().selectNodeIterator(holder, "Diagram.element/DiagramElement");
				Element de;
				while ((de=(Element) nit.nextNode())!=null) {
					elements.add(new DiagramElementImpl(this, de));
				}
			} catch (Exception e) {
				throw new UmlException("Can't load diagram elements for diagram "+getId()+": "+e, e, getId());						
			}
		}		
		
		return elements;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Diagram#getDiagramType()
	 */
	public String getDiagramType() {
		return holder.getAttribute("diagramType");
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Diagram#getToolName()
	 */
	public String getToolName() {
		return holder.getAttribute("toolName");
	}

}
