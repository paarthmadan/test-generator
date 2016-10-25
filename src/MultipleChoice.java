import java.util.ArrayList;

public class MultipleChoice extends Question {

	private ArrayList<String> choices;
	private int correctIndex;
	
	public MultipleChoice(String question, ArrayList<String> choices, int correctIndex) {
		super(question);
		this.choices = choices;
		this.correctIndex = correctIndex;
	}
	
	public ArrayList<String> getChoices() {
		return choices;
	}
	
	public int getCorrectIndex() {
		return correctIndex;
	}


}
