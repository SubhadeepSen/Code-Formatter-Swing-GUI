package sdp.code.formatter.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sdp.code.formatter.handler.OutputHandler;

public class TextAreaDocumentListener implements DocumentListener {

	@Override
	public void insertUpdate(DocumentEvent e) {
		OutputHandler.handleOutputText(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		OutputHandler.handleOutputText(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		OutputHandler.handleOutputText(e);
	}
}