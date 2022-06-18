import java.awt.BorderLayout;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFrame;
/**
 * 
 * @author Drew
 * View Class that represents the first view to select 
 * either start new or load program.
 */
public class View implements Serializable{

	/**
	 * Creates a new instance of JFrame and Model.
	 */
	private JFrame frame;
	private ModelOne model;
	
	/**
	 * Creates a new View with the given attributes.
	 */
	public View() throws IOException{
		
		this.frame = new JFrame("ClassPlan");
		this.model = new ModelOne();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout();
		frame.getContentPane().add(model);
	}
	
	/**
	 * Sets the location and visibility of the View.
	 */
	public void see() {
		
		frame.add(model, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
