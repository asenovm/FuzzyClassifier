package edu.fmi.fuzzy.classifier.view;

import javax.swing.JFrame;

import edu.fmi.fuzzy.classifier.OnItemClassifiedListener;
import edu.fmi.fuzzy.classifier.OnSubmitListener;

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
	public void onItemClassified(float actionValue, float comedyValue,
			float adventureValue, float scifiValue, float thrillerValue) {
		itemClassifiedListener.onItemClassified(actionValue, comedyValue,
				adventureValue, scifiValue, thrillerValue);
	}

}
