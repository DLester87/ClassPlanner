import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Drew
 *	Semester class to add ArrayList of Courses.
 */
public class Semester  implements Serializable{

	/**
	 * Creates instances of int, boolean, and ArrayList.
	 */
	private int count = 0;
	private boolean alreadyAdded = false;
	private ArrayList<Courses> classes;
	
	/**
	 * Construct to initiate ArrayList classes.
	 */
	public Semester() {
		classes = new ArrayList<Courses>();
	}
	
	/**
	 * Method to add courses. Increases count for each.
	 * @param tempCourse
	 */
	public void add(Courses tempCourse) {
		classes.add(tempCourse);
		count++;
	}

	/**
	 * Method to get the count of classes.
	 * @return count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Method to see which courses are already added.
	 * @param prefix
	 * @param number
	 * @param title
	 * @return alreadyAdded
	 */
	public boolean checkCourses(String prefix, String number, String title) {
		for(int i = 0; i < classes.size(); i++) {
			if(classes.get(i).getPrefix().equals(prefix) && classes.get(i).getNumber().equals(number) &&
					classes.get(i).getTitle().equals(title)) {
				alreadyAdded = true;
			}
		}
		return alreadyAdded;
	}
	/**
	 * Method to get ArrayList of courses.
	 * @return classes
	 */
	public ArrayList<Courses> getCourse() {
		
		return classes;
	}
	/**
	 * Method to get a certain course.
	 * @param i
	 * @return classes.get(i)
	 */
	public Courses getCourse(int i){
		return classes.get(i);
	}
}
