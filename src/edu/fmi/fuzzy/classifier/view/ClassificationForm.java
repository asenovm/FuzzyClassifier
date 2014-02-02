package edu.fmi.fuzzy.classifier.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.fmi.fuzzy.classifier.OnSubmitListener;

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

	private final OnSubmitListener listener;

	private ScrollableTextArea titleTextArea;

	private ScrollableTextArea primaryRoleTextArea;

	private ScrollableTextArea secondaryRoleTextArea;

	private ScrollableTextArea directorTextArea;

	private ScrollableTextArea summaryTextArea;

	private class ClassifyClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			listener.onSubmit(titleTextArea.getText(),
					primaryRoleTextArea.getText(),
					secondaryRoleTextArea.getText(),
					directorTextArea.getText(), summaryTextArea.getText());
		}
	}

	public ClassificationForm(final OnSubmitListener listener) {
		super();

		this.listener = listener;

		final SpringLayout layout = new SpringLayout();
		setLayout(layout);

		final JLabel titleLabel = new JLabel(LABEL_FORM);
		add(titleLabel);
		layout.putConstraint(SpringLayout.NORTH, titleLabel,
				MARGIN_TOP_INITIAL, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, titleLabel, MARGIN_LEFT,
				SpringLayout.WEST, this);

		titleTextArea = new ScrollableTextArea();
		primaryRoleTextArea = new ScrollableTextArea();
		secondaryRoleTextArea = new ScrollableTextArea();
		directorTextArea = new ScrollableTextArea();
		summaryTextArea = new ScrollableTextArea();

		attachAnswerBox(layout, titleTextArea, LABEL_TITLE, 35);
		attachAnswerBox(layout, primaryRoleTextArea, LABEL_PRIMARY_ROLE, 70);
		attachAnswerBox(layout, secondaryRoleTextArea, LABEL_SECONDARY_ROLE,
				105);
		attachAnswerBox(layout, directorTextArea, LABEL_DIRECTOR, 140);
		attachAnswerBox(layout, summaryTextArea, LABEL_SUMMARY, 175, 200, 120);

		final JButton submitButton = new JButton("Classify");
		add(submitButton);
		layout.putConstraint(SpringLayout.WEST, submitButton,
				MARGIN_LEFT + 140, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, submitButton, 325,
				SpringLayout.NORTH, this);

		submitButton.addActionListener(new ClassifyClickListener());
	}

	private void attachAnswerBox(final SpringLayout layout,
			final ScrollableTextArea textArea, final String title,
			final int offset) {
		attachAnswerBox(layout, textArea, title, offset, 200, 20);
	}

	private void attachAnswerBox(final SpringLayout layout,
			final ScrollableTextArea textArea, final String labelText,
			final int offset, final int width, final int height) {

		final JLabel label = new JLabel(labelText);
		final Dimension labelDimension = new Dimension(100, 20);
		label.setPreferredSize(labelDimension);
		add(label);

		final Dimension dimension = new Dimension(width, height);
		textArea.setPreferredSize(dimension);
		textArea.setAutoscrolls(true);
		add(textArea);

		layout.putConstraint(SpringLayout.WEST, label, MARGIN_LEFT,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, label, MARGIN_TOP_INITIAL
				+ offset, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, textArea, MARGIN_LEFT,
				SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, textArea, MARGIN_TOP_INITIAL
				+ offset, SpringLayout.NORTH, this);
	}
}
