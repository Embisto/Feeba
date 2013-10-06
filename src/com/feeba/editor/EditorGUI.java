package com.feeba.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.feeba.core.FeebaCore;
import com.feeba.data.Question;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpringLayout;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.feeba.data.QuestionType;
import com.feeba.editor.components.FeebaToolbar;
import com.feeba.editor.components.PreviewPanel;
import com.feeba.editor.components.QuestionContainer;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static PreviewPanel pp;
	private JPanel contentPane;
	public static JLabel backgroundPreview;
	public static JTabbedPane centerTab;
	private static JTextField questionNameEdit;
	private JTextField fieldG;
	private JTextField fieldH;
	private JTextField fieldF;
	private JTextField fieldE;
	private JTextField fieldA;
	private JTextField fieldD;
	private JTextField fieldC;
	private JTextField fieldB;
	static JTextField[] editFields;
	public static JPanel previewOptions;
	private static boolean listenerEnabled = false;
	boolean mouseDragging = false;
	private static JComboBox questionTypeEdit;
	private static JTextArea questionTextEdit;
	public static JPanel results;
	public static QuestionContainer questionList;
	private static JLabel lblAntwortmglichkeit;
	private static JPanel choicesEdit;
	public final Color UICOLOR = FeebaCore.FEEBA_BLUE;
	public static JComboBox chartTypeSelector;
	private JPanel resultOptions;

	/**
	 * Launch the application.
	 */
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
	 * Create the frame.
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
		
		//add toolbar
		contentPane.add(new FeebaToolbar(), BorderLayout.NORTH);

		//add center tabs
		centerTab = new JTabbedPane(JTabbedPane.TOP);
		centerTab.setBorder(new LineBorder(Color.WHITE, 12));
		centerTab.setBackground(null);
		
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
		
		JPanel options = new JPanel();
		options.setOpaque(false);
		options.setMaximumSize(new Dimension(200, 32767));
		options.setMinimumSize(new Dimension(200, 10));
		options.setPreferredSize(new Dimension(250, 10));
		contentPane.add(options, BorderLayout.EAST);
		
		resultOptions = new JPanel();
		resultOptions.setVisible(false);
		resultOptions.setMinimumSize(new Dimension(200, 200));
		resultOptions.setOpaque(false);
		options.add(resultOptions);
		resultOptions.setPreferredSize(new Dimension(250, 400));
		SpringLayout sl_resultOptions = new SpringLayout();
		resultOptions.setLayout(sl_resultOptions);
		
		chartTypeSelector = new JComboBox();
		sl_resultOptions.putConstraint(SpringLayout.WEST, chartTypeSelector, 10, SpringLayout.WEST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.EAST, chartTypeSelector, -52, SpringLayout.EAST, resultOptions);
		chartTypeSelector.setModel(new DefaultComboBoxModel(new String[] {"Kuchendiagramm", "Balkendiagramm", "Radardiagramm"}));
		chartTypeSelector.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        EditorController.generateChart(results, questionList.getSelectedIndex());}
		});
		resultOptions.add(chartTypeSelector);
		
		JLabel lblChartType = new JLabel("Diagrammtyp:     ");
		sl_resultOptions.putConstraint(SpringLayout.NORTH, lblChartType, 21, SpringLayout.NORTH, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.EAST, lblChartType, -115, SpringLayout.EAST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.NORTH, chartTypeSelector, 50, SpringLayout.NORTH, lblChartType);
		sl_resultOptions.putConstraint(SpringLayout.WEST, lblChartType, 0, SpringLayout.WEST, chartTypeSelector);
		lblChartType.setOpaque(true);
		lblChartType.setForeground(Color.WHITE);
		lblChartType.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblChartType.setBorder(new LineBorder(UICOLOR, 7));
		lblChartType.setBackground(new Color(23, 116, 143));
		resultOptions.add(lblChartType);
		
		JButton resetDataButton = new JButton("Daten zur\u00FCcksetzen");
		sl_resultOptions.putConstraint(SpringLayout.WEST, resetDataButton, 10, SpringLayout.WEST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.EAST, resetDataButton, -10, SpringLayout.EAST, resultOptions);
		resetDataButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EditorController.resetResults(questionList.getSelectedIndex());
				EditorController.generateChart(results, questionList.getSelectedIndex());
			}
		});
		resultOptions.add(resetDataButton);
		
		JLabel lblChartActions = new JLabel("Diagrammaktionen:     ");
		sl_resultOptions.putConstraint(SpringLayout.EAST, lblChartActions, -80, SpringLayout.EAST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.NORTH, resetDataButton, 50, SpringLayout.NORTH, lblChartActions);
		sl_resultOptions.putConstraint(SpringLayout.NORTH, lblChartActions, 50, SpringLayout.NORTH, chartTypeSelector);
		sl_resultOptions.putConstraint(SpringLayout.WEST, lblChartActions, 0, SpringLayout.WEST, chartTypeSelector);
		lblChartActions.setOpaque(true);
		lblChartActions.setForeground(Color.WHITE);
		lblChartActions.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblChartActions.setBorder(new LineBorder(UICOLOR, 7));
		lblChartActions.setBackground(new Color(23, 116, 143));
		resultOptions.add(lblChartActions);
		
		JButton saveAsImageButton = new JButton("Diagramm als Bild speichern...");
		sl_resultOptions.putConstraint(SpringLayout.NORTH, saveAsImageButton, 40, SpringLayout.NORTH, resetDataButton);
		sl_resultOptions.putConstraint(SpringLayout.EAST, saveAsImageButton, -5, SpringLayout.EAST, resultOptions);
		saveAsImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				EditorController.saveChartImage((JLabel) results.getComponents()[0], questionList.getSelectedIndex());
			}
		});
		sl_resultOptions.putConstraint(SpringLayout.WEST, saveAsImageButton, 0, SpringLayout.WEST, chartTypeSelector);
		resultOptions.add(saveAsImageButton);
		
		previewOptions = new JPanel();
		previewOptions.setVisible(false);
		options.add(previewOptions);
		previewOptions.setBorder(new LineBorder(Color.WHITE, 10));
		previewOptions.setBackground(Color.WHITE);
		previewOptions.setPreferredSize(new Dimension(250, 70000));
		SpringLayout sl_previewOptions = new SpringLayout();
		previewOptions.setLayout(sl_previewOptions);
		
		questionTypeEdit = new JComboBox();
		sl_previewOptions.putConstraint(SpringLayout.EAST, questionTypeEdit, -14, SpringLayout.EAST, previewOptions);
		questionTypeEdit.setModel(new DefaultComboBoxModel(QuestionType.values()));
		questionTypeEdit.addItemListener(new ItemListener() {
		     @Override
		     public void itemStateChanged(ItemEvent e) {
		    	 if(listenerEnabled ) {
		    	 FeebaCore.currentSurvey.getQuestions().get(questionList.getSelectedIndex()).changeQuestionType((QuestionType)questionTypeEdit.getSelectedItem());
			     toggleChoices();
			     pp.fillPreviewFields(questionList.getSelectedIndex());}
		     }
		 });
		previewOptions.add(questionTypeEdit);
		
		JLabel questionType = new JLabel("Fragetyp:     ");
		questionType.setBorder(new LineBorder(UICOLOR, 7));
		questionType.setForeground(Color.WHITE);
		questionType.setOpaque(true);
		questionType.setBackground(UICOLOR);
		questionType.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_previewOptions.putConstraint(SpringLayout.WEST, questionType, 10, SpringLayout.WEST, previewOptions);
		sl_previewOptions.putConstraint(SpringLayout.NORTH, questionTypeEdit, 6, SpringLayout.SOUTH, questionType);
		sl_previewOptions.putConstraint(SpringLayout.WEST, questionTypeEdit, 10, SpringLayout.WEST, questionType);
		sl_previewOptions.putConstraint(SpringLayout.NORTH, questionType, 10, SpringLayout.NORTH, previewOptions);
		previewOptions.add(questionType);
		
		JLabel lblQuestionName = new JLabel("Name:    ");
		sl_previewOptions.putConstraint(SpringLayout.WEST, lblQuestionName, 0, SpringLayout.WEST, questionType);
		lblQuestionName.setOpaque(true);
		lblQuestionName.setBackground(UICOLOR);
		lblQuestionName.setBorder(new LineBorder(UICOLOR, 7));
		lblQuestionName.setForeground(Color.WHITE);
		lblQuestionName.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_previewOptions.putConstraint(SpringLayout.NORTH, lblQuestionName, 10, SpringLayout.SOUTH, questionTypeEdit);
		previewOptions.add(lblQuestionName);
		
		questionNameEdit = new JTextField();
		sl_previewOptions.putConstraint(SpringLayout.NORTH, questionNameEdit, 10, SpringLayout.SOUTH, lblQuestionName);
		sl_previewOptions.putConstraint(SpringLayout.WEST, questionNameEdit, 0, SpringLayout.WEST, questionTypeEdit);
		sl_previewOptions.putConstraint(SpringLayout.EAST, questionNameEdit, 0, SpringLayout.EAST, questionTypeEdit);
		questionNameEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int selectedIndex = questionList.getSelectedIndex();
				FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setName(questionNameEdit.getText().toString());
				pp.fillPreviewFields(selectedIndex);
				
				
			}
		});
		questionNameEdit.setForeground(Color.WHITE);
		questionNameEdit.setFont(new Font("Helvetica", Font.PLAIN, 20));
		questionNameEdit.setBackground(Color.LIGHT_GRAY);
		questionNameEdit.setBorder(new LineBorder(Color.LIGHT_GRAY, 7));
		previewOptions.add(questionNameEdit);
		questionNameEdit.setColumns(10);
		
		JLabel lbQuestion = new JLabel("Frage:    ");
		sl_previewOptions.putConstraint(SpringLayout.NORTH, lbQuestion, 10, SpringLayout.SOUTH, questionNameEdit);
		lbQuestion.setOpaque(true);
		lbQuestion.setBackground(UICOLOR);
		lbQuestion.setBorder(new LineBorder(UICOLOR, 7));
		lbQuestion.setForeground(Color.WHITE);
		lbQuestion.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_previewOptions.putConstraint(SpringLayout.WEST, lbQuestion, 10, SpringLayout.WEST, previewOptions);
		previewOptions.add(lbQuestion);
		
		choicesEdit = new JPanel();
		sl_previewOptions.putConstraint(SpringLayout.WEST, choicesEdit, 0, SpringLayout.WEST, questionNameEdit);
		choicesEdit.setBackground(Color.WHITE);
		previewOptions.add(choicesEdit);
		GridBagLayout gbl_choicesEdit = new GridBagLayout();
		gbl_choicesEdit.columnWidths = new int[] {40, 150, 0};
		gbl_choicesEdit.rowHeights = new int[] {20, 20, 20, 20, 20, 20, 20, 20, 20};
		gbl_choicesEdit.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_choicesEdit.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		choicesEdit.setLayout(gbl_choicesEdit);
		
		
		JLabel lblA = new JLabel("A : ");
		lblA.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.anchor = GridBagConstraints.WEST;
		gbc_lblA.gridx = 0;
		gbc_lblA.gridy = 0;
		choicesEdit.add(lblA, gbc_lblA);
		
	
		fieldA = new JTextField();
		fieldA.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldA.setBorder(new LineBorder(new Color(192, 192, 192), 4));		
		fieldA.setBackground(Color.LIGHT_GRAY);
		fieldA.setForeground(Color.WHITE);
		fieldA.setBackground(Color.LIGHT_GRAY);

		GridBagConstraints gbc_fieldA = new GridBagConstraints();
		gbc_fieldA.fill = GridBagConstraints.BOTH;
		gbc_fieldA.insets = new Insets(0, 0, 5, 0);
		gbc_fieldA.gridx = 1;
		gbc_fieldA.gridy = 0;
		choicesEdit.add(fieldA, gbc_fieldA);
		fieldA.setColumns(8);
		
		JLabel lblB = new JLabel("B :  ");
		lblB.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.fill = GridBagConstraints.BOTH;
		gbc_lblB.insets = new Insets(0, 0, 5, 5);
		gbc_lblB.gridx = 0;
		gbc_lblB.gridy = 1;
		choicesEdit.add(lblB, gbc_lblB);
		
		fieldB = new JTextField();
		fieldB.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldB.setForeground(Color.WHITE);
		fieldB.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldB.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		fieldB.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_fieldB = new GridBagConstraints();
		gbc_fieldB.fill = GridBagConstraints.BOTH;
		gbc_fieldB.insets = new Insets(0, 0, 5, 0);
		gbc_fieldB.gridx = 1;
		gbc_fieldB.gridy = 1;
		choicesEdit.add(fieldB, gbc_fieldB);
		fieldB.setColumns(8);
		
		JLabel lblC = new JLabel("C :   ");
		lblC.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblC = new GridBagConstraints();
		gbc_lblC.fill = GridBagConstraints.BOTH;
		gbc_lblC.insets = new Insets(0, 0, 5, 5);
		gbc_lblC.gridx = 0;
		gbc_lblC.gridy = 2;
		choicesEdit.add(lblC, gbc_lblC);
		
		fieldC = new JTextField();
		GridBagConstraints gbc_fieldC = new GridBagConstraints();
		fieldC.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldC.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		fieldC.setBackground(Color.LIGHT_GRAY);
		fieldC.setForeground(Color.WHITE);
		gbc_fieldC.fill = GridBagConstraints.BOTH;
		gbc_fieldC.insets = new Insets(0, 0, 5, 0);
		gbc_fieldC.gridx = 1;
		gbc_fieldC.gridy = 2;
		choicesEdit.add(fieldC, gbc_fieldC);
		fieldC.setColumns(8);
		
		JLabel lblD = new JLabel("D :  ");
		lblD.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.fill = GridBagConstraints.BOTH;
		gbc_lblD.insets = new Insets(0, 0, 5, 5);
		gbc_lblD.gridx = 0;
		gbc_lblD.gridy = 3;
		choicesEdit.add(lblD, gbc_lblD);
		
		fieldD = new JTextField();
		GridBagConstraints gbc_fieldD = new GridBagConstraints();
		fieldD.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldD.setForeground(Color.WHITE);
		fieldD.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		fieldD.setBackground(Color.LIGHT_GRAY);
		gbc_fieldD.fill = GridBagConstraints.BOTH;
		gbc_fieldD.insets = new Insets(0, 0, 5, 0);
		gbc_fieldD.gridx = 1;
		gbc_fieldD.gridy = 3;
		choicesEdit.add(fieldD, gbc_fieldD);
		fieldD.setColumns(10);
		
		JLabel lblE = new JLabel("E :  ");
		lblE.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblE = new GridBagConstraints();
		gbc_lblE.fill = GridBagConstraints.BOTH;
		gbc_lblE.insets = new Insets(0, 0, 5, 5);
		gbc_lblE.gridx = 0;
		gbc_lblE.gridy = 4;
		choicesEdit.add(lblE, gbc_lblE);
		
		fieldE = new JTextField();
		GridBagConstraints gbc_fieldE = new GridBagConstraints();
		fieldE.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldE.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		fieldE.setBackground(Color.LIGHT_GRAY);
		fieldE.setForeground(Color.WHITE);
		gbc_fieldE.fill = GridBagConstraints.BOTH;
		gbc_fieldE.insets = new Insets(0, 0, 5, 0);
		gbc_fieldE.gridx = 1;
		gbc_fieldE.gridy = 4;
		choicesEdit.add(fieldE, gbc_fieldE);
		fieldE.setColumns(10);
		
		JLabel lblF = new JLabel("F :  ");
		lblF.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblF = new GridBagConstraints();
		gbc_lblF.anchor = GridBagConstraints.WEST;
		gbc_lblF.fill = GridBagConstraints.BOTH;
		gbc_lblF.insets = new Insets(0, 0, 5, 5);
		gbc_lblF.gridx = 0;
		gbc_lblF.gridy = 5;
		choicesEdit.add(lblF, gbc_lblF);
		
		fieldF = new JTextField();
		GridBagConstraints gbc_fieldF = new GridBagConstraints();
		fieldF.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldF.setForeground(Color.WHITE);
		fieldF.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		fieldF.setBackground(Color.LIGHT_GRAY);
		gbc_fieldF.fill = GridBagConstraints.BOTH;
		gbc_fieldF.insets = new Insets(0, 0, 5, 0);
		gbc_fieldF.gridx = 1;
		gbc_fieldF.gridy = 5;
		choicesEdit.add(fieldF, gbc_fieldF);
		fieldF.setColumns(10);
		
		JLabel lblG = new JLabel("G :  ");
		lblG.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblG = new GridBagConstraints();
		gbc_lblG.fill = GridBagConstraints.BOTH;
		gbc_lblG.insets = new Insets(0, 0, 5, 5);
		gbc_lblG.gridx = 0;
		gbc_lblG.gridy  = 6;
		choicesEdit.add(lblG, gbc_lblG);
		
		fieldG = new JTextField();
		GridBagConstraints gbc_fieldG = new GridBagConstraints();
		fieldG.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldG.setForeground(Color.WHITE);
		fieldG.setBackground(Color.LIGHT_GRAY);
		fieldG.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		gbc_fieldG.fill = GridBagConstraints.BOTH;
		gbc_fieldG.insets = new Insets(0, 0, 5, 0);
		gbc_fieldG.gridx = 1;
		gbc_fieldG.gridy = 6;
		choicesEdit.add(fieldG, gbc_fieldG);
		fieldG.setColumns(10);
		
		JLabel lblH = new JLabel("H :  ");
		lblH.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblH = new GridBagConstraints();
		gbc_lblH.fill = GridBagConstraints.BOTH;
		gbc_lblH.insets = new Insets(0, 0, 5, 5);
		gbc_lblH.gridx = 0;
		gbc_lblH.gridy = 7;
		choicesEdit.add(lblH, gbc_lblH);
		
		fieldH = new JTextField();
		GridBagConstraints gbc_fieldH = new GridBagConstraints();
		fieldH.setFont(new Font("Helvetica", Font.PLAIN, 20));
		fieldH.setBackground(Color.LIGHT_GRAY);
		fieldH.setForeground(Color.WHITE);
		fieldH.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		gbc_fieldH.fill = GridBagConstraints.BOTH;
		gbc_fieldH.insets = new Insets(0, 0, 5, 0);
		gbc_fieldH.gridx = 1;
		gbc_fieldH.gridy = 7;
		choicesEdit.add(fieldH, gbc_fieldH);
		fieldH.setColumns(10);
		
		
		
		lblAntwortmglichkeit = new JLabel("Antwortm\u00F6glichkeiten:    ");
		lblAntwortmglichkeit.setOpaque(true);
		lblAntwortmglichkeit.setBackground(UICOLOR);
		lblAntwortmglichkeit.setBorder(new LineBorder(UICOLOR, 7));
		lblAntwortmglichkeit.setForeground(Color.WHITE);
		lblAntwortmglichkeit.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_previewOptions.putConstraint(SpringLayout.WEST, lblAntwortmglichkeit, 10, SpringLayout.WEST, previewOptions);
		sl_previewOptions.putConstraint(SpringLayout.NORTH, choicesEdit, 14, SpringLayout.SOUTH, lblAntwortmglichkeit);
		
		
		previewOptions.add(lblAntwortmglichkeit);
		
		questionTextEdit = new JTextArea();
		sl_previewOptions.putConstraint(SpringLayout.NORTH, lblAntwortmglichkeit, 10, SpringLayout.SOUTH, questionTextEdit);
		sl_previewOptions.putConstraint(SpringLayout.NORTH, questionTextEdit, 10, SpringLayout.SOUTH, lbQuestion);
		sl_previewOptions.putConstraint(SpringLayout.WEST, questionTextEdit, 0, SpringLayout.WEST, questionTypeEdit);
		sl_previewOptions.putConstraint(SpringLayout.EAST, questionTextEdit, 0, SpringLayout.EAST, questionTypeEdit);
		questionTextEdit.setLineWrap(true);
		questionTextEdit.setWrapStyleWord(true);
		sl_previewOptions.putConstraint(SpringLayout.EAST, choicesEdit, 0, SpringLayout.EAST, questionTextEdit);
		questionTextEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int selectedIndex = questionList.getSelectedIndex();
				FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setQuestionText(questionTextEdit.getText().toString());
				pp.fillPreviewFields(selectedIndex);
			}
		});
		questionTextEdit.setForeground(Color.WHITE);
		questionTextEdit.setFont(new Font("Helvetica", Font.PLAIN, 16));
		questionTextEdit.setBackground(Color.LIGHT_GRAY);
		questionTextEdit.setBorder(new LineBorder(Color.LIGHT_GRAY, 6));
		questionTextEdit.setRows(3);
		previewOptions.add(questionTextEdit);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        if(index == 0) {
		        	EditorController.toggleOptionPanel(resultOptions, previewOptions, false, true);
		        }
		        if(index ==1) {
		        	
		        	if(FeebaCore.currentSurvey!=null){
		        	EditorController.toggleOptionPanel(resultOptions, previewOptions, true, false);
		        	EditorController.generateChart(results,questionList.getSelectedIndex());}
		        	else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}
		        }
		      }
		    };
		    
		centerTab.addChangeListener(changeListener);
		editFields = new JTextField[] {fieldA,fieldB,fieldC,fieldD,fieldE,fieldF,fieldG,fieldH};
		ChoicesChangedAdapter cca = new ChoicesChangedAdapter(questionList.getQuestionList(), editFields, pp);
		for(int i = 0; i < editFields.length ; i++) {
			
			editFields[i].addKeyListener(cca);
			
		}
		
	}

	public static void removeQuestion() {
		
		FeebaCore.currentSurvey.removeQuestionAt(questionList.getSelectedIndex());
		EditorController.initModel(questionList.getQuestionList());
		questionList.getQuestionList().setSelectedIndex(0);
		
	}
	
	private static void fillEditFields(int selectedIndex) {

		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
		questionNameEdit.setText(ques.getName());
		questionTextEdit.setText(ques.getQuestionText());
		listenerEnabled = false;
		questionTypeEdit.setSelectedItem(ques.getType());
		listenerEnabled = true;
		
		for (JTextField field:editFields) {
			field.setText("");
			
		}
		
		toggleChoices();
		fillChoices(ques);
		
	}

	
	
	private static void fillChoices(Question ques) {
		
		if(ques.getType().equals(QuestionType.FREETEXT)) {
			return;
		}
	
		
		for (int i = 0; i< ques.getChoices().size();i++)
		{
			editFields[i].setText(ques.getChoices().get(i));
			editFields[i].setVisible(true);
		}
		
	}

	public static void toggleChoices() {

		if(questionTypeEdit.getSelectedItem().equals(QuestionType.FREETEXT)) {
			
			lblAntwortmglichkeit.setVisible(false);
			choicesEdit.setVisible(false);
		}
		else {
			
			lblAntwortmglichkeit.setVisible(true);
			choicesEdit.setVisible(true);
			
		}
		
	}
	
	public JComboBox getChartTypeSelector() {
		return chartTypeSelector;
	}

	public static void finishLoadingFile(String inputDir) {
		    
		    centerTab.setVisible(true);
	        questionList.setVisible(true);
	        EditorController.loadSurvey(inputDir,questionList.getQuestionList(),pp);
	        previewOptions.setVisible(true);
	        questionList.setButtonsVisible();
	        questionList.getQuestionList().requestFocus();
		
	}
	
	public static void selectedQuestionChanged() {
		
		int selectedIndex = questionList.getSelectedIndex();
		
		pp.fillPreviewFields(selectedIndex);
		fillEditFields(selectedIndex);
		EditorController.generateChart(results,selectedIndex);
		
	}
}
