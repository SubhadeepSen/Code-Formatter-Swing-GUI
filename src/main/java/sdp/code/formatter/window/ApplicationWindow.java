package sdp.code.formatter.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import sdp.code.formatter.constants.CommonConstants;
import sdp.code.formatter.listener.TextAreaDocumentListener;

public class ApplicationWindow {

	private static final String ARIAL = "Arial";
	private static final String WINDOW_TITLE = "Code Formatter";
	
	private JFrame frame;

	public ApplicationWindow() {
		initialize();
	}

	private void initialize() {
		createFrame();
		
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);

		JScrollPane leftScrollPane = new JScrollPane();
		leftScrollPane.setBorder(border);
		JScrollPane rightScrollPane = new JScrollPane();
		rightScrollPane.setBorder(border);

		JRadioButton rdbtnXml = new JRadioButton("XML", true);
		rdbtnXml.setBackground(Color.LIGHT_GRAY);
		rdbtnXml.setToolTipText("Select this to format unformatted XML.");

		JRadioButton rdbtnJson = new JRadioButton("JSON");
		rdbtnJson.setBackground(Color.LIGHT_GRAY);
		rdbtnJson.setToolTipText("Select this to format unformatted JSON.");

		JRadioButton rdbtnSingleLine = new JRadioButton("Single Line");
		rdbtnSingleLine.setBackground(Color.LIGHT_GRAY);
		rdbtnSingleLine.setToolTipText("Select this to format multiline text to single line.");

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnXml);
		radioGroup.add(rdbtnJson);
		radioGroup.add(rdbtnSingleLine);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(leftScrollPane, GroupLayout.PREFERRED_SIZE, 480,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE).addComponent(
										rightScrollPane, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(35).addComponent(rdbtnXml).addGap(10)
								.addComponent(rdbtnJson).addGap(10).addComponent(rdbtnSingleLine)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnXml, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnJson, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnSingleLine, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rightScrollPane, Alignment.TRAILING).addComponent(leftScrollPane,
								Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
				.addContainerGap()));

		JTextArea rightTextArea = new JTextArea();
		rightTextArea.setTabSize(4);
		rightScrollPane.setViewportView(rightTextArea);
		rightTextArea.setEditable(false);
		rightTextArea.setForeground(new Color(102, 98, 98));
		rightTextArea.setFont(new Font(ARIAL, Font.BOLD, 13));

		JTextArea leftTextArea = new JTextArea();
		leftTextArea.setTabSize(4);
		rightTextArea.setForeground(new Color(0, 139, 139));
		leftTextArea.setFont(new Font(ARIAL, Font.PLAIN, 13));
		leftScrollPane.setViewportView(leftTextArea);
		frame.getContentPane().setLayout(groupLayout);

		leftTextArea.getDocument().putProperty(CommonConstants.LEFT_TEXT_AREA, leftTextArea);
		leftTextArea.getDocument().putProperty(CommonConstants.RIGHT_TEXT_AREA, rightTextArea);
		leftTextArea.getDocument().putProperty(CommonConstants.RADIO_GROUP, radioGroup);
		leftTextArea.getDocument().addDocumentListener(new TextAreaDocumentListener());
	}

	private void createFrame() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);
		frame.setTitle(WINDOW_TITLE);
		frame.setSize(1024, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	public JFrame getWindow() {
		return frame;
	}
}
