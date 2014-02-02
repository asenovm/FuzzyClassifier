package edu.fmi.fuzzy.classifier;

import java.io.IOException;

public class FuzzyClassifier {

	private final TrainingSet trainingSet;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		this.trainingSet = trainingSet;
		SummaryIndexer indexer;
		try {
			indexer = new SummaryIndexer();
			for (final Movie movie : trainingSet) {
				indexer.index(movie);
			}

			// TODO add GUI for all this stuff
			final Movie inputMovie = new Movie(System.in);
			indexer.getClosestMatch(inputMovie.getSummary());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final TrainingSet trainingSet = new TrainingSet();
		final FuzzyClassifier classifier = new FuzzyClassifier(trainingSet);
	}
}
