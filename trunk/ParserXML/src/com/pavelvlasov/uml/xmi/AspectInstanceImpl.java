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
import java.util.LinkedList;

import com.pavelvlasov.uml.Aspect;
import com.pavelvlasov.uml.AspectInstance;
import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Visitor;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
class AspectInstanceImpl implements AspectInstance {
	private AspectImpl aspectImpl;
	private Classifier aspected;
	private Collection advices;		

	/**
	 * @param aspect
	 * @param aspected
	 */
	protected AspectInstanceImpl(AspectImpl aspectImpl, Classifier aspected, Collection advices) {
		super();
		this.aspectImpl = aspectImpl;
		this.aspected = aspected;
		this.advices=advices;
	}
	/**
	 * @param visitor
	 */
	public void accept(Visitor visitor) {
		aspectImpl.accept(visitor);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return aspectImpl.equals(obj);
	}

	/**
	 * @return
	 */
	public String getAbsoluteName() {
		return aspectImpl.getAbsoluteName();
	}

	/**
	 * @param separator
	 * @return
	 */
	public String getAbsoluteName(String separator) {
		return aspectImpl.getAbsoluteName(separator);
	}

	/**
	 * @return
	 */
	public Collection getAspectInstances() {
		return aspectImpl.getAspectInstances();
	}

	/**
	 * @return
	 */
	public Collection getAssociationEnds() {
		return aspectImpl.getAssociationEnds();
	}

	private Collection attributes;
	/**
	 * @return
	 */
	public Collection getAttributes() {
		if (attributes==null) {
			attributes=new LinkedList();
			Iterator it=aspectImpl.getAttributes().iterator();
			while (it.hasNext()) {
				attributes.add(new AttributeDelegate((Attribute) it.next(), this));
			}
		}
		return attributes;
	}

	/**
	 * @return
	 */
	public Collection getClientDependencies() {
		return aspectImpl.getClientDependencies();
	}

	/**
	 * @return
	 */
	public Collection getConstraints() {
		return aspectImpl.getConstraints();
	}

	/**
	 * @return
	 */
	public Collection getDiagrams() {
		return aspectImpl.getDiagrams();
	}

	/**
	 * @return
	 */
	public Collection getElements() {
		return aspectImpl.getElements();
	}

	/**
	 * @param type
	 * @param stereotype
	 * @return
	 */
	public Collection getElements(String type) {
		return aspectImpl.getElements(type);
	}

	/**
	 * @return
	 */
	//public Classifier getExtends() {
		//return aspectImpl.getExtends();
	//}

	/**
	 * @return
	 */
	public Collection getFiles() {
		return aspectImpl.getFiles();
	}

	/**
	 * @return
	 */
	public Collection getGeneralizations() {
		return aspectImpl.getGeneralizations();
	}

	/**
	 * @return
	 */
	public String getId() {
		return aspectImpl.getId();
	}

	/**
	 * @return
	 */
	public Collection getImplements() {
		return aspectImpl.getImplements();
	}

	/**
	 * @return
	 */
	public Collection getImports() {
		return aspectImpl.getImports();
	}

	/**
	 * @return
	 */
	public Collection getInstances() {
		return aspectImpl.getInstances();
	}

	/**
	 * @return
	 */
	public String getName() {
		return aspectImpl.getName();
	}

	private Collection operations;
	/**
	 * @return
	 */
	public Collection getOperations() {
		if (operations==null) {
			operations=new LinkedList();
			Iterator it=aspectImpl.getOperations().iterator();
			while (it.hasNext()) {
				operations.add(new OperationDelegate((Operation) it.next(), this));
			}
		}
		return operations;
	}


	/**
	 * @return
	 */
	public ModelElement getOwner() {
		return aspectImpl.getOwner();
	}

	/**
	 * @return
	 */
	public Collection getRealizations() {
		return aspectImpl.getRealizations();
	}

	/**
	 * @return
	 */
	public Collection getRequirements() {
		return aspectImpl.getRequirements();
	}

	/**
	 * @return
	 */
	public Collection getScenarios() {
		return aspectImpl.getScenarios();
	}

	/**
	 * @return
	 */
	public String getStereotype() {
		return aspectImpl.getStereotype();
	}

	/**
	 * @return
	 */
	public Collection getSupplierDependencies() {
		return aspectImpl.getSupplierDependencies();
	}

	/**
	 * @return
	 */
	/*public TaggedValues getTaggedValues() {
		return aspectImpl.getTaggedValues();
	}

	/**
	 * @return
	 */
	/*public TaggedValues getToolTaggedValues() {
		return aspectImpl.getToolTaggedValues();
	}*/

	/**
	 * @return
	 */
	public String getType() {
		return aspectImpl.getType();
	}

	/**
	 * @return
	 */
	public String getVisibility() {
		return aspectImpl.getVisibility();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return aspectImpl.hashCode();
	}

	/**
	 * @return
	 */
	public boolean isAbstract() {
		return aspectImpl.isAbstract();
	}

	/**
	 * @return
	 */
	public boolean isActive() {
		return aspectImpl.isActive();
	}

	/**
	 * @param aspect
	 * @return
	 */
	public boolean isAspectedBy(Aspect aspect) {
		return aspect.isAspectedBy(aspect);
	}

	/**
	 * @return
	 */
	public boolean isLeaf() {
		return aspectImpl.isLeaf();
	}

	/**
	 * @return
	 */
	public boolean isRoot() {
		return aspectImpl.isRoot();
	}

	/**
	 * @param base
	 * @return
	 */
	public boolean isTypeOf(Classifier base) {
		return aspectImpl.isTypeOf(base);
	}

	/**
	 * @param xpath
	 * @return
	 */
	public Iterator iterate(String xpath) {
		return aspectImpl.iterate(xpath);
	}

	/**
	 * @param xpath
	 * @return
	 */
	public Object navigate(String xpath) {
		return aspectImpl.navigate(xpath);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return aspectImpl.toString();
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.AspectInstance#getAdvices()
	 */
	public Collection getAdvices() {
		return advices;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.AspectInstance#getAspected()
	 */
	public Classifier getAspected() {
		return aspected;
	}

	/**
	 * @param operationName
	 * @return
	 */
	public Collection getAdvices(String operationName) {
		return aspectImpl.getAdvices(operationName);
	}

	/**
	 * @param operationName
	 * @param parameter1Classifier
	 * @return
	 */
	public Collection getAdvices(
		String operationName,
		Classifier parameter1Classifier) {
		return aspectImpl.getAdvices(operationName, parameter1Classifier);
	}

	/**
	 * @param operationName
	 * @param parameter1Classifier
	 * @param parameter2Classifier
	 * @return
	 */
	public Collection getAdvices(
		String operationName,
		Classifier parameter1Classifier,
		Classifier parameter2Classifier) {
		return aspectImpl.getAdvices(
			operationName,
			parameter1Classifier,
			parameter2Classifier);
	}

	/**
	 * @param operationName
	 * @param parameterClassifiers
	 * @return
	 */
	public Collection getAdvices(
		String operationName,
		Collection parameterClassifiers) {
		return aspectImpl.getAdvices(operationName, parameterClassifiers);
	}

	/**
	 * @param separator
	 * @return
	 */
	public String getRootPath(String separator) {
		return aspectImpl.getRootPath(separator);
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
