package com.feeba.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.feeba.data.Question;
import com.feeba.data.Survey;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Component;

import javax.swing.Box;
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

import java.awt.FlowLayout;

import javax.swing.SpringLayout;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.feeba.data.QuestionType;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JList questions;
	public static JLabel backgroundPreview;
	static JTabbedPane tabbedPane;
	private JTextField questionNameEdit;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	public static JPanel editPanel;
	private JComboBox questionTypeEdit;
	private JTextArea questionTextEdit;
	private JPanel results;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//start editor maximized
					EditorGUI frame = new EditorGUI();
					frame.setState(Frame.NORMAL);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setPreferredSize(new Dimension(16, 60));
		toolBar.setOpaque(false);
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Fragebogen laden");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(new Color(0x17748F));
		btnNewButton.setBorder(new LineBorder(new Color(0x17748F), 7));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				openFileChooser();
			}

		});
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		toolBar.add(btnNewButton);
		
		JButton btnFragebogenSpeichern = new JButton("Fragebogen speichern");
		btnFragebogenSpeichern.setForeground(Color.WHITE);
		btnFragebogenSpeichern.setFont(new Font("Helvetica", Font.PLAIN, 20));
		btnFragebogenSpeichern.setBackground(new Color(0x17748F));
		btnFragebogenSpeichern.setOpaque(true);
		btnFragebogenSpeichern.setBorder(new LineBorder(new Color(0x17748F), 7));
		btnFragebogenSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				saveFileChoser();
			}

		});
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
	
		toolBar.add(btnFragebogenSpeichern);
		
		JButton btnUmfrageStarten = new JButton("Umfrage Starten");
		btnUmfrageStarten.setForeground(Color.WHITE);
		btnUmfrageStarten.setBackground(new Color(0x17748F));
		btnUmfrageStarten.setOpaque(true);
		btnUmfrageStarten.setBorder(new LineBorder(new Color(0x17748F), 7));
		btnUmfrageStarten.setFont(new Font("Helvetica", Font.PLAIN, 20));
		btnUmfrageStarten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EditorController.startSurvey();
				
			}
		});
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		toolBar.add(btnUmfrageStarten);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(Color.WHITE, 12));
		tabbedPane.setBackground(null);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        if(index ==1) {
		        	generateChart(results,questions.getSelectedIndex());
		        }
		      }
		    };
		    
		tabbedPane.addChangeListener(changeListener);
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
		questionChoices.setBackground(null);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, questionChoices, 80, SpringLayout.SOUTH, questionText);
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
		results.setBackground(Color.WHITE);
		tabbedPane.addTab("Auswertung", null, results, null);
		results.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Box questionWrapper = Box.createVerticalBox();
		contentPane.add(questionWrapper, BorderLayout.WEST);
		
		questions = new JList();
		questions.setBorder(new LineBorder(new Color(0, 0, 0)));
		questions.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				int selectedIndex = questions.getSelectedIndex();
				fillPreviewFields(selectedIndex,questionName,questionText,questionChoices);
				fillEditFields(selectedIndex,questionNameEdit,questionTextEdit,questionTypeEdit);
				
			}



		});
		questions.setDragEnabled(true);
		questions.setPreferredSize(new Dimension(200, 10));
		
		JScrollPane questionScroller = new JScrollPane(questions);
		questionScroller.setBorder(null);
		questionWrapper.add(questionScroller);
		
		JLabel lblQuestions = new JLabel("Fragen");
		lblQuestions.setForeground(Color.WHITE);
		lblQuestions.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));
		lblQuestions.setFont(new Font("Helvetica", Font.PLAIN, 20));
		lblQuestions.setOpaque(true);
		lblQuestions.setBackground(Color.LIGHT_GRAY);
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		questionScroller.setColumnHeaderView(lblQuestions);
		
		editPanel = new JPanel();
		editPanel.setBorder(new LineBorder(Color.WHITE, 10));
		editPanel.setBackground(Color.WHITE);
		editPanel.setPreferredSize(new Dimension(250, 10));
		contentPane.add(editPanel, BorderLayout.EAST);
		SpringLayout sl_editPanel = new SpringLayout();
		editPanel.setLayout(sl_editPanel);
		editPanel.setVisible(false);
		
		questionTypeEdit = new JComboBox();
		sl_editPanel.putConstraint(SpringLayout.EAST, questionTypeEdit, -14, SpringLayout.EAST, editPanel);
		questionTypeEdit.setModel(new DefaultComboBoxModel(QuestionType.values()));
		editPanel.add(questionTypeEdit);
		
		JLabel questionType = new JLabel("Fragetyp:");
		questionType.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_editPanel.putConstraint(SpringLayout.WEST, questionType, 10, SpringLayout.WEST, editPanel);
		sl_editPanel.putConstraint(SpringLayout.NORTH, questionTypeEdit, 6, SpringLayout.SOUTH, questionType);
		sl_editPanel.putConstraint(SpringLayout.WEST, questionTypeEdit, 10, SpringLayout.WEST, questionType);
		sl_editPanel.putConstraint(SpringLayout.NORTH, questionType, 10, SpringLayout.NORTH, editPanel);
		editPanel.add(questionType);
		
		JLabel lblFrage = new JLabel("Name:");
		sl_editPanel.putConstraint(SpringLayout.WEST, lblFrage, 9, SpringLayout.WEST, editPanel);
		lblFrage.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_editPanel.putConstraint(SpringLayout.NORTH, lblFrage, 10, SpringLayout.SOUTH, questionTypeEdit);
		editPanel.add(lblFrage);
		
		questionNameEdit = new JTextField();
		questionNameEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Survey survey = EditorController.loadedSurvey;
				int selectedIndex = questions.getSelectedIndex();
				survey.getQuestions().get(selectedIndex).setName(questionNameEdit.getText().toString());
				fillPreviewFields(selectedIndex,questionName,questionText,questionChoices);
				
			}
		});
		sl_editPanel.putConstraint(SpringLayout.WEST, questionNameEdit, 0, SpringLayout.WEST, questionType);
		sl_editPanel.putConstraint(SpringLayout.EAST, questionNameEdit, -10, SpringLayout.EAST, editPanel);
		questionNameEdit.setForeground(Color.WHITE);
		questionNameEdit.setFont(new Font("Helvetica", Font.PLAIN, 20));
		questionNameEdit.setBackground(Color.LIGHT_GRAY);
		questionNameEdit.setBorder(new LineBorder(Color.LIGHT_GRAY, 7));
		editPanel.add(questionNameEdit);
		questionNameEdit.setColumns(10);
		
		JLabel lblFrage_1 = new JLabel("Frage:");
		sl_editPanel.putConstraint(SpringLayout.SOUTH, questionNameEdit, -6, SpringLayout.NORTH, lblFrage_1);
		lblFrage_1.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_editPanel.putConstraint(SpringLayout.NORTH, lblFrage_1, 43, SpringLayout.SOUTH, lblFrage);
		sl_editPanel.putConstraint(SpringLayout.WEST, lblFrage_1, 10, SpringLayout.WEST, editPanel);
		editPanel.add(lblFrage_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		sl_editPanel.putConstraint(SpringLayout.WEST, panel_4, 0, SpringLayout.WEST, editPanel);
		sl_editPanel.putConstraint(SpringLayout.EAST, panel_4, 0, SpringLayout.EAST, editPanel);
		editPanel.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {40, 150, 0};
		gbl_panel_4.rowHeights = new int[]{23, 23, 23, 23, 23, 23, 23, 23,23};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		
		JLabel lblA = new JLabel("A:  ");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.anchor = GridBagConstraints.EAST;
		gbc_lblA.gridx = 0;
		gbc_lblA.gridy = 0;
		panel_4.add(lblA, gbc_lblA);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_5.setBorder(new LineBorder(new Color(192, 192, 192), 4));		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setForeground(Color.WHITE);
		textField_5.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 0;
		panel_4.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblB = new JLabel("B:  ");
		lblB.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.fill = GridBagConstraints.BOTH;
		gbc_lblB.insets = new Insets(0, 0, 5, 5);
		gbc_lblB.gridx = 0;
		gbc_lblB.gridy = 1;
		panel_4.add(lblB, gbc_lblB);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_8.setForeground(Color.WHITE);
		textField_8.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_8.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		textField_8.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.fill = GridBagConstraints.BOTH;
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 1;
		panel_4.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblC = new JLabel("C:  ");
		lblC.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblC = new GridBagConstraints();
		gbc_lblC.fill = GridBagConstraints.BOTH;
		gbc_lblC.insets = new Insets(0, 0, 5, 5);
		gbc_lblC.gridx = 0;
		gbc_lblC.gridy = 2;
		panel_4.add(lblC, gbc_lblC);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		textField_7.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_7.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		textField_7.setBackground(Color.LIGHT_GRAY);
		textField_7.setForeground(Color.WHITE);
		gbc_textField_7.fill = GridBagConstraints.BOTH;
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 2;
		panel_4.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblD = new JLabel("D:  ");
		lblD.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.fill = GridBagConstraints.BOTH;
		gbc_lblD.insets = new Insets(0, 0, 5, 5);
		gbc_lblD.gridx = 0;
		gbc_lblD.gridy = 3;
		panel_4.add(lblD, gbc_lblD);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		textField_6.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_6.setForeground(Color.WHITE);
		textField_6.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		textField_6.setBackground(Color.LIGHT_GRAY);
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 3;
		panel_4.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblE = new JLabel("E:  ");
		lblE.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblE = new GridBagConstraints();
		gbc_lblE.fill = GridBagConstraints.BOTH;
		gbc_lblE.insets = new Insets(0, 0, 5, 5);
		gbc_lblE.gridx = 0;
		gbc_lblE.gridy = 4;
		panel_4.add(lblE, gbc_lblE);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		textField_4.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_4.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setForeground(Color.WHITE);
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		panel_4.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblF = new JLabel("F:  ");
		lblF.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblF = new GridBagConstraints();
		gbc_lblF.fill = GridBagConstraints.BOTH;
		gbc_lblF.insets = new Insets(0, 0, 5, 5);
		gbc_lblF.gridx = 0;
		gbc_lblF.gridy = 5;
		panel_4.add(lblF, gbc_lblF);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		textField_3.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_3.setForeground(Color.WHITE);
		textField_3.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		textField_3.setBackground(Color.LIGHT_GRAY);
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 5;
		panel_4.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblG = new JLabel("G:  ");
		lblG.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblG = new GridBagConstraints();
		gbc_lblG.fill = GridBagConstraints.BOTH;
		gbc_lblG.insets = new Insets(0, 0, 5, 5);
		gbc_lblG.gridx = 0;
		gbc_lblG.gridy  = 6;
		panel_4.add(lblG, gbc_lblG);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		textField_1.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_1.setForeground(Color.WHITE);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 6;
		panel_4.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblH = new JLabel("H:  ");
		lblH.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblH = new GridBagConstraints();
		gbc_lblH.fill = GridBagConstraints.BOTH;
		gbc_lblH.insets = new Insets(0, 0, 5, 5);
		gbc_lblH.gridx = 0;
		gbc_lblH.gridy = 7;
		panel_4.add(lblH, gbc_lblH);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		textField_2.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setForeground(Color.WHITE);
		textField_2.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 7;
		panel_4.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("I: ");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.fill = GridBagConstraints.VERTICAL;
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 8;
		panel_4.add(label_2, gbc_label_2);
		
		
		JLabel lblAntwortmglichkeit = new JLabel("Antwortm\u00F6glichkeiten:");
		lblAntwortmglichkeit.setFont(new Font("Helvetica", Font.PLAIN, 15));
		sl_editPanel.putConstraint(SpringLayout.WEST, lblAntwortmglichkeit, 10, SpringLayout.WEST, editPanel);
		sl_editPanel.putConstraint(SpringLayout.NORTH, panel_4, 14, SpringLayout.SOUTH, lblAntwortmglichkeit);
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.BOTH;
		textField_9.setFont(new Font("Helvetica", Font.PLAIN, 20));
		textField_9.setBackground(Color.LIGHT_GRAY);
		textField_9.setForeground(Color.WHITE);
		textField_9.setBorder(new LineBorder(new Color(192, 192, 192), 4));
		gbc_textField_9.insets = new Insets(0, 0, 5, 0);
		gbc_textField_9.gridx = 1;
		gbc_textField_9.gridy = 8;
		panel_4.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		editPanel.add(lblAntwortmglichkeit);
		
		questionTextEdit = new JTextArea();
		questionTextEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Survey survey = EditorController.loadedSurvey;
				int selectedIndex = questions.getSelectedIndex();
				survey.getQuestions().get(selectedIndex).setQuestionText(questionTextEdit.getText().toString());
				fillPreviewFields(selectedIndex,questionName,questionText,questionChoices);
			}
		});
		questionTextEdit.setWrapStyleWord(true);
		questionTextEdit.setForeground(Color.WHITE);
		sl_editPanel.putConstraint(SpringLayout.NORTH, questionTextEdit, 6, SpringLayout.SOUTH, lblFrage_1);
		questionTextEdit.setFont(new Font("Helvetica", Font.PLAIN, 20));
		sl_editPanel.putConstraint(SpringLayout.NORTH, lblAntwortmglichkeit, 20, SpringLayout.SOUTH, questionTextEdit);
		sl_editPanel.putConstraint(SpringLayout.WEST, questionTextEdit, 0, SpringLayout.WEST, questionType);
		questionTextEdit.setBackground(Color.LIGHT_GRAY);
		sl_editPanel.putConstraint(SpringLayout.EAST, questionTextEdit, -10, SpringLayout.EAST, editPanel);
		questionTextEdit.setBorder(new LineBorder(Color.LIGHT_GRAY, 6));
		questionTextEdit.setRows(4);
		editPanel.add(questionTextEdit);
	}

	public static void openFileChooser() {
		
		final JFileChooser chooser = new JFileChooser("Fragebogen laden"); 
        chooser.setDialogType(JFileChooser.OPEN_DIALOG); 
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
        
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
              if (f.isDirectory()) return false;
              return f.getName().toLowerCase().endsWith(".feeba");
            }
            public String getDescription () { return "Feeba Fragebšgen (*.feeba)"; }  
          });
          chooser.setMultiSelectionEnabled(false);

        chooser.setVisible(true); 
        
        final int result = chooser.showOpenDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            File inputFile = chooser.getSelectedFile(); 
            String inputDir = inputFile.getPath(); 
            EditorController.loadSurvey(inputDir,questions,backgroundPreview);
            editPanel.setVisible(true);
            
        } 
			
	}

	private void saveFileChoser() {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(EditorController.loadedSurvey.getName()+".feeba"));
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

	public static void showData(Survey loadedSurvey, JList list, JLabel backgroundLabel) {
		
		
		  
		try {
			backgroundLabel.setIcon(new ImageIcon(resize(ImageIO.read(EditorGUI.class.getResource("/images/Background.png")),backgroundLabel.getWidth()+50,backgroundLabel.getHeight())));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultListModel model = new DefaultListModel();
		int index = 1;
	    for(Question q : loadedSurvey.getQuestions()){
	         model.addElement("Frage " + (index++) +": " +q.getName());
	    } 
	    
	    list.setModel(model);     
	    list.setSelectedIndex(0);
		
		
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
	
	private void fillPreviewFields(int selectedIndex, JLabel name, JLabel text, JLabel answers) {
		
		Survey survey = EditorController.loadedSurvey;
		Question ques = survey.getQuestions().get(selectedIndex);
		name.setText("Frage " + (selectedIndex+1) +" - " + ques.getName());
		text.setText(ques.getQuestionText());
		answers.setText(ques.getChoicesText());
		
	}
	
	private void fillEditFields(int selectedIndex, JTextField questionNameEdit,
			JTextArea questionTextEdit, JComboBox questionTypeEdit) {
		
		Survey survey = EditorController.loadedSurvey;
		Question ques = survey.getQuestions().get(selectedIndex);
		questionNameEdit.setText(ques.getName());
		questionTextEdit.setText(ques.getQuestionText());
		questionTypeEdit.setSelectedItem(ques.getType());
		
	}

	private void generateChart(JPanel results, int selectedIndex) {
		
		String name = EditorController.loadedSurvey.getQuestions().get(selectedIndex).getQuestionText();
		
		results.removeAll();
		results.add(EditorController.pieChart(selectedIndex,name));
		
		
	}
}
