import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author Drew
 * Driver class to run the Program.
 * Creates a new Instance of view.
 * Calls the see method of view class.
 */
public class Project3Driver implements Serializable{

	
	public static void main(String[] args) throws IOException{

		View view = new View();
		view.see();
	}

}
