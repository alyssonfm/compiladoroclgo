package util.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.text.html.HTML.Tag;

import com.pavelvlasov.uml.Element;

import util.Constants;

public class HTMLFileManager {

	private StringBuilder htmlContent = new StringBuilder();
	static private HTMLFileManager instance = null;

	protected HTMLFileManager() {

	}

	public String getContent() {
		return htmlContent.toString();
	}

	static public HTMLFileManager getInstance() {
		if (instance == null) {
			instance = new HTMLFileManager();
		}
		return instance;
	}

	public void createDir(String path) {
		File file = new File(path);
		file.mkdirs();
	}

	public void writeHTMLFile(String htmlContent, String htmlPath)
			throws IOException {
		File file = new File(htmlPath);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(htmlContent);
		bw.close();
	}

	public void buildElement(String name, TreeMap<String, String> attributes,
			String content) {
		String attributesAux = "";
		if (attributes != null) {

			for (Entry<String, String> entry : attributes.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				attributesAux += " " + key + "=" + "\"" + value + "\"";
			}
		}
		htmlContent.append("<" + name + attributesAux + ">" + Constants.EOF);
		htmlContent.append(content + Constants.EOF);
		htmlContent.append("</" + name + ">" + Constants.EOF);

	}

	public void openTag(String tag) {
		htmlContent.append("<" + tag + ">" + Constants.EOF);
	}

	public void openTag(String tag, String attributes) {
		htmlContent.append("<" + tag + " " + attributes + ">" + Constants.EOF);
	}

	public void addContent(String text) {
		htmlContent.append(text + Constants.EOF);
	}

	public void closeTag(String tag) {
		htmlContent.append("</" + tag + ">" + Constants.EOF);
	}

	public void setTableTop() {
		HTMLFileManager.getInstance().openTag(Tag.TR.toString());
		HTMLFileManager.getInstance().openTag(Tag.TD.toString());
		HTMLFileManager.getInstance().addContent(Constants.ATTRIBUTE);
		HTMLFileManager.getInstance().closeTag(Tag.TD.toString());
		HTMLFileManager.getInstance().openTag(Tag.TD.toString());
		HTMLFileManager.getInstance().addContent("Old Version");
		HTMLFileManager.getInstance().closeTag(Tag.TD.toString());
		HTMLFileManager.getInstance().openTag(Tag.TD.toString());
		HTMLFileManager.getInstance().addContent("New Version");
		HTMLFileManager.getInstance().closeTag(Tag.TD.toString());
		HTMLFileManager.getInstance().closeTag(Tag.TR.toString());
	}

	public void setTableLine(String attribute, String oldValue, String newValue) {
		HTMLFileManager.getInstance().openTag(Tag.TR.toString());
		HTMLFileManager.getInstance().openTag(Tag.TD.toString());
		HTMLFileManager.getInstance().addContent(attribute);
		HTMLFileManager.getInstance().closeTag(Tag.TD.toString());
		HTMLFileManager.getInstance().openTag(Tag.TD.toString());
		HTMLFileManager.getInstance().addContent(oldValue);
		HTMLFileManager.getInstance().closeTag(Tag.TD.toString());
		HTMLFileManager.getInstance().openTag(Tag.TD.toString());
		HTMLFileManager.getInstance().addContent(newValue);
		HTMLFileManager.getInstance().closeTag(Tag.TD.toString());
		HTMLFileManager.getInstance().closeTag(Tag.TR.toString());
	}

}
