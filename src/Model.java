import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Model class that creates the second view of plan.
 * @author Drew
 *
 */
public class Model extends JPanel implements Serializable{

	/**
	 * Creates instances of all data needed.
	 */
	//private Scanner scan, lineScan;
	private int count, count2, count3, count4, count5, count6;
	private String line, prefix, number, title, tempPre, tempPre2, string2;
	private JButton semesterButton, addClasses, print, printAll, saveProg;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel model;
	private ArrayList<AllClasses> allClasses;
	private ArrayList<Semester> allSemesters;
	private JComboBox comboBox, semesterBox;
	private String[] combOptions;
	private String[] string = {"Select Semester"};
	private File file;
	//private ButtonListener listener;
	private JFrame dFrame;
	private boolean isSelected;
	private ModelOne modelOne = new ModelOne();
	
	/**
	 * Constructor to setPanes, addModel, setData, and readFileToTable.
	 * @throws IOException
	 */
	public Model() throws IOException{
		
		setPanes();
		addModel();
		setData();
		readFileToTable();
	}
	/**
	 * Method to read the txt file into a table.
	 * Initiates and sets table vars.
	 * Adds combo boxes after table is created.
	 */
	public void readFileToTable() {
		
		initTableVars();
		setTableVars();
		Scanner scan = null;// = new Scanner();
		Scanner lineScan;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			model.addRow(new Object[0]);
			count++;	
		}
		count = 0;
		line = "";
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			lineScan = new Scanner(line);
			lineScan.useDelimiter("\t");
			if(lineScan.hasNext()) {
				prefix = lineScan.next();
				model.setValueAt(prefix, count, 1);
			}
			if(lineScan.hasNext()) {
				number = lineScan.next();
				model.setValueAt(number, count, 2);
			}
			if(lineScan.hasNext()) {
				title = lineScan.next();
				model.setValueAt(title, count, 3);
			}
			allClasses.add(new AllClasses(prefix, number, title));
			model.setValueAt(false, count, 0);
			count++;
		}
		addComboBox();
		addSemesterBox();
	}
	/**
	 * Method to initTableVars. Initiates all variables.
	 */
	public void initTableVars() {
		
		count = 0;
		line = "";
		prefix = "";
		number = "";
		title = "";
		allClasses = new ArrayList<AllClasses>();
		allSemesters = new ArrayList<Semester>();
		file = new File("src/Coursefile.txt");
		semesterButton = new JButton("Add Semester");
		addClasses = new JButton("Add To Semester");
		print = new JButton("Print Semester");
		printAll = new JButton("Print All Semesters");
		saveProg = new JButton("Save");
		//ButtonListener listener = new ButtonListener();
		dFrame = new JFrame();
	}
	/**
	 * Method to setTableVars. Sets the variables and adds them.
	 */
	public void setTableVars() {
		
		ButtonListener listener = new ButtonListener();
		table.setEnabled(true);
		semesterButton.setBounds(160, 560, 120, 30);
		add(semesterButton);
		addClasses.setBounds(315, 560, 150, 30);
		add(addClasses);
		print.setBounds(510, 560, 120, 30);
		add(print);
		printAll.setBounds(670, 560, 150, 30);
		add(printAll);
		saveProg.setBounds(20, 560, 120, 30);
		add(saveProg);
		semesterButton.addActionListener(listener);
		addClasses.addActionListener(listener);
		print.addActionListener(listener);
		printAll.addActionListener(listener);
		saveProg.addActionListener(listener);
		string2 = "Select Semester";
		count3 = -1;
	}
	/**
	 * Method to setPanes of the table and scrollPane.
	 */
	public void setPanes() {
		
		this.setLayout(null);
		scroll = new JScrollPane();
	    table = new JTable();
	    scroll.setBounds(140,0,700,550);
	    add(scroll); 
	    scroll.setViewportView(table);
	    setPreferredSize(new Dimension(850,600));
		setBackground(Color.gray);
	}
	/**
	 * Method to addModel. creates new DefaultTableModel.
	 * @author www.camposha.info/source/java-jtable-render-checkbox-column//
	 */
	public void addModel() {
		model = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
		    {
				switch(column)
		        {
				case 0:
		          return Boolean.class;
		        case 1:
		          return String.class;
		        case 2:
		          return String.class;
		        case 3:
		          return String.class;
		        case 4:
		          return String.class;
		        default:
		          return String.class;
		        }
		    }
		};
	}
	/**
	 * Method to setData.
	 * Sets data for the model. Adds and sets columns.
	 */
	public void setData() {
		
		table.setModel(model);
		model.addColumn("Select");
		model.addColumn("Class");
		model.addColumn("Section");
		model.addColumn("Title");
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
	}
	/**
	 * Method to makePrefixTable
	 * Resets the table if a prefix is selected
	 */
	public void makePrefixTable() {
		prefix = comboBox.getSelectedItem().toString();
		addModel();
		setData();
		count = 0;
		model.setRowCount(0);
		for(int iii = 0; iii < allClasses.size(); iii++) {
			if(prefix.equals(allClasses.get(iii).getPrefix())) {
				count++;
			}
		}
		model.setRowCount(count);
		count = 0;
		for(int iiii = 0; iiii < allClasses.size(); iiii++) {
			if(prefix.equals(allClasses.get(iiii).getPrefix())) {
				number = allClasses.get(iiii).getNumber();
				title = allClasses.get(iiii).getTitle();
				model.setValueAt(false, count, 0);
				model.setValueAt(prefix, count, 1);
				model.setValueAt(number, count, 2);
				model.setValueAt(title, count, 3);
				count++;
			}
		}
	}
	/**
	 * Method to addComboBox.
	 * Adds the Prefix comboBox to table.
	 */
	public void addComboBox() {
		count = 1;
		tempPre = allClasses.get(0).getPrefix();
		for(int i = 0; i < allClasses.size(); i++) {
			tempPre2 = allClasses.get(i).getPrefix();
			if(!(tempPre2.equals(tempPre))) {
				tempPre = tempPre2;
				count++;
				}
		}
		combOptions = new String[count+1];
		count = 2;
		tempPre = allClasses.get(0).getPrefix();
		combOptions[0] = "Select Prefix";
		combOptions[1] = tempPre;
		for(int ii = 0; ii < allClasses.size(); ii++) {
			tempPre2 = allClasses.get(ii).getPrefix();
			if(!(tempPre2.equals(tempPre))) {
				tempPre = tempPre2;
				combOptions[count] = tempPre;
				count++;
			}
		}
		comboBox = new JComboBox(combOptions);
		comboBox.setBounds(5, 20, 130, 40);
		add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					makePrefixTable();
			    }
			}
		});
	}
	/**
	 * Method to addSemesterBox.
	 * Adds the semester box to the table.
	 */
	private void addSemesterBox() {
		semesterBox = new JComboBox(string);
		semesterBox.setBounds(5, 150, 130, 40);
		add(semesterBox);
		semesterBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					count3 = semesterBox.getSelectedIndex() - 1;
				}
			}
		});
		count2 = 1;
		count4 = 0;
		
	}
	/**
	 * ButtonListener class to see which button is pressed.
	 * @author Drew
	 *
	 */
	private class ButtonListener implements ActionListener, Serializable {
		/**
		 * Method to determine if button is pressed.
		 */
		public void actionPerformed (ActionEvent event) {
			/**
			 * Determines what happens if add semester Button is pressed.
			 */
			if(event.getSource() == semesterButton) {
				string2 = "Semester " + count2;
				semesterBox.addItem(string2);
				allSemesters.add(new Semester());
				count2++;
				count4 = 0;
			}
			/**
			 * Determines what happens if addClass button is pressed.
			 * Shows error message if no semester is selected or course is already added.
			 */
			else if(event.getSource() == addClasses) {
				for(int row = 0; row < table.getRowCount(); row++) {
					isSelected = Boolean.valueOf(table.getValueAt(row, 0).toString());
					if(isSelected) {
						prefix = table.getValueAt(row, 1).toString();
						number = table.getValueAt(row, 2).toString();
						title = table.getValueAt(row, 3).toString();
						Courses tempCourse = new Courses(prefix, number, title);
						if(string2.equals("Select Semester") || count3 == -1) {
							JOptionPane.showMessageDialog(dFrame, "Add or Select a Semester");
						}
						else if(allSemesters.get(count3).checkCourses(prefix, number, title)) {
							JOptionPane.showMessageDialog(dFrame, "Class " + prefix + " " + number + " already added to " + string2);
						}
						
						else {
							allSemesters.get(count3).add(tempCourse);
						}
					}		
				}
			}
			/**
			 * Determines what happens if print button is pressed.
			 */
			else if(event.getSource() == print) {
				count4 = 0;
				setModelPrint();
				model.setRowCount(allSemesters.get(semesterBox.getSelectedIndex()-1).getCount());
				for(int p = 0; p < allSemesters.get(semesterBox.getSelectedIndex()-1).getCourse().size();p++) {	
					model.setValueAt(allSemesters.get(semesterBox.getSelectedIndex()-1).getCourse().get(p).getPrefix(), count4, 0);
					model.setValueAt(allSemesters.get(semesterBox.getSelectedIndex()-1).getCourse().get(p).getNumber(), count4, 1);
					model.setValueAt(allSemesters.get(semesterBox.getSelectedIndex()-1).getCourse().get(p).getTitle(), count4, 2);
					count4++;
				}
				
			}
			/**
			 * Determines what happens if the print all button is pressed.
			 */
			else if(event.getSource() == printAll) {
				setModelPrint();
				count4 = 0;
				count5 = 0;
				count6 = 1;
				for(int q = 0; q < allSemesters.size(); q++) {
					count4 += allSemesters.get(q).getCount();
				}
				count4 += allSemesters.size();
				model.setRowCount(count4);
				for(int r = 0; r < allSemesters.size(); r++) {
					count4 = 0;
					model.setValueAt("Semester " + count6, count5, 0);
					count5++;
					count6++;
					for(int s = 0; s < allSemesters.get(r).getCourse().size(); s++) {
						model.setValueAt(allSemesters.get(r).getCourse().get(s).getPrefix(), count5, 0);
						model.setValueAt(allSemesters.get(r).getCourse().get(s).getNumber(), count5, 1);
						model.setValueAt(allSemesters.get(r).getCourse().get(s).getTitle(), count5, 2);
						count4++;
						count5++;
					}
				}
			}
			/**
			 * Determines what happens if the save Progress button is pressed.
			 */
			else if(event.getSource() == saveProg) {
				String saveFileName;
				saveFileName = JOptionPane.showInputDialog("Name of file");
				
				try {
					save(saveFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Method to setModelPrint
	 * Changes the table to print the semesters
	 * @author www.camposha.info/source/java-jtable-render-checkbox-column//
	 */
	public void setModelPrint() {
		
		model = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
		    {
				switch(column)
		        {
				case 0:
		          return String.class;
		        case 1:
		          return String.class;
		        case 2:
		          return String.class;
		        case 3:
		          return String.class;
		        default:
		          return String.class;
		        }
		    }
		};
		table.setModel(model);
		model.addColumn("Class");
		model.addColumn("Section");
		model.addColumn("Title");
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
	}
	/**
	 * Method to save the file.
	 * @param fileName
	 * @throws IOException
	 */
	public void save(String fileName) throws IOException{
		/*JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		File f = chooser.getSelectedFile();
		if(f != null){
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));*/
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		//ModelOne modelOne = new ModelOne();
		oos.writeObject(model);
		oos.flush();
		oos.close();
	}
	public ArrayList<Semester> getSemester(){
		return allSemesters;
	}
}
