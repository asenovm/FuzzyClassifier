package edu.fmi.fuzzy.classifier.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.fmi.fuzzy.classifier.OnItemClassifiedListener;
import edu.fmi.fuzzy.classifier.OnSubmitListener;
import edu.fmi.fuzzy.classifier.model.Genre;

public class ClassificationForm extends JPanel implements
		OnItemClassifiedListener {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = 6653839408661026150L;

	/**
	 * {@value}
	 */
	private static final int HEIGHT_INPUT_FIELD_LABEL = 20;

	/**
	 * {@value}
	 */
	private static final int WIDTH_INPUT_FIELD_LABEL = 100;

	/**
	 * {@value}
	 */
	private static final int HEIGHT_INPUT_FIELD_SUMMARY = 120;

	/**
	 * {@value}
	 */
	private static final int WIDTH_INPUT_FIELD_SUMMARY = 200;

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

		addTitleLabel(layout);
		addResultsLabel(layout);

		titleTextArea = new ScrollableTextArea();
		primaryRoleTextArea = new ScrollableTextArea();
		secondaryRoleTextArea = new ScrollableTextArea();
		directorTextArea = new ScrollableTextArea();
		summaryTextArea = new ScrollableTextArea();

		addLabeledBox(layout, titleTextArea, Label.TITLE,
				Margin.LEFT_INPUT_BOX, Margin.TOP_INPUT_BOX_TITLE);
		addLabeledBox(layout, primaryRoleTextArea, Label.PRIMARY_ROLE,
				Margin.LEFT_INPUT_BOX, Margin.TOP_INPUT_BOX_PRIMARY_ROLE);
		addLabeledBox(layout, secondaryRoleTextArea, Label.SECONDARY_ROLE,
				Margin.LEFT_INPUT_BOX, Margin.TOP_INPUT_BOX_SECONDARY_ROLE);
		addLabeledBox(layout, directorTextArea, Label.DIRECTOR,
				Margin.LEFT_INPUT_BOX, Margin.TOP_INPUT_BOX_DIRECTOR);
		addLabeledBox(layout, summaryTextArea, Label.SUMMARY,
				Margin.LEFT_INPUT_BOX, Margin.TOP_INPUT_BOX_SUMMARY,
				WIDTH_INPUT_FIELD_SUMMARY, HEIGHT_INPUT_FIELD_SUMMARY);

		actionLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		comedyLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		adventureLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		scifiLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);
		thrillerLabel = new JLabel(Label.CLASSIFICATION_INITIAL_VALUE);

		addLabeledBox(layout, actionLabel, Label.RESULTS_ACTION,
				Margin.LEFT_RESULT_BOX, Margin.TOP_RESULT_BOX_ACTION);
		addLabeledBox(layout, comedyLabel, Label.RESULTS_COMEDY,
				Margin.LEFT_RESULT_BOX, Margin.TOP_RESULT_BOX_COMEDY);
		addLabeledBox(layout, adventureLabel, Label.RESULTS_ADVENTURE,
				Margin.LEFT_RESULT_BOX, Margin.TOP_RESULT_BOX_ADVENTURE);
		addLabeledBox(layout, scifiLabel, Label.RESULTS_SCIFI,
				Margin.LEFT_RESULT_BOX, Margin.TOP_RESULT_BOX_SCIFI);
		addLabeledBox(layout, thrillerLabel, Label.RESULTS_THRILLER,
				Margin.LEFT_RESULT_BOX, Margin.TOP_RESULT_BOX_THRILLER);

		addSubmitButton(layout);
	}

	private void addSubmitButton(final SpringLayout layout) {
		final JButton submitButton = new JButton(Label.CLASSIFY);
		add(submitButton);

		layout.putConstraint(SpringLayout.WEST, submitButton,
				Margin.LEFT_INPUT_SUBMIT_BUTTON, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, submitButton,
				Margin.TOP_INPUT_SUBMIT_BUTTON, SpringLayout.NORTH, this);

		submitButton.addActionListener(new ClassifyClickListener());
	}

	private void addResultsLabel(final SpringLayout layout) {
		final JLabel resultsLabel = new JLabel(Label.RESULTS);
		add(resultsLabel);
		layout.putConstraint(SpringLayout.NORTH, resultsLabel,
				Margin.TOP_INPUT_BOX, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, resultsLabel,
				Margin.LEFT_RESULT_BOX, SpringLayout.WEST, this);
	}

	private void addTitleLabel(final SpringLayout layout) {
		final JLabel titleLabel = new JLabel(Label.INPUT);
		add(titleLabel);
		layout.putConstraint(SpringLayout.NORTH, titleLabel,
				Margin.TOP_INPUT_BOX, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, titleLabel,
				Margin.LEFT_INPUT_BOX_TEXTFIELD, SpringLayout.WEST, this);
	}

	private void addLabeledBox(final SpringLayout layout,
			final Component textArea, final String title, final int offsetX,
			final int offsetY) {
		addLabeledBox(layout, textArea, title, offsetX, offsetY, 200, 20);
	}

	private void addLabeledBox(final SpringLayout layout,
			final Component component, final String labelText,
			final int offsetX, final int offsetY, final int width,
			final int height) {

		final JLabel label = new JLabel(labelText);
		final Dimension labelDimension = new Dimension(WIDTH_INPUT_FIELD_LABEL,
				HEIGHT_INPUT_FIELD_LABEL);
		label.setPreferredSize(labelDimension);
		add(label);

		final Dimension dimension = new Dimension(width, height);
		component.setPreferredSize(dimension);
		add(component);

		layout.putConstraint(SpringLayout.WEST, label,
				Margin.LEFT_INPUT_BOX_TEXTFIELD + offsetX, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.NORTH, label, Margin.TOP_INPUT_BOX
				+ offsetY, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, component,
				Margin.LEFT_INPUT_BOX_TEXTFIELD + offsetX
						+ WIDTH_INPUT_FIELD_LABEL, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, component,
				Margin.TOP_INPUT_BOX + offsetY, SpringLayout.NORTH, this);
	}

	@Override
	public void onItemClassified(final Map<Genre, Float> result) {
		actionLabel.setText(Float.toString(result.get(Genre.ACTION)));
		comedyLabel.setText(Float.toString(result.get(Genre.COMEDY)));
		adventureLabel.setText(Float.toString(result.get(Genre.ADVENTURE)));
		scifiLabel.setText(Float.toString(result.get(Genre.SCIFI)));
		thrillerLabel.setText(Float.toString(result.get(Genre.THRILLER)));
	}
}
