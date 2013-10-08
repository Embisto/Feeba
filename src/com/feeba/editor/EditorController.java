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
import com.feeba.tools.ChartFactory;


public class EditorController {
	
	
	static DataController dc = new DataController();
	public static boolean serverWindowIsOpen = false;

	
	public static void loadSurvey(String file, JList list,PreviewPanel pp) {
		
		FeebaCore.currentSurvey = dc.loadFromJson(file);
		showData(list,pp);
		
	}

	public static void saveSurvey(String file) {
		
		dc.saveAsJson(file);
		
	}

	public static void startSurvey() {
		
		if(!serverWindowIsOpen) {
			serverWindowIsOpen = true;
			ServerController.showGui();
			}
	}
	
	
	public static void showData(JList list, PreviewPanel pp) {
		
		pp.updateBackground();

	    initModel(list);
	    list.setSelectedIndex(0);
	    
	    //add Drag'n'Drop
	    MouseAdapter listener = new ReorderListener(list);
	    list.addMouseListener(listener);
	    list.addMouseMotionListener(listener);
		
	}
	

		
		public static void initModel(JList list) {
			
			DefaultListModel model = new DefaultListModel();
			int index = 1;
		    for(Question q : FeebaCore.currentSurvey.getQuestions()){
		         model.addElement("Frage " + (index++) +": " +q.getName());
		    } 
		    
		    list.setModel(model);  
		    
		}
		
		public static void resetResults(int selectedIndex) {
			
			FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setResults(new ArrayList<String>());
			
		}
		
		public static void generateChart(JPanel results, int selectedIndex) {
			
			String name = FeebaCore.currentSurvey.getQuestions().get(selectedIndex).getQuestionText();
			
			results.removeAll();
			results.updateUI();
			
			if(!FeebaCore.currentSurvey.checkForData(selectedIndex)) {
				results.add(new JLabel("Noch keine Daten vorhanden"));
			}
			
			else {
			
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
				EditorGUI.getChartTypeSelector().setModel(new DefaultComboBoxModel(new String[] {"Kuchendiagramm", "Balkendiagramm", "Radardiagramm"}));
				EditorGUI.getChartTypeSelector().setSelectedIndex(selectedChartTypeIndex);
				}
			
			else {
				
				EditorGUI.getChartTypeSelector().setModel(new DefaultComboBoxModel(new String[] {"Wordcloud"}));
				results.add(ChartFactory.freetextChart(selectedIndex,name));
				
			}
			
			}
			
			results.updateUI();
			
			
		}
		
		public static void toggleOptionPanel(ResultOptions panel1,JPanel panel2, boolean isVisible1, boolean isVisible2) {
			
			panel1.setVisible(isVisible1);
			panel2.setVisible(isVisible2);
			
		}

		public static void addQuestion(String s) {
			Question ques = new Question(s, "Beispielfragetext", QuestionType.FREETEXT);
			FeebaCore.currentSurvey.addQuestion(ques);
			
		}
		

		public static void fillEditFields(int selectedIndex,
				PreviewOptions previewOptions) {
			
			Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
			previewOptions.fillEditFields(ques);
			
			
		}

}
