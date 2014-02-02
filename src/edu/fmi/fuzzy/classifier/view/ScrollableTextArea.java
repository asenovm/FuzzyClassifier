package edu.fmi.fuzzy.classifier.view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollableTextArea extends JScrollPane {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -8012857041530410311L;

	private final JTextArea textArea;

	public ScrollableTextArea() {
		super();

		textArea = new JTextArea();
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		setViewportView(textArea);
	}

	public String getText() {
		return textArea.getText();
	}

}
