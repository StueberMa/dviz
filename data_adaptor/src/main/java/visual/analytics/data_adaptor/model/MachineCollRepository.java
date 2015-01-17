package visual.analytics.data_adaptor.model;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 * Class to persist MachineCollRepository to CouchDB.
 * 
 * @author Data Visualization@University Mannheim
 * @version 16.01.2015
 */
public class MachineCollRepository extends
		CouchDbRepositorySupport<MachineCollection> {

	/**
	 * Constructor
	 * 
	 * @param couchDB
	 */
	public MachineCollRepository(CouchDbConnector couchDB) {
		super(MachineCollection.class, couchDB);
	}
}
