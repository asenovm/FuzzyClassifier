package edu.fmi.fuzzy.classifier.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class ClassificationForm extends JPanel {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = 6653839408661026150L;

	/**
	 * {@value}
	 */
	private static final int MARGIN_LEFT = 15;

	/**
	 * {@value}
	 */
	private static final int MARGIN_TOP_INITIAL = 15;

	/**
	 * {@value}
	 */
	private static final String LABEL_FORM = "Please fill in the following form:";

	/**
	 * {@value}
	 */
	private static final String LABEL_TITLE = "Movie Title:";

	/**
	 * {@value}
	 */
	private static final String LABEL_PRIMARY_ROLE = "Primary Role:";

	/**
	 * {@value}
	 */
	private static final String LABEL_SECONDARY_ROLE = "Secondary Role:";

	/**
	 * {@value}
	 */
	private static final String LABEL_DIRECTOR = "Director:";

	/**
	 * {@value}
	 */
	private static final String LABEL_SUMMARY = "Summary:";

	public ClassificationForm() {
		super();

		final SpringLayout layout = new SpringLayout();
		setLayout(layout);

		final JLabel titleLabel = new JLabel(LABEL_FORM);
		add(titleLabel);
		layout.putConstraint(SpringLayout.NORTH, titleLabel,
				MARGIN_TOP_INITIAL, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, titleLabel, MARGIN_LEFT,
				SpringLayout.WEST, this);

		attachAnswerBox(layout, LABEL_TITLE, 35);
		attachAnswerBox(layout, LABEL_PRIMARY_ROLE, 70);
		attachAnswerBox(layout, LABEL_SECONDARY_ROLE, 105);
		attachAnswerBox(layout, LABEL_DIRECTOR, 140);
		attachAnswerBox(layout, LABEL_SUMMARY, 175, 200, 120);
	}

	private void attachAnswerBox(final SpringLayout layout, final String title,
			final int offset) {
		attachAnswerBox(layout, title, offset, 200, 20);
	}

	private void attachAnswerBox(final SpringLayout layout,
			final String labelText, final int offset, final int width,
			final int height) {

		final JLabel label = new JLabel(labelText);
		final Dimension labelDimension = new Dimension(100, 20);
		label.setPreferredSize(labelDimension);
		add(label);

		final ScrollableTextArea inputField = new ScrollableTextArea();
		final Dimension dimension = new Dimension(width, height);
		inputField.setPreferredSize(dimension);
		inputField.setAutoscrolls(true);
		add(inputField);

		layout.putConstraint(SpringLayout.WEST, label, MARGIN_LEFT,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, label, MARGIN_TOP_INITIAL
				+ offset, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, inputField, MARGIN_LEFT,
				SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, inputField, MARGIN_TOP_INITIAL
				+ offset, SpringLayout.NORTH, this);
	}
}
