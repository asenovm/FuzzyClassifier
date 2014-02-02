package edu.fmi.fuzzy.classifier;

import java.io.InputStream;
import java.util.Scanner;

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
	private static final String VALUE_ACTION = "action";

	/**
	 * {@value}
	 */
	private static final String VALUE_COMEDY = "comedy";

	/**
	 * {@value}
	 */
	private static final String VALUE_ADVENTURE = "adventure";

	/**
	 * {@value}
	 */
	private static final String VALUE_SCI_FI = "sci-fi";

	/**
	 * {@value}
	 */
	private static final String VALUE_THRILLER = "thriller";

	/**
	 * {@value}
	 */
	private final JSONObject movieMeta;

	public Movie(final JSONObject movie) {
		this.movieMeta = movie;
	}

	public Movie(final InputStream inputStream) {
		final Scanner scanner = new Scanner(inputStream);
		movieMeta = new JSONObject();

		movieMeta.put(TITLE, scanner.nextLine());
		movieMeta.put(PRIMARY_ROLE, scanner.nextLine());
		movieMeta.put(SECONDARY_ROLE, scanner.nextLine());
		movieMeta.put(DIRECTOR, scanner.nextLine());
		movieMeta.put(SUMMARY, scanner.nextLine());
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

	public double getActionValue() {
		return movieMeta.getDouble(VALUE_ACTION);
	}

	public double getComedyValue() {
		return movieMeta.getDouble(VALUE_COMEDY);
	}

	public double getAdventureValue() {
		return movieMeta.getDouble(VALUE_ADVENTURE);
	}

	public double getSciFiValue() {
		return movieMeta.getDouble(VALUE_SCI_FI);
	}

	public double getThrillerValue() {
		return movieMeta.getDouble(VALUE_THRILLER);
	}

	@Override
	public String toString() {
		return movieMeta.toString();
	}

}
