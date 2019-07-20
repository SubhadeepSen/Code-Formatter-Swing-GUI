package sdp.code.formatter.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class CodeFormatter {

	private static final String NO_STRING = "";
	private static final String YES = "yes";
	private static final String INDENT_NUMBER = "indent-number";
	private static final String NEW_LINE = "\n";
	private static final String COLON = ":";
	private static final String SPACE = " ";
	private static final String EXCEPTION_TEXT = "Exception";

	private CodeFormatter() {
	}

	public static String formatToXML(String xmlString) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute(INDENT_NUMBER, 4);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, YES);
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			Source xmlInput = new StreamSource(new StringReader(xmlString));
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.contains(EXCEPTION_TEXT)) {
				return message.split(COLON)[1];
			}
			return message;
		}
	}

	public static String formatToJSON(String jsonString) {
		try {
			JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
			return new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject);
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.contains(EXCEPTION_TEXT)) {
				return message.split(COLON)[1];
			}
			return message;
		}
	}

	public static String formatToSingleLine(String inputText) {
		String[] lines = inputText.split(NEW_LINE);
		int length = lines.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length - 1; i++) {
			if (!lines[i].equals(NO_STRING)) {
				sb.append(lines[i]).append(SPACE);
			}
		}
		sb.append(lines[length - 1]);
		return sb.toString();
	}
}
