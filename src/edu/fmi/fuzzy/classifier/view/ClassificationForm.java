package edu.fmi.fuzzy.classifier.view;

import java.awt.Component;
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

	private static final String LABEL_RESULTS_THRILLER = "Thriller:";

	private static final String LABEL_RESULTS_SCIFI = "Sci-Fi:";

	private static final String LABEL_RESULTS_ADVENTURE = "Adventure:";

	private static final String LABEL_RESULTS_COMEDY = "Comedy:";

	private static final String LABEL_RESULTS_ACTION = "Action:";

	private static final String LABEL_RESULTS = "Result classification:";

	/**
	 * {@value}
	 */
	private static final String LABEL_INPUT = "Please fill in the following form:";

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

		final JLabel titleLabel = new JLabel(LABEL_INPUT);
		add(titleLabel);
		layout.putConstraint(SpringLayout.NORTH, titleLabel,
				MARGIN_TOP_INITIAL, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, titleLabel, MARGIN_LEFT,
				SpringLayout.WEST, this);

		final JLabel resultsLabel = new JLabel(LABEL_RESULTS);
		add(resultsLabel);
		layout.putConstraint(SpringLayout.NORTH, resultsLabel,
				MARGIN_TOP_INITIAL, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, resultsLabel, 360,
				SpringLayout.WEST, this);

		titleTextArea = new ScrollableTextArea();
		primaryRoleTextArea = new ScrollableTextArea();
		secondaryRoleTextArea = new ScrollableTextArea();
		directorTextArea = new ScrollableTextArea();
		summaryTextArea = new ScrollableTextArea();

		attachAnswerBox(layout, titleTextArea, LABEL_TITLE, 0, 35);
		attachAnswerBox(layout, primaryRoleTextArea, LABEL_PRIMARY_ROLE, 0, 70);
		attachAnswerBox(layout, secondaryRoleTextArea, LABEL_SECONDARY_ROLE, 0,
				105);
		attachAnswerBox(layout, directorTextArea, LABEL_DIRECTOR, 0, 140);
		attachAnswerBox(layout, summaryTextArea, LABEL_SUMMARY, 0, 175, 200,
				120);

		final JLabel actionLabel = new JLabel("1");
		final JLabel comedyLabel = new JLabel("2");
		final JLabel adventureLabel = new JLabel("3");
		final JLabel scifiLabel = new JLabel("4");
		final JLabel thrillerLabel = new JLabel("5");

		attachAnswerBox(layout, actionLabel, LABEL_RESULTS_ACTION, 350, 35);
		attachAnswerBox(layout, comedyLabel, LABEL_RESULTS_COMEDY, 350, 70);
		attachAnswerBox(layout, adventureLabel, LABEL_RESULTS_ADVENTURE, 350,
				105);
		attachAnswerBox(layout, scifiLabel, LABEL_RESULTS_SCIFI, 350, 140);
		attachAnswerBox(layout, thrillerLabel, LABEL_RESULTS_THRILLER, 350, 175);

		final JButton submitButton = new JButton("Classify");
		add(submitButton);
		layout.putConstraint(SpringLayout.WEST, submitButton,
				MARGIN_LEFT + 140, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, submitButton, 325,
				SpringLayout.NORTH, this);

		submitButton.addActionListener(new ClassifyClickListener());
	}

	private void attachAnswerBox(final SpringLayout layout,
			final Component textArea, final String title, final int offsetX,
			final int offsetY) {
		attachAnswerBox(layout, textArea, title, offsetX, offsetY, 200, 20);
	}

	private void attachAnswerBox(final SpringLayout layout,
			final Component textArea, final String labelText,
			final int offsetX, final int offsetY, final int width,
			final int height) {

		final JLabel label = new JLabel(labelText);
		final Dimension labelDimension = new Dimension(100, 20);
		label.setPreferredSize(labelDimension);
		add(label);

		final Dimension dimension = new Dimension(width, height);
		textArea.setPreferredSize(dimension);
		add(textArea);

		layout.putConstraint(SpringLayout.WEST, label, MARGIN_LEFT + offsetX,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, label, MARGIN_TOP_INITIAL
				+ offsetY, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, textArea, MARGIN_LEFT + offsetX
				+ 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, textArea, MARGIN_TOP_INITIAL
				+ offsetY, SpringLayout.NORTH, this);
	}
}
