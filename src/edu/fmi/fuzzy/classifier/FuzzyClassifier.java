package edu.fmi.fuzzy.classifier;

public class FuzzyClassifier {

	private final TrainingSet trainingSet;

	public FuzzyClassifier(final TrainingSet trainingSet) {
		this.trainingSet = trainingSet;
	}

	public static void main(String[] args) {
		final TrainingSet trainingSet = new TrainingSet();
		final FuzzyClassifier classifier = new FuzzyClassifier(trainingSet);

		// TODO add GUI for all this stuff
		final Movie inputMovie = new Movie(System.in);
	}
}
