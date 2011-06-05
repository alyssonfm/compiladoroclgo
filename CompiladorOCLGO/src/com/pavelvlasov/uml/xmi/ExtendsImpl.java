package com.pavelvlasov.uml.xmi;

import org.w3c.dom.Element;

public class ExtendsImpl extends ModelElementImpl {

	public ExtendsImpl(ClassifierImpl classifierImpl, Element item) {
		super(classifierImpl, item);
	}

	public ExtendsImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}

	private String extendsElement;
	
	
	public String getExtendedElement() {
		if (extendsElement == null && holder.hasAttribute("name")) {
			extendsElement = holder.getAttribute("name");
		}
		return extendsElement;
	}

}
