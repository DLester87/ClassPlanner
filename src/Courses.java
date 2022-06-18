import java.io.Serializable;

/**
 * Method of Courses to add each course select to ArrayList.
 * @author Drew
 *
 */
public class Courses  implements Serializable{

	/**
	 * Makes new instance of int and String
	 */
	private int count = 0;
	private String prefix, number, title;
	/**
	 * Constructor for Courses. Inititates variables.
	 * @param _prefix
	 * @param _number
	 * @param _title
	 */
	public Courses(String _prefix, String _number, String _title) {
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
	 * @return number.
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Method to get the Title.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Method to get the toString.
	 * @return (prefix + number + title)
	 */
	public String toString() {
		return (prefix + " " + number + " " + title);
	}
	/**
	 * Method to get the size of ArrayList.
	 * @return count
	 */
	public int size() {
		return count;
	}
}
