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

import com.pavelvlasov.uml.TaggedValue;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class TaggedValueImpl implements TaggedValue, Comparable {
	private String name;
	private String value;
	private String notes;

	public TaggedValueImpl(String name, String value) {
		if (name==null ) {
			throw new NullPointerException("name==null");
		}
		
		if (value==null ) {
			throw new NullPointerException("value==null");
		}
		
		this.name=name;
		this.value=value;		
	}

	public TaggedValueImpl(String name, String value, String notes) {
		if (name==null ) {
			throw new NullPointerException("name==null");
		}
		
		if (value==null ) {
			throw new NullPointerException("value==null");
		}
		
		this.name=name;
		this.value=value;
		this.notes=notes;		
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValue#getNotes()
	 */
	public String getNotes() {
		return notes;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValue#getValue()
	 */
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o instanceof TaggedValue) {
			TaggedValue tv=(TaggedValue) o;
			if (name.equals(tv.getName())) {
				return value.compareTo(tv.getValue());
			} else {
				return name.compareTo(tv.getName());
			}
		} else {
			return 1;
		}
	}
	
	public boolean equals(Object o) {
		if (o instanceof TaggedValue) {
			TaggedValue tv=(TaggedValue) o;
			return name.equals(tv.getName()) && value.equals(tv.getValue());
		} else {
			return false;
		}		
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValue#getName()
	 */
	public String getName() {
		return name;
	}
}
