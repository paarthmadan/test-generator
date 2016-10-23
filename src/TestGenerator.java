import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
}
