import java.util.*;
import java.io.*;

public class Publish{

   private Test test;
   private String testTitle;
	private ArrayList<Question> saQuestions;
   private ArrayList<TrueFalse> tfQuestions;
   private ArrayList<MultipleChoice> mcQuestions;
   
   public Publish(Test t){
      test = t;
      testTitle = t.getTestTitle();
      saQuestions = t.getShortAnswerQuestions();
      mcQuestions = t.getMultipleChoiceQuestions();
      tfQuestions = t.getTrueFalseQuestions();
   }
   
   public void print(){
      
      int questionCounter = 0;
      
      try {
        BufferedWriter out = new BufferedWriter(new FileWriter(testTitle + ".txt"));
        BufferedWriter answerKey = new BufferedWriter(new FileWriter(testTitle + " AnswerKey.txt"));
            printTitleSequence(out);
   
            for(int i = 0; i < saQuestions.size(); i++){
               questionCounter++;
               out.write(questionCounter + ". ");
               answerKey.write(questionCounter + ". ");
               out.write((String)(saQuestions.get(i).getQuestion()));
               answerKey.newLine();
               answerKey.write("Answers may vary.");
               for(int j = 0; j < 3; j++)
                  out.newLine();
                  answerKey.newLine();
            }
            
            for(int i = 0; i < tfQuestions.size(); i++){
               questionCounter++;
               out.write(questionCounter + ". ");
               answerKey.write(questionCounter + ". ");
               out.write((String)(tfQuestions.get(i).getQuestion()));
               out.newLine();
               answerKey.newLine();
               if(tfQuestions.get(i).getCorrectAnswer())
                  answerKey.write("True");
               else
                  answerKey.write("False");
               out.write("\tTrue");
               out.newLine();
               out.write("\tFalse");
               out.newLine();
               answerKey.newLine();
            }
            
            for(int i = 0; i < mcQuestions.size(); i++){
               questionCounter++;
               out.write(questionCounter + ". ");
               answerKey.write(questionCounter + ". ");
               out.write((String)(mcQuestions.get(i).getQuestion()));
               out.newLine();
               answerKey.newLine();
               for(int j = 0; j < mcQuestions.get(i).getChoices().size(); j++){
                  out.write("\t" + getCharForNumber(j) + ") " + mcQuestions.get(i).getChoices().get(j));
                  out.newLine();
               }
               int correctIndex = mcQuestions.get(i).getCorrectIndex();
               
               answerKey.write(mcQuestions.get(i).getChoices().get(correctIndex));
               answerKey.newLine();
            }
            
            out.close();
            answerKey.close();
        } catch(Exception e){
        
        }
   }
   
   private String getCharForNumber(int i) {
    return i >= 0 && i < 27 ? String.valueOf((char)((i + 65))): null;
   }

   public void printTitleSequence(BufferedWriter out) throws IOException{
         out.write(testTitle);
         out.newLine();
         out.newLine();
         out.write("Date: _________________   Class Code: _________________");
         out.newLine();
         out.newLine();
   }
}