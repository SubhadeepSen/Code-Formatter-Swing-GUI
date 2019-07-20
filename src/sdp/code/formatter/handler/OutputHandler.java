package sdp.code.formatter.handler;

import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;

import sdp.code.formatter.constants.CommonConstants;
import sdp.code.formatter.utils.CodeFormatter;

public final class OutputHandler {

	private static final String SINGLE_LINE = "Single Line";
	private static final String JSON = "JSON";
	private static final String XML = "XML";
	
	private static ButtonGroup radioGroup;
	private static JTextArea leftTextArea;
	private static JTextArea rightTextArea;

	private OutputHandler() {
	}

	public static void handleOutputText(DocumentEvent e) {
		radioGroup = (ButtonGroup) e.getDocument().getProperty(CommonConstants.RADIO_GROUP);
		leftTextArea = (JTextArea) e.getDocument().getProperty(CommonConstants.LEFT_TEXT_AREA);
		rightTextArea = (JTextArea) e.getDocument().getProperty(CommonConstants.RIGHT_TEXT_AREA);

		radioGroup.getElements().asIterator().forEachRemaining(b -> {
			if (b.isSelected()) {
				if (XML.equals(b.getActionCommand())) {
					rightTextArea.setText(CodeFormatter.formatToXML(leftTextArea.getText()));
				} else if (JSON.equals(b.getActionCommand())) {
					rightTextArea.setText(CodeFormatter.formatToJSON(leftTextArea.getText()));
				} else if (SINGLE_LINE.equals(b.getActionCommand())) {
					rightTextArea.setText(CodeFormatter.formatToSingleLine(leftTextArea.getText()));
				}
			}
		});
	}
}
