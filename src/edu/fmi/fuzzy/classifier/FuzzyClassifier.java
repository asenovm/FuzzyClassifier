package edu.fmi.fuzzy.classifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.queryparser.classic.ParseException;

import edu.fmi.fuzzy.classifier.model.Genre;
import edu.fmi.fuzzy.classifier.model.Movie;

public class FuzzyClassifier {

	private static final int NEIGHBOURS_CONSIDERED = 3;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		MovieIndexer indexer;
		try {
			indexer = new MovieIndexer();
			for (final Movie movie : trainingSet) {
				indexer.index(movie);
			}
			final Movie inputMovie = new Movie(System.in);
			final Map<Movie, Float> neighbours = indexer.getClosestMatches(
					inputMovie, NEIGHBOURS_CONSIDERED);

			final Map<Genre, Float> genres = new HashMap<Genre, Float>();
			computeGenreValues(neighbours, genres);
			normalizeGenreValues(genres);
			System.out.println(genres);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void computeGenreValues(final Map<Movie, Float> neighbours,
			final Map<Genre, Float> genres) {
		float nominator = 0f;
		float denominator = 0f;
		for (Genre genre : Genre.values()) {
			for (final Entry<Movie, Float> entry : neighbours.entrySet()) {
				final Movie currentMovie = entry.getKey();
				nominator += currentMovie.getGenreValue(genre)
						* (1f / Math.pow(entry.getValue(), 2));
				denominator += (1f / Math.pow(entry.getValue(), 2));
			}
			genres.put(genre, nominator / denominator);
		}
	}

	private void normalizeGenreValues(final Map<Genre, Float> genres) {
		float total = 0f;
		for (float genre : genres.values()) {
			total += genre;
		}

		final float coef = 1 / total;
		for (final Entry<Genre, Float> entry : genres.entrySet()) {
			genres.put(entry.getKey(), entry.getValue() * coef);
		}
	}

	public static void main(String[] args) {
		final TrainingSet trainingSet = new TrainingSet();
		final FuzzyClassifier classifier = new FuzzyClassifier(trainingSet);
	}
}
