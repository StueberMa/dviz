package visual.analytics.data_adaptor.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class LogEntry for Conversion
 * 
 * @author Data Visualization@University Mannheim
 * @version 15.01.2015
 */
@JsonPropertyOrder({ "machinenumber", "timestamp", "channel", "idx", "level",
		"logentry", "logfile", "messageid", "msg", "param", "starttimestamp",
		"type" })
public class LogEntry {

	// attributes
	private String machinenumber;
	private String timestamp;
	private String channel;
	private int idx;
	private int level;
	private int logentry;
	private String logfile;
	private int messageid;
	private String msg;
	private int param;
	private String starttimestamp;
	private int type;

	/**
	 * Constructor
	 */
	public LogEntry() {

	}

	/**
	 * Constructor
	 * 
	 * @param machinenumber
	 * @param timestamp
	 * @param channel
	 * @param idx
	 * @param level
	 * @param logentry
	 * @param messageid
	 * @param msg
	 * @param param
	 * @param starttimestamp
	 * @param type
	 */
	public LogEntry(String machinenumber, String timestamp, String channel,
			int idx, int level, int logentry, String logfile, int messageid,
			String msg, int param, String starttimestamp, int type) {
		this.machinenumber = machinenumber;
		this.timestamp = timestamp;
		this.channel = channel;
		this.idx = idx;
		this.level = level;
		this.logentry = logentry;
		this.logfile = logfile;
		this.messageid = messageid;
		this.msg = msg;
		this.param = param;
		this.starttimestamp = starttimestamp;
		this.type = type;
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
	 * GET timestamp
	 * 
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * SET timestamp
	 * 
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * GET channel
	 * 
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * SET channel
	 * 
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * GET idc
	 * 
	 * @return the idx
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * SET idc
	 * 
	 * @param idx
	 *            the idx to set
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}

	/**
	 * GET level
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * SET level
	 * 
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * GET logentry
	 * 
	 * @return the logentry
	 */
	public int getLogentry() {
		return logentry;
	}

	/**
	 * SET logentry
	 * 
	 * @param logentry
	 *            the logentry to set
	 */
	public void setLogentry(int logentry) {
		this.logentry = logentry;
	}

	/**
	 * GET logfile
	 * 
	 * @return the logfile
	 */
	public String getLogfile() {
		return logfile;
	}

	/**
	 * SET logfile
	 * 
	 * @param logfile
	 *            the logfile to set
	 */
	public void setLogfile(String logfile) {
		this.logfile = logfile;
	}

	/**
	 * GET messageid
	 * 
	 * @return the messageid
	 */
	public int getMessageid() {
		return messageid;
	}

	/**
	 * SET messageid
	 * 
	 * @param messageid
	 *            the messageid to set
	 */
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}

	/**
	 * GET msg
	 * 
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * SET msg
	 * 
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * GET param
	 * 
	 * @return the param
	 */
	public int getParam() {
		return param;
	}

	/**
	 * SET param
	 * 
	 * @param param
	 *            the param to set
	 */
	public void setParam(int param) {
		this.param = param;
	}

	/**
	 * GET starttimestamp
	 * 
	 * @return the starttimestamp
	 */
	public String getStarttimestamp() {
		return starttimestamp;
	}

	/**
	 * SET starttimestamp
	 * 
	 * @param starttimestamp
	 *            the starttimestamp to set
	 */
	public void setStarttimestamp(String starttimestamp) {
		this.starttimestamp = starttimestamp;
	}

	/**
	 * GET type
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * SET type
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}