package com.feeba.data;

import java.util.ArrayList;

public class JsonResponseWrapper {
	
	public class QuestionWrapper {
		
		public String content;
		public ArrayList<String> choices;
		public String name;
		public String type;
		
		public QuestionType getQuestionType() {
			
			if(type.equals("checkbox"))
				return QuestionType.MULTIPLE_CHOICE;
			if(type.equals("radio"))
				return QuestionType.SINGLE_SELECTION;
			
				return QuestionType.FREETEXT;
			
		}
		
	}
	
	private String surveyName;
	private ArrayList<QuestionWrapper> questions;
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public ArrayList<QuestionWrapper> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionWrapper> questions) {
		this.questions = questions;
	}
}
