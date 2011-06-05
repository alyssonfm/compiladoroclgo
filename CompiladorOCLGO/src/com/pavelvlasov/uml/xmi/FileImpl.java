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

import com.pavelvlasov.uml.File;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class FileImpl extends TaggedImpl implements File {
	private String description;
	private String fileType;
	/**
	 * @param owner
	 * @param holder
	 */
	public FileImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
		
		if (holder.hasAttribute("description")) {
			description=holder.getAttribute("description");
		}
		
		if (holder.hasAttribute("type")) {
			fileType=holder.getAttribute("type");
		}
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.File#getDescription()
	 */
	public String getDescription() {
		return description;
	}
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.File#getFileType()
	 */
	public String getFileType() {
		return fileType;
	}

}
