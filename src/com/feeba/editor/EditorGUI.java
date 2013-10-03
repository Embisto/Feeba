package com.feeba.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.feeba.core.FeebaCore;
import com.feeba.data.Question;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLayeredPane;

import javax.swing.SpringLayout;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.feeba.data.QuestionType;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;

public class EditorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JList questions;
	public static JLabel backgroundPreview;
	public static JTabbedPane tabbedPane;
	private JTextField questionNameEdit;
	private JTextField fieldG;
	private JTextField fieldH;
	private JTextField fieldF;
	private JTextField fieldE;
	private JTextField fieldA;
	private JTextField fieldD;
	private JTextField fieldC;
	private JTextField fieldB;
	private JTextField fieldI;
	JTextField[] editFields;
	public static JPanel previewOptions;
	private static boolean listenerEnabled = false;
	boolean mouseDragging = false;
	private JComboBox questionTypeEdit;
	private JTextArea questionTextEdit;
	public static JPanel results;
	public static Box questionWrapper;
	private JLabel lblAntwortmglichkeit;
	private JPanel choicesEdit;
	public final Color UICOLOR = FeebaCore.FEEBA_BLUE;
	public static JComboBox chartTypeSelector;
	private JPanel resultOptions;
	private static JButton removeButton;
	private static JButton addButton;
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
					
					updateBackgroundLabel(backgroundPreview,tabbedPane);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void updateBackgroundLabel(JLabel label, JTabbedPane pane) {
				label.setSize(new Dimension(pane.getWidth(),pane.getHeight()));
				
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
		
		JPanel toolBar = new JPanel();
		toolBar.setBackground(new Color(0x222325));
		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		toolBar.setPreferredSize(new Dimension(16, 50));
		contentPane.add(toolBar, BorderLayout.NORTH);
		SpringLayout sl_toolBar = new SpringLayout();
		toolBar.setLayout(sl_toolBar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EditorGUI.class.getResource("/images/logo_toolbar.png")));
		toolBar.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		sl_toolBar.putConstraint(SpringLayout.WEST, panel_1, 50, SpringLayout.EAST, lblNewLabel);
		sl_toolBar.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, toolBar);
		panel_1.setOpaque(false);
		toolBar.add(panel_1);
		
		JButton btnNewButton = new JButton("Fragebogen Laden");
		panel_1.add(btnNewButton);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEADING);
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setPreferredSize(new Dimension(200, 50));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Helvetica", Font.BOLD, 10));
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(new Color(0x17748F));
		btnNewButton.setBorder(new LineBorder(new Color(0x17748F), 7));
		
		Component horizontalStrut = Box.createHorizontalStrut(1);
		panel_1.add(horizontalStrut);
		
		JButton btnFragebogenSpeichern = new JButton("Fragebogen Speichern");
		panel_1.add(btnFragebogenSpeichern);
		btnFragebogenSpeichern.setMargin(new Insets(0, 0, 0, 0));
		btnFragebogenSpeichern.setPreferredSize(new Dimension(200, 50));
		btnFragebogenSpeichern.setForeground(Color.WHITE);
		btnFragebogenSpeichern.setFont(new Font("Helvetica", Font.BOLD, 10));
		btnFragebogenSpeichern.setBackground(new Color(0x17748F));
		btnFragebogenSpeichern.setOpaque(true);
		btnFragebogenSpeichern.setBorder(new LineBorder(new Color(0x17748F), 7));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(1);
		panel_1.add(horizontalStrut_1);
		
		JButton btnUmfrageStarten = new JButton("Umfrage Starten");
		panel_1.add(btnUmfrageStarten);
		btnUmfrageStarten.setMargin(new Insets(0, 0, 0, 0));
		btnUmfrageStarten.setPreferredSize(new Dimension(200, 50));
		btnUmfrageStarten.setForeground(Color.WHITE);
		btnUmfrageStarten.setBackground(new Color(0x17748F));
		btnUmfrageStarten.setOpaque(true);
		btnUmfrageStarten.setBorder(new LineBorder(new Color(0x17748F), 7));
		btnUmfrageStarten.setFont(new Font("Helvetica", Font.BOLD, 10));
		btnUmfrageStarten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(FeebaCore.currentSurvey!=null){
				EditorController.startSurvey();}
	        	else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}

				
			}
		});
		btnFragebogenSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				saveFileChoser();
			}

		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				openFileChooser();
				
			}

		});
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(Color.WHITE, 12));
		tabbedPane.setBackground(null);
	
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel preview = new JPanel();
		preview.setBorder(null);
		preview.setBackground(null);
		tabbedPane.addTab("Vorschau", null, preview, null);
		SpringLayout sl_preview = new SpringLayout();
		preview.setLayout(sl_preview);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(null);
		sl_preview.putConstraint(SpringLayout.NORTH, layeredPane, 0, SpringLayout.NORTH, preview);
		sl_preview.putConstraint(SpringLayout.WEST, layeredPane, 0, SpringLayout.WEST, preview);
		sl_preview.putConstraint(SpringLayout.SOUTH, layeredPane, 0, SpringLayout.SOUTH, preview);
		sl_preview.putConstraint(SpringLayout.EAST, layeredPane, 0, SpringLayout.EAST, preview);
		preview.add(layeredPane);
		SpringLayout sl_layeredPane = new SpringLayout();
		layeredPane.setLayout(sl_layeredPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		sl_layeredPane.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.NORTH, layeredPane);
		sl_layeredPane.putConstraint(SpringLayout.WEST, panel_3, 0, SpringLayout.WEST, layeredPane);
		sl_layeredPane.putConstraint(SpringLayout.SOUTH, panel_3, 444, SpringLayout.NORTH, layeredPane);
		sl_layeredPane.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, layeredPane);
		panel_3.setBackground(null);
		layeredPane.add(panel_3);
		SpringLayout sl_panel_3 = new SpringLayout();
		panel_3.setLayout(sl_panel_3);
		
		final JLabel questionName = new JLabel("");
		questionName.setFont(new Font("Helvetica", Font.PLAIN, 30));
		questionName.setForeground(Color.WHITE);
		sl_panel_3.putConstraint(SpringLayout.NORTH, questionName, 103, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, questionName, 0, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, questionName, 0, SpringLayout.EAST, panel_3);
		questionName.setHorizontalAlignment(SwingConstants.CENTER);
		questionName.setBackground(null);
		panel_3.add(questionName);
		
		final JLabel questionText = new JLabel("");
		questionText.setBackground(null);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, questionText, 100, SpringLayout.SOUTH, questionName);
		questionText.setFont(new Font("Helvetica", Font.PLAIN, 20));
		questionText.setForeground(Color.WHITE);
		questionText.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel_3.putConstraint(SpringLayout.WEST, questionText, 0, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, questionText, 0, SpringLayout.EAST, panel_3);
		panel_3.add(questionText);
		
		final JLabel questionChoices = new JLabel("");
		sl_panel_3.putConstraint(SpringLayout.NORTH, questionChoices, 50, SpringLayout.SOUTH, questionText);
		questionChoices.setBackground(null);
		questionChoices.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		questionChoices.setForeground(Color.WHITE);
		questionChoices.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel_3.putConstraint(SpringLayout.WEST, questionChoices, 0, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, questionChoices, 0, SpringLayout.EAST, panel_3);
		panel_3.add(questionChoices);
		
		backgroundPreview = new JLabel("");
		backgroundPreview.setBackground(null);
		sl_preview.putConstraint(SpringLayout.NORTH, backgroundPreview, 0, SpringLayout.NORTH, preview);
		sl_preview.putConstraint(SpringLayout.WEST, backgroundPreview, 0, SpringLayout.WEST, preview);
		sl_preview.putConstraint(SpringLayout.SOUTH, backgroundPreview, 0, SpringLayout.SOUTH, preview);
		sl_preview.putConstraint(SpringLayout.EAST, backgroundPreview, 0, SpringLayout.EAST, preview);
		preview.add(backgroundPreview);
		backgroundPreview.setAlignmentY(Component.TOP_ALIGNMENT);
		backgroundPreview.setIconTextGap(0);
		

		results = new JPanel();
		results.setEnabled(false);
		results.setBackground(Color.WHITE);
		tabbedPane.addTab("Auswertung", null, results, null);
		GridBagLayout gbl_results = new GridBagLayout();
		gbl_results.columnWidths = new int[]{0};
		gbl_results.rowHeights = new int[]{0};
		gbl_results.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_results.rowWeights = new double[]{Double.MIN_VALUE};
		results.setLayout(gbl_results);
		questionWrapper = Box.createVerticalBox();
		questionWrapper.setPreferredSize(new Dimension(200, 200));
		questionWrapper.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		questionWrapper.setAlignmentY(Component.TOP_ALIGNMENT);
		questionWrapper.setBorder(null);
		contentPane.add(questionWrapper, BorderLayout.WEST);
		
		questions = new JList();
		questions.setMinimumSize(new Dimension(200, 200));
		questions.setMaximumSize(new Dimension(200, 200));
		questions.setFont(new Font("Helvetica", Font.PLAIN, 15));
		questions.setSelectionBackground(new Color(0x17748F));
		questions.setBorder(new LineBorder(Color.WHITE, 8));
		questions.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				int selectedIndex = questions.getSelectedIndex();
				if(selectedIndex!=-1){
				fillPreviewFields(selectedIndex,questionName,questionText,questionChoices);
				fillEditFields(selectedIndex,questionNameEdit,questionTextEdit,questionTypeEdit);
				EditorController.generateChart(results,selectedIndex);}
				
			}

		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		questionWrapper.add(verticalStrut);
		questions.setPreferredSize(new Dimension(200, 10));
		
		JScrollPane questionScroller = new JScrollPane(questions);
		questionScroller.setBorder(null);
		questionWrapper.add(questionScroller);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(null);
		panel.setBackground(new Color(0x2D2F31));
		panel.setMaximumSize(new Dimension(32767, 30));
		panel.setPreferredSize(new Dimension(200, 30));
		panel.setSize(new Dimension(200, 40));
		questionWrapper.add(panel);
		
		addButton = new JButton("Hinzuf\u00FCgen");
		addButton.setVisible(false);
		addButton.setForeground(Color.WHITE);
		addButton.setBackground(FeebaCore.FEEBA_BLUE);
		addButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
		addButton.setOpaque(true);
		addButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		addButton.setMargin(new Insets(0, 0, 0, 0));
		addButton.setBorder(new LineBorder(FeebaCore.FEEBA_BLUE, 6));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Bitte geben Sie den Namen der Frage ein:\n",
	                    "Neue Frage",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    "");

	//If a string was returned, say so.
	if ((s != null) && (s.length() > 0)) {
	    
		
		EditorController.addQuestion(s);
		EditorController.initModel(questions);
		questions.setSelectedIndex(questions.getModel().getSize()-1);
		
	}

	//If you're here, the return value was null/empty.
			}
		});
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, addButton, 3, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, addButton, -3, SpringLayout.EAST, panel);
		panel.setLayout(sl_panel);
		panel.add(addButton);
		
		removeButton = new JButton("L\u00F6schen");
		removeButton.setVisible(false);
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int result = JOptionPane.showConfirmDialog(
					    null,
					    "Mšchten sie Frage "+(questions.getSelectedIndex()+1)+" wirklich lšschen?",
					    "",
					    JOptionPane.YES_NO_OPTION);
				
				if(result == 0) {
					
					removeQuestion();
					
				}
			}
		});
		
		removeButton.setForeground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.EAST, removeButton, -3, SpringLayout.WEST, addButton);
		removeButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
		removeButton.setOpaque(true);
		removeButton.setBackground(FeebaCore.FEEBA_BLUE);
		sl_panel.putConstraint(SpringLayout.SOUTH, removeButton, 0, SpringLayout.SOUTH, addButton);
		removeButton.setMargin(new Insets(0, 0, 0, 0));
		removeButton.setBorder(new LineBorder(FeebaCore.FEEBA_BLUE, 6));
		removeButton.setAlignmentY(1.0f);
		panel.add(removeButton);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		questionWrapper.add(verticalStrut_1);
		
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
		        EditorController.generateChart(results, questions.getSelectedIndex());}
		});
		resultOptions.add(chartTypeSelector);
		
		JLabel lblDiagrammtyp = new JLabel("Diagrammtyp:     ");
		sl_resultOptions.putConstraint(SpringLayout.NORTH, lblDiagrammtyp, 21, SpringLayout.NORTH, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.EAST, lblDiagrammtyp, -115, SpringLayout.EAST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.NORTH, chartTypeSelector, 50, SpringLayout.NORTH, lblDiagrammtyp);
		sl_resultOptions.putConstraint(SpringLayout.WEST, lblDiagrammtyp, 0, SpringLayout.WEST, chartTypeSelector);
		lblDiagrammtyp.setOpaque(true);
		lblDiagrammtyp.setForeground(Color.WHITE);
		lblDiagrammtyp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblDiagrammtyp.setBorder(new LineBorder(UICOLOR, 7));
		lblDiagrammtyp.setBackground(new Color(23, 116, 143));
		resultOptions.add(lblDiagrammtyp);
		
		JButton btnNewButton_1 = new JButton("Daten zur\u00FCcksetzen");
		sl_resultOptions.putConstraint(SpringLayout.WEST, btnNewButton_1, 10, SpringLayout.WEST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.EAST, btnNewButton_1, -10, SpringLayout.EAST, resultOptions);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EditorController.resetResults(questions.getSelectedIndex());
				EditorController.generateChart(results, questions.getSelectedIndex());
			}
		});
		resultOptions.add(btnNewButton_1);
		
		JLabel lblDiagrammaktionen = new JLabel("Diagrammaktionen:     ");
		sl_resultOptions.putConstraint(SpringLayout.EAST, lblDiagrammaktionen, -80, SpringLayout.EAST, resultOptions);
		sl_resultOptions.putConstraint(SpringLayout.NORTH, btnNewButton_1, 50, SpringLayout.NORTH, lblDiagrammaktionen);
		sl_resultOptions.putConstraint(SpringLayout.NORTH, lblDiagrammaktionen, 50, SpringLayout.NORTH, chartTypeSelector);
		sl_resultOptions.putConstraint(SpringLayout.WEST, lblDiagrammaktionen, 0, SpringLayout.WEST, chartTypeSelector);
		lblDiagrammaktionen.setOpaque(true);
		lblDiagrammaktionen.setForeground(Color.WHITE);
		lblDiagrammaktionen.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblDiagrammaktionen.setBorder(new LineBorder(UICOLOR, 7));
		lblDiagrammaktionen.setBackground(new Color(23, 116, 143));
		resultOptions.add(lblDiagrammaktionen);
		
		JButton btnDiagrammAlsBild = new JButton("Diagramm als Bild speichern...");
		sl_resultOptions.putConstraint(SpringLayout.NORTH, btnDiagrammAlsBild, 40, SpringLayout.NORTH, btnNewButton_1);
		sl_resultOptions.putConstraint(SpringLayout.EAST, btnDiagrammAlsBild, -5, SpringLayout.EAST, resultOptions);
		btnDiagrammAlsBild.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				EditorController.saveChartImage((JLabel) results.getComponents()[0], questions.getSelectedIndex());
			}
		});
		sl_resultOptions.putConstraint(SpringLayout.WEST, btnDiagrammAlsBild, 0, SpringLayout.WEST, chartTypeSelector);
		resultOptions.add(btnDiagrammAlsBild);
		
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
		    	 FeebaCore.currentSurvey.getQuestions().get(questions.getSelectedIndex()).changeQuestionType((QuestionType)questionTypeEdit.getSelectedItem());
			     toggleChoices();
			     fillPreviewFields(questions.getSelectedIndex(),questionName,questionText,questionChoices);}
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
		
		JLabel lblFrage = new JLabel("Name:    ");
		sl_previewOptions.putConstraint(SpringLayout.WEST, lblFrage, 0, SpringLayout.WEST, questionType);
		lblFrage.setOpaque(true);
		lblFrage.setBackground(UICOLOR);
		lblFrage.setBorder(new LineBorder(UICOLOR, 7));
		lblFrage.setForeground(Color.WHITE);
		lblFrage.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_previewOptions.putConstraint(SpringLayout.NORTH, lblFrage, 10, SpringLayout.SOUTH, questionTypeEdit);
		previewOptions.add(lblFrage);
		
		questionNameEdit = new JTextField();
		sl_previewOptions.putConstraint(SpringLayout.NORTH, questionNameEdit, 10, SpringLayout.SOUTH, lblFrage);
		sl_previewOptions.putConstraint(SpringLayout.WEST, questionNameEdit, 0, SpringLayout.WEST, questionTypeEdit);
		sl_previewOptions.putConstraint(SpringLayout.EAST, questionNameEdit, 0, SpringLayout.EAST, questionTypeEdit);
		questionNameEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int selectedIndex = questions.getSelectedIndex();
				FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setName(questionNameEdit.getText().toString());
				fillPreviewFields(selectedIndex,questionName,questionText,questionChoices);
				
				
			}
		});
		questionNameEdit.setForeground(Color.WHITE);
		questionNameEdit.setFont(new Font("Helvetica", Font.PLAIN, 20));
		questionNameEdit.setBackground(Color.LIGHT_GRAY);
		questionNameEdit.setBorder(new LineBorder(Color.LIGHT_GRAY, 7));
		previewOptions.add(questionNameEdit);
		questionNameEdit.setColumns(10);
		
		JLabel choicesLbl = new JLabel("Frage:    ");
		sl_previewOptions.putConstraint(SpringLayout.NORTH, choicesLbl, 10, SpringLayout.SOUTH, questionNameEdit);
		choicesLbl.setOpaque(true);
		choicesLbl.setBackground(UICOLOR);
		choicesLbl.setBorder(new LineBorder(UICOLOR, 7));
		choicesLbl.setForeground(Color.WHITE);
		choicesLbl.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_previewOptions.putConstraint(SpringLayout.WEST, choicesLbl, 10, SpringLayout.WEST, previewOptions);
		previewOptions.add(choicesLbl);
		
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
		sl_previewOptions.putConstraint(SpringLayout.NORTH, questionTextEdit, 10, SpringLayout.SOUTH, choicesLbl);
		sl_previewOptions.putConstraint(SpringLayout.WEST, questionTextEdit, 0, SpringLayout.WEST, questionTypeEdit);
		sl_previewOptions.putConstraint(SpringLayout.EAST, questionTextEdit, 0, SpringLayout.EAST, questionTypeEdit);
		questionTextEdit.setLineWrap(true);
		questionTextEdit.setWrapStyleWord(true);
		sl_previewOptions.putConstraint(SpringLayout.EAST, choicesEdit, 0, SpringLayout.EAST, questionTextEdit);
		questionTextEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int selectedIndex = questions.getSelectedIndex();
				FeebaCore.currentSurvey.getQuestions().get(selectedIndex).setQuestionText(questionTextEdit.getText().toString());
				fillPreviewFields(selectedIndex,questionName,questionText,questionChoices);
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
		        	EditorController.generateChart(results,questions.getSelectedIndex());}
		        	else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}
		        }
		      }
		    };
		    
		tabbedPane.addChangeListener(changeListener);
		editFields = new JTextField[] {fieldA,fieldB,fieldC,fieldD,fieldE,fieldF,fieldG,fieldH};
		ChoicesChangedAdapter cca = new ChoicesChangedAdapter(questions, editFields, questionName, questionText, questionChoices);
		for(int i = 0; i < editFields.length ; i++) {
			
			editFields[i].addKeyListener(cca);
			
		}
		
	}

	protected void removeQuestion() {
		
		FeebaCore.currentSurvey.removeQuestionAt(questions.getSelectedIndex());
		EditorController.initModel(questions);
		questions.setSelectedIndex(0);
		
	}

	public static void openFileChooser() {
		
		final JFileChooser chooser = new JFileChooser("Fragebogen laden"); 
        chooser.setDialogType(JFileChooser.OPEN_DIALOG); 
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
        
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
              if (f.isDirectory()) return true;
              return f.getName().toLowerCase().endsWith(".feeba");
            }
            public String getDescription () { return "Feeba Fragebšgen (*.feeba)"; }  
          });
          chooser.setMultiSelectionEnabled(false);

        chooser.setVisible(true); 
        
        final int result = chooser.showOpenDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            tabbedPane.setVisible(true);
            questionWrapper.setVisible(true);
            File inputFile = chooser.getSelectedFile(); 
            String inputDir = inputFile.getPath();
            EditorController.loadSurvey(inputDir,questions,backgroundPreview);
            previewOptions.setVisible(true);
            removeButton.setVisible(true);
            addButton.setVisible(true);
            questions.requestFocus();
            
        } 
			
	}

	private void saveFileChoser() {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(FeebaCore.currentSurvey.getName()+".feeba"));
        chooser.setDialogTitle("Speichern unter...");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                return f.getName().toLowerCase().endsWith(".feeba");
            }

            public String getDescription() {
                return "Feeba Fragebogen (*.feeba)";
            }
        });
        
      chooser.setVisible(true); 
        
        final int result = chooser.showSaveDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            File saveFile = chooser.getSelectedFile(); 
            String saveDir = saveFile.getPath(); 
            EditorController.saveSurvey(saveDir);
            
        } 
		
	}
	
	
	public static void fillPreviewFields(int selectedIndex, JLabel name, JLabel text, JLabel answers) {
		
		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
		name.setText("Frage " + (questions.getSelectedIndex()+1) + " - " + ques.getName());
		text.setText(ques.getQuestionText());
		answers.setText(ques.getChoicesText());
		
	}
	
	private void fillEditFields(int selectedIndex, JTextField questionNameEdit,
			JTextArea questionTextEdit, JComboBox questionTypeEdit) {

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

	
	
	private void fillChoices(Question ques) {
		
		if(ques.getType().equals(QuestionType.FREETEXT)) {
			return;
		}
	
		
		for (int i = 0; i< ques.getChoices().size();i++)
		{
			editFields[i].setText(ques.getChoices().get(i));
			editFields[i].setVisible(true);
		}
		
	}

	public void toggleChoices() {

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
}
