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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
abstract class ChainedInvocationHandler implements InvocationHandler {

	private InvocationHandler next;

	protected ChainedInvocationHandler(InvocationHandler next) {
		super();
		this.next=next;
	}

	protected Object invokeNext(Object proxy, Method method, Object[] args)
		throws Throwable {
		return next.invoke(proxy, method, args);
	}

	static final String PACKAGE_TO_PROXY = "com.pavelvlasov.uml";
	
	protected boolean isToProxy(Method method) {
		return isToProxy(method.getDeclaringClass());
	}
	
	protected static boolean isToProxy(Class clazz) {
		String declaringClassName=clazz.getName();
		int idx=declaringClassName.lastIndexOf('.');
		return idx!=-1 && PACKAGE_TO_PROXY.equals(declaringClassName.substring(0,idx));		
	}
}
