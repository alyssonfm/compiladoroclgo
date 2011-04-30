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

import java.util.Iterator;
import java.util.Map;

import FESI.jslib.JSException;
import FESI.jslib.JSGlobalObject;
import FESI.jslib.JSUtil;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class FesiEvaluator implements Evaluator {
	private JSGlobalObject global;
	private WriteFesiExtension writeExtension=new WriteFesiExtension();

	public FesiEvaluator(FesiEvaluatorConfig config) throws JSException {
		super();		
		global=config.getExtensions()==null ? JSUtil.makeEvaluator() : JSUtil.makeEvaluator(config.getExtensions());
		if (config.getGlobalObjects()!=null) {
			Iterator it=config.getGlobalObjects().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry=(Map.Entry) it.next();
				global.setMember((String) entry.getKey(), entry.getValue());
			}
		}
		writeExtension.initializeExtension(global);
	}

	public FesiEvaluator() throws JSException {
		super();
		global=JSUtil.makeEvaluator();
		writeExtension.initializeExtension(global);
	}

	public String evaluate(String expr, Object contextObject) throws EvaluationException {
		try {
			writeExtension.reset();
			Object res=global.makeObjectWrapper(contextObject).eval(expr);
			if (writeExtension.isObtained()) {
				return writeExtension.getOutput();
			} else {
				return res==null ? "" : res.toString();
			}
		} catch (JSException e) {
			throw new EvaluationException(e);            
		}
	}
}
