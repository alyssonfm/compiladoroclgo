package cvslogic.generatedxmi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ComparedElement {
	private String elementID;

	public String getElementID() {
		return elementID;
	}

	public HashMap<String, String> getOldAttributes() {
		return oldAttributes;
	}

	public HashMap<String, String> getNewAttributes() {
		return newAttributes;
	}

	public List<String> getGeneralizations() {
		return oldGeneralizations;
	}

	public List<String> getIncludes() {
		return oldIncludes;
	}

	public List<String> getExtends() {
		return oldExtends;
	}

	public List<String> getExtensionPoints() {
		return oldExtensionPoints;
	}

	public List<String> getAssociations() {
		return oldAssociations;
	}

	private HashMap<String, String> oldAttributes = new HashMap<String, String>();
	private HashMap<String, String> newAttributes = new HashMap<String, String>();
	private List<String> oldGeneralizations = new LinkedList<String>();

	public List<String> getOldGeneralizations() {
		return oldGeneralizations;
	}

	public void setOldGeneralizations(List<String> oldGeneralizations) {
		this.oldGeneralizations = oldGeneralizations;
	}

	public List<String> getOldIncludes() {
		return oldIncludes;
	}

	public void setOldIncludes(List<String> oldIncludes) {
		this.oldIncludes = oldIncludes;
	}

	public List<String> getOldExtends() {
		return oldExtends;
	}

	public void setOldExtends(List<String> oldExcludes) {
		this.oldExtends = oldExcludes;
	}

	public List<String> getOldExtensionPoints() {
		return oldExtensionPoints;
	}

	public void setOldExtensionPoints(List<String> oldExtensionPoints) {
		this.oldExtensionPoints = oldExtensionPoints;
	}

	public List<String> getOldAssociations() {
		return oldAssociations;
	}

	public void setOldAssociations(List<String> oldAssociations) {
		this.oldAssociations = oldAssociations;
	}

	public void setOldAttributes(HashMap<String, String> oldAttributes) {
		this.oldAttributes = oldAttributes;
	}

	public void setNewAttributes(HashMap<String, String> newAttributes) {
		this.newAttributes = newAttributes;
	}

	public void setNewGeneralizations(List<String> newGeneralizations) {
		this.newGeneralizations = newGeneralizations;
	}

	public void setNewIncludes(List<String> newIncludes) {
		this.newIncludes = newIncludes;
	}

	public void setNewExtends(List<String> newExtends) {
		this.newExtends = newExtends;
	}

	public void setNewExtensionPoints(List<String> newExtensionPoints) {
		this.newExtensionPoints = newExtensionPoints;
	}

	public void setNewAssociations(List<String> newAssociations) {
		this.newAssociations = newAssociations;
	}

	private List<String> oldIncludes = new LinkedList<String>();
	private List<String> oldExtends = new LinkedList<String>();
	private List<String> oldExtensionPoints = new LinkedList<String>();
	private List<String> oldAssociations = new LinkedList<String>();
	private List<String> newGeneralizations = new LinkedList<String>();

	public List<String> getNewGeneralizations() {
		return newGeneralizations;
	}

	public List<String> getNewIncludes() {
		return newIncludes;
	}

	public List<String> getNewExtends() {
		return newExtends;
	}

	public List<String> getNewExtensionPoints() {
		return newExtensionPoints;
	}

	public List<String> getNewAssociations() {
		return newAssociations;
	}

	private List<String> newIncludes = new LinkedList<String>();
	private List<String> newExtends = new LinkedList<String>();
	private List<String> newExtensionPoints = new LinkedList<String>();
	private List<String> newAssociations = new LinkedList<String>();
	private String elementType = "Default";

	public ComparedElement(String elementID) {
		this.elementID = elementID;

	}

	public static ComparedElement CreateComparedElement(String elementID) {
		return new ComparedElement(elementID);
	}

	public void addAttribute(String attribute, String newValue, String oldValue) {
		this.oldAttributes.put(attribute, oldValue);
		this.newAttributes.put(attribute, newValue);
	}

	public void addOldGeneralization(String elementID) {
		this.oldGeneralizations.add(elementID);
	}

	public void addOldAssociation(String elementID) {
		this.oldAssociations.add(elementID);
	}

	public void addOldInclude(String elementID) {
		this.oldIncludes.add(elementID);
	}

	public void addOldExtends(String elementID) {
		this.oldExtends.add(elementID);
	}

	public void addOldExtensionPoint(String elementID) {
		this.newExtensionPoints.add(elementID);
	}

	public void addNewGeneralization(String elementID) {
		this.newGeneralizations.add(elementID);
	}

	public void addNewAssociation(String elementID) {
		this.newAssociations.add(elementID);
	}

	public void addNewInclude(String elementID) {
		this.newIncludes.add(elementID);
	}

	public void addNewExtends(String elementID) {
		this.newExtends.add(elementID);
	}

	public void addNewExtensionPoint(String elementID) {
		this.newExtensionPoints.add(elementID);
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementType() {
		return elementType;
	}

}
