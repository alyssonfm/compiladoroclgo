package cvslogic.generatedxmi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import util.ConstantsXML;

/**
 * Classe que cria um XML, a partir do caminho especificado.
 * 
 * @author Jonathan Brilhante
 * @author Jose Rafael
 * @author Nata Venancio
 * @author Renato Almeida
 * 
 * @version 1.0
 */

public class XMICreator {
	private DocumentBuilderFactory dbf;
	private DocumentBuilder builder;
	private DOMImplementation impl;
	private Document doc;
	private String raiz, path;

	/**
	 * Cria um CriaXML, a partir da raiz e do caminho para criar
	 * 
	 * @param raiz
	 *            a raiz
	 * @param path
	 *            o caminho para criacao
	 */
	public XMICreator(String raiz, String path) {
		this.raiz = raiz;
		this.path = path;
	}

	/**
	 * Metodo que cria um documento XML usando a estrutura DOM. Nesse documento
	 * serao armazenados os dados dos usuarios.
	 * 
	 * Passa a raiz do documento como parametro.
	 * 
	 * @throws Exception
	 */
	private void criaDoc(List<ComparedElement> ChangedElements)
			throws Exception {
		dbf = DocumentBuilderFactory.newInstance();
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException erro) {
			throw new Exception("Erro ao criar DocumentBuilder!");
		}
		impl = builder.getDOMImplementation();
		doc = impl.createDocument(null, raiz, null);
		Element eAux = XMLManipulator.createElement(doc,
				ConstantsXML.CHANGEMENTS, new HashMap<String, String>());
		Element root = doc.getDocumentElement();
		root.appendChild(eAux);
		
		doc.setStrictErrorChecking(true);

		doc = XMLManipulator.buildElements(doc, ChangedElements);

		DOMSource source = new DOMSource(doc);

		StreamResult result = null;
		try {
			FileOutputStream fileStream = new FileOutputStream(path);
			result = new StreamResult(fileStream);
		} catch (FileNotFoundException erro) {
			throw new Exception("Problema na criacao do arquivo");
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = null;
		try {
			serializer = tf.newTransformer();
		} catch (TransformerConfigurationException erro) {
			throw new Exception("Problema na serializacao do arquivo");
		}
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		try {
			serializer.transform(source, result);
		} catch (TransformerException erro) {
			throw new Exception("Problema na copia do arquivo");
		}
	}

	/**
	 * Retorna o doc criado
	 * 
	 * @return Document
	 * @throws Exception
	 */
	public Document getDoc(List<ComparedElement> ChangedElements)
			throws Exception {
		this.criaDoc(ChangedElements);
		return doc;
	}

	/**
	 * Retorna a raiz
	 * 
	 * @return a raiz
	 */
	public String getRaiz() {
		return raiz;
	}

	/*
	 * public void salvaModificacoes() throws Exception { Document doc =
	 * getDoc(); // setDoc(doc);
	 * 
	 * // manipula o documento aqui!
	 * 
	 * DOMSource source = new DOMSource(doc); StreamResult result = null; try {
	 * FileOutputStream fileStream = new FileOutputStream(path); result = new
	 * StreamResult(fileStream); } catch (FileNotFoundException erro) { throw
	 * new Exception("Problema na criacao do arquivo"); } TransformerFactory tf
	 * = TransformerFactory.newInstance(); Transformer serializer = null; try {
	 * serializer = tf.newTransformer(); } catch
	 * (TransformerConfigurationException erro) { throw new
	 * Exception("Problema na serializacao do arquivo"); }
	 * serializer.setOutputProperty(OutputKeys.INDENT, "yes"); try {
	 * serializer.transform(source, result); } catch (TransformerException erro)
	 * { throw new Exception("Problema na copia do arquivo"); } }
	 */

}
