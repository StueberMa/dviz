package visual.analytics.data_adaptor.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.Revision;

import visual.analytics.data_adaptor.model.LogEntry;
import visual.analytics.data_adaptor.model.LogFile;
import visual.analytics.data_adaptor.model.LogFileRepository;
import visual.analytics.data_adaptor.model.Machine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Class PoolWorker to process single file
 * 
 * @author Data Visualization@University Mannheim
 * @version 15.01.2015
 */
public class PoolWorker extends Thread {

	// attributes
	private CouchDbConnector couchDB;
	private WorkQueue queue;
	private QueuePosition position;

	// constants
	private static final char SEPERATOR = ';';

	/**
	 * Constructor.
	 * 
	 * @param queue
	 */
	public PoolWorker(WorkQueue queue, CouchDbConnector db) {
		this.couchDB = db;
		this.queue = queue;
		this.position = null;
	}

	/**
	 * Run method of thread.
	 */
	@Override
	public void run() {

		// declaration
		CsvMapper mapper = null;
		MappingIterator<LogEntry> it = null;
		CsvSchema schema = null;
		LogFile log = null;
		Machine machine = null;
		Gson gson = null;
		LogFileRepository logFileRepo = null;
		List<Revision> revisions = null;
		BufferedWriter bw = null;
		int index = 0;

		// initialization
		mapper = new CsvMapper();
		schema = CsvSchema.builder().addColumn("machinenumber")
				.addColumn("timestamp").addColumn("channel")
				.addColumn("idx", CsvSchema.ColumnType.NUMBER)
				.addColumn("level", CsvSchema.ColumnType.NUMBER)
				.addColumn("logentry", CsvSchema.ColumnType.NUMBER)
				.addColumn("logfile")
				.addColumn("messageid", CsvSchema.ColumnType.NUMBER)
				.addColumn("msg")
				.addColumn("param", CsvSchema.ColumnType.NUMBER)
				.addColumn("starttimestamp")
				.addColumn("type", CsvSchema.ColumnType.NUMBER)
				.setColumnSeparator(SEPERATOR).setSkipFirstDataRow(true)
				.build();

		// run ALL the time
		while (true) {

			// pop new queue position
			position = queue.pop();

			// terminate if queue is empty
			if (position == null)
				break;

			System.out.println("Info: Start processing ("
					+ position.getFile().getName() + ")");

			// convert file to JSON
			try {
				it = mapper.reader(LogEntry.class).with(schema)
						.readValues(position.getFile());
				log = new LogFile();

				while (it.hasNextValue()) {
					LogEntry entry = it.nextValue();

					// save machinenumber
					if (log.getMachinenumber().equals("")) {
						log.setMachinenumber(entry.getMachinenumber());
						log.setId(entry.getMachinenumber());
					} else if (!log.getMachinenumber().equals(
							entry.getMachinenumber())) {
						System.out
								.println("Error: Multiple machines in single file ("
										+ position.getFile().getName() + ")");
						return;
					}

					// add to logfile based on msgid
					switch (entry.getMessageid()) {

					// motEncTemp
					case 6455349:
						log.addMotEncTemp(entry);
						break;

					// actVel
					case 6457586:
						log.addActVel(entry);
						break;

					// motEncTemp (OR motEncDiode)
					case 6460270:
						if (entry.getMsg().contains("motEncTemp"))
							log.addMotEncTemp(entry);
						break;

					// actTorque OR actVel (OR filtTorque)
					case 6461550:
						if (entry.getMsg().contains("actTorque"))
							log.addActTorque(entry);
						else if (entry.getMsg().contains("actVel"))
							log.addActVel(entry);
						break;

					// refVel
					case 6462271:
						log.addRefVel(entry);
						break;

					// actMotTemp OR actTorque OR motEncTemp(OR filtTorque)
					case 6465195:
						if (entry.getMsg().contains("actMotTemp"))
							log.addActMotTemp(entry);
						else if (entry.getMsg().contains("actTorque"))
							log.addActTorque(entry);
						else if (entry.getMsg().contains("motEncTemp"))
							log.addMotEncTemp(entry);
						break;

					// actPstTemp OR actSupVolt OR actTorque OR motEncTemp OR
					// actPstTemp (OR motEncDiode OR
					// filtTorque)
					case 6465537:
						if (entry.getMsg().contains("actPstTemp"))
							log.addActPstTemp(entry);
						else if (entry.getMsg().contains("actSupVolt"))
							log.addActSupVolt(entry);
						else if (entry.getMsg().contains("actTorque"))
							log.addActTorque(entry);
						else if (entry.getMsg().contains("motEncTemp"))
							log.addMotEncTemp(entry);
						else if (entry.getMsg().contains("actPstTemp"))
							;
						log.addActPstTemp(entry);
						break;

					// actDclVolt OR motEncTemp (OR motEncDiode)
					case 6467205:
						if (entry.getMsg().contains("actDclVolt"))
							log.addActDclVolt(entry);
						else if (entry.getMsg().contains("motEncTemp"))
							log.addMotEncTemp(entry);
						break;

					// actSupVolt OR motEncTemp (OR motEncDiod)
					case 6470192:
						if (entry.getMsg().contains("actSupVolt"))
							log.addActSupVolt(entry);
						else if (entry.getMsg().contains("motEncTemp"))
							log.addActTorque(entry);
						break;

					// filtTorque (not relevant)
					case 6461433:
						break;

					// MainQuality (not relevant)
					case 6465334:
						break;

					default:
						log.addOthers(entry);
						break;
					}
				}

				// set last data for MachineOverview
				machine = new Machine();
				machine.setMachinenumber(log.getMachinenumber());

				index = log.getActDclVolt().size() - 1;
				if (index >= 0)
					machine.setLastActDclVolt(log.getActDclVolt().get(index));

				index = log.getActMotTemp().size() - 1;
				if (index >= 0)
					machine.setLastActMotTemp(log.getActMotTemp().get(index));

				index = log.getActPstTemp().size() - 1;
				if (index >= 0)
					machine.setLastActPstTemp(log.getActPstTemp().get(index));

				index = log.getActSupVolt().size() - 1;
				if (index >= 0)
					machine.setLastActSupVolt(log.getActSupVolt().get(index));

				index = log.getActTorque().size() - 1;
				if (index >= 0)
					machine.setLastActTorque(log.getActTorque().get(index));

				index = log.getActVel().size() - 1;
				if (index >= 0)
					machine.setLastActVel(log.getActVel().get(index));

				index = log.getMotEncTemp().size() - 1;
				if (index >= 0)
					machine.setLastMotEncTemp(log.getMotEncTemp().get(index));

				index = log.getRefVel().size() - 1;
				if (index >= 0)
					machine.setLastRefVel(log.getRefVel().get(index));

				// add to central machineColl
				queue.addMachine(machine);
			} catch (JsonProcessingException e) {
				System.out.println("Error: JSON processing failed ("
						+ position.getFile().getName() + ")");
			} catch (IOException e) {
				System.out.println("Error: I/O error during processing ("
						+ position.getFile().getName() + ")");
			}

			// write to CouchDB
			if (couchDB != null) {
				logFileRepo = new LogFileRepository(couchDB);

				// check if LogFile already exist
				if (logFileRepo.contains(log.getMachinenumber())) {
					revisions = couchDB.getRevisions(log.getMachinenumber());
					log.setRevision(revisions.get(0).getRev());
				}

				// add logFile to central bulk buffer
				queue.addLogFile(log);

			} else {
				File outputDir = new File(DataAdaptor.OUTPUT_PATH);

				try {
					// create the directory, if does not exist
					if (!outputDir.exists())
						outputDir.mkdir();

					// write JSON to file
					gson = new GsonBuilder().setPrettyPrinting().create();
					bw = new BufferedWriter(new FileWriter(outputDir + "/"
							+ log.getMachinenumber() + ".json"));
					bw.write(gson.toJson(log));

				} catch (SecurityException se) {
					System.out.println("Error: Not able to create directory("
							+ position.getFile().getName() + ")");
				} catch (IOException ioe) {
					System.out.println("Error: I/O error during processing ("
							+ position.getFile().getName() + ")");
				}

				// close BufferedWriter
				try {
					if (bw != null)
						bw.close();
				} catch (IOException ioe) {
					System.out
							.println("Error: Not able to close BufferedWriter("
									+ position.getFile().getName() + ")");
				}
			}

			// print complete message
			System.out.println("Info: Finished processing ("
					+ position.getFile().getName() + ")");
		}
	}
}
