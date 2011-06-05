package com.pavelvlasov.uml.xmi;

import org.w3c.dom.Element;

public class ExtensionPointImpl extends ModelElementImpl {

	public ExtensionPointImpl(ClassifierImpl classifierImpl, Element item) {
		super(classifierImpl, item);
	}

	public ExtensionPointImpl(ModelImpl model, Element holder) {
		super(model, holder);
	}

	private String extendsElement;
	private String id;

	public String getExtendedElement() {
		if (extendsElement == null && holder.hasAttribute("extendedCase")) {
			extendsElement = holder.getAttribute("extendedCase");
		}
		return extendsElement;
	}

}
