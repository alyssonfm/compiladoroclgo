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
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Wraps objects into evaluator and cacher. Evaluator evaluates returns
 * for all methods from com.pavelvlasov.uml package which return String.
 * Cacher caches returns for all methods without parameters.
 * @author Pavel Vlasov
 * @version $Revision$
 */
public class WrapperFactory {
	private Evaluator evaluator;
	
	// object -> wrapper
	private Map wrapMap=new HashMap();
	
	// wrapper -> object
	private Map reverseWrapMap=new HashMap();
	
	/**
	 * 
	 */
	public WrapperFactory(Evaluator evaluator) {
		super();
		this.evaluator=evaluator;
	}
	
	public Object wrap(Object obj) throws InstantiationException, IllegalAccessException {
		if (obj==null) {
			return null;
		}
		
		Object ret=wrapMap.get(obj);
		if (ret==null) {
			if (isToProxy(obj.getClass())) {
				InvocationHandler delegatingHandler=new DelegatingInvocationHandler(obj);
				InvocationHandler wrappingHandler=new WrappingInvocationHandler(delegatingHandler, this);
				InvocationHandler evaluatingHandler=new EvaluatingInvocationHandler(wrappingHandler, evaluator);
				InvocationHandler cachingHandler=new CachingInvocationHandler(evaluatingHandler);
				ret=Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), cachingHandler);
			} else if (obj instanceof Collection) {
				Collection retCol;
				
				try {
					retCol=(Collection) obj.getClass().newInstance();
				} catch (InstantiationException e) {
					retCol=new LinkedList((Collection) obj);
				} catch (IllegalAccessException e) {
					retCol=new LinkedList((Collection) obj);
				}
				
				Iterator it=((Collection) obj).iterator();
				while (it.hasNext()) {
					retCol.add(wrap(it.next()));
				}
				ret=retCol;
			} else {
				return obj;
			}
			wrapMap.put(obj, ret);
			reverseWrapMap.put(ret, obj);
		}
		return ret;
	}
	
	/**
	 * @param clazz
	 * @return true if clazz implements interfaces from com.pavelvlasov.uml package
	 */
	private static boolean isToProxy(Class clazz) {
		Class[] interfaces=clazz.getInterfaces();
		for (int i=0; i<interfaces.length; i++) {
			if (ChainedInvocationHandler.isToProxy(interfaces[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param wrapper
	 * @return
	 */
	Object convertParameter(Object arg, Class parameterType) {		
		Object ret=reverseWrapMap.get(arg);
		if (ret==null) { // wrap
			return Proxy.newProxyInstance(
				arg.getClass().getClassLoader(),
				new Class[] { parameterType },
				new ParameterInvocationHanlder(arg, this));
		} else { // unwrap proxy to the original object
			return ret;
		}  
	}
	
	/**
	 * Converts wrapper to original object. For collections
	 * converts creates a copy of collection and converts
	 * elements to original ones.
	 * @param wrapped
	 * @return original object or the argument if it is not a wrapper
	 */
	Object reverse(Object wrapper) throws InstantiationException, IllegalAccessException {
		if (wrapper==null) {
			return null;
		}
		
		Object ret=reverseWrapMap.get(wrapper);
		if (ret==null) {
			if (wrapper instanceof Collection) {
				Collection retCol;
				
				try {
					retCol=(Collection) wrapper.getClass().newInstance();
				} catch (InstantiationException e) {
					retCol=new LinkedList((Collection) wrapper);
				} catch (IllegalAccessException e) {
					retCol=new LinkedList((Collection) wrapper);
				}
				
				Iterator it=((Collection) wrapper).iterator();
				while (it.hasNext()) {
					retCol.add(reverse(it.next()));
				}
				return retCol;
			} else {
				return wrapper;
			}
		} else {
			return ret;
		}
	}

}
