package com.feeba.editor;

import static com.googlecode.charts4j.Color.BEIGE;
import static com.googlecode.charts4j.Color.BLACK;
import static com.googlecode.charts4j.Color.TURQUOISE;
import static com.googlecode.charts4j.Color.BISQUE;
import static com.googlecode.charts4j.Color.BLUE;
import static com.googlecode.charts4j.Color.YELLOW;
import static com.googlecode.charts4j.Color.CORAL;
import static com.googlecode.charts4j.Color.CRIMSON;
import static com.googlecode.charts4j.Color.CYAN;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import com.feeba.data.DataController;
import com.feeba.data.Question;
import com.feeba.data.ReturnDataController;
import com.feeba.data.Survey;
import com.feeba.server.ServerController;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;


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
		ReturnDataController.setWorkingSurvey(dc.loadFromJson());
		ServerController.showGui();
	}
	
	
	public static JLabel pieChart(int selectedIndex, String chartTitle) {
		
		Color[] colors = new Color[] {TURQUOISE,BEIGE,BISQUE,BLUE,YELLOW,CORAL,CRIMSON,CYAN};
		Question ques = ReturnDataController.workingSurvey.getQuestions().get(selectedIndex);
		Slice[] slices = new Slice[ques.getChoices().size()];
	    for (int i = 0; i < slices.length;i++) {
	    	slices[i] = Slice.newSlice(Collections.frequency(ques.getResults(),ques.getChoices().get(i)), colors[i],ques.getChoices().get(i));
	    	
	    }
	    
	    PieChart chart = GCharts.newPieChart();
	    switch(slices.length){
	    case 1:
	    	chart = GCharts.newPieChart(slices[0]);
	    	break;
	    case 2:
	    	chart = GCharts.newPieChart(slices[0],slices[1]);
	    	break;
	    case 3:
	    	chart = GCharts.newPieChart(slices[0],slices[1],slices[2]);
	    	break;
	    case 4:
	    	chart = GCharts.newPieChart(slices[0],slices[1],slices[2],slices[3]);
	    	break;
	    case 5:
	    	chart = GCharts.newPieChart(slices[0],slices[1],slices[2],slices[3],slices[4]);
	    	break;
	    case 6:
	    	chart = GCharts.newPieChart(slices[0],slices[1],slices[2],slices[3],slices[4],slices[5]);
	    	break;
	    case 7:
	    	chart = GCharts.newPieChart(slices[0],slices[1],slices[2],slices[3],slices[4],slices[5],slices[6]);
	    	break;
	    case 8:
	    	chart = GCharts.newPieChart(slices[0],slices[1],slices[2],slices[3],slices[4],slices[5],slices[6],slices[7]);
	    	break;
	    	
	    }
	    
        chart.setTitle(chartTitle, BLACK, 16);
        chart.setSize(600, 300);
        JLabel label = new JLabel();
		try {
			label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart.toURLString()))));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return label;
    }
	
}
