package edu.fmi.fuzzy.classifier.view;

import javax.swing.JFrame;

import edu.fmi.fuzzy.classifier.OnSubmitListener;

public class ClassificationView extends JFrame {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = 9222532852127044087L;

	public ClassificationView(final OnSubmitListener listener) {
		super();

		setSize(600, 400);
		add(new ClassificationForm(listener));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
