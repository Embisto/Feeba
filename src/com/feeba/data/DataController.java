package com.feeba.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.feeba.core.FeebaCore;
import com.google.gson.Gson;


public class DataController {
	
	final String PATH = "ServerData/survey.feeba";
	
	/**
	 * saves currenSurvey in default location (jSon file for the server)
	 */
	public void saveAsJson() {
		
		
		saveAsJson(PATH);
		
	}
	
	/**
	 * Puts a certain string s in quotes
	 * @param s String to quote
	 * @return quoted String
	 */
	
	public String putInQuotes(String s) {
		
		return "\""+s+"\"";
		
	}
	
	/**
	 * Saves JSon file to a certain path
	 * @param path where to save the file
	 */
	
	public void saveAsJson(String path) {
		
		Survey survey = FeebaCore.currentSurvey;
		ArrayList<Question> questions = survey.getQuestions();
		
		// create json String
		
		StringBuffer json = new StringBuffer("{"+putInQuotes("surveyName")+":");
		json.append(putInQuotes(survey.getName())+",");
		json.append(putInQuotes("welcomeMessage")+":" + putInQuotes(survey.getWelcomeMessage())+","+putInQuotes("questions")+":[{");

		
		for(int i = 0; i < questions.size();i++) {
		
			json.append(putInQuotes("name")+":"+putInQuotes(questions.get(i).getName())+",");
			json.append(putInQuotes("content")+":"+putInQuotes(questions.get(i).getQuestionText())+",");
			json.append(putInQuotes("choices")+":"+questions.get(i).getChoicesString()+",");
			json.append(putInQuotes("results")+":"+questions.get(i).getResultsString()+",");
			json.append(putInQuotes("type")+":"+putInQuotes(questions.get(i).getType().getJsonText()));
			json.append("},{");

		}
		
		json.deleteCharAt(json.length()-1).deleteCharAt(json.length()-1);
		json.append("]}");
		
		// save to file 
				
		try {
			//write converted json data to a file named "file.json"
			FileWriter writer = new FileWriter(path);
			writer.write(json.toString());
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * loads file from default location
	 * @return loaded Survey
	 */
	
	public Survey loadFromJson() {
		
		return loadFromJson(PATH);
		
	}
	
	/**
	 * loads file from certain location
	 * @param path path to load from
	 * @return loaded survey
	 */
	
	public Survey loadFromJson(String path) {
		

		Gson gson = new Gson();
		JsonResponseWrapper response = new JsonResponseWrapper();
		 
		try {
	 
			BufferedReader br = new BufferedReader(
				new FileReader(path));
	 
			//convert the json string back to object
			response = gson.fromJson(br, JsonResponseWrapper.class);
	 

	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create Survey-Object
		Survey surv = new Survey(response.getSurveyName());
		surv.setWelcomeMessage(response.getWelcomeMessage());
		
		for (int i = 0; i < response.getQuestions().size();i++) {
			
			
			
			Question quest = new Question(response.getQuestions().get(i).name,
										  response.getQuestions().get(i).content,
										  response.getQuestions().get(i).getQuestionType());
			for(String choice : response.getQuestions().get(i).choices)
				quest.addChoice(choice);
			if(response.getQuestions().get(i).results!=null){
				quest.addResult(response.getQuestions().get(i).results.toArray(new String[response.getQuestions().get(i).results.size()]));
			}
			surv.addQuestion(quest);
			
			
		}
		
		return surv;
	 
		
	}
	
}
