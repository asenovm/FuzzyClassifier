package edu.fmi.fuzzy.classifier.view;

import java.util.Map;

import javax.swing.JFrame;

import edu.fmi.fuzzy.classifier.OnItemClassifiedListener;
import edu.fmi.fuzzy.classifier.OnSubmitListener;
import edu.fmi.fuzzy.classifier.model.Genre;

public class ClassificationView extends JFrame implements
		OnItemClassifiedListener {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = 9222532852127044087L;

	private final OnItemClassifiedListener itemClassifiedListener;

	public ClassificationView(final OnSubmitListener listener) {
		super();

		setSize(700, 400);

		final ClassificationForm form = new ClassificationForm(listener);
		add(form);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		itemClassifiedListener = form;
	}

	@Override
	public void onItemClassified(final Map<Genre, Float> classification) {
		itemClassifiedListener.onItemClassified(classification);
	}

}
