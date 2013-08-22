package com.feeba.data;

import java.util.ArrayList;
import java.util.Collections;

public class Survey {
	
	private ArrayList<Question> questions;
	private String name;
	
	/**
	 * 
	 * @param name Name of the survey, filename.
	 */
	public Survey(String name) {
		questions = new ArrayList<Question>();
		this.name = name;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	/**
	 * Adds questions to questionsArrayList
	 * @param question question to add 
	 * @param questionArgs (optional) more questions to add
	 */
	public void addQuestion(Question question, Question... questionArgs) {
		questions.add(question);
		for (Question ques : questionArgs)
			questions.add(ques);
		
	}
	
	public void removeLastQuestion() {
		questions.remove(questions.size()-1);
		
	}
	
	public void removeQuestionAt(int index) {
		questions.remove(index);
		
	}
	
	public void getIndexOfQuestion(Question question) {
		questions.indexOf(question);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Moves element at oldIndex to newIndex
	 * @param oldIndex index of element to move
	 * @param newIndex index of element after function is executed
	 */
	
	public void moveItemToPosition(int oldIndex, int newIndex) {
		
		if(oldIndex < newIndex) {
			
			while(oldIndex!=newIndex) {
				Collections.swap(questions, oldIndex, oldIndex+1);
				oldIndex++;
				}
				
		}
		
		if(oldIndex > newIndex) {
			
			while(oldIndex!=newIndex) {
				Collections.swap(questions, oldIndex, oldIndex-1);
				oldIndex--;
				
				}
		}
	}
	
	@Override
	public String toString() {
		
		String returnString = name + "\n\n";
		
		for(Question question : questions) {
			
			returnString += question.toString();
		}
		
		return returnString;
		
	}


}
