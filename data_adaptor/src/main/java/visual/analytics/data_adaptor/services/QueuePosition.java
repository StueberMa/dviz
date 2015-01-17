package visual.analytics.data_adaptor.services;

import java.io.File;

/**
 * Class QueuePosition.
 * 
 * @author Data Visualization@University Mannheim
 * @version 15.01.2015
 */
public class QueuePosition {

	// attribute
	private File file;

	/**
	 * constructor
	 * 
	 * @param file
	 */
	public QueuePosition(File file) {
		this.file = file;
	}

	/**
	 * get file
	 * 
	 * @return
	 */
	public File getFile() {
		return this.file;
	}
}
