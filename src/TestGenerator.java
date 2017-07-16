import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
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
   JFrame newQuestionFrame;
	OptionPanel openPane;
	OptionPanel newTestPane;
   JLabel questionsCounter;
	
	TrueFalse finalTf;
   
   ArrayList<MultipleChoice> mcQuestions;
   ArrayList<TrueFalse> tfQuestions;
	ArrayList<Question> saQuestions;
   
   boolean stateTrueFalse;
   JLabel trueFalseLabel;
   
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

	public void clearScreen(JFrame f){
		Container pane = f.getContentPane();
		pane.removeAll();
		pane.validate();
		pane.repaint();
	}

	public void createQuestionPanel(){
		
		JPanel questionPanel = new JPanel();
		tfQuestions = new ArrayList<TrueFalse>();
		mcQuestions = new ArrayList<MultipleChoice>();
      saQuestions = new ArrayList<Question>();
      
		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
		
		OptionPanel questionButtonPanel = new OptionPanel("Add Question", new File("QuestionIcon.png"));
		
		questionButtonPanel.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				generateQuestion();
			}
		});
		
		JLabel enterTitleLabel = new JLabel("Enter the Test Title:");
		
		final JTextField textField = new JTextField(20);
		textField.setMaximumSize(new Dimension(700, 20));
		
		JLabel numberOfVariationsLabel = new JLabel("Input the number of alternate tests:");
		
		JSlider slider = new JSlider(1,5);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		questionsCounter = new JLabel();
		
      updateQuestionCounter();
		
		JButton generateTestButton = new JButton("Generate Test");
      
      generateTestButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Test t = new Test(textField.getText(), tfQuestions, mcQuestions, saQuestions);
            Publish p = new Publish(t);
            p.print();
            cleanup();
			}
		});
		
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
	
   public void addContent(JFrame f){
      f.setLocationRelativeTo(null);
		f.getContentPane().validate();
		f.getContentPane().repaint();
   }
   
	public void generateQuestion(){
   		
      newQuestionFrame = new JFrame();
      
		newQuestionFrame.setTitle("Choose Question");
		
		OptionPanel mp = new OptionPanel("Multiple Choice", new File("MultipleChoiceIcon.png"));
		
		mp.getButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createMultipleChoice();
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
				createShortAnswer();
			}
		});

		newQuestionFrame.add(BorderLayout.WEST, mp);
		newQuestionFrame.add(BorderLayout.CENTER, tf);
		newQuestionFrame.add(BorderLayout.EAST, sa);
		
		newQuestionFrame.setSize(mainFrame.getWidth() + 100, SCREEN_HEIGHT);
		
      newQuestionFrame.setVisible(true);
      
		addContent(newQuestionFrame);
		
		
	}
   
   public void cleanup(){
      newQuestionFrame.dispose();
      mainFrame.dispose();
   }
   
   public void updateQuestionCounter(){
      questionsCounter.setText("Questions: " + (tfQuestions.size() + mcQuestions.size() + saQuestions.size()));
      addContent();
   }
   
   public void addTrueFalse(String q, boolean answer){
      assert(tfQuestions != null);
      tfQuestions.add(new TrueFalse(q, answer));
      updateQuestionCounter();
   }
   
   public void addShortAnswer(String q){
      assert(saQuestions != null);
      saQuestions.add(new Question(q));
      updateQuestionCounter();
   }
   
   public void addMultipleChoice(String q, String one, String two, String three, String four, int correctIndex){
      assert(mcQuestions != null);
      ArrayList<String> mc = new ArrayList<String>();
      mc.add(one);
      mc.add(two);
      mc.add(three);
      mc.add(four);
      mcQuestions.add(new MultipleChoice(q, mc, correctIndex));
      updateQuestionCounter();
   }
	
	public void createTrueFalse(){
		clearScreen(newQuestionFrame);
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new BoxLayout(tfPanel, BoxLayout.Y_AXIS));
		
		final JTextField questionField = new JTextField();
		questionField.setMaximumSize(new Dimension(450, 25)); 
		
		JLabel questionLabel = new JLabel("Statement:");
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton addButton = new JButton("Add Question");
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
      JToggleButton trueFalse = new JToggleButton("True / False");
      trueFalse.setAlignmentX(Component.CENTER_ALIGNMENT);
      

      
		trueFalseLabel = new JLabel(": False");
		
      addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
            String question = questionField.getText();
            addTrueFalse(question, stateTrueFalse);
            System.out.println(question);
            newQuestionFrame.dispose();
         }
			
		});

      trueFalse.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent ev) {
            if(ev.getStateChange()==ItemEvent.SELECTED){
               stateTrueFalse = true;
               trueFalseLabel.setText(": True");
            } else if(ev.getStateChange()==ItemEvent.DESELECTED){
               stateTrueFalse = false;
               trueFalseLabel.setText(": False");
            }
         }
      });
      
		tfPanel.add(questionLabel);
		tfPanel.add(questionField);
      tfPanel.add(Box.createRigidArea(new Dimension(0,10)));
      JPanel trueFalseTogglePanel = new JPanel();
      trueFalseTogglePanel.add(trueFalse);
      trueFalseTogglePanel.add(trueFalseLabel);
      tfPanel.add(trueFalseTogglePanel);
