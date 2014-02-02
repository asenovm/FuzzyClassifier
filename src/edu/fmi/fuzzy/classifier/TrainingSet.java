package edu.fmi.fuzzy.classifier;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrainingSet implements Iterable<Movie> {

	/**
	 * {@value}
	 */
	@SuppressWarnings("unused")
	private static final String TAG = TrainingSet.class.getSimpleName();

	/**
	 * {@value}
	 */
	private static final String KEY_MOVIES = "movies";

	/**
	 * {@value}
	 */
	private static final String FILE_PATH_TRAINING_DATA = "assets/training_set.txt";

	private final List<Movie> movies;

	public TrainingSet() {
		movies = new LinkedList<Movie>();

		final JsonInputReader reader = new JsonInputReader();
		JSONObject trainingData = new JSONObject();
		try {
			trainingData = reader.read(FILE_PATH_TRAINING_DATA);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			trainingData.put(KEY_MOVIES, new JSONArray());
		}

		final JSONArray movieMeta = trainingData.getJSONArray(KEY_MOVIES);
		for (int i = 0; i < movieMeta.length(); ++i) {
			final JSONObject currentMovie = movieMeta.getJSONObject(i);
			final Movie movie = new Movie(currentMovie);
			movies.add(movie);
		}
	}

	@Override
	public String toString() {
		return movies.toString();
	}

	@Override
	public Iterator<Movie> iterator() {
		return movies.iterator();
	}
}
