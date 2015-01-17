package visual.analytics.data_adaptor.services;

import java.io.File;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DbAccessException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

/**
 * Class to read csv-files, convert to JSON and store in MongoDB
 * 
 * @author Data Visualization@University Mannheim
 * @version 15.01.2015
 */
public class DataAdaptor {

	// constants
	public static final int WORKERPOOL_SIZE = 10;
	public static final String INPUT_PATH = System.getProperty("user.dir")
			+ "/../data";
	public static final String OUTPUT_PATH = System.getProperty("user.dir")
			+ "/../data/json";

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// declaration
		File directory = null;
		File[] allFiles = null;
		HttpClient httpClient = null;
		CouchDbInstance dbInstance = null;
		CouchDbConnector couchDB = null;
		WorkQueue queue = null;

		// connect to couchDB
		try {
			System.out.println("Info: Opening connection to couchDB");
			httpClient = new StdHttpClient.Builder().build();
			dbInstance = new StdCouchDbInstance(httpClient);
			couchDB = dbInstance.createConnector("visual_analytics", true);
		} catch (DbAccessException e) {
			System.out
					.println("Warning: Connection to couchDB failed; Storing data as file");
		}

		// create queue
		queue = new WorkQueue(WORKERPOOL_SIZE, httpClient, couchDB);

		// get files from directory
		System.out.println("Info: Scanning directory " + INPUT_PATH
				+ " for logs");
		directory = new File(INPUT_PATH);
		allFiles = directory.listFiles();

		// add files to queue
		for (File file : allFiles) {
			// filter files not ending in *.csv
			if (!file.getName().contains(".csv"))
				continue;

			// call worker
			queue.push(file);
		}

		// create workers
		for (int i = 0; i < WORKERPOOL_SIZE; i++) {
			PoolWorker worker = new PoolWorker(queue, couchDB);
			worker.start();
		}
	}
}
