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
import java.util.Iterator;

import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Visitor;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class OperationDelegate implements Operation {

	private Operation master;
	private ModelElement owner;

	/**
	 * 
	 */
	public OperationDelegate(Operation master, ModelElement owner) {
		super();
		this.master=master;
		this.owner=owner;
	}

	/**
	 * @param visitor
	 */
	public void accept(Visitor visitor) {
		master.accept(visitor);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return master.equals(obj);
	}

	/**
	 * @return
	 */
	public String getAbsoluteName() {
		return master.getAbsoluteName();
	}

	/**
	 * @param separator
	 * @return
	 */
	public String getAbsoluteName(String separator) {
		return master.getAbsoluteName(separator);
	}

	/**
	 * @return
	 */
	public Collection getAdvices() {
		return master.getAdvices();
	}

	/**
	 * @param adviceType
	 * @return
	 */
	public Collection getAdvices(String adviceType) {
		return master.getAdvices(adviceType);
	}

	/**
	 * @return
	 */
	public String getConcurrency() {
		return master.getConcurrency();
	}

	/**
	 * @return
	 */
	public Collection getConstraints() {
		return master.getConstraints();
	}

	/**
	 * @return
	 */
	public String getId() {
		return master.getId();
	}

	/**
	 * @return
	 */
	public String getName() {
		return master.getName();
	}

	/**
	 * @return
	 */
	public Collection getParameters() {
		return master.getParameters();
	}

	/**
	 * @param separator
	 * @return
	 */
	public String getRootPath(String separator) {
		return master.getRootPath(separator);
	}

	/**
	 * @return
	 */
	/*public String getStereotype() {
		return master.getStereotype();
	}*/

	/**
	 * @return
	 */
	/*public TaggedValues getTaggedValues() {
		return master.getTaggedValues();
	}*/

	/**
	 * @return
	 */
	/*public TaggedValues getToolTaggedValues() {
		return master.getToolTaggedValues();
	}*/

	/**
	 * @return
	 */
	public String getType() {
		return master.getType();
	}

	/**
	 * @return
	 */
	public String getVisibility() {
		return master.getVisibility();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return master.hashCode();
	}

	/**
	 * @return
	 */
	public boolean isQuery() {
		return master.isQuery();
	}

	/**
	 * @param xpath
	 * @return
	 */
	public Iterator iterate(String xpath) {
		return master.iterate(xpath);
	}

	/**
	 * @param xpath
	 * @return
	 */
	public Object navigate(String xpath) {
		return master.navigate(xpath);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return master.toString();
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Element#getOwner()
	 */
	public ModelElement getOwner() {
		return owner;
	}

	@Override
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return false;
	}

}
