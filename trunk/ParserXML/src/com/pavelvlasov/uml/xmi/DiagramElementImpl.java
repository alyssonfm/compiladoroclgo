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

import org.w3c.dom.Element;

import com.pavelvlasov.uml.DiagramElement;
import com.pavelvlasov.uml.ModelElement;
import com.pavelvlasov.uml.Rectangle;

/**
 * @author Pavel Vlasov
 * @version $Revision$
 */
class DiagramElementImpl implements DiagramElement {
	private DiagramImpl diagram;
	private Element holder;
	private int left;
	private int right;
	private int top;
	private int bottom;
	
	private int imgLeft;
	private int imgRight;
	private int imgTop;
	private int imgBottom;
	
	private int seqno;
	
	private Rectangle diagramRectangle=new Rectangle() {

		public int getLeft() {
			return left;
		}

		public int getRight() {
			return right;
		}

		public int getTop() {
			return top;
		}

		public int getBottom() {
			return bottom;
		}		
	};
	
	private Rectangle imageRectangle=new Rectangle() {

		public int getLeft() {
			return imgLeft;
		}

		public int getRight() {
			return imgRight;
		}

		public int getTop() {
			return imgTop;
		}

		public int getBottom() {
			return imgBottom;
		}		
	};
	
	/**
	 * 
	 */
	public DiagramElementImpl(DiagramImpl d, Element holder) {
		this.diagram=d;
		this.holder=holder;
		parseGeometry();
		if (holder.hasAttribute("seqno")) {
			try {
				seqno=Integer.parseInt(holder.getAttribute("seqno"));
			} catch (NumberFormatException e) {
				System.err.println("Invalid seqno: "+holder.getAttribute("seqno"));
			}
		}
	}
	
	private void parseGeometry() {
		left=parseCoordinate("Left");
		right=parseCoordinate("Right");
		top=parseCoordinate("Top");
		bottom=parseCoordinate("Bottom");
		
		imgLeft=parseCoordinate("imgL");
		imgRight=parseCoordinate("imgR");
		imgTop=parseCoordinate("imgT");
		imgBottom=parseCoordinate("imgB");
		
	}
	
	private int parseCoordinate(String coordinate) {		
		int ei=getGeometry().indexOf(coordinate+"=");
		if (ei==-1) {
			return 0;
		} else {
			int sci=getGeometry().indexOf(";",ei);
			if (sci==-1) {
				return 0;
			} else {
				try {
					return Integer.parseInt(getGeometry().substring(ei+1+coordinate.length(),sci));
				} catch (NumberFormatException e) {
					System.err.println("Could not parse coordinate: "+coordinate+"\n in geometry: "+getGeometry()+"\n Exception: "+e);
					return 0;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.DiagramElement#getGeometry()
	 */
	public String getGeometry() {
		return holder.getAttribute("geometry");
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.DiagramElement#getSeqno()
	 */
	public int getSeqno() {
		return seqno;
	}

	private ModelElement subject;
	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.DiagramElement#getSubject()
	 */
	public ModelElement getSubject() {
		if (subject==null) {
			subject=(ModelElement) diagram.getModel().findElement(holder.getAttribute("subject"));
		}
		return subject;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.DiagramElement#getDiagramRectangle()
	 */
	public Rectangle getDiagramRectangle() {
		return diagramRectangle;
	}

	/* (non-Javadoc)
	 * @see com.pavelvlasov.uml.DiagramElement#getImageRectangle()
	 */
	public Rectangle getImageRectangle() {
		return imageRectangle;
	}
}
