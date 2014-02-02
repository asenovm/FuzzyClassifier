package edu.fmi.fuzzy.classifier.view;

import javax.swing.JFrame;

public class ClassificationView extends JFrame {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = 9222532852127044087L;

	public ClassificationView() {
		super();

		setSize(600, 400);
		add(new ClassificationForm());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
