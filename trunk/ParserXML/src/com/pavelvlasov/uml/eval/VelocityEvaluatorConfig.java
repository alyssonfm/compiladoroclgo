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

import java.util.Map;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
public class VelocityEvaluatorConfig {
	private VelocityContextFactory contextFactory;
	private Map context;

	/**
	 * @return
	 */
	public Map getContext() {
		return context;
	}

	/**
	 * @return
	 */
	public VelocityContextFactory getContextFactory() {
		return contextFactory;
	}

	/**
	 * @param contextFactory
	 * @param context
	 */
	public VelocityEvaluatorConfig(
		VelocityContextFactory contextFactory,
		Map context) {
		super();
		this.contextFactory = contextFactory;
		this.context = context;
	}
}
