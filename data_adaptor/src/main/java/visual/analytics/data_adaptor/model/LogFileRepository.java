package visual.analytics.data_adaptor.model;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 * Class to persist LogFile to CouchDB
 * 
 * @author Data Visualization@University Mannheim
 * @version 16.01.2015
 */
public class LogFileRepository extends CouchDbRepositorySupport<LogFile> {

	/**
	 * Constructor
	 * 
	 * @param couchDB
	 */
	public LogFileRepository(CouchDbConnector couchDB) {
		super(LogFile.class, couchDB);
	}
}
