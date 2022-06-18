import java.io.Serializable;

/**
 * Creates a new class of AllClasses
 * @author Drew
 *
 */
public class AllClasses  implements Serializable{

	/**
	 * Initiates three Strings for prefix, number, and title.
	 */
	private String prefix, number, title;
	
	/**
	 * Constructor for AllClasses.
	 * @param _prefix
	 * @param _number
	 * @param _title
	 */
	public AllClasses(String _prefix, String _number, String _title) {
		this.prefix = _prefix;
		this.number = _number;
		this.title = _title;
	}
	/**
	 * Method to get the prefix.
	 * @return prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * Method to get the number.
	 * @return number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Method to get the title.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Method for the toString.
	 * @return prefix + number + title
	 */
	public String toString() {
		return (prefix + " " + number + " " + title);
	}
}
