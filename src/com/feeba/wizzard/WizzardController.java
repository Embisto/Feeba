package com.feeba.wizzard;

import java.util.ArrayList;

import com.feeba.core.FeebaCore;
import com.feeba.data.Question;
import com.feeba.data.QuestionType;
import com.feeba.data.Survey;

public class WizzardController {

	public static String SurveyName;
	public static String SurveyInfoText;
	
	public static String TheQuestion;
	public static String QuestionName; 
	public static ArrayList<String> answers;
	public static ArrayList<Question> allQuestions;
	public static QuestionType type;
	
	public static Survey survey = new Survey(SurveyName);
	public static Question question;
	

	
	public static ArrayList<String> answersToList(String answersString){
		String current = "";
		for(int i = 0; i < answersString.length()-1 ; i++){
			if(answersString.charAt(i)  != 13){
				current = current + answersString.charAt(i);
			} else {
				answers.add(current);
				current= "";
			}
			if( i == answersString.length()){
				answers.add(current);
			}		
		}		
		return answers;
	}
	

	public static void newQuestion(String name, String exactQuestion, QuestionType type, ArrayList<String> answers){
		question= new Question(name, exactQuestion, type);
		question.setChoices(answers); 
		survey.addQuestion(question);
	}
	
	
	
	
}
