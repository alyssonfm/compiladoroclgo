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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class PrefixedCompositeEvaluator implements Evaluator {
	private Map evaluators=new HashMap();
	private Evaluator defaultEvaluator;
	
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.eval.Evaluator#evaluate(java.lang.String, java.lang.Object)
	 */
	public String evaluate(String expr, Object context) throws EvaluationException {
		Iterator it=evaluators.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry=(Map.Entry) it.next();
			if (expr.startsWith((String) entry.getKey())) {
				return ((Evaluator) entry.getKey()).evaluate(expr.substring(((String) entry.getKey()).length()), context);
			}
		}
		
		if (defaultEvaluator==null) {
			return expr;
		} else {
			return defaultEvaluator.evaluate(expr, context);
		}
	}

	/**
	 * 
	 * @param prefix Prefix. If prefix is null then evaluator is 
	 * a default evaluator which is used if no prefixes match.
	 * @param evaluator
	 */
	public void setEvaluator(String prefix, Evaluator evaluator) {
		if (prefix==null) {
			defaultEvaluator=evaluator;
		} else {
			evaluators.put(prefix, evaluator);
		}
	}
}
