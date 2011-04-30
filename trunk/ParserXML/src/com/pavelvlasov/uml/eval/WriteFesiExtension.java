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

import FESI.Data.ESUndefined;
import FESI.jslib.JSException;
import FESI.jslib.JSExtension;
import FESI.jslib.JSFunctionAdapter;
import FESI.jslib.JSGlobalObject;
import FESI.jslib.JSObject;

/**
 * Introduces write and writeln methods to script environment.
 * @author Pavel Vlasov
 * @version $Revision$
 */
public class WriteFesiExtension implements JSExtension {
	private StringBuffer output;
	
	/**
	 * 
	 * @return true if write or writeln were called
	 */
	public boolean isObtained() {
		return output!=null;	
	}
	
	/**
	 * 
	 * @return output as String
	 */
	public String getOutput() {
		return output==null ? null : output.toString();
	}
	
	/**
	 * Resets output. After this call isObtained returns false and getOutput returns null
	 *
	 */
	public void reset() {
		output=null;
	}
	
    
    public void initializeExtension(final JSGlobalObject global) throws JSException {
        global.setMember("writeln",
        new JSFunctionAdapter() {
            public Object doCall(JSObject thisObject, Object args[]) throws JSException {
                if (args.length == 0) {
                    throw new JSException("writeln: At least one argument needed.");
                }

				if (output==null) {
					output=new StringBuffer();
				}                
				
                for (int i = 0; i<args.length; i++) {
                    output.append(args[i]);
                }
                
                output.append(System.getProperty("line.separator"));
                
                //return buf.toString();
                return ESUndefined.theUndefined;
            }
        });
        
        global.setMember("write",
        new JSFunctionAdapter() {
            public Object doCall(JSObject thisObject, Object args[]) throws JSException {
				if (args.length == 0) {
					throw new JSException("write: At least one argument needed.");
				}

				if (output==null) {
					output=new StringBuffer();
				}                
				
				for (int i = 0; i<args.length; i++) {
					output.append(args[i]);
				}
                
				//return buf.toString();
				return ESUndefined.theUndefined;
            }
        });
    }
}
