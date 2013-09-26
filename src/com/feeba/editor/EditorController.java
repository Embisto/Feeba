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

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.feeba.core.FeebaCore;
import com.feeba.data.DataController;
import com.feeba.data.Question;
import com.feeba.data.QuestionType;
import com.feeba.data.ReturnDataController;
import com.feeba.data.Survey;
import com.feeba.server.ServerController;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fill;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.RadarChart;
import com.googlecode.charts4j.RadarPlot;
import com.googlecode.charts4j.RadialAxisLabels;
import com.googlecode.charts4j.Shape;
import com.googlecode.charts4j.Slice;


public class EditorController {
	
	
	static DataController dc = new DataController();

	
	public static void loadSurvey(String file, JList list,JLabel backgroundLabel) {
		
		FeebaCore.currentSurvey = dc.loadFromJson(file);
		showData(list,backgroundLabel);
		
	}

	public static void saveSurvey(String file) {
		
		dc.saveAsJson(file);
		
	}

	public static void startSurvey() {

		dc.saveAsJson();
		ServerController.showGui();
	}
	
	public static void saveChartImage(JLabel label, int selectedIndex) {
		
		ImageIcon icon = (ImageIcon) label.getIcon();
		Image img = icon.getImage();

		BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);

		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, 0, 0, null);
		g2.dispose();
		
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(FeebaCore.currentSurvey.getName()+"_"+FeebaCore.currentSurvey.getQuestions().get(selectedIndex).getName()+"_Reults.png"));
        chooser.setDialogTitle("Speichern unter...");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                return f.getName().toLowerCase().endsWith(".feeba");
              }
              public String getDescription () { return "PNG (*.png)"; }  
            });
        
      chooser.setVisible(true); 
        
      final int result = chooser.showSaveDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            File saveFile = chooser.getSelectedFile(); 
            String saveDir = saveFile.getPath(); 
		try {
			ImageIO.write(bi, "png", new File(saveDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
		
	}
	
	public static JLabel barChart(int selectedIndex, String chartTitle) {
		
		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
		double[] dataArray = new double[ques.getChoices().size()];
		for(int i = 0;i < ques.getChoices().size();i++)
		{
			
			dataArray[i] = ((double)Collections.frequency(ques.getResults(),ques.getChoices().get(i))/(double)ques.getResults().size())*100;
			
		}
		Data data = new Data(dataArray);
		Plot plot = Plots.newPlot(data);
		plot.setColor(Color.newColor("17748F"));
		
		for(int j = 0;j < dataArray.length;j++) {
			plot.addTextMarker((int)dataArray[j]+" %", BLACK, 16, j);
		}
		BarChart chart = GCharts.newBarChart(plot);
        chart.setBarWidth(80);
        chart.setTitle(chartTitle, BLACK, 16);
        chart.setSize(600, 500);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(ques.getChoices()));
        chart.addYAxisLabels(AxisLabelsFactory.newAxisLabels(Arrays.asList("0 %", "20 %", "40 %", "60 %", "80 %", "100 %")));
		
		 JLabel label = new JLabel();
			try {
				label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart.toURLString()))));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	        return label;
	}
	
	public static JLabel radarChart(int selectedIndex, String chartTitle) {
		
		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
		double[] dataArray = new double[ques.getChoices().size()];
		for(int i = 0;i < ques.getChoices().size();i++)
		{
			
			dataArray[i] = ((double)Collections.frequency(ques.getResults(),ques.getChoices().get(i))/(double)ques.getResults().size())*100;
			
		}
		Arrays.toString(dataArray);
		Data data = new Data(dataArray);
		RadarPlot plot = Plots.newRadarPlot(data);
        Color plotColor = Color.newColor("17748F");
        plot.addShapeMarkers(Shape.SQUARE, plotColor, 12);
        plot.addShapeMarkers(Shape.SQUARE, Color.WHITE, 8);
        plot.setColor(plotColor);
        plot.setLineStyle(LineStyle.newLineStyle(4, 1, 0));
        RadarChart chart = GCharts.newRadarChart(plot);
        chart.setTitle(chartTitle, BLACK, 20);
        chart.setSize(500, 500);
        chart.setSpline(true);
        RadialAxisLabels radialAxisLabels = AxisLabelsFactory.newRadialAxisLabels(ques.getChoices());
        radialAxisLabels.setRadialAxisStyle(BLACK, 12);
        chart.addRadialAxisLabels(radialAxisLabels);
        AxisLabels contrentricAxisLabels = AxisLabelsFactory.newAxisLabels(Arrays.asList("0 %", "20 %", "40 %", "60 %", "80 %", "100 %"));
        contrentricAxisLabels.setAxisStyle(AxisStyle.newAxisStyle(BLACK, 12, AxisTextAlignment.RIGHT));
        chart.addConcentricAxisLabels(contrentricAxisLabels);
        
        JLabel label = new JLabel();
		try {
			label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart.toURLString()))));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return label;
		
	}
	
	public static JLabel pieChart(int selectedIndex, String chartTitle) {
		
		Color[] colors = new Color[] {TURQUOISE,BEIGE,BISQUE,BLUE,YELLOW,CORAL,CRIMSON,CYAN};
		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
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
        chart.setSize(700, 330);
        chart.setThreeD(true);
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
	
	public static void showData(JList list, JLabel backgroundLabel) {
		
		try {
			backgroundLabel.setIcon(new ImageIcon(resize(ImageIO.read(EditorGUI.class.getResource("/images/Background.png")),backgroundLabel.getWidth(),backgroundLabel.getHeight())));
		} catch (IOException e) {
			System.err.println("Error loading Background");
		}
		

	    initModel(list);
	    list.setSelectedIndex(0);
	    
	    //add Drag'n'Drop
	    MouseAdapter listener = new ReorderListener(list);
	    list.addMouseListener(listener);
	    list.addMouseMotionListener(listener);
		
	}
	
	    //http://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
		public static BufferedImage resize(BufferedImage image, int width, int height) {
		    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		    Graphics2D g2d = (Graphics2D) bi.createGraphics();
		    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		    g2d.drawImage(image, 0, 0, width, height, null);
		    g2d.dispose();
		    return bi;
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
			
				switch(EditorGUI.comboBox.getSelectedIndex()){
				case 0:
					results.add(EditorController.pieChart(selectedIndex,name));
					break;
				case 1:
					results.add(EditorController.barChart(selectedIndex,name));
					break;
				case 2:
					results.add(EditorController.radarChart(selectedIndex,name));
					break;
				}
				}
			
			else {
				
				results.add(EditorController.freetextChart(selectedIndex,name));
				
			}
			
			}
			
			results.updateUI();
			
			
		}

		private static Component freetextChart(int selectedIndex, String name) {
			JLabel label = new JLabel();
			String text = "<HTML>";
			ArrayList<String> results = FeebaCore.currentSurvey.getQuestions().get(selectedIndex).getResults();
			for(int i = 0; i < results.size();i++) {
				
				text += results.get(i) + "<BR>";
				
			}
			
			text+="</HTML>";
			
			label.setText(text);
			return label;
		}
		
		public static void toggleOptionPanel(JPanel panel1,JPanel panel2, boolean isVisible1, boolean isVisible2) {
			
			panel1.setVisible(isVisible1);
			panel2.setVisible(isVisible2);
			
		}
}
