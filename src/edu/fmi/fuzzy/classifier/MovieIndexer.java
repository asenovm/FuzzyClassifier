package edu.fmi.fuzzy.classifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class MovieIndexer {

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

	public Movie getClosestMatch(final Movie movie) throws ParseException,
			IOException {
		final StandardAnalyzer analyzer = new StandardAnalyzer(
				Version.LUCENE_46);
		final QueryParser parser = new QueryParser(Version.LUCENE_46,
				FIELD_SUMMARY, analyzer);
		final Query query = parser.parse(movie.getSummary());
		final TopScoreDocCollector collector = TopScoreDocCollector.create(1,
				true);

		final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(
				writer, true));
		searcher.search(query, collector);

		final ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;
		return indexedMovies.get(scoreDocs[0].doc);
	}
}