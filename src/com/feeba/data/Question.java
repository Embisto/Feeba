package com.feeba.data;

import java.util.ArrayList;

public class Question {
	
	private final int MAX_CHOICES = 8;
	
	private QuestionType type;
	private String questionText;
	private String name;
	private ArrayList<String> choices;
	
	/**
	 * 
	 * @param name Name of the question, for example "Question 1"
	 * @param questionText The actual question
	 * @param type QuestionType.MULTIPLE_CHOICE,QuestionType.MULTI_SELECTION or QuestionType.FREETEXT
	 */
	public Question(String name, String questionText,QuestionType type){
		this.setType(type);
		this.name = name;
		this.questionText = questionText;
		this.choices = new ArrayList<String>();
	}
	public QuestionType getType() {
		return type;
	}
	public void setType(QuestionType type) {
		this.type = type;
	}
	public ArrayList<String> getChoices() {
		return choices;
	}
	
	public String getChoicesString() {
		
		String choicesString = "[";
		for(int i = 0; i < choices.size();i++) {
			choicesString += "\""+choices.get(i).toString()+"\"";
			if(i != choices.size()-1)
				choicesString += ",";
		}
		
		choicesString += "]";
		return choicesString;
		
	}
	
	// check if number of choices is smaller than MAX_CHOICES
	public boolean choiceSpaceIsAvailable() {
		
		if(choices.size() > MAX_CHOICES)
			return false;
					return true;
		
	}
	
	public void setChoices(ArrayList<String> choices) {
		
		if(choiceSpaceIsAvailable()) {
			
			choices = (ArrayList<String>) choices.subList(0, 9);
		}
		
		this.choices = choices;
	}
	
	public void addChoice(String text, String... texts) {
		
		if(choiceSpaceIsAvailable())
			choices.add(text);
		for(String t : texts)
			if(choiceSpaceIsAvailable())
				choices.add(t);
	}
	
	public void removeLastChoice() {
		
		choices.remove(choices.size()-1);
	}
	
	public void removeChoiceAt(int index) {
		
		choices.remove(index);
	
	}
	
	public void getIndexOf(String choice) {
		
		choices.indexOf(choice);
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getQuestionText() {
		
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		
		this.questionText = questionText;
	}
	
	@Override
	public String toString() {
		
		String choicesString = "";
		
		for(int i = 0; i < choices.size();i++) {
			choicesString += (char)(65+i) + ": " + choices.get(i)+ " ";
			if(i%2!=0)
				choicesString +="\n";
		}
		
		return name + "\n\n" + questionText + "\n" + choicesString + "\n\n";
		
	}

}
