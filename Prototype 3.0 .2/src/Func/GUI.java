package Func;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class GUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public  static String URLstring;
	public static int timeNum;
	public static String xpathString;
	public static String value;
	private JTextField input1Var;
	private JTextField input2Var;
	private JButton clickVar;
	private JButton waitVar;
	private JButton inputVar;
	private JLabel stepnumVar;
	private JButton minusVar;
	private JButton runVar;
	private JTextArea stepsdisplayVar;
		
	
	ArrayList<String> steps = new ArrayList<String>();
	ArrayList<String> input1arr = new ArrayList<String>();
	ArrayList<String> input2arr = new ArrayList<String>();
	
	public String input1Value;
	public String input2Value;
	int count=0;
	String countstr;
	int input2counter=0;
	String stepsdisplay="";
	private JButton btnstopVar;
	private JButton btnsaveVar;
	private JTextField savednameVar;
	
	Map<String,RunObj> myMap = new TreeMap<String,RunObj>();
	private JTextField fileinputVar;
	private JButton btnimportVar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("GUI Prototype 3.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		OpenURL OpenURLObject = new OpenURL();
		
		
		
		JButton openurlVar = new JButton("Open URL");
		
		
		openurlVar.setBounds(337, 61, 85, 21);
		contentPane.add(openurlVar);
		
		input1Var = new JTextField();
		input1Var.setText("input 1");
		input1Var.setBounds(24, 281, 88, 19);
		contentPane.add(input1Var);
		input1Var.setColumns(10);
		
		input2Var = new JTextField();
		input2Var.setText("input 2");
		input2Var.setBounds(122, 281, 88, 19);
		contentPane.add(input2Var);
		input2Var.setColumns(10);
		
		
		clickVar = new JButton("Click");
		
		
		clickVar.setBounds(337, 92, 85, 21);
		contentPane.add(clickVar);
		
		waitVar = new JButton("Wait");
		
		
		waitVar.setBounds(337, 123, 85, 21);
		contentPane.add(waitVar);
		
		inputVar = new JButton("Input");
	
		
		inputVar.setBounds(337, 154, 85, 21);
		contentPane.add(inputVar);
	
		stepnumVar = new JLabel("   0");
		stepnumVar.setFont(new Font("Tahoma", Font.BOLD, 15));
		stepnumVar.setBounds(75, 65, 33, 32);
		contentPane.add(stepnumVar);
		
		minusVar = new JButton("-");
		
		minusVar.setFont(new Font("Arial", Font.PLAIN, 20));
		minusVar.setBounds(39, 73, 40, 21);
		contentPane.add(minusVar);
		
		runVar = new JButton("RUN");
		
		runVar.setBounds(302, 280, 76, 21);
		contentPane.add(runVar);
		
		stepsdisplayVar = new JTextArea();
		stepsdisplayVar.setText("STEPS ");
		stepsdisplayVar.setBounds(20, 123, 268, 126);
		contentPane.add(stepsdisplayVar);
		
		JProgressBar progressbarVar = new JProgressBar();
		progressbarVar.setStringPainted(true);
		progressbarVar.setBounds(142, 73, 146, 11);
		progressbarVar.setValue(0);
		contentPane.add(progressbarVar);
		
		btnstopVar = new JButton("STOP");
		
		btnstopVar.setBounds(398, 280, 76, 21);
		contentPane.add(btnstopVar);
		
		btnsaveVar = new JButton("Save");
		
		btnsaveVar.setBounds(24, 321, 65, 21);
		contentPane.add(btnsaveVar);
		
		savednameVar = new JTextField();
		savednameVar.setText("Enter save name");
		savednameVar.setBounds(99, 322, 85, 19);
		contentPane.add(savednameVar);
		savednameVar.setColumns(10);
		
		fileinputVar = new JTextField();
		fileinputVar.setText("Enter file name to run");
		fileinputVar.setBounds(302, 322, 172, 19);
		contentPane.add(fileinputVar);
		fileinputVar.setColumns(10);
		
		btnimportVar = new JButton("Import");
		btnimportVar.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		btnimportVar.setBounds(337, 350, 85, 21);
		contentPane.add(btnimportVar);
		
		
		stepnumVar.setText("0");
		//
		
		minusVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int count = Integer.parseInt(stepnumVar.getText()) - 1;
				if(count >= 0) {
					String countstr = String.valueOf(count);
					stepnumVar.setText(countstr);
					
					steps.remove(steps.size()-1);
					
					stepsdisplay = stepsdisplayVar.getText();
					int index = stepsdisplay.lastIndexOf("-");
					stepsdisplay = stepsdisplay.substring(0, index);
					stepsdisplayVar.setText(stepsdisplay);
				}
				
			}
		});

		
		runVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String step;
				String input1;
				progressbarVar.setValue(0);
				for(int i=0; i<=steps.size(); i++) {
					
					step = steps.get(i);
					input1 = input1arr.get(i);
					
					switch(step) {
					case "openURL":
						OpenURLObject.openURL(input1);
						break;
					case "click":
						OpenURLObject.click(input1);
						break;
					case "wait":
						int time = Integer.parseInt(input1);
						OpenURLObject.wait(time);
						break;
					case "input":
						String input2 = input2arr.get(0+input2counter);
						input2counter += 1;
						OpenURLObject.input(input2, input1);
						break;


					}
					//
					int progress = progressbarVar.getValue() + 100/(steps.size());
					progressbarVar.setValue(progress);
					
					if(i == steps.size()) { progressbarVar.setValue(100); }
					//
				}
			}
		});
		
		//
		
		btnimportVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fileName = fileinputVar.getText() + ".txt";
				String path = System.getProperty("user.dir");
				path = path + "\\saved tests\\" + fileName;
				
				try {

					FileInputStream fi = new FileInputStream(new File(path));
					ObjectInputStream oi = new ObjectInputStream(fi);

					// Read objects
					RunObj currentRunObject = (RunObj) oi.readObject();
					
					int size = (currentRunObject.steps).size();
					System.out.println(size);
					for(int i=0; i<size; i++) {
						String step = (currentRunObject.steps).get(i);
						System.out.println(step);
						steps.add(step);
						
						String input1 = (currentRunObject.input1arr).get(i);
						System.out.println(input1);
						input1arr.add(input1);
					}
					
					int size2 = (currentRunObject.input2arr).size();
					System.out.println(size2);
					for(int j=0; j<size2; j++) {
						String input2 = (currentRunObject.input2arr).get(j);
						System.out.println(input2);
						input2arr.add(input2);
					}
					
					
					  //steps = currentRunObject.steps; 
					  //input1arr = currentRunObject.input1arr;
					  input2arr = currentRunObject.input2arr;
					 

					oi.close();
					fi.close();

				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				} catch (IOException e1) {
					 e1.printStackTrace();
					System.out.println("Error initializing stream");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
					
				stepsdisplay = "STEPS - ";
				int count = 0;
				for(int i=0; i<steps.size(); i++) {
					stepsdisplay = stepsdisplay + steps.get(i) + " - ";
					count += 1;
					
				}
				String countstr = String.valueOf(count);
				stepnumVar.setText(countstr);
				int ind = stepsdisplay.lastIndexOf("-");
				String stepsdisplayfinal = stepsdisplay.substring(0, ind);
				stepsdisplayVar.setText(stepsdisplayfinal);
				
				
			}
		});
				
		
		btnstopVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenURLObject.stop();
			}
		});
		
		openurlVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				steps.add("openURL");
				input1Value = input1Var.getText();
				input1arr.add(input1Value);
				
				count = Integer.parseInt(stepnumVar.getText()) + 1;
				countstr =Integer.toString(count);
				stepnumVar.setText(countstr);
				
				stepsdisplay = stepsdisplayVar.getText() + " - OpenURL";
				stepsdisplayVar.setText(stepsdisplay);
				
				input1Var.setText("");
			}
		});
		
		
		clickVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				steps.add("click");
				input1Value = input1Var.getText();
				input1arr.add(input1Value);
				
				count = Integer.parseInt(stepnumVar.getText()) + 1;
				countstr =Integer.toString(count);
				stepnumVar.setText(countstr);
				
				stepsdisplay = stepsdisplayVar.getText() + " - click";
				stepsdisplayVar.setText(stepsdisplay);
				
				input1Var.setText("");
			}
		});
		
		
		inputVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				steps.add("input");
				input1Value = input1Var.getText();
				input1arr.add(input1Value);
				input2Value = input2Var.getText();
				input2arr.add(input2Value);
				
				int count = Integer.parseInt(stepnumVar.getText()) + 1;
				countstr =Integer.toString(count);
				stepnumVar.setText(countstr);
				
				stepsdisplay = stepsdisplayVar.getText() + " - input";
				stepsdisplayVar.setText(stepsdisplay);
				
				input1Var.setText("");
				input2Var.setText("");
			}
		});
		
		
		waitVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				steps.add("wait");
				input1Value = input1Var.getText();
				input1arr.add(input1Value);
				
				int count = Integer.parseInt(stepnumVar.getText()) + 1;
				countstr =Integer.toString(count);
				stepnumVar.setText(countstr);
				
				stepsdisplay = stepsdisplayVar.getText() + " - wait";
				stepsdisplayVar.setText(stepsdisplay);
				
				input1Var.setText("");
			}
		});
		
		
		btnsaveVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String savedName = savednameVar.getText() + ".txt";
				String path = System.getProperty("user.dir");
				path = path + "\\saved tests\\" + savedName;
				
				myMap.put(savedName, new RunObj());
				RunObj currentRunObject = myMap.get(savedName);
				currentRunObject.steps = steps;
				currentRunObject.filename = savedName;
				currentRunObject.input1arr = input1arr;
				currentRunObject.input2arr = input2arr;
				
				 
				File file = new File(path);
				try {
					if (file.createNewFile()) {
					  System.out.println("File created: " + file.getName());
					 } 
					else {
					  
						System.out.println("File already exists.");
					  }
				} catch (IOException e2) {
					e2.printStackTrace();
				} 
				  
				 
				try {
					//File file = new File(savedName);
					FileOutputStream f = new FileOutputStream(file);
					ObjectOutputStream o = new ObjectOutputStream(f);

					// Write objects to file
					o.writeObject(currentRunObject);

					o.close();
					f.close();
					
					
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("Error initializing stream");
				} 

				
			}
		});
		
		
		//
	}
}
