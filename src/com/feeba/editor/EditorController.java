package com.feeba.editor;

import javax.swing.JLabel;
import javax.swing.JList;

import com.feeba.data.DataController;
import com.feeba.data.Survey;
import com.feeba.server.ServerController;

public class EditorController {
	
	static Survey loadedSurvey;
	static DataController dc = new DataController();

	
	public static void loadSurvey(String file, JList list,JLabel backgroundLabel) {
		
		loadedSurvey = dc.loadFromJson(file);
		EditorGUI.showData(loadedSurvey,list,backgroundLabel);
		
	}

	public static void saveSurvey(String file) {
		
		dc.saveAsJson(loadedSurvey, file);
		
		
	}

	public static void startSurvey() {

		dc.saveAsJson(loadedSurvey);
		ServerController.showGui();
	}



}
