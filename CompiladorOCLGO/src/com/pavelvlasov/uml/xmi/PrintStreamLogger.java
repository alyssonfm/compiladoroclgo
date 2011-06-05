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
package com.pavelvlasov.uml.xmi;

import java.io.PrintStream;

import com.pavelvlasov.uml.Element;

/**
 * @author Pavel Vlasov	
 * @version $Revision$
 */
public class PrintStreamLogger implements Logger {
	private PrintStream out;
	private PrintStream err;
		
	public PrintStreamLogger(PrintStream out, PrintStream err) {
		this.out=out;
		this.err=err;
	}

	public PrintStreamLogger() {
		this.out=System.out;
		this.err=System.err;
	}
	
	public void debug(Element element, String msg) {
		if (element==null) {
			out.println("DEBUG "+msg);
		} else {
			out.println("DEBUG ["+element.getClass().getName()+":"+element.getId()+"] "+msg);			
		}
	}

	public void verbose(Element element, String msg) {
		if (element==null) {
			out.println("VERBOSE "+msg);
		} else {
			out.println("VERBOSE ["+element.getClass().getName()+":"+element.getId()+"] "+msg);			
		}
	}

	public void info(Element element, String msg) {
		if (element==null) {
			out.println("INFO "+msg);
		} else {
			out.println("INFO ["+element.getClass().getName()+":"+element.getId()+"] "+msg);			
		}
	}

	public void warn(Element element, String msg) {
		if (element==null) {
			err.println("WARN "+msg);
		} else {
			err.println("WARN ["+element.getClass().getName()+":"+element.getId()+"] "+msg);			
		}
	}

	public void error(Element element, String msg) {
		if (element==null) {
			err.println("ERROR "+msg);
		} else {
			err.println("ERROR ["+element.getClass().getName()+":"+element.getId()+"] "+msg);			
		}
	}

	public void fatal(Element element, String msg) {
		if (element==null) {
			err.println("FATAL "+msg);
		} else {
			err.println("FATAL ["+element.getClass().getName()+":"+element.getId()+"] "+msg);			
		}
	}

	public void warn(Element element, String msg, Throwable th) {
		warn(element, msg);
		th.printStackTrace();
	}

	public void error(Element element, String msg, Throwable th) {
		error(element, msg);
		th.printStackTrace();
	}

	public void fatal(Element element, String msg, Throwable th) {
		fatal(element, msg);
		th.printStackTrace();
	}

}
