package com.feeba.editor;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.feeba.core.FeebaCore;
import com.feeba.data.DataController;
import com.feeba.data.Question;
import com.feeba.data.QuestionType;
import com.feeba.editor.components.PreviewOptions;
import com.feeba.editor.components.PreviewPanel;
import com.feeba.editor.components.ResultOptions;
import com.feeba.server.ServerController;
import com.feeba.server.ServerGUI;
import com.feeba.tools.ChartFactory;


public class EditorController {
	
	
	static DataController dc = new DataController();
	public static boolean serverWindowIsOpen = false;
	public static boolean metaEditorIsOpen = false;

	
	/**
	 * loads survey and showsData
	 * @param file path to the file to load
	 * @param list the list to display the questions
	 * @param pp the panel to show the preview
	 */
	public static void loadSurvey(String file, JList list,PreviewPanel pp) {
		
		FeebaCore.currentSurvey = dc.loadFromJson(file);
		showData(list,pp);
		
	}

	/**
	 * Saves current survey to given location
	 * @param file path to save
	 */
	
	public static void saveSurvey(String file) {
		
		dc.saveAsJson(file);
		
	}

	/**
	 * Checks if serverWindow is allready open and brings it to front or creates it
	 */
	public static void startSurvey() {
		
		if(!serverWindowIsOpen) {
			serverWindowIsOpen = true;
			ServerController.showGui();
			}
		else {
			ServerGUI.frame.toFront();
			ServerGUI.frame.repaint();
		}
	}
	
	/**
	 * shows surveyData
	 * @param list the list to display the questionNames
	 * @param pp the preview panel to display the Questions
	 */
	public static void showData(JList list, PreviewPanel pp) {
		
		pp.updateBackground();

	    initModel(list);
	    list.setSelectedIndex(0);
	    
	    //add Drag'n'Drop
	    MouseAdapter listener = new ReorderListener(list);
	    list.addMouseListener(listener);
	    list.addMouseMotionListener(listener);
		
	}
	

		/**
		 * creates the model for the QuestionList according to currently loaded survey
		 * @param list the list to display the questionNames
		 */
		public static void initModel(JList list) {
			
			DefaultListModel model = new DefaultListModel();
			int index = 1;
		    for(Question q : FeebaCore.currentSurvey.getQuestions()){
		         model.addElement("Frage " + (index++) +": " +q.getName());
		    } 
		    
		    list.setModel(model);  
		    
		}
		
		/**
		 * resets results of a certain question
		 * @param selectedIndex index of the question to reset
		 */
		public static void resetResults(int selectedIndex) {
			
			FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setResults(new ArrayList<String>());
			
		}
		
		/**
		 * Generates chart of certain question
		 * @param results the panel to display the chart on 
		 * @param selectedIndex the index of the question to display
		 */
		public static void generateChart(JPanel results, int selectedIndex) {
			
			// get questionName
			String name = FeebaCore.currentSurvey.getQuestions().get(selectedIndex).getQuestionText();
			
			//clear panel
			results.removeAll();
			results.updateUI();
			
			// check if there is data
			if(!FeebaCore.currentSurvey.checkForData(selectedIndex)) {
				results.add(new JLabel("Noch keine Daten vorhanden"));
			}
			
			// if there is data, create chart 
			else {
			
			// check if a numeric chart is needed
			if(!FeebaCore.currentSurvey.getQuestions().get(selectedIndex).getType().equals(QuestionType.FREETEXT)) {
				
				int selectedChartTypeIndex = EditorGUI.getChartTypeSelector().getSelectedIndex();
				switch(selectedChartTypeIndex){
				case 0:
					results.add(ChartFactory.pieChart(selectedIndex,name));
					break;
				case 1:
					results.add(ChartFactory.barChart(selectedIndex,name));
					break;
				case 2:
					results.add(ChartFactory.radarChart(selectedIndex,name));
					break;
				}
				
				// update chartTypeModel
				EditorGUI.getChartTypeSelector().setModel(new DefaultComboBoxModel(new String[] {"Kuchendiagramm", "Balkendiagramm", "Radardiagramm"}));
				EditorGUI.getChartTypeSelector().setSelectedIndex(selectedChartTypeIndex);
				}
			
			else {
				// create a wordCloud
				EditorGUI.getChartTypeSelector().setModel(new DefaultComboBoxModel(new String[] {"Wordcloud"}));
				results.add(ChartFactory.freetextChart(selectedIndex,name));
				
			}
			
			}
			
			results.updateUI();
			
			
		}
		
		/**
		 * Sets visibility of two panels
		 * @param panel1 Panel 1 
		 * @param panel2 Panel 2
		 * @param isVisible1 state of Panel 1 (visible = true)
		 * @param isVisible2 state of Panel 2 (visible = true)
		 */
		public static void toggleOptionPanel(ResultOptions panel1,JPanel panel2, boolean isVisible1, boolean isVisible2) {
			
			panel1.setVisible(isVisible1);
			panel2.setVisible(isVisible2);
			
		}
		
		/**
		 * Adds a new Question
		 * @param name  name of the question
		 */
		public static void addQuestion(String name) {
			Question ques = new Question(name, "Platzhalterfragetext ?", QuestionType.FREETEXT);
			FeebaCore.currentSurvey.addQuestion(ques);
			
		}
		
		/**
		 * Fills edit Fields
		 * @param selectedIndex index of selected question
		 * @param previewOptions previewOptionPanel to fill in data 
		 */
		public static void fillEditFields(int selectedIndex,
				PreviewOptions previewOptions) {
			
			Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
			previewOptions.fillEditFields(ques);
			
			
		}

		/**
		 * Checks if MetaDataEditor is already open and brings it to front, or creates a new instance
		 */
		public static void startMetaDataEditor() {
			
			if(!metaEditorIsOpen) {
				metaEditorIsOpen = true;
				EditSurveyMetaGUI.main(null);
				}
			else {
				EditSurveyMetaGUI.frame.toFront();
				EditSurveyMetaGUI.frame.repaint();
			}
			
			
		}
		
		/**
		 * Updates SurveyMetaData
		 * @param name name of the survey
		 * @param info WelcomeMesage (Shown to the user before the survey)
		 */
		public static void updateMetaData(String name, String info) {
			
			FeebaCore.currentSurvey.setName(name);
			FeebaCore.currentSurvey.setWelcomeMessage(info);
			
		}

}
