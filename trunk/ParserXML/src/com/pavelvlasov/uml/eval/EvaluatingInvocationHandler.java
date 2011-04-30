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
 * Evaluates returns from methods in PACKAGE_TO_PROXY which return String 
 * @author Pavel Vlasov
 * @version $Revision$
 */
class EvaluatingInvocationHandler extends ChainedInvocationHandler implements InvocationHandler {
	private Evaluator evaluator;
	
	EvaluatingInvocationHandler(InvocationHandler next, Evaluator evaluator) {
		super(next);
		this.evaluator=evaluator;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
		Object ret=invokeNext(proxy, method, args);
		if (ret!=null && String.class.equals(ret.getClass())) {
			String declaringClassName=method.getDeclaringClass().getName();
			int idx=declaringClassName.lastIndexOf('.');
			if (isToProxy(method)) {
				return evaluator.evaluate((String) ret, proxy);
			} 			
		} 
		return ret;
	}
}
