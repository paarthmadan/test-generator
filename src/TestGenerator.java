import java.awt.BorderLayout;
import java.awt.Component;
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
	
	TrueFalse finalTf;
	
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
		
		OptionPanel questionButtonPanel = new OptionPanel("Add Question", new File("QuestionIcon.png"));
		
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
	
	public Question generateQuestion(){
		clearScreen();
		
		mainFrame.setTitle("Choose Question");
		
		Question [] questionArray = new Question[1];
		
		OptionPanel mp = new OptionPanel("Multiple Choice", new File("MultipleChoiceIcon.png"));
		
		mp.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		OptionPanel tf = new OptionPanel("True or False", new File("TrueFalseIcon.png"));
		
		tf.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				createTrueFalse();

			}
			
		});
		
		OptionPanel sa = new OptionPanel("Short Answer", new File("ShortAnswerIcon.png"));
		
		sa.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});

		mainFrame.add(BorderLayout.WEST, mp);
		mainFrame.add(BorderLayout.CENTER, tf);
		mainFrame.add(BorderLayout.EAST, sa);
		
		mainFrame.setSize(mainFrame.getWidth() + 100, SCREEN_HEIGHT);
		
		addContent();
		
		return questionArray[0];
		
	}
	
	public void createTrueFalse(){
		clearScreen();
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new BoxLayout(tfPanel, BoxLayout.Y_AXIS));
		
		JTextField questionField = new JTextField();
		questionField.setMaximumSize(new Dimension(450, 25)); 
		
		JTextField trueField = new JTextField();
		trueField.setMaximumSize(new Dimension(450, 25));
		
		JTextField falseField = new JTextField();
		falseField.setMaximumSize(new Dimension(450, 25));
		
		JLabel questionLabel = new JLabel("Question:");
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel trueLabel = new JLabel("True:");
		trueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel falseLabel = new JLabel("False:");
		falseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton addButton = new JButton("Add Question");
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		tfPanel.add(questionLabel);
		tfPanel.add(questionField);
		tfPanel.add(trueLabel);
		tfPanel.add(trueField);
		tfPanel.add(falseLabel);
		tfPanel.add(falseField);
		tfPanel.add(Box.createRigidArea(new Dimension(0,10)));
		tfPanel.add(addButton);
		
		
		mainFrame.add(tfPanel);
		mainFrame.setSize(mainFrame.getWidth() - 200 , SCREEN_HEIGHT - 125);
		
		addContent();
		
	}
	
	public static void main(String[] args) {
		new TestGenerator();
	}
	
	
}
