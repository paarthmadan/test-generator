import java.util.ArrayList;

public class Test {
	
	private String testTitle;
	private ArrayList<Question> saQuestions;
   private ArrayList<TrueFalse> tfQuestions;
   private ArrayList<MultipleChoice> mcQuestions;
	
   
   public Test(String testTitle, ArrayList<TrueFalse> tfQuestions, ArrayList<MultipleChoice> mcQuestions, ArrayList<Question> saQuestions){
      this.testTitle = testTitle;
      this.tfQuestions = tfQuestions;
      this.mcQuestions = mcQuestions;
      this.saQuestions = saQuestions;
      
   }
   
	public String getTestTitle(){
		return testTitle;
	}
	
	public ArrayList<Question> getShortAnswerQuestions(){
		return saQuestions;
	}
   
   public ArrayList<MultipleChoice> getMultipleChoiceQuestions(){
		return mcQuestions;
	}
   
   public ArrayList<TrueFalse> getTrueFalseQuestions(){
		return tfQuestions;
	}
	
}
