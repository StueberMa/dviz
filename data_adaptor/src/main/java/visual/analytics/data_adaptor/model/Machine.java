package visual.analytics.data_adaptor.model;


/**
 * Class Machine for log conversion
 * 
 * @author Data Visualization@University Mannheim
 * @version 16.01.2015
 */
public class Machine {
	
	// attributes
	private String machinenumber;
	private LogEntry lastActDclVolt;
	private LogEntry lastActVel;
	private LogEntry lastActMotTemp;
	private LogEntry lastActPstTemp;
	private LogEntry lastActSupVolt;
	private LogEntry lastActTorque;
	private LogEntry lastMotEncTemp;
	private LogEntry lastRefVel;
	
	//15.01.2015: Not relevant
	//private LogEntry lastFiltTorque;
	//private LogEntry lastMotEncDiode;
	//private LogEntry> lastMainQuality;
	
	/**
	 * Constructor.
	 */
	public Machine(){
		
	}

	/**
	 * GET machinenumber
	 * @return the machinenumber
	 */
	public String getMachinenumber() {
		return machinenumber;
	}

	/**
	 * SET machinenumber
	 * @param machinenumber the machinenumber to set
	 */
	public void setMachinenumber(String machinenumber) {
		this.machinenumber = machinenumber;
	}

	/**
	 * GET lastActDclVolt
	 * @return the lastActDclVolt
	 */
	public LogEntry getLastActDclVolt() {
		return lastActDclVolt;
	}

	/**
	 * SET lastActDclVolt
	 * @param lastActDclVolt the lastActDclVolt to set
	 */
	public void setLastActDclVolt(LogEntry lastActDclVolt) {
		this.lastActDclVolt = lastActDclVolt;
	}

	/**
	 * GET lastActVel
	 * @return the lastActVel
	 */
	public LogEntry getLastActVel() {
		return lastActVel;
	}

	/**
	 * SET lastActVel
	 * @param lastActVel the lastActVel to set
	 */
	public void setLastActVel(LogEntry lastActVel) {
		this.lastActVel = lastActVel;
	}

	/**
	 * GET lastActMotTemp
	 * @return the lastActMotTemp
	 */
	public LogEntry getLastActMotTemp() {
		return lastActMotTemp;
	}

	/**
	 * SET lastActMotTemp
	 * @param lastActMotTemp the lastActMotTemp to set
	 */
	public void setLastActMotTemp(LogEntry lastActMotTemp) {
		this.lastActMotTemp = lastActMotTemp;
	}

	/**
	 * GET lastActPstTemp
	 * @return the lastActPstTemp
	 */
	public LogEntry getLastActPstTemp() {
		return lastActPstTemp;
	}

	/**
	 * SET lastActPstTemp
	 * @param lastActPstTemp the lastActPstTemp to set
	 */
	public void setLastActPstTemp(LogEntry lastActPstTemp) {
		this.lastActPstTemp = lastActPstTemp;
	}

	/**
	 * GET lastActSupVolt
	 * @return the lastActSupVolt
	 */
	public LogEntry getLastActSupVolt() {
		return lastActSupVolt;
	}

	/**
	 * SET lastActSupVolt
	 * @param lastActSupVolt the lastActSupVolt to set
	 */
	public void setLastActSupVolt(LogEntry lastActSupVolt) {
		this.lastActSupVolt = lastActSupVolt;
	}

	/**
	 * GET lastActTorque
	 * @return the lastActTorque
	 */
	public LogEntry getLastActTorque() {
		return lastActTorque;
	}

	/**
	 * SET lastActTorque
	 * @param lastActTorque the lastActTorque to set
	 */
	public void setLastActTorque(LogEntry lastActTorque) {
		this.lastActTorque = lastActTorque;
	}

	/**
	 * GET lastMotEncTemp
	 * @return the lastMotEncTemp
	 */
	public LogEntry getLastMotEncTemp() {
		return lastMotEncTemp;
	}

	/**
	 * SET lastMotEncTemp
	 * @param lastMotEncTemp the lastMotEncTemp to set
	 */
	public void setLastMotEncTemp(LogEntry lastMotEncTemp) {
		this.lastMotEncTemp = lastMotEncTemp;
	}

	/**
	 * GET lastRefVel
	 * @return the lastRefVel
	 */
	public LogEntry getLastRefVel() {
		return lastRefVel;
	}

	/**
	 * SET lastRefVel
	 * @param lastRefVel the lastRefVel to set
	 */
	public void setLastRefVel(LogEntry lastRefVel) {
		this.lastRefVel = lastRefVel;
	}
}
