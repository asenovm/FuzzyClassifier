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

	private static final int NEIGHBOURS_CONSIDERED = 3;

	private MovieIndexer indexer;

	private final OnItemClassifiedListener itemClassifiedListener;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		try {
			indexer = new MovieIndexer();
			for (final Movie movie : trainingSet) {
				indexer.index(movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		final ClassificationView view = new ClassificationView(this);
		view.setVisible(true);

		itemClassifiedListener = view;
	}

	public void classifyExample(Movie movie) {
		Map<Movie, Float> neighbours = null;
		try {
			neighbours = indexer
					.getClosestMatches(movie, NEIGHBOURS_CONSIDERED);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Can't classify this example due to error");
			return;
		} catch (IOException e) {
			System.out.println("Can't classify this example due to error");
			e.printStackTrace();
			return;
		}

		final Map<Genre, Float> genres = new HashMap<Genre, Float>();
		computeGenreValues(neighbours, genres);
		normalizeGenreValues(genres);
		itemClassifiedListener.onItemClassified(genres.get(Genre.ACTION),
				genres.get(Genre.COMEDY), genres.get(Genre.ADVENTURE),
				genres.get(Genre.SCIFI), genres.get(Genre.THRILLER));
	}

	private void computeGenreValues(final Map<Movie, Float> neighbours,
			final Map<Genre, Float> genres) {
		float nominator = 0f;
		float denominator = 0f;
		for (Genre genre : Genre.values()) {
			for (final Entry<Movie, Float> entry : neighbours.entrySet()) {
				final Movie currentMovie = entry.getKey();
				nominator += currentMovie.getGenreValue(genre)
						* (1 / Math.pow(entry.getValue(), 2));
				denominator += (1 / Math.pow(entry.getValue(), 2));
			}
			genres.put(genre, denominator != 0.0 ? nominator / denominator : 0f);
		}
	}

	private void normalizeGenreValues(final Map<Genre, Float> genres) {
		float total = 0f;
		for (float genre : genres.values()) {
			total += genre;
		}

		if (total != 0) {
			final float coef = 1 / total;
			for (final Entry<Genre, Float> entry : genres.entrySet()) {
				genres.put(entry.getKey(), entry.getValue() * coef);
			}
		}

	}

	public static void main(String[] args) {
		final TrainingSet trainingSet = new TrainingSet();
		final FuzzyClassifier classifier = new FuzzyClassifier(trainingSet);
	}

	@Override
	public void onSubmit(String title, String primaryRole,
			String secondaryRole, String director, String summary) {
		final Movie inputMovie = new Movie(title, primaryRole, secondaryRole,
				director, summary);
		classifyExample(inputMovie);
	}
}