//       tfPanel.add(trueFalse);
//       tfPanel.add(trueFalseLabel);
		tfPanel.add(addButton);
		
		newQuestionFrame.add(tfPanel);
		newQuestionFrame.setSize(mainFrame.getWidth() - 150 , SCREEN_HEIGHT - 175);
		
		addContent(newQuestionFrame);
		
	}
   
   public void createShortAnswer(){
		clearScreen(newQuestionFrame);
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new BoxLayout(tfPanel, BoxLayout.Y_AXIS));
		
		final JTextField questionField = new JTextField();
		questionField.setMaximumSize(new Dimension(450, 25)); 
		
		JLabel questionLabel = new JLabel("Question:");
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton addButton = new JButton("Add Question");
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
      addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
            String question = questionField.getText();
            addShortAnswer(question);
            newQuestionFrame.dispose();
         }
			
		});

      
		tfPanel.add(questionLabel);
		tfPanel.add(questionField);
		tfPanel.add(Box.createRigidArea(new Dimension(0,10)));
		tfPanel.add(addButton);
		
		
		newQuestionFrame.add(tfPanel);
		newQuestionFrame.setSize(mainFrame.getWidth() - 150 , SCREEN_HEIGHT - 200);
		
		addContent(newQuestionFrame);
		
	}

   
   public void createMultipleChoice(){
		clearScreen(newQuestionFrame);
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new BoxLayout(tfPanel, BoxLayout.Y_AXIS));
		
		final JTextField questionField = new JTextField();
		questionField.setMaximumSize(new Dimension(450, 25)); 
		
		final JTextField optionOne = new JTextField();
		optionOne.setMaximumSize(new Dimension(450, 25));
		
		final JTextField optionTwo = new JTextField();
		optionTwo.setMaximumSize(new Dimension(450, 25));
      
      final JTextField optionThree = new JTextField();
		optionThree.setMaximumSize(new Dimension(450, 25));
		
		final JTextField optionFour = new JTextField();
		optionFour.setMaximumSize(new Dimension(450, 25));
      
      JLabel questionLabel = new JLabel("Question:");
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel one = new JLabel("Option One / Correct Answer:");
		one.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel two = new JLabel("Option Two:");
		two.setAlignmentX(Component.CENTER_ALIGNMENT);
		
      JLabel three = new JLabel("Option Three:");
		three.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel four = new JLabel("Option Four:");
		four.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      JButton addButton = new JButton("Add Question");
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
      addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
            String question = questionField.getText();
            String aOne = optionOne.getText();
            String aTwo = optionTwo.getText();
            String aThree = optionThree.getText();
            String aFour = optionFour.getText();
            
            addMultipleChoice(question, aOne, aTwo, aThree, aFour, 0);
            newQuestionFrame.dispose();
			}
			
		});

		tfPanel.add(questionLabel);
		tfPanel.add(questionField);
		tfPanel.add(one);
		tfPanel.add(optionOne);
		tfPanel.add(two);
		tfPanel.add(optionTwo);
		tfPanel.add(three);
		tfPanel.add(optionThree);
		tfPanel.add(four);
		tfPanel.add(optionFour);
		tfPanel.add(Box.createRigidArea(new Dimension(0,10)));
		tfPanel.add(addButton);
		
		
		newQuestionFrame.add(tfPanel);
		newQuestionFrame.setSize(mainFrame.getWidth() - 200 , SCREEN_HEIGHT - 25);
		
		addContent(newQuestionFrame);
		
	}

	
	public static void main(String[] args) {
		new TestGenerator();
	}   
}
