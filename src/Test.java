import java.util.ArrayList;

public class Test {
	
	private String testTitle;
	private ArrayList<Question> questions;
	
	public Test(String testTitle, ArrayList<Question> questions){
		this.testTitle = testTitle;
		this.questions = questions;
	}
	
	public String getTestTitle(){
		return testTitle;
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
}
