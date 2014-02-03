package edu.fmi.fuzzy.classifier;

public interface OnItemClassifiedListener {
	void onItemClassified(final float actionValue, final float comedyValue,
			final float adventureValue, final float scifiValue,
			final float thrillerValue);
}
