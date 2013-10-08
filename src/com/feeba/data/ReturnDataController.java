package com.feeba.data;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.feeba.core.FeebaCore;
import com.feeba.editor.EditorController;
import com.feeba.editor.EditorGUI;

public class ReturnDataController {

	/**
	 * Creates a String Array of the received JSon String
	 * @param dataString the received data
	 * @return the formatted StringArray
	 */
	
	static String[] getAnswers(String dataString) {

		ArrayList<String> answers = new ArrayList<String>();

		JSONParser parser = new JSONParser();

		Object obj = null;
		try {
			obj = parser.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = (JSONObject) obj;

		JSONArray msg = (JSONArray) jsonObject.get("answers");
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = msg.iterator();
		while (iterator.hasNext()) {
			answers.add(iterator.next());
		}

		return answers.toArray(new String[answers.size()]);

	}

	/**
	 * Extracts the questionID of the received JSon String
	 * @param dataString received data 
	 * @return returns received question index
	 */
	static int getQuestionId(String dataString) {

		JSONParser parser = new JSONParser();

		Object obj = null;
		try {
			obj = parser.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = (JSONObject) obj;

		long questionIndex = (Long) jsonObject.get("question");

		return (int) questionIndex;

	}
	
	/**
	 * adds new Data to survey and triggers a chartRepaint if the question is currently viewed by the user 
	 * @param dataString received data
	 */
	public static void newData(String dataString) {
		FeebaCore.currentSurvey.addData(getQuestionId(dataString),getAnswers(dataString));
		
		if(getQuestionId(dataString)==EditorGUI.questionList.getSelectedIndex()){
		EditorController.generateChart(EditorGUI.results, getQuestionId(dataString));}
	}


}
