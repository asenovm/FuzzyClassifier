package edu.fmi.fuzzy.classifier;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

public class FuzzyClassifier {

	private static final int NEIGHBOURS_CONSIDERED = 5;

	private final TrainingSet trainingSet;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		this.trainingSet = trainingSet;
		MovieIndexer indexer;
		try {
			indexer = new MovieIndexer();
			for (final Movie movie : trainingSet) {
				indexer.index(movie);
			}
			final Movie inputMovie = new Movie(System.in);
			System.out.println(indexer.getClosestMatches(inputMovie,
					NEIGHBOURS_CONSIDERED));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final TrainingSet trainingSet = new TrainingSet();
		final FuzzyClassifier classifier = new FuzzyClassifier(trainingSet);
	}
}
