package edu.fmi.fuzzy.classifier.view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollableTextArea extends JScrollPane {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -8012857041530410311L;

	public ScrollableTextArea() {
		super(new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

}
