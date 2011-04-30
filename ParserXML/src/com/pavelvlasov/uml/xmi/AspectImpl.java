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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.pavelvlasov.uml.Aspect;
import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.TaggedValues;
import com.pavelvlasov.uml.Visitor;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
class AspectImpl implements Aspect {
	private ClassifierImpl master;

	/**
	 * @param owner
	 * @param holder
	 */
	public AspectImpl(ClassifierImpl master) {
		this.master=master;
	}
	
	private Collection instances;

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.Aspect#getInstances()
	 */
	public Collection getInstances() {
		if (instances==null) {
			instances=master.getModel().getAspectResolver().getAspectInstances(this);
		}
		return instances;
	}
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.xmi.ElementImpl#getType()
	 */
	public String getType() {
		return "aspect:"+master.getType();
	}

	/**
	 * @param visitor
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
		Iterator eit = master.getElements().iterator();
		while (eit.hasNext()) {
			ModelElement me = (ModelElement) eit.next();
			me.accept(visitor);
		}
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
	public Collection getAspectInstances() {
		return master.getAspectInstances();
	}

	/**
	 * @return
	 */
	public Collection getAssociationEnds() {
		return master.getAssociationEnds();
	}

	private Collection attributes;
	/**
	 * @return
	 */
	public Collection getAttributes() {
		if (attributes==null) {
			attributes=new LinkedList();
			Iterator it=master.getAttributes().iterator();
			while (it.hasNext()) {
				Attribute a=(Attribute) it.next();
				if (!master.getModel().getAspectResolver().isFacet(a)) {
					attributes.add(new AttributeDelegate(a, this));
				}
			}
		}
		return attributes;
	}

	/**
	 * @return
	 */
	public Collection getClientDependencies() {
		return master.getClientDependencies();
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
	public Collection getDiagrams() {
		return master.getDiagrams();
	}

	/**
	 * @return
	 */
	public Collection getElements() {
		return master.getElements();
	}

	/**
	 * @param type
	 * @param stereotype
	 * @return
	 */
	public Collection getElements(String type) {
		return master.getElements(type);
	}

	/**
	 * @return
	 */
	//public Classifier getExtends() {
		//return master.getExtends();
	//}

	/**
	 * @return
	 */
	public Collection getFiles() {
		return master.getFiles();
	}

	/**
	 * @return
	 */
	public Collection getGeneralizations() {
		return master.getGeneralizations();
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
	public Collection getImplements() {
		return master.getImplements();
	}

	/**
	 * @return
	 */
	public Collection getImports() {
		return master.getImports();
	}

	/**
	 * @return
	 */
	public String getName() {
		return master.getName();
	}

	private Collection operations;
	/**
	 * @return
	 */
	public Collection getOperations() {
		if (operations==null) {
			operations=new LinkedList();
			Iterator it=master.getOperations().iterator();
			while (it.hasNext()) {
				Operation o=(Operation) it.next();
				if (!master.getModel().getAspectResolver().isAdvice(o)) {
					operations.add(new OperationDelegate(o, this));
				}
			}
		}
		return operations;
	}

	/**
	 * @return
	 */
	public ModelElement getOwner() {
		return master.getOwner();
	}

	/**
	 * @return
	 */
	public Collection getRealizations() {
		return master.getRealizations();
	}

	/**
	 * @return
	 */
	public Collection getRequirements() {
		return master.getRequirements();
	}

	/**
	 * @return
	 */
	public Collection getScenarios() {
		return master.getScenarios();
	}

	/**
	 * @return
	 */
	public String getStereotype() {
		return master.getStereotype();
	}

	/**
	 * @return
	 */
	public Collection getSupplierDependencies() {
		return master.getSupplierDependencies();
	}

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
	public boolean isAbstract() {
		return master.isAbstract();
	}

	/**
	 * @return
	 */
	public boolean isActive() {
		return master.isActive();
	}

	/**
	 * @param aspect
	 * @return
	 */
	public boolean isAspectedBy(Aspect aspect) {
		return master.isAspectedBy(aspect);
	}

	/**
	 * @return
	 */
	public boolean isLeaf() {
		return master.isLeaf();
	}

	/**
	 * @return
	 */
	public boolean isRoot() {
		return master.isRoot();
	}

	/**
	 * @param base
	 * @return
	 */
	public boolean isTypeOf(Classifier base) {
		return master.isTypeOf(base);
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
	
	public Collection getAdvices(String operationName) {
		return getAdvices(operationName, new LinkedList());
	}
	
	public Collection getAdvices(String operationName, Classifier parameter1Classifier) {
		return getAdvices(operationName, Arrays.asList(new Classifier[] {parameter1Classifier}));
	}
	
	public Collection getAdvices(String operationName, Classifier parameter1Classifier, Classifier parameter2Classifier) {
		return getAdvices(operationName, Arrays.asList(new Classifier[] {parameter1Classifier, parameter2Classifier}));
	}
	
	public Collection getAdvices(String operationName, Collection parameterClassifiers) {
		return master.getModel().getAspectResolver().getAdvices(this, operationName, parameterClassifiers);
	}

	/**
	 * @param separator
	 * @return
	 */
	public String getRootPath(String separator) {
		return master.getRootPath(separator);
	}
	
	/**
	 * @param string
	 */
	void setName(String name) {
		master.setName(name);
	}
	
	ModelImpl getModel() {
		return master.getModel();
	}
	@Override
	public Collection getIncludes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection getExtends() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection getExtensionPoints() {
		// TODO Auto-generated method stub
		return null;
	}
}
