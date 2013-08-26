package com.feeba.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DataController {
	
	final String PATH = "ServerData/survey.feeba";
	
	public void saveAsJson(Survey survey) {
		
		
		saveAsJson(survey,PATH);
		
	}
	
	public String putInQuotes(String s) {
		
		return "\""+s+"\"";
		
	}
	
	public void saveAsJson(Survey survey,String path) {
		
		ArrayList<Question> questions = survey.getQuestions();
		
		// create json String
		
		StringBuffer json = new StringBuffer("{"+putInQuotes("surveyName")+":");
		json.append(putInQuotes(survey.getName())+","+putInQuotes("questions")+":[{");
		
		for(int i = 0; i < questions.size();i++) {
		
			json.append(putInQuotes("name")+":"+putInQuotes(questions.get(i).getName())+",");
			json.append(putInQuotes("content")+":"+putInQuotes(questions.get(i).getQuestionText())+",");
			json.append(putInQuotes("choices")+":"+questions.get(i).getChoicesString()+",");
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
			
	
}
