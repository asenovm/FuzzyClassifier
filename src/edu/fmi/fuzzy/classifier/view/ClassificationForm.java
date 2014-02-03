package edu.fmi.fuzzy.classifier.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.fmi.fuzzy.classifier.OnItemClassifiedListener;
import edu.fmi.fuzzy.classifier.OnSubmitListener;

public class ClassificationForm extends JPanel implements
		OnItemClassifiedListener {

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

	private final OnSubmitListener listener;

	private final ScrollableTextArea titleTextArea;

	private final ScrollableTextArea primaryRoleTextArea;

	private final ScrollableTextArea secondaryRoleTextArea;

	private final ScrollableTextArea directorTextArea;

	private final ScrollableTextArea summaryTextArea;

	private final JLabel actionLabel;

	private final JLabel comedyLabel;

	private final JLabel adventureLabel;

	private final JLabel scifiLabel;

	private final JLabel thrillerLabel;

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

		final JLabel titleLabel = new JLabel(Label.INPUT);
		add(titleLabel);
		layout.putConstraint(SpringLayout.NORTH, titleLabel,
				MARGIN_TOP_INITIAL, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, titleLabel, MARGIN_LEFT,
				SpringLayout.WEST, this);

		final JLabel resultsLabel = new JLabel(Label.RESULTS);
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

		attachAnswerBox(layout, titleTextArea, Label.TITLE, 0, 35);
		attachAnswerBox(layout, primaryRoleTextArea, Label.PRIMARY_ROLE, 0, 70);
		attachAnswerBox(layout, secondaryRoleTextArea, Label.SECONDARY_ROLE, 0,
				105);
		attachAnswerBox(layout, directorTextArea, Label.DIRECTOR, 0, 140);
		attachAnswerBox(layout, summaryTextArea, Label.SUMMARY, 0, 175, 200,
				120);

		actionLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		comedyLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		adventureLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		scifiLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		thrillerLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);

		attachAnswerBox(layout, actionLabel, Label.RESULTS_ACTION, 350, 35);
		attachAnswerBox(layout, comedyLabel, Label.RESULTS_COMEDY, 350, 70);
		attachAnswerBox(layout, adventureLabel, Label.RESULTS_ADVENTURE, 350,
				105);
		attachAnswerBox(layout, scifiLabel, Label.RESULTS_SCIFI, 350, 140);
		attachAnswerBox(layout, thrillerLabel, Label.RESULTS_THRILLER, 350, 175);

		final JButton submitButton = new JButton(Label.CLASSIFY);
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

	@Override
	public void onItemClassified(final float actionValue,
			final float comedyValue, final float adventureValue,
			final float scifiValue, final float thrillerValue) {
		System.out.println(actionValue);
		actionLabel.setText(Float.toString(actionValue));
		comedyLabel.setText(Float.toString(comedyValue));
		adventureLabel.setText(Float.toString(adventureValue));
		scifiLabel.setText(Float.toString(scifiValue));
		thrillerLabel.setText(Float.toString(thrillerValue));
	}
}
