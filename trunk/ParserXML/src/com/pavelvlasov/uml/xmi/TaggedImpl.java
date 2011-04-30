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
import org.w3c.dom.traversal.NodeIterator;

import com.pavelvlasov.uml.Tagged;
import com.pavelvlasov.uml.TaggedValues;
import com.pavelvlasov.uml.UmlException;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class TaggedImpl extends ElementImpl implements Tagged {
	static final String NOTES_SEPARATOR = "#NOTES#";

	private TaggedValuesImpl taggedValues = new TaggedValuesImpl();
	private TaggedValuesImpl toolTaggedValues = new TaggedValuesImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Tagged#getTaggedValues()
	 */
	public TaggedValues getTaggedValues() {
		return taggedValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Element#getToolTaggedValues()
	 */
	public TaggedValues getToolTaggedValues() {
		return toolTaggedValues;
	}

	public TaggedImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
		// loadTaggedValues();
	}

	public TaggedImpl(ModelImpl model, Element holder) {
		super(model, holder);
		// loadTaggedValues();
	}

}
