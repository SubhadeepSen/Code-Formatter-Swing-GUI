package sdp.code.formatter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class BrowseButtonListener implements ActionListener {

	private static final String NEW_LINE = "\n";
	private static final String COLON = ":";
	private static final String EXCEPTION_TEXT = "Exception";

	private JFileChooser fileChooser;
	private JButton fileBrowseButton;
	private JTextArea leftTextArea;
	private JTextArea rightTextArea;

	public BrowseButtonListener(JButton fileBrowseButton, JTextArea leftTextArea, JTextArea rightTextArea) {
		fileChooser = new JFileChooser();
		this.fileBrowseButton = fileBrowseButton;
		this.leftTextArea = leftTextArea;
		this.rightTextArea = rightTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (fileChooser.showOpenDialog(fileBrowseButton) == JFileChooser.APPROVE_OPTION) {
			List<String> lines = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			try {
				lines = Files.readAllLines(Paths.get(fileChooser.getSelectedFile().getAbsolutePath()));
				for (String line : lines) {
					sb.append(line + NEW_LINE);
				}
				leftTextArea.setText(sb.toString());
			} catch (IOException e) {
				String message = e.getMessage();
				if (message.contains(EXCEPTION_TEXT)) {
					rightTextArea.setText(message.split(COLON)[1]);
					return;
				}
				rightTextArea.setText(message);
			}
		}
	}

}
