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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.xpath.CachedXPathAPI;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;

import util.AuxiliaryFunctionsXML;
import util.Constants;

import com.pavelvlasov.uml.Acceptor;
import com.pavelvlasov.uml.Classifier;
import com.pavelvlasov.uml.Model;
import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.UmlException;
import com.pavelvlasov.uml.Visitor;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class ModelImpl implements Model {
	private Element holder;
	private Map elementsMap = new HashMap();
	private CachedXPathAPI cachedXPathAPI;
	private String toolName;
	private String toolVersion;
	private Logger logger;
	private boolean loaded = false;
	private AspectResolver aspectResolver;

	ModelImpl(Element holder, CachedXPathAPI cachedXPathAPI, Acceptor acceptor) {
		this(holder, cachedXPathAPI, acceptor, new PrintStreamLogger());
	}

	ModelImpl(Element holder, CachedXPathAPI cachedXPathAPI, Acceptor acceptor,
			Logger logger) {
		this.holder = holder;
		this.cachedXPathAPI = cachedXPathAPI;
		this.logger = logger;
		try {
			loadDataTypes(acceptor);
			loadPackages(acceptor);
			loadClasses(acceptor);
		} catch (UmlException e) {
			throw e;
		} catch (Exception e) {
			throw new UmlException("Can't load model packages: " + e, e,
					getId());
		}
		loaded = true;
	}

	private void loadPackages(Acceptor acceptor) throws TransformerException {
		packages = new LinkedList();
		NodeIterator nit = cachedXPathAPI.selectNodeIterator(holder,
				"packagedElement");
		Element pe;
		while ((pe = (Element) nit.nextNode()) != null) {
			if (AuxiliaryFunctionsXML.equalsNodeType(pe, Constants.PACKAGE)
					&& !pe.hasAttribute("href")) {
				PackageImpl pkg = new PackageImpl(this, pe, acceptor);
				if (acceptor == null || acceptor.accept(pkg)) {
					packages.add(pkg);
					putElements(pkg.getAllElements());
				}
			}
		}
	}

	/**
	 * 
	 */
	private void loadDataTypes(Acceptor acceptor) throws TransformerException {
		NodeIterator nit = cachedXPathAPI.selectNodeIterator(holder, "Model");
		Element pe;
		while ((pe = (Element) nit.nextNode()) != null) {
			ClassifierImpl dataType = new ClassifierImpl(this, pe, acceptor);
			elementsMap.put(dataType.getId(), dataType);
			getLogger()
					.debug(dataType, "Loaded datatype " + dataType.getName());
		}
	}

	private Collection packages;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Model#packages()
	 */
	public Collection getPackages() {
		return packages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Model#getName()
	 */
	public String getName() {
		return holder.getAttribute("name");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Model#getId()
	 */
	public String getId() {
		return holder.getAttribute("xmi.id");
	}

	public com.pavelvlasov.uml.Element findElement(String id) {
		if (!loaded) {
			throw new UmlException("Model is not fully loaded.");
		}

		return (com.pavelvlasov.uml.Element) elementsMap.get(id);
	}

	void putElements(Collection elements) {
		Iterator it = elements.iterator();
		while (it.hasNext()) {
			com.pavelvlasov.uml.Element element = (com.pavelvlasov.uml.Element) it
					.next();
			elementsMap.put(element.getId(), element);
		}
	}

	Collection getModelElements() {
		return Collections.unmodifiableCollection(elementsMap.values());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Model#accept(com.pavelvlasov.uml.Visitor)
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
		Iterator it = packages.iterator();
		while (it.hasNext()) {
			((ModelElement) it.next()).accept(visitor);
		}
	}

	CachedXPathAPI getCachedXPathAPI() {
		return cachedXPathAPI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Model#getToolName()
	 */
	public String getToolName() {
		return toolName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Model#getToolVersion()
	 */
	public String getToolVersion() {
		return toolVersion;
	}

	Logger getLogger() {
		return logger;
	}

	/**
	 * @return Returns the aspectResolver.
	 */
	AspectResolver getAspectResolver() {
		return aspectResolver;
	}

	private void loadClasses(Acceptor acceptor) throws TransformerException {
		classes = new LinkedList();
		NodeIterator nit = cachedXPathAPI.selectNodeIterator(holder,
				"ownedMember");
		Element pe;
		while ((pe = (Element) nit.nextNode()) != null) {
			if (AuxiliaryFunctionsXML.equalsNodeType(pe, Constants.CLASS)
					&& !pe.hasAttribute("href")) {
				Classifier Class = new ClassifierImpl(this, pe, acceptor);
				if (acceptor == null || acceptor.accept(Class)) {
					classes.add(Class);
					elementsMap.put(Class.getId(), Class);
				}
			}
		}
	}

	private Collection classes;

	public Collection getClasses() {
		// TODO Auto-generated method stub
		return classes;
	}

}
