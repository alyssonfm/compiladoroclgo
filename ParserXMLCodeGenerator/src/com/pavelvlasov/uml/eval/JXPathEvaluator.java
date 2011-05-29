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
package com.pavelvlasov.uml.eval;

import org.apache.commons.jxpath.JXPathContext;

import com.pavelvlasov.uml.Element;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class JXPathEvaluator implements Evaluator {
	private JXPathParentContextResolver parentContextResolver;
	
	
	public String evaluate(String expr, Object contextObject) {
		Object res=null;
		if (contextObject instanceof Element) {
			res=((Element) contextObject).navigate(expr);			
		} else {
			JXPathContext parentContext =
				parentContextResolver == null
					? null
					: parentContextResolver.resolveParentContext(contextObject);

			/*
			 * Don't cache results if parent context resolver is not null
			 */
			if (parentContextResolver!=null) {
				CachingInvocationHandler.doNotCacheCurrentResult();
			}
			
			JXPathContext context=parentContext==null ? JXPathContext.newContext(contextObject) : JXPathContext.newContext(parentContext, contextObject);
			res=context.getValue(expr);
		}
		return res==null ? "" : res.toString();
	}
	
	/**
	 * @param parentContextResolver
	 */
	public JXPathEvaluator(JXPathParentContextResolver parentContextResolver) {
		super();
		this.parentContextResolver = parentContextResolver;
	}
}
