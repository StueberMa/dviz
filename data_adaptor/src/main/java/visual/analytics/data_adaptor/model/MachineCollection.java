package visual.analytics.data_adaptor.model;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.support.CouchDbDocument;

/**
 * Class MachineCollection for log conversion
 * 
 * @author Data Visualization@University Mannheim
 * @version 16.01.2015
 */
@SuppressWarnings("serial")
public class MachineCollection extends CouchDbDocument {

	// attributes
	private List<Machine> machineColl;

	/**
	 * Constructor
	 */
	public MachineCollection() {
		this.machineColl = new ArrayList<Machine>();
	}

	/**
	 * GET machineColl
	 * 
	 * @return the machineColl
	 */
	public List<Machine> getMachineColl() {
		return machineColl;
	}

	/**
	 * SET machineColl
	 * 
	 * @param machineColl
	 *            the machineColl to set
	 */
	public void setMachineColl(List<Machine> machineColl) {
		this.machineColl = machineColl;
	}
}
