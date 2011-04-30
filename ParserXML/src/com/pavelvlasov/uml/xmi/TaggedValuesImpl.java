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
import java.util.Map;
import java.util.TreeMap;

import com.pavelvlasov.uml.TaggedValue;
import com.pavelvlasov.uml.TaggedValues;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class TaggedValuesImpl implements TaggedValues {
	private LinkedList allValues=new LinkedList();
	private Map nameMap=new TreeMap();
		
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#getValue(java.lang.String)
	 */
	public TaggedValue getValue(String name) {
		Object o=nameMap.get(name);
		
		if (o instanceof TaggedValue) {
			return (TaggedValue) o;
		} else if (o instanceof LinkedList){
			LinkedList values=(LinkedList) o;
			return (TaggedValue) values.getFirst();
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#getValue(java.lang.String, java.lang.String)
	 */
	public String getValue(String name, String defaultValue) {
		TaggedValue ret=getValue(name);
		if (ret==null) {
			return defaultValue;
		} else {
			return ret.getValue();
		}
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#getMultiValue(java.lang.String)
	 */
	public Collection getMultiValue(String name) {
		Collection ret=new LinkedList();
		Object o=nameMap.get(name);		
		if (o instanceof TaggedValue) {
			ret.add(o);
		} else if (o instanceof LinkedList){
			LinkedList values=(LinkedList) o;
			ret.addAll(values);
		} 
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#contains(java.lang.String)
	 */
	public boolean contains(String name) {
		return nameMap.containsKey(name);
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#isMultiValue(java.lang.String)
	 */
	public boolean isMultiValue(String name) {
		return nameMap.get(name) instanceof LinkedList;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#getNames()
	 */
	public Collection getNames() {
		return nameMap.keySet();
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.TaggedValues#getValues()
	 */
	public Collection getValues() {
		return allValues;
	}
	
	void add(TaggedValue newValue) {
		allValues.add(newValue);
		Object o=nameMap.get(newValue.getName());
		
		if (o instanceof TaggedValue) {
			LinkedList values=new LinkedList();
			values.add(o);
			values.add(newValue);
			nameMap.put(newValue.getName(), values);
		} else if (o instanceof LinkedList){
			LinkedList values=(LinkedList) o;
			values.add(newValue);
		} else {
			nameMap.put(newValue.getName(), newValue);
		}		
	}

}
