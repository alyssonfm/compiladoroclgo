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
 * Converts invocation arguments to parameter into wrapped objects (proxies)
 * converts returned stuff back to internal object.
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class ParameterInvocationHanlder implements InvocationHandler {
	
	private Object parameter;
	private WrapperFactory factory;

	/**
	 * 
	 */
	public ParameterInvocationHanlder(Object parameter, WrapperFactory factory) {
		super();
		this.parameter=parameter;
		this.factory=factory;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
		Object[] convertedArgs = args==null ? null : new Object[args.length];
		if (args!=null) {
			for (int i=0; i<args.length; i++) {
				convertedArgs[i]=factory.wrap(args[i]);
			}
		}
				
//		Method targetMethod =
//			parameter.getClass().getMethod(method.getName(), method.getParameterTypes());
		return factory.reverse(method.invoke(parameter, convertedArgs));
	}
}
