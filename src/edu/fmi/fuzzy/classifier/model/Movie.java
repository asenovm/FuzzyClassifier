package edu.fmi.fuzzy.classifier.model;

import org.json.JSONObject;

public class Movie {

	@SuppressWarnings("unused")
	private static final String TAG = Movie.class.getSimpleName();

	/**
	 * {@value}
	 */
	private static final String TITLE = "title";

	/**
	 * {@value}
	 */
	private static final String PRIMARY_ROLE = "primaryRole";

	/**
	 * {@value}
	 */
	private static final String SECONDARY_ROLE = "secondaryRole";

	/**
	 * {@value}
	 */
	private static final String DIRECTOR = "director";

	/**
	 * {@value}
	 */
	private static final String SUMMARY = "summary";

	/**
	 * {@value}
	 */
	private final JSONObject movieMeta;

	public Movie(final JSONObject movie) {
		this.movieMeta = movie;
	}

	public Movie(final String title, final String primaryRole,
			final String secondaryRole, final String director,
			final String plotSummary) {
		movieMeta = new JSONObject();

		movieMeta.put(TITLE, title);
		movieMeta.put(PRIMARY_ROLE, primaryRole);
		movieMeta.put(SECONDARY_ROLE, secondaryRole);
		movieMeta.put(DIRECTOR, director);
		movieMeta.put(SUMMARY, plotSummary);
	}

	public String getTitle() {
		return movieMeta.getString(TITLE);
	}

	public String getPrimaryRole() {
		return movieMeta.getString(PRIMARY_ROLE);
	}

	public String getSecondaryRole() {
		return movieMeta.getString(SECONDARY_ROLE);
	}

	public String getDirector() {
		return movieMeta.getString(DIRECTOR);
	}

	public String getSummary() {
		return movieMeta.getString(SUMMARY);
	}

	public double getGenreValue(Genre genre) {
		return movieMeta.getDouble(genre.name().toLowerCase());
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(getTitle());
		builder.append("\n");
		builder.append(getPrimaryRole());
		builder.append("\n");
		builder.append(getSecondaryRole());
		builder.append("\n");
		builder.append(getDirector());
		builder.append("\n");
		builder.append(getSummary());
		builder.append("\n");
		return builder.toString();
	}
}
