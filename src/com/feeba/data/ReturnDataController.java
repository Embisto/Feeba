package com.feeba.data;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReturnDataController {

	static Survey workingSurvey;

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

	public static void newData(String dataString) {

		workingSurvey.addData(getQuestionId(dataString),getAnswers(dataString));

	}

	public static void setWorkingSurvey(Survey survey) {

		workingSurvey = survey;

	}

}
