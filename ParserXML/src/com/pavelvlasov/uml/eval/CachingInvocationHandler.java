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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
public class CachingInvocationHandler extends ChainedInvocationHandler implements InvocationHandler {
	private Map resultsMap=new HashMap();
	
	private static ThreadLocal cacheFlag=new ThreadLocal() {
		protected Object initialValue() {
			return Boolean.TRUE;
		}
	};
	
	/**
	 * Disables result caching for current invocation. 
	 * Call this method if evaluation result depends on external context,
	 * such as parent Velocity context.
	 */
	public static void doNotCacheCurrentResult() {
		cacheFlag.set(Boolean.FALSE);
	}
	
	private static class ResultEntry {
		Object result;
		
		ResultEntry(Object result) {
			this.result=result;
		}
	}
	
	/**
	 * 
	 */
	public CachingInvocationHandler(InvocationHandler next) {
		super(next);
	}

	/**
	 * Caches results only for methods from com.pavelvlasov.uml and without parameters
	 * Ignores proxy parameter - proxy-handler mapping is set at another level. 
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {
			if (args==null && isToProxy(method)) {
				ResultEntry re=(ResultEntry) resultsMap.get(method);
				if (re==null) {
					cacheFlag.set(Boolean.TRUE);
					Object result=invokeNext(proxy, method, args);
					if (Boolean.TRUE.equals(cacheFlag.get())) {
						re=new ResultEntry(result);
						resultsMap.put(method, re);
					}
					return result;
				} else {				
					return re.result;
				}
			} else {
				return invokeNext(proxy, method, args);
			}
	}

}
