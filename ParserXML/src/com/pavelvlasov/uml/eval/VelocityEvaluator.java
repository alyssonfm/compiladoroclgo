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

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class VelocityEvaluator implements Evaluator {
	private VelocityEvaluatorConfig config;

	public VelocityEvaluator(VelocityEvaluatorConfig config) {		
		super();
		this.config=config;
	}

	public String evaluate(String expr, Object contextObject) throws EvaluationException {
		if (expr==null) {
			return "";
		} else {
			StringWriter sw = new StringWriter();
			VelocityContext context =
				(config == null || config.getContextFactory() == null)
					? new VelocityContext()
					: config.getContextFactory().newContext();
					
			if (config!=null && config.getContext()!=null) {
				Iterator it=config.getContext().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry entry=(Map.Entry) it.next();
					context.put((String) entry.getKey(), entry.getValue());
				}	
			}	
							
			try {
				context.put("this", contextObject);
				Velocity.evaluate(context, sw, contextObject.getClass().getName(), expr);
				sw.close();
				if (config!=null) {
					CachingInvocationHandler.doNotCacheCurrentResult();
				}
				return sw.toString();
			} catch (ParseErrorException e) {
				throw new EvaluationException(e);
			} catch (MethodInvocationException e) {
				throw new EvaluationException(e);
			} catch (ResourceNotFoundException e) {
				throw new EvaluationException(e);
			} catch (IOException e) {
				throw new EvaluationException(e);
			} finally {
				if (config!=null && config.getContextFactory()!=null) {
					config.getContextFactory().releaseContext(context);
				}
			}
		}
	}
}
