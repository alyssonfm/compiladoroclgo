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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import util.xmi.AuxiliaryFunctionsXML;
import util.xmi.Constants;

import com.pavelvlasov.uml.Acceptor;
import com.pavelvlasov.uml.Aspect;
import com.pavelvlasov.uml.AspectInstance;
import com.pavelvlasov.uml.Association;
import com.pavelvlasov.uml.Attribute;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Dependency;
import com.pavelvlasov.uml.Generalization;
import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Parameter;
import com.pavelvlasov.uml.Realization;
import com.pavelvlasov.uml.UmlException;
import com.pavelvlasov.uml.Visitor;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class ClassifierImpl extends ModelElementImpl implements Classifier {

	/**
	 * @param owner
	 * @param holder
	 */

	public ClassifierImpl(ModelElementImpl owner, Element holder,
			Acceptor acceptor) {
		super(owner, holder);
		loadElements(acceptor);
		loadGeneralizationsIncludesExtends(holder);
	}

	public ClassifierImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
	}

	public ClassifierImpl(ModelImpl owner, Element holder) {
		super(owner, holder);
	}

	/**
	 * @param model
	 * @param holder
	 */
	public ClassifierImpl(ModelImpl model, Element holder, Acceptor acceptor) {
		super(model, holder);
		loadElements(acceptor);
		loadGeneralizationsIncludesExtends(holder);

	}

	Collection includes;

	public Collection getIncludes() {
		return includes;
	}

	private void addClass(Classifier classifier) {
		classes.add(classifier);
	}

	public Collection<Classifier> getClasses() {
		return classes;
	}

	Collection<Classifier> classes = new LinkedList<Classifier>();

	Collection Extends;
	Collection extensionPoints;

	public Collection getExtensionPoints() {
		return extensionPoints;
	}

	private void loadGeneralizationsIncludesExtends(Element holder) {
		NodeList list = holder.getChildNodes();

		if (generalizations == null) {
			generalizations = new LinkedList();
		}
		if (includes == null) {
			includes = new LinkedList();
		}
		if (Extends == null) {
			Extends = new LinkedList();
		}
		if (extensionPoints == null) {
			extensionPoints = new LinkedList();
		}

		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).getNodeName().equals(
					Constants.GENERALIZATION.toLowerCase())) {
				generalizations.add(new GeneralizationImpl(this, (Element) list
						.item(i)));
			}
			if (list.item(i).getNodeName().equals(
					Constants.INCLUDE.toLowerCase())) {
				includes.add(new IncludeImpl(this, (Element) list.item(i)));
			}
			if (list.item(i).getNodeName().equals(
					Constants.EXTEND.toLowerCase())) {
				Extends.add(new ExtendsImpl(this, (Element) list.item(i)));
			}
			if (list.item(i).getNodeName().equals(Constants.EXTENSION_POINT)) {
				extensionPoints.add(new ExtensionPointImpl(this, (Element) list
						.item(i)));
			}
		}

		// System.out.println(listGeneralization.getLength());

	}

	private void loadAttributes() {
		if (attributes == null) {
			attributes = new LinkedList();
			try {
				NodeIterator nit = getModel().getCachedXPathAPI()
						.selectNodeIterator(holder, "ownedAttribute");
				Element ae;
				while ((ae = (Element) nit.nextNode()) != null) {
					AttributeImpl attribute = new AttributeImpl(this, ae);
					attributes.add(attribute);
					// }
				}
			} catch (Exception e) {
				throw new UmlException("Can't load attributes for classifier "
						+ getId() + ": " + e, e, getId());
			}
		}
	}

	private void loadOperations() {
		if (operations == null) {
			operations = new LinkedList();
			try {
				NodeIterator nit = getModel().getCachedXPathAPI()
						.selectNodeIterator(holder, "ownedOperation");
				Element oe;
				while ((oe = (Element) nit.nextNode()) != null) {
					OperationImpl operation = new OperationImpl(this, oe);
					if (operation.isComplete()) {
						operations.add(operation);
					}
				}
			} catch (Exception e) {
				throw new UmlException("Can't load operations for classifier "
						+ getId() + ": " + e, e, getId());
			}
		}
	}

	private Collection associationEnds;
	private Collection attributes;
	private Collection operations;
	private Collection clientDependencies;
	private Collection supplierDependencies;
	private Collection generalizations;
	private Collection realizations;

	public Collection getAssociationEnds() {
		if (associationEnds == null) {
			associationEnds = new LinkedList();
			Iterator it = getModel().getModelElements().iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Association) {
					Association a = (Association) o;
					if (a.getSource().getClassifier() == this) {
						if (a.getTarget().getClassifier() != null) {
							getModel()
									.getLogger()
									.debug(
											this,
											"Association "
													+ a.getId()
													+ " target classifier is null, skipped");
						} else {
							associationEnds.add(a.getSource());
						}
					}

					if (a.getTarget().getClassifier() == this) {
						if (a.getSource().getClassifier() == null) {
							getModel()
									.getLogger()
									.debug(
											this,
											"Association "
													+ a.getId()
													+ " source classifier is null, skipped");
						} else {
							associationEnds.add(a.getTarget());
						}
					}
				}
			}
		}

		return associationEnds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#attributes()
	 */
	public Collection getAttributes() {
		loadAttributes();
		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#dependencies()
	 */
	public Collection getClientDependencies() {
		loadDependencies();
		return clientDependencies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getSupplierDependencies()
	 */
	public Collection getSupplierDependencies() {
		loadDependencies();
		return supplierDependencies;
	}

	private void loadDependencies() {
		if (clientDependencies == null) {
			clientDependencies = new LinkedList();
			supplierDependencies = new LinkedList();
			Iterator it = getModel().getModelElements().iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Dependency) {
					Dependency d = (Dependency) o;
					if (d.getClient() == this) {
						if (d.getSupplier() == null) {
							getModel().getLogger().debug(
									this,
									"Dependency " + d.getId()
											+ " supplier is null, skipped");
						} else {
							clientDependencies.add(d);
						}
					}

					if (d.getSupplier() == this) {
						if (d.getClient() == null) {
							getModel().getLogger().debug(
									this,
									"Dependency " + d.getId()
											+ " client is null, skipped");
						} else {
							supplierDependencies.add(d);
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#generalizations()
	 */
	public Collection getGeneralizations() {
		/*
		 * if (generalizations == null) { generalizations = new LinkedList();
		 * Iterator it = getModel().getModelElements().iterator(); while
		 * (it.hasNext()) { // System.out.println("passou"); Object o =
		 * it.next(); if (o instanceof Generalization) { Generalization g =
		 * (Generalization) o; if (g.getSubtype() == this) { if
		 * (g.getSupertype() == null) { getModel().getLogger().debug( this,
		 * "Generalization " + g.getId() + " supertype is null, skipped"); }
		 * else { generalizations.add(g); } } } } }
		 */

		return generalizations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#operations()
	 */
	public Collection getOperations() {
		loadOperations();
		return operations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#realizations()
	 */
	public Collection getRealizations() {
		if (realizations == null) {
			realizations = new LinkedList();
			Iterator it = getModel().getModelElements().iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Realization) {
					Realization r = (Realization) o;
					if (r.getSubtype() == this) {
						if (r.getSupertype() == null) {
							getModel().getLogger().debug(
									this,
									"Realization " + r.getId()
											+ " supertype is null, skipped");
						} else {
							realizations.add(r);
						}
					}
				}
			}
		}

		return realizations;
	}

	private Collection implementsList;
	private Classifier extendsClassifier;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getExtends()
	 */
	public Collection getExtends() {
		// loadImplements();
		return Extends;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getImplements()
	 */
	public Collection getImplements() {
		loadImplements();
		return implementsList;
	}

	private void loadImplements() {
		if (implementsList == null) {
			implementsList = new LinkedList();
			Iterator it = getGeneralizations().iterator();
			while (it.hasNext()) {
				Generalization g = (Generalization) it.next();
				// if (extendsClassifier == null)
				/* && g.getSupertype().getType().equals(getType())) */{
					// extendsClassifier = g.getSupertype();
					// } else {
					// implementsList.add(g.getSupertype());
				}
			}

			it = getRealizations().iterator();
			while (it.hasNext()) {
				Realization r = (Realization) it.next();
				implementsList.add(r.getSupertype());
			}
		}
	}

	private Collection imports;

	/**
	 * @see com.pavelvlasov.uml.Classifier#getImports()
	 */
	public Collection getImports() {
		if (imports == null) {
			Set ids = new HashSet();
			imports = new LinkedList();

			// attribute types
			Iterator it = getAttributes().iterator();
			while (it.hasNext()) {
				Attribute a = (Attribute) it.next();
				Classifier c = a.getClassifier();
				if (c != null && !ids.contains(c.getId())
						&& !getOwner().equals(getOwner())) {
					ids.add(c.getId());
					imports.add(c);
				}
			}

			// dependencies
			it = getClientDependencies().iterator();
			while (it.hasNext()) {
				Dependency d = (Dependency) it.next();
				Classifier c = d.getSupplier();
				if (!getOwner().equals(c.getOwner())
						&& !ids.contains(c.getId())) {
					ids.add(c.getId());
					imports.add(c);
				}
			}

			// associaton ends ****isinbound
			/*
			 * it = getAssociationEnds().iterator(); while (it.hasNext()) {
			 * AssociationEnd end = (AssociationEnd) it.next(); Classifier c =
			 * end.getOtherEnd().getClassifier(); if
			 * (end.getOtherEnd().isInbound() &&
			 * !getOwner().equals(c.getOwner()) && !ids.contains(c.getId())) {
			 * ids.add(c.getId()); imports.add(c); } }
			 */

			// operations
			it = getOperations().iterator();
			while (it.hasNext()) {
				Operation o = (Operation) it.next();
				Iterator pit = o.getParameters().iterator();
				while (pit.hasNext()) {
					Parameter p = (Parameter) pit.next();
					Classifier c = p.getClassifier();
					if (c != null && !getOwner().equals(c.getOwner())
							&& !ids.contains(c.getId())) {
						ids.add(c.getId());
						imports.add(c);
					}
				}
			}
		}

		return imports;
	}

	private Collection files;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getFiles()
	 */
	public Collection getFiles() {
		if (files == null) {
			files = new LinkedList();
			try {
				NodeIterator nit = getModel()
						.getCachedXPathAPI()
						.selectNodeIterator(
								holder,
								"/XMI/XMI.extensions[@xmi.extender='Enterprise Architect 2.5']/EAModel.file/EAFile[@subject='"
										+ getId() + "']");
				Element fe;
				while ((fe = (Element) nit.nextNode()) != null) {
					files.add(new FileImpl(this, fe));
				}
			} catch (Exception e) {
				throw new UmlException("Can't load files for classifier "
						+ getId() + ": " + e);
			}
		}
		return files;
	}

	private Collection requirements;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getRequirements()
	 */
	public Collection getRequirements() {
		if (requirements == null) {
			try {
				requirements = new LinkedList();
				NodeIterator nit = getModel().getCachedXPathAPI()
						.selectNodeIterator(holder,
								"ModelElement.requirement/Dependency");
				Element re;
				while ((re = (Element) nit.nextNode()) != null) {
					requirements.add(new RequirementImpl(this, re));
				}
			} catch (Exception e) {
				throw new UmlException(
						"Can't load requirements for classifier " + getId()
								+ ": " + e, e, getId());
			}
		}

		return requirements;
	}

	private Collection scenarios;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getScenarios()
	 */
	public Collection getScenarios() {
		if (scenarios == null) {
			try {
				scenarios = new LinkedList();
				NodeIterator nit = getModel()
						.getCachedXPathAPI()
						.selectNodeIterator(
								holder,
								"/XMI/XMI.extensions[@xmi.extender='Enterprise Architect 2.5']/EAModel.scenario/EAScenario[@subject='"
										+ getId() + "']");
				Element se;
				while ((se = (Element) nit.nextNode()) != null) {
					scenarios.add(new ScenarioImpl(this, se));
				}
			} catch (Exception e) {
				throw new UmlException("Can't load scenarios for classifier "
						+ getId() + ": " + e, e, getId());
			}
		}

		return scenarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#isAbstract()
	 */
	public boolean isAbstract() {
		return "true".equals(holder.getAttribute("isAbstract"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#isActive()
	 */
	public boolean isActive() {
		return "true".equals(holder.getAttribute("isActive"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#isLeaf()
	 */
	public boolean isLeaf() {
		return "true".equals(holder.getAttribute("isLeaf"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#isRoot()
	 */
	public boolean isRoot() {
		return "true".equals(holder.getAttribute("isRoot"));
	}

	private Collection diagrams;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getDiagrams()
	 */
	public Collection getDiagrams() {
		if (diagrams == null) {
			try {
				diagrams = new LinkedList();
				NodeIterator nit = getModel().getCachedXPathAPI()
						.selectNodeIterator(
								holder,
								"/XMI/XMI.content/Diagram[@owner='" + getId()
										+ "']");
				Element de;
				while ((de = (Element) nit.nextNode()) != null) {
					diagrams.add(new DiagramImpl(this, de));
				}
			} catch (Exception e) {
				throw new UmlException("Can't load diagrams for classifier "
						+ getId() + ": " + e, e, getId());
			}
		}

		return diagrams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pavelvlasov.uml.Classifier#isAspectedBy(com.pavelvlasov.uml.Aspect)
	 */
	public boolean isAspectedBy(Aspect aspect) {
		Iterator it = getAspectInstances().iterator();
		while (it.hasNext()) {
			AspectInstance ai = (AspectInstance) it.next();
			if (ai.getId() == aspect.getId()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pavelvlasov.uml.Classifier#isTypeOf(com.pavelvlasov.uml.Classifier)
	 */
	public boolean isTypeOf(Classifier base) {
		if (base.getId() == getId()) {
			return true;
		}

		Iterator it = getGeneralizations().iterator();
		while (it.hasNext()) {
			Generalization g = (Generalization) it.next();
			// if (g.getSupertype().isTypeOf(base)) {
			// return true;
			// }
		}

		it = getRealizations().iterator();
		while (it.hasNext()) {
			Realization r = (Realization) it.next();
			if (r.getSupertype().isTypeOf(base)) {
				return true;
			}
		}

		return false;
	}

	private Collection aspectInstances;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Classifier#getAspectInstances()
	 */
	public Collection getAspectInstances() {
		if (aspectInstances == null) {
			aspectInstances = getModel().getAspectResolver()
					.getAspectInstances(this);
		}
		return aspectInstances;
	}

	private Collection allElements = new LinkedList();
	private Collection elements;

	Collection getAllElements() {
		return allElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Package#elements()
	 */
	public Collection getElements() {
		return elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Package#elements(java.lang.String,
	 * java.lang.String) OK.
	 */
	public Collection getElements(String type) {
		Collection ret = new LinkedList();
		Iterator eit = elements.iterator();
		while (eit.hasNext()) {
			ModelElement me = (ModelElement) eit.next();
			if ((type == null || type.equals(me.getType()))) {
				ret.add(me);
			}
		}
		return ret;
	}

	private void loadElements(Acceptor acceptor) {
		allElements.add(this);
		try {
			elements = new LinkedList();
			NodeIterator nit = getModel().getCachedXPathAPI()
					.selectNodeIterator(holder, "packagedElement");
			Node n;
			while ((n = nit.nextNode()) != null) {
				if (n instanceof Element) {
					Element el = (Element) n;
					ModelElement me;
					if (AuxiliaryFunctionsXML.equalsNodeType(el,
							Constants.PACKAGE)) {
						me = new PackageImpl(this, el, acceptor);
					} else if (AuxiliaryFunctionsXML.equalsNodeType(el,
							Constants.ASSOCIATION)) {
						me = new AssociationImpl(this, el);
					} else if (AuxiliaryFunctionsXML.equalsNodeType(el,
							Constants.CLASS)
							|| AuxiliaryFunctionsXML.equalsNodeType(el,
									Constants.ENUMERATION)) {
						me = new ClassifierImpl(this, el, acceptor);
						if (acceptor == null || acceptor.accept(me)) {
							addClass((Classifier) me);
						}

					} else {
						me = new ClassifierImpl(this, el, acceptor);
					}

					if (acceptor == null || acceptor.accept(me)) {
						elements.add(me);
						if (me instanceof PackageImpl) {
							allElements.addAll(((PackageImpl) me)
									.getAllElements());
						} else {
							allElements.add(me);
						}
					}
				}
			}
		} catch (UmlException e) {
			throw e;
		} catch (Exception e) {
			throw new UmlException("Can't load model packages: " + e, e,
					getId());
		}
	}

	private Map classifierRoles = new HashMap();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.ModelElement#accept(com.pavelvlasov.uml.Visitor)
	 */
	public void accept(Visitor visitor) {
		super.accept(visitor);

		Iterator oit = getOperations().iterator();
		while (oit.hasNext()) {
			ModelElement me = (ModelElement) oit.next();
			me.accept(visitor);
		}

		Iterator ait = getAttributes().iterator();
		while (ait.hasNext()) {
			ModelElement me = (ModelElement) ait.next();
			me.accept(visitor);
		}

		Iterator eit = getElements().iterator();
		while (eit.hasNext()) {
			ModelElement me = (ModelElement) eit.next();
			me.accept(visitor);
		}
	}

	protected ClassifierRole findClassifierRole(String id) {
		return (ClassifierRole) classifierRoles.get(id);
	}

	private void loadClassifierRoles(Element collaborationHolder) {
		try {
			classifierRoles = new HashMap();
			NodeIterator nit = getModel().getCachedXPathAPI()
					.selectNodeIterator(collaborationHolder,
							"Namespace.ownedElement/ClassifierRole");
			Element el;
			while ((el = (Element) nit.nextNode()) != null) {
				ClassifierRole cr = new ClassifierRole(this, el);
				classifierRoles.put(cr.getId(), cr);
			}
		} catch (UmlException e) {
			throw e;
		} catch (Exception e) {
			throw new UmlException("Can't load model packages: " + e, e,
					getId());
		}
	}

	public Collection getAdvices(String operationName) {
		return getAdvices(operationName, new LinkedList());
	}

	public Collection getAdvices(String operationName,
			Classifier parameter1Classifier) {
		return getAdvices(operationName, Arrays
				.asList(new Classifier[] { parameter1Classifier }));
	}

	public Collection getAdvices(String operationName,
			Classifier parameter1Classifier, Classifier parameter2Classifier) {
		return getAdvices(operationName, Arrays.asList(new Classifier[] {
				parameter1Classifier, parameter2Classifier }));
	}

	public Collection getAdvices(String operationName,
			Collection parameterClassifiers) {
		Collection ret = new LinkedList();
		Iterator it = getAspectInstances().iterator();
		while (it.hasNext()) {
			AspectInstance ai = (AspectInstance) it.next();
			ret.addAll(ai.getAdvices(operationName, parameterClassifiers));
		}

		return ret;
	}
}
