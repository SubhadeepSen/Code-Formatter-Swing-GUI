package sdp.code.formatter.runner;

import javax.swing.SwingUtilities;

import sdp.code.formatter.window.ApplicationWindow;

public class CodeFormatterRunner {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new ApplicationWindow().getWindow().setVisible(true);
		});
	}
}
