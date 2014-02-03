package edu.fmi.fuzzy.classifier;

import java.util.Map;

import edu.fmi.fuzzy.classifier.model.Genre;

public interface OnItemClassifiedListener {
	void onItemClassified(final Map<Genre, Float> classification);
}
