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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import util.AuxiliaryFunctionsXML;
import util.ConstantsXML;

import com.pavelvlasov.uml.Advice;
import com.pavelvlasov.uml.Operation;
import com.pavelvlasov.uml.Parameter;
import com.pavelvlasov.uml.UmlException;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class OperationImpl extends ModelElementImpl implements Operation {

	/**
	 * @param owner
	 * @param holder
	 */
	public OperationImpl(ModelElementImpl owner, Element holder) {
		super(owner, holder);
		setType("");
	}

	/**
	 * @param model
	 * @param holder
	 */
	public OperationImpl(ModelImpl model, Element holder) {
		super(model, holder);
		setType("");
		if (holder.hasAttribute("concurrency")) {
			concurrency = holder.getAttribute("concurrency");
		}

		isQuery = "true".equals(holder.getAttribute("isQuery"));
	}

	private Collection parameters;

	public Collection getParameters() {
		if (parameters == null) {
			parameters = new LinkedList();
			try {
				NodeIterator nit = getModel().getCachedXPathAPI()
						.selectNodeIterator(holder, "ownedParameter");
				Element pe;
				while ((pe = (Element) nit.nextNode()) != null) {
					if (pe.hasAttribute("direction")) {
						if (pe.getAttribute("direction").equals("return")) {
							NodeList nodeList = pe.getElementsByTagName("type");
							if (nodeList.getLength() > 0) {
								setType(AuxiliaryFunctionsXML
										.filterAttributeType(nodeList.item(0)
												.getAttributes().item(0)
												.toString()));
							} else {
								setType(pe.getAttribute(ConstantsXML.TYPE_ONLY));
							}
						}
					}
					if (!pe.hasAttribute("direction")) {
						parameters.add(new ParameterImpl(this, pe));
					} else if (pe.hasAttribute("direction")) {
						if (!pe.getAttribute("direction").equals("return")) {
							parameters.add(new ParameterImpl(this, pe));
						}
					}
				}

			} catch (Exception e) {
				throw new UmlException("Can't load parameters for operation "
						+ getId() + ": " + e, e, getId());
			}
		}
		return parameters;
	}

	private String concurrency;
	private boolean isQuery;

	public String getConcurrency() {
		return concurrency;
	}

	public boolean isQuery() {
		return isQuery;
	}

	/**
	 * Indicates that all parameters and return value has classifiers
	 */
	boolean isComplete() {
		Iterator it = getParameters().iterator();
		while (it.hasNext()) {
			Parameter parameter = (Parameter) it.next();
			// if (parameter.getClassifier() == null) {
			// getModel().getLogger().debug(
			// this,
			// "Parameter " + parameter.getName()
			// + " classifier is null, skipped.");
			// return false;
			// }
		}
		return true;
	}

	private Collection advices;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Operation#getAdvices()
	 */
	public Collection getAdvices() {
		if (advices == null) {
			advices = getModel().getAspectResolver().getAdvices(this);
		}

		return advices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pavelvlasov.uml.Operation#getAdvices(java.lang.String)
	 */
	public Collection getAdvices(String adviceType) {
		Collection ret = new LinkedList();
		Iterator it = getAdvices().iterator();
		while (it.hasNext()) {
			Advice a = (Advice) it.next();
			if (adviceType.equals(a.getAdviceType())) {
				ret.add(a);
			}
		}
		return ret;
	}

}
