import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;
/**
 * 
 * @author Drew
 * ModelOne class creates the buttons and attributes of First View.
 */
public class ModelOne extends JPanel  implements Serializable{

	/**
	 * Creates instances of JButton, JPanel, JLabel, JFrame, and Model.
	 */
	private JButton newPlan, loadPlan;
	private JPanel buttonPanel;
	private JLabel label;
	private JFrame frame;
	private Model model;
	private ArrayList<Semester> semester = new ArrayList<Semester>();
	private ArrayList<Semester> allSemesters;
	
	/**
	 * Sets the instances to their information.
	 * Creates newPlan and loadPlan buttons.
	 * Adds a buttonlistener to both buttons.
	 */
	public ModelOne(){
		newPlan = new JButton("Start New Plan");
		loadPlan = new JButton("Load Plan");
		label = new JLabel();
		buttonPanel = new JPanel();
		ButtonListener listener = null;
		listener = new ButtonListener();
		newPlan.addActionListener(listener);
		loadPlan.addActionListener(listener);
		newPlan.setPreferredSize(new Dimension(120,80));
		loadPlan.setPreferredSize(new Dimension(120,80));
				
		buttonPanel.setPreferredSize(new Dimension(300,150));
		buttonPanel.setBackground(Color.blue);
		buttonPanel.add(newPlan);
		buttonPanel.add(loadPlan);
		setPreferredSize(new Dimension(300,160));
		setBackground(Color.gray);
		add(label);
		add(buttonPanel);
	}
	
	/**
	 * 
	 * @author Drew
	 * Creates a new class of ButtonListener
	 */
	private class ButtonListener implements ActionListener, Serializable{
		
		/**
		 * Method to determine which button is pressed.
		 * Creates a new plan or loads a previous plan.
		 */
		public void actionPerformed (ActionEvent event){
			
			if(event.getSource() == newPlan) {
				try {
					model = new Model();
					frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().add(model);
					frame.add(model, BorderLayout.CENTER);
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if( event.getSource() == loadPlan){
				String loadFileName;
				loadFileName = JOptionPane.showInputDialog("Name of File to Load");
				try {
					load(loadFileName);
					model = this.load(loadFileName);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		public Model load(String fileName) throws IOException, ClassNotFoundException{
			ModelOne one = new ModelOne();
			
			//ArrayList<Semester> allSemesters = model.getSemester();
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			one = (ModelOne) ois.readObject();
			//Model model = new Model();
			//model = new Model(allSemesters);
			ois.close();
			return model;
		}
	}
		/**
		 * Method to load a file.
		 * @param fileName
		 * @return mod
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		/*public Model load(String fileName) throws IOException, ClassNotFoundException{
			/*JFileChooser fileChooser = new JFileChooser();
			File loadedFile = null;
			allSemesters = model.getSemester();
			int returnVal = fileChooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				loadedFile = fileChooser.getSelectedFile();
				//try{
					ObjectInputStream is = new ObjectInputStream(new FileInputStream(loadedFile));
					model = (Model) is.readObject();
					//try {
						//model = new Model(allSemesters);
						frame = new JFrame();
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.getContentPane().add(model);
						frame.add(model, BorderLayout.CENTER);
						frame.pack();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					}//Model mod = new Model(allSemesters);
					int semesterIndex = 0;
					int semestersAdded = allSemesters.size();
					//return model;
					//fileChecker = true;
					JOptionPane.showMessageDialog(null, "Your program was loaded successfully");
				}catch(Exception error){
					JOptionPane.showMessageDialog(null, "This program could not be loaded");
				}
				}
			
			return model;
			
			 
			ModelOne one = new ModelOne();
			
			//ArrayList<Semester> allSemesters = model.getSemester();
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			model = (Model) ois.readObject();
			//Model model = new Model();
			//model = new Model(allSemesters);
			ois.close();
			return model;
		
	}*/
}
