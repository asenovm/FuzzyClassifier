package edu.fmi.fuzzy.classifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.queryparser.classic.ParseException;

import edu.fmi.fuzzy.classifier.model.Genre;
import edu.fmi.fuzzy.classifier.model.Movie;
import edu.fmi.fuzzy.classifier.view.ClassificationView;

public class FuzzyClassifier implements OnSubmitListener {

	private static final int NEIGHBOURS_COUNT = 1;

	private MovieIndex index;

	private OnItemClassifiedListener listener;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		try {
			index = new MovieIndex();
			for (final Movie movie : trainingSet) {
				index.index(movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setOnItemClassifiedListener(OnItemClassifiedListener listener) {
		this.listener = listener;
	}

	public void classifyExample(final Movie movie) {
		try {
			Map<Movie, Float> neighbours = index.getNeighbours(movie,
					NEIGHBOURS_COUNT);
			listener.onItemClassified(computeGenreValues(neighbours));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<Genre, Float> computeGenreValues(Map<Movie, Float> neighbours) {
		final Map<Genre, Float> classification = new HashMap<Genre, Float>();

		float nominator = 0f;
		float denominator = 0f;
		float total = 0f;

		for (Genre genre : Genre.values()) {
			for (final Entry<Movie, Float> entry : neighbours.entrySet()) {
				final Movie movie = entry.getKey();
				final float score = entry.getValue();
				nominator += movie.getGenreValue(genre) * (1 / (score * score));
				denominator += (1 / (score * score));
			}

			final float value = denominator != 0 ? nominator / denominator : 0f;
			total += value;
			classification.put(genre, value);
		}

		if (total != 0) {
			final float coef = 1 / total;
			for (final Entry<Genre, Float> entry : classification.entrySet()) {
				classification.put(entry.getKey(), entry.getValue() * coef);
			}
		}

		return classification;
	}

	@Override
	public void onSubmit(String title, String primaryRole,
			String secondaryRole, String director, String summary) {
		final Movie inputMovie = new Movie(title, primaryRole, secondaryRole,
				director, summary);
		classifyExample(inputMovie);
	}

	public static void main(String[] args) {
		final TrainingSet trainingSet = new TrainingSet();
		final FuzzyClassifier classifier = new FuzzyClassifier(trainingSet);

		final ClassificationView view = new ClassificationView(classifier);
		view.setVisible(true);

		classifier.setOnItemClassifiedListener(view);
	}
}
