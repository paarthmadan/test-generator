import java.awt.BorderLayout;
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
		
		mainFrame.add(BorderLayout.WEST, openPane);
		mainFrame.add(BorderLayout.EAST, newTestPane);
		
		mainFrame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new TestGenerator();
	}
}
