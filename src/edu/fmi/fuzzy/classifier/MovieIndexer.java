package edu.fmi.fuzzy.classifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import edu.fmi.fuzzy.classifier.model.Movie;

public class MovieIndexer {

	/**
	 * {@value}
	 */
	@SuppressWarnings("unused")
	private static final String TAG = MovieIndexer.class.getSimpleName();

	/**
	 * {@value}
	 */
	private static final String FIELD_DIRECTOR = "director";

	/**
	 * {@value}
	 */
	private static final String FIELD_SECONDARY_ROLE = "secondaryRole";

	/**
	 * {@value}
	 */
	private static final String FIELD_PRIMARY_ROLE = "primaryRole";

	/**
	 * {@value}
	 */
	private static final String FIELD_TITLE = "title";

	/**
	 * {@value}
	 */
	private static final String FIELD_SUMMARY = "summary";

	private final List<Movie> indexedMovies;

	private final IndexWriter writer;

	public MovieIndexer() throws IOException {
		final RAMDirectory indexDirectory = new RAMDirectory();
		final IndexWriterConfig config = new IndexWriterConfig(
				Version.LUCENE_46, new StandardAnalyzer(Version.LUCENE_46));
		writer = new IndexWriter(indexDirectory, config);

		indexedMovies = new ArrayList<Movie>();
	}

	public void index(final Movie movie) {
		final Document document = new Document();

		final TextField title = new TextField(FIELD_TITLE, movie.getTitle(),
				Field.Store.YES);
		document.add(title);

		final TextField primaryRole = new TextField(FIELD_PRIMARY_ROLE,
				movie.getPrimaryRole(), Field.Store.YES);
		document.add(primaryRole);

		final TextField secondaryRole = new TextField(FIELD_SECONDARY_ROLE,
				movie.getSecondaryRole(), Field.Store.YES);
		document.add(secondaryRole);

		final TextField director = new TextField(FIELD_DIRECTOR,
				movie.getDirector(), Field.Store.YES);
		document.add(director);

		final TextField summary = new TextField(FIELD_SUMMARY,
				movie.getSummary(), Field.Store.YES);
		document.add(summary);

		try {
			writer.addDocument(document);
			indexedMovies.add(movie);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Movie, Float> getClosestMatches(final Movie movie,
			final int numberOfHits) throws ParseException, IOException {
		final StandardAnalyzer analyzer = new StandardAnalyzer(
				Version.LUCENE_46);
		final QueryParser parser = new QueryParser(Version.LUCENE_46,
				FIELD_SUMMARY, analyzer);
		final Query query = parser.parse(movie.toString());
		final TopScoreDocCollector collector = TopScoreDocCollector.create(
				numberOfHits, true);

		final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(
				writer, true));
		searcher.search(query, collector);

		final Map<Movie, Float> result = new LinkedHashMap<Movie, Float>();
		final ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;
		for (final ScoreDoc doc : scoreDocs) {
			result.put(indexedMovies.get(doc.doc), 1 - doc.score);
		}
		return result;
	}
}
