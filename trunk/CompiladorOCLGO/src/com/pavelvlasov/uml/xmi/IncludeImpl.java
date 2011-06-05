package com.pavelvlasov.uml.xmi;

import org.w3c.dom.Element;

public class IncludeImpl extends ModelElementImpl {

	public IncludeImpl(ClassifierImpl classifierImpl, Element item) {
		super(classifierImpl, item);
	}

	public IncludeImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}

	private String includedElement;


	public String getIncludedElement() {
		if (includedElement == null && holder.hasAttribute("addition")) {
			includedElement = holder.getAttribute("addition");
		}
		return includedElement;
	}

}
