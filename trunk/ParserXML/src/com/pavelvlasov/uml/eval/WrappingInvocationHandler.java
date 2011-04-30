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
 * Wraps returns from methods from PACKAGE_TO_PROXY which implement interfaces from
 * PACKAGE_TO_PROXY into invocation handlers stack. 
 * For collections creates a copy of a collection with 
 * wrapped elements.    
 * @author Pavel Vlasov	
 * @version $Revision$
 */
class WrappingInvocationHandler extends ChainedInvocationHandler implements InvocationHandler {

	private WrapperFactory factory;

	public WrappingInvocationHandler(InvocationHandler next, WrapperFactory factory) {
		super(next);
		this.factory=factory;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {		
		Object[] convertedArgs = args==null ? null : new Object[args.length];
		if (args!=null) {
			Class[] parameterTypes = method.getParameterTypes();			
			for (int i=0; i<args.length; i++) {
				if (isToProxy(parameterTypes[i])) {
					convertedArgs[i]=factory.convertParameter(args[i], parameterTypes[i]);
				} else {
					convertedArgs[i]=args[i];
				}
			}
		}
		
		Object ret=invokeNext(proxy, method, convertedArgs);
		return isToProxy(method) ? factory.wrap(ret) : ret;
	}	
}
