
public class TrueFalse extends Question{

	private String trueValue;
	private String falseValue;
	
	public TrueFalse(String question, String trueValue, String falseValue){
		super(question);
		
		this.trueValue = trueValue;
		this.falseValue = falseValue;
		
	}
	
	public String getTrueValue(){
		return this.trueValue;
	}

	public String getFalseValue(){
		return this.falseValue;
	}
	
	public void setTrueValue(String trueValue){
		this.trueValue = trueValue;
	}
	
	public void setFalseValue(String falseValue){
		this.falseValue = falseValue;
	}
	
}
