package com.feeba.wizzard;

import java.util.ArrayList;
import java.util.Arrays;

import com.feeba.data.Question;
import com.feeba.data.QuestionType;
import com.feeba.data.Survey;

public class WizzardController {

	public static String SurveyName;
	public static String SurveyInfoText;
	
	public static String TheQuestion;
	public static String QuestionName; 
	public static ArrayList<String> answers = new ArrayList<String>(); // muss initialisiert werden sonst nullpointer
	public static ArrayList<Question> allQuestions;
	public static QuestionType type;
	
	public static Survey survey = new Survey(SurveyName);
	public static Question question;
	

	
	public static ArrayList<String> answersToList(String answersString){
		
//		String current = "";
//		for(int i = 0; i < answersString.length()-1 ; i++){
//			if((int)answersString.charAt(i)  != 44){
//				current = current + answersString.charAt(i);
//			} else {
//				answers.add(current);
//				current= "";
//			}
//			if( i == answersString.length()-1){
//				answers.add(current);
//			}		
//		}
		
// Deins funktionierte so ned :D es braucht erstmal nen cast zu int und der asci wert von comma is auch 44 ned 13.
// Dann spackte immer noch das Einlesen des letzten Wortes. Eine Zeile tuts auch.
		
		answers = new ArrayList<String>(Arrays.asList(answersString.split("\\s*,\\s*")));
		return answers;
	}
	

	public static void newQuestion(String name, String exactQuestion, QuestionType type, ArrayList<String> answers){
		question= new Question(name, exactQuestion, type);
		System.out.println(answers.get(0));
		question.setChoices(answers); 
		survey.addQuestion(question);
	}
	
	
	
	
}
