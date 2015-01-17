package visual.analytics.data_adaptor.model;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.support.CouchDbDocument;

/**
 * Class LogFile for log conversion.
 * 
 * @author Data Visualization@University Mannheim
 * @version 15.01.2015
 */
@SuppressWarnings("serial")
public class LogFile extends CouchDbDocument {

	// attributes
	private String machinenumber;
	private List<LogEntry> actDclVolt;
	private List<LogEntry> actVel;
	private List<LogEntry> actMotTemp;
	private List<LogEntry> actPstTemp;
	private List<LogEntry> actSupVolt;
	private List<LogEntry> actTorque;
	private List<LogEntry> motEncTemp;
	private List<LogEntry> refVel;
	private List<LogEntry> others;

	// 15.01.2015: Not relevant
	// private ArrayList<LogEntry> filtTorque;
	// private ArrayList<LogEntry> motEncDiode;
	// private ArrayList<LogEntry> mainQuality;

	/**
	 * Constructor
	 */
	public LogFile() {
		this.actDclVolt = new ArrayList<LogEntry>();
		this.actVel = new ArrayList<LogEntry>();
		this.actMotTemp = new ArrayList<LogEntry>();
		this.actPstTemp = new ArrayList<LogEntry>();
		this.actSupVolt = new ArrayList<LogEntry>();
		this.actTorque = new ArrayList<LogEntry>();
		this.machinenumber = "";
		this.motEncTemp = new ArrayList<LogEntry>();
		this.refVel = new ArrayList<LogEntry>();
	}

	/**
	 * ADD actDclVolt
	 * 
	 * @param entry
	 */
	public void addActDclVolt(LogEntry entry) {
		this.actDclVolt.add(entry);
	}

	/**
	 * ADD actVel
	 * 
	 * @param entry
	 */
	public void addActVel(LogEntry entry) {
		this.actVel.add(entry);
	}

	/**
	 * ADD actMotTemp
	 * 
	 * @param entry
	 */
	public void addActMotTemp(LogEntry entry) {
		this.actMotTemp.add(entry);
	}

	/**
	 * ADD actPstTemp
	 * 
	 * @param entry
	 */
	public void addActPstTemp(LogEntry entry) {
		this.actPstTemp.add(entry);
	}

	/**
	 * ADD actSupVolt
	 * 
	 * @param entry
	 */
	public void addActSupVolt(LogEntry entry) {
		this.actSupVolt.add(entry);
	}

	/**
	 * ADD actTorque
	 * 
	 * @param entry
	 */
	public void addActTorque(LogEntry entry) {
		this.actTorque.add(entry);
	}

	/**
	 * ADD motEncTemp
	 * 
	 * @param entry
	 */
	public void addMotEncTemp(LogEntry entry) {
		this.motEncTemp.add(entry);
	}

	/**
	 * ADD refVel
	 * 
	 * @param entry
	 */
	public void addRefVel(LogEntry entry) {
		this.refVel.add(entry);
	}

	/**
	 * ADD others
	 * 
	 * @param entry
	 */
	public void addOthers(LogEntry entry) {
		this.others.add(entry);
	}

	/**
	 * GET actDclVolt
	 * 
	 * @return the actDclVolt
	 */
	public List<LogEntry> getActDclVolt() {
		return actDclVolt;
	}

	/**
	 * GET actVel
	 * 
	 * @return the actVel
	 */
	public List<LogEntry> getActVel() {
		return actVel;
	}

	/**
	 * GET actMotTemp
	 * 
	 * @return the actMotTemp
	 */
	public List<LogEntry> getActMotTemp() {
		return actMotTemp;
	}

	/**
	 * GET actPstTemp
	 * 
	 * @return the actPstTemp
	 */
	public List<LogEntry> getActPstTemp() {
		return actPstTemp;
	}

	/**
	 * GET actSupVolt
	 * 
	 * @return the actSupVolt
	 */
	public List<LogEntry> getActSupVolt() {
		return actSupVolt;
	}

	/**
	 * GET actTorque
	 * 
	 * @return the actTorque
	 */
	public List<LogEntry> getActTorque() {
		return actTorque;
	}

	/**
	 * GET machinenumber
	 * 
	 * @return the machinenumber
	 */
	public String getMachinenumber() {
		return machinenumber;
	}

	/**
	 * SET machinenumber
	 * 
	 * @param machinenumber
	 *            the machinenumber to set
	 */
	public void setMachinenumber(String machinenumber) {
		this.machinenumber = machinenumber;
	}

	/**
	 * GET motEncTemp
	 * 
	 * @return the motEncTemp
	 */
	public List<LogEntry> getMotEncTemp() {
		return motEncTemp;
	}

	/**
	 * GET refVel
	 * 
	 * @return the refVel
	 */
	public List<LogEntry> getRefVel() {
		return refVel;
	}

	/**
	 * GET others
	 * 
	 * @return the others
	 */
	public List<LogEntry> getOthers() {
		return others;
	}
}
