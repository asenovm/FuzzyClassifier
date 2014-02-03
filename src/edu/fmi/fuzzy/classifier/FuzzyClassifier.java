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

	private MovieIndexer indexer;

	private OnItemClassifiedListener itemClassifiedListener;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		try {
			indexer = new MovieIndexer();
			for (final Movie movie : trainingSet) {
				indexer.index(movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setOnItemClassifiedListener(OnItemClassifiedListener listener) {
		itemClassifiedListener = listener;
	}

	public void classifyExample(final Movie movie) {
		try {
			final Map<Movie, Float> neighbours = indexer.getNeighbours(movie,
					NEIGHBOURS_COUNT);

			final Map<Genre, Float> genres = new HashMap<Genre, Float>();
			computeGenreValues(neighbours, genres);

			itemClassifiedListener.onItemClassified(genres.get(Genre.ACTION),
					genres.get(Genre.COMEDY), genres.get(Genre.ADVENTURE),
					genres.get(Genre.SCIFI), genres.get(Genre.THRILLER));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void computeGenreValues(final Map<Movie, Float> neighbours,
			final Map<Genre, Float> genres) {
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
			genres.put(genre, value);
		}

		if (total == 0) {
			return;
		}

		final float coef = 1 / total;
		for (final Entry<Genre, Float> entry : genres.entrySet()) {
			genres.put(entry.getKey(), entry.getValue() * coef);
		}

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
