import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

public class TestGenerator {

	//CONSTANTS
	final String APP_NAME = "Test Generator";
	final int SCREEN_WIDTH = 500;
	final int SCREEN_HEIGHT = 325;
	
	//UI Components
	JFrame mainFrame;
	OptionPanel openPane;
	OptionPanel newTestPane;
	
	public TestGenerator(){
		mainFrame = new JFrame();
		
		mainFrame.setTitle(APP_NAME);
		mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		
		openPane = new OptionPanel("Open Existing Test", new File("OpenIcon.png"));
		newTestPane = new OptionPanel("Create New Test", new File("NewTestIcon.png"));
		
		//ON CLICK LISTENERS
		openPane.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Handle Open Test
				System.out.println("Open Test");
			}
		});
		
		newTestPane.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Handle New Test
				System.out.println("New Test");
				clearScreen();
				createQuestionPanel();
			}
		});
		
		mainFrame.add(BorderLayout.WEST, openPane);
		mainFrame.add(BorderLayout.EAST, newTestPane);
		
		mainFrame.setVisible(true);
		
		//prevents button from being selected
		mainFrame.requestFocus();
	}
	
	
	public static void main(String[] args) {
		new TestGenerator();
	}
	
	public void clearScreen(){
		Container pane = mainFrame.getContentPane();
		pane.removeAll();
		pane.validate();
		pane.repaint();
	}
	
	public void createQuestionPanel(){
		
		JPanel questionPanel = new JPanel();
		ArrayList<Question> questions = new ArrayList<Question>();
		
		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
		
		OptionPanel questionButtonPanel = new OptionPanel("Add Question", new File("OpenIcon.png"));
		
		questionButtonPanel.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				generateQuestion();
			}
		});
		
		JLabel enterTitleLabel = new JLabel("Enter the Test Title:");
		
		JTextField textField = new JTextField(20);
		textField.setMaximumSize(new Dimension(700, 20));
		
		JLabel numberOfVariationsLabel = new JLabel("Input the number of alternate tests:");
		
		JSlider slider = new JSlider(1,5);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		JLabel questionsCounter = new JLabel();
		questionsCounter.setText("Questions: " + questions.size());
		
		JButton generateTestButton = new JButton("Generate Test");
		
		questionPanel.add(enterTitleLabel);
		questionPanel.add(textField);
		questionPanel.add(Box.createRigidArea(new Dimension(0,25)));
		questionPanel.add(numberOfVariationsLabel);
		questionPanel.add(slider);
		questionPanel.add(Box.createRigidArea(new Dimension(0,25)));
		questionPanel.add(questionsCounter);
		questionPanel.add(Box.createRigidArea(new Dimension(0,40)));
		questionPanel.add(generateTestButton);
		
		mainFrame.add(BorderLayout.WEST, questionButtonPanel);
		mainFrame.add(BorderLayout.EAST, questionPanel);
		
		mainFrame.setSize(SCREEN_WIDTH + 100, SCREEN_HEIGHT);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		addContent();
	}
	
	public void addContent(){
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().validate();
		mainFrame.getContentPane().repaint();
	}
	
	public void generateQuestion(){
		clearScreen();
		
		mainFrame.setTitle("Choose Question");
		
		OptionPanel mp = new OptionPanel("Multiple Choice", new File("OpenIcon.png"));
		OptionPanel tf = new OptionPanel("True or False", new File("OpenIcon.png"));
		OptionPanel sa = new OptionPanel("Short Answer", new File("OpenIcon.png"));
		
		mainFrame.add(BorderLayout.WEST, mp);
		mainFrame.add(BorderLayout.CENTER, tf);
		mainFrame.add(BorderLayout.EAST, sa);
		
		mainFrame.setSize(mainFrame.getWidth() + 100, SCREEN_HEIGHT);
		
		addContent();
		
	}
	
}
