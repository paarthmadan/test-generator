
public class TrueFalse extends Question{

	private boolean answer;
	
	public TrueFalse(String question, boolean answer){
      super(question);
      this.answer = answer;
	}
   
   public boolean getCorrectAnswer(){
      return this.answer;
   }	
}
