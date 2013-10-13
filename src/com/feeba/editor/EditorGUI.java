package com.feeba.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.feeba.core.FeebaCore;
import com.feeba.data.QuestionType;
import com.feeba.editor.components.CenterPanel;
import com.feeba.editor.components.FeebaToolbar;
import com.feeba.editor.components.PreviewOptions;
import com.feeba.editor.components.PreviewPanel;
import com.feeba.editor.components.QuestionContainer;
import com.feeba.editor.components.ResultOptions;
import com.feeba.tools.ImageTools;




public class EditorGUI extends JFrame {


	private static final long serialVersionUID = 5915672707327607986L;
	
	private JPanel contentPane;
	private static PreviewPanel pp;
	public static CenterPanel centerTab;
	public static PreviewOptions previewOptionPanel;
	public static JPanel results;
	public static QuestionContainer questionList;
	public static ResultOptions resultOptions;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					EditorGUI frame = new EditorGUI();
					frame.setState(Frame.NORMAL);
					
					//start editor maximized
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Dimension dimension = toolkit.getScreenSize();
					frame.setSize(dimension);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}
	
	/**
	 * Creates basic EditorGUI
	 */
	public EditorGUI() {
		
		setTitle("Feeba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 544);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//add toolBar
		contentPane.add(new FeebaToolbar(), BorderLayout.NORTH);

		//add center tabs
		centerTab = new CenterPanel(JTabbedPane.TOP);
		contentPane.add(centerTab, BorderLayout.CENTER);

		
		// add PreviewPanel
		pp = new PreviewPanel();
		centerTab.addTab("Vorschau", null, pp, null);

		
		// create and add resultsTab
		results = new JPanel();
		results.setEnabled(false);
		results.setBackground(Color.WHITE);
		centerTab.addTab("Auswertung", null, results, null);
		GridBagLayout gbl_results = new GridBagLayout();
		gbl_results.columnWidths = new int[]{0};
		gbl_results.rowHeights = new int[]{0};
		gbl_results.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_results.rowWeights = new double[]{Double.MIN_VALUE};
		results.setLayout(gbl_results);
		
		// add questionList
		questionList = new QuestionContainer();
		contentPane.add(questionList, BorderLayout.WEST);
		
		//add optionPanel 
		JPanel options = new JPanel();
		options.setOpaque(false);
		options.setMaximumSize(new Dimension(200, 32767));
		options.setMinimumSize(new Dimension(200, 10));
		options.setPreferredSize(new Dimension(250, 10));
		contentPane.add(options, BorderLayout.EAST);
		
		//add resultOptions
		resultOptions = new ResultOptions();
		options.add(resultOptions);

		// add previewOptions
		previewOptionPanel = new PreviewOptions();
		options.add(previewOptionPanel);
		
		//init CenterTabListeners
		centerTab.initListener();
		
	}

	/**
	 * removes question from survey, and model and selects first item in list
	 */
	public static void removeQuestion() {
		
		FeebaCore.currentSurvey.removeQuestionAt(questionList.getSelectedIndex());
		EditorController.initModel(questionList.getQuestionList());
		questionList.getQuestionList().setSelectedIndex(0);
		
	}
	
	/**
	 * returns the chartTypeSelector of resultOptions
	 * @return chartTypeSelector
	 */
	public static JComboBox getChartTypeSelector() {
		return resultOptions.getChartTypeSelector();
	}

	/**
	 * Finishes the file loading process, and creates UI
	 * @param inputDir the path of the survey to load
	 */
	public static void finishLoadingFile(String inputDir) {
		    
		    centerTab.setVisible(true);
	        questionList.setVisible(true);
	        EditorController.loadSurvey(inputDir,questionList.getQuestionList(),pp);
	        previewOptionPanel.setVisible(true);
	        questionList.setButtonsVisible();
	        questionList.getQuestionList().requestFocus();
	        previewOptionPanel.initListeners();
		
	}
	
	public static void selectedQuestionChanged() {
		
		int selectedIndex = questionList.getSelectedIndex();
		
		pp.fillPreviewFields(selectedIndex);
		EditorController.fillEditFields(selectedIndex,previewOptionPanel);
		EditorController.generateChart(results,selectedIndex);
		
	}

	public static void generateChart() {
		
		EditorController.generateChart(results, questionList.getSelectedIndex());
		
	}
	
	public static void resetResults() {
		
		EditorController.resetResults(questionList.getSelectedIndex());
		EditorController.generateChart(results, questionList.getSelectedIndex());
		
	}

	public static void saveChartImage() {
		
		ImageTools.saveChartImage((JLabel) results.getComponents()[0], questionList.getSelectedIndex());
		
	}

	public static void changeQuestionType(QuestionType type) {
	
		FeebaCore.currentSurvey.getQuestions().get(questionList.getSelectedIndex()).changeQuestionType(type);
		previewOptionPanel.toggleChoicesVisibilty();
	    pp.fillPreviewFields(questionList.getSelectedIndex());
		
	}

	public static void questionNameChanged(String s) {
		
		int selectedIndex = questionList.getSelectedIndex();
		FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setName(s);
		pp.fillPreviewFields(selectedIndex);
		EditorController.initModel(questionList.getQuestionList());
		questionList.getQuestionList().setSelectedIndex(selectedIndex);
		
	}

	public static void questionContentChanged(String s) {
		
		int selectedIndex = questionList.getSelectedIndex();
		FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setQuestionText(s);
		pp.fillPreviewFields(selectedIndex);
		
	}

	public static PreviewPanel getPreviewPanel() {
		
		return pp;
	}

	public static PreviewOptions getPreviewOptionPanel() {

		return previewOptionPanel;
	}

	public static JList getQuestionList() {
		
		return questionList.getQuestionList();
	}

	/**
	 * Triggered if Tab in CenterTab has changed 
	 * @param index index of selected tab
	 */
	public static void changeToTab(int index) {
		
		 if(index == 0) {
	        	
	        	EditorController.toggleOptionPanel(resultOptions, previewOptionPanel, false, true);
	        }
	        if(index == 1) {
	        	
	        	if(FeebaCore.currentSurvey!=null){
	        	EditorController.toggleOptionPanel(resultOptions, previewOptionPanel, true, false);
	        	EditorController.generateChart(results,questionList.getSelectedIndex());}
	        	else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}
	        }
		
	}
}
