package util.reports;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLWriter;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.Element;

public class Tags {

	public void createHTML() {
		HTML htmlFile = new HTML();
		HTMLDocument document = new HTMLDocument();
		OutputStream outputStream = new OutputStream() {
			@Override
			public void write(int b) throws IOException {

			}
		};
		;
		;
		Writer writer = new OutputStreamWriter(outputStream);
		HTMLWriter htmlWriter = new HTMLWriter(writer, document);

	}

	public static void main(String[] args) {
		HTML htmlFile = new HTML();
		HTMLDocument document = new HTMLDocument();
		

		//HTMLWriter htmlWriter = new HTMLWriter(writer, document);
		System.out.println(Tag.ADDRESS);

	}
}
