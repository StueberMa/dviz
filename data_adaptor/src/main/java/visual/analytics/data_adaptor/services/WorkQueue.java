package visual.analytics.data_adaptor.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.Revision;
import org.ektorp.http.HttpClient;

import visual.analytics.data_adaptor.model.LogFile;
import visual.analytics.data_adaptor.model.Machine;
import visual.analytics.data_adaptor.model.MachineCollRepository;
import visual.analytics.data_adaptor.model.MachineCollection;

/**
 * Class WorkQueue to read/convert data.
 * 
 * @author Data Visualization@University Mannheim
 * @version 15.01.2015
 */
public class WorkQueue {

	// attributes
	private List<QueuePosition> queue;
	private HttpClient httpClient;
	private CouchDbConnector couchDB;
	private MachineCollection machineColl;
	private int runningProcesses;

	/**
	 * Constructor
	 */
	public WorkQueue(int runningProcesses, HttpClient httpClient,
			CouchDbConnector couchDB) {
		this.queue = new ArrayList<QueuePosition>();
		this.httpClient = httpClient;
		this.couchDB = couchDB;
		this.machineColl = new MachineCollection();
		this.runningProcesses = runningProcesses;
	}

	/**
	 * PUSH operation
	 * 
	 * @param ds_server
	 * @param dp_req
	 */
	public synchronized void push(File file) {
		this.queue.add(new QueuePosition(file));
	}

	/**
	 * POP operation.
	 * 
	 * @return
	 */
	public synchronized QueuePosition pop() {

		if (queue.size() == 0) {
			runningProcesses--;

			// store data AND cleanup
			if (runningProcesses == 0) {

				// declaration
				MachineCollRepository machineCollRepo = null;
				List<Revision> revisions = null;

				// store MachineOverview
				System.out
						.println("Info: Storing machineOverview document to couchDB");
				machineCollRepo = new MachineCollRepository(this.couchDB);
				machineColl.setId("MACHINE_OVERVIEW");

				// check MachineOverview already exist
				if (machineCollRepo.contains("MACHINE_OVERVIEW")) {
					revisions = couchDB.getRevisions("MACHINE_OVERVIEW");
					machineColl.setRevision(revisions.get(0).getRev());
				}

				// update / create document
				machineCollRepo.update(machineColl);

				// flush bulk buffer
				System.out
						.println("Info: Flushing bulk buffer of logFile documents");
				couchDB.flushBulkBuffer();

				// clean up couchDB
				System.out.println("Info: Cleaning up couchDB");
				couchDB.compact();

				// terminate httpClient
				System.out.println("Info: Closing connection to couchDB");
				httpClient.shutdown();
			}

			return null;
		}

		return this.queue.remove(0);
	}

	/**
	 * ADD machine
	 * 
	 * @param machine
	 */
	public synchronized void addMachine(Machine machine) {
		this.machineColl.getMachineColl().add(machine);
	}

	/**
	 * ADD logFileRepo to bulk buffer
	 * 
	 * @param logFile
	 */
	public synchronized void addLogFile(LogFile logFile) {
		this.couchDB.addToBulkBuffer(logFile);
		this.couchDB.flushBulkBuffer();
	}
}
