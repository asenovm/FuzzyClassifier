package edu.fmi.fuzzy.classifier;

public interface OnSubmitListener {
	void onSubmit(final String title, final String primaryRole,
			final String secondaryRole, final String director,
			final String summary);
}
