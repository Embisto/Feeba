package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.feeba.core.FeebaCore;
import com.feeba.data.Question;
import com.feeba.editor.EditorGUI;
import com.feeba.tools.ImageTools;

public class PreviewPanel extends JPanel {

	private static final long serialVersionUID = 3035209089387085185L;
	
	private SpringLayout layout;
	private SpringLayout layoutLP;
	private SpringLayout layoutPP;
	
	private JLayeredPane layeredPane;
	private JPanel previewPanel;
	private JLabel backgroundPreview;
	
	private JLabel questionName;
	private JLabel questionText;
	private JLabel questionChoices;

	
	public PreviewPanel() {

		initDefaultProperties();
		initLayeredPane();
		addPreviewPanel();
		addLabels();
		addBackground();
	}
	
	private void initDefaultProperties() {
		
		setBorder(null);
		setBackground(null);
		layout = new SpringLayout();
		setLayout(layout);
		
	}

	private void initLayeredPane() {
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(null);
		layout.putConstraint(SpringLayout.NORTH, layeredPane, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, layeredPane, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, layeredPane, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, layeredPane, 0, SpringLayout.EAST, this);
		add(layeredPane);
		layoutLP = new SpringLayout();
		layeredPane.setLayout(layoutLP);
		
		
	}
	
	private void addPreviewPanel() {
		
		previewPanel = new JPanel();
		previewPanel.setOpaque(false);
		layoutLP.putConstraint(SpringLayout.NORTH, previewPanel, 0, SpringLayout.NORTH, layeredPane);
		layoutLP.putConstraint(SpringLayout.WEST, previewPanel, 0, SpringLayout.WEST, layeredPane);
		layoutLP.putConstraint(SpringLayout.SOUTH, previewPanel, 0, SpringLayout.SOUTH, layeredPane);
		layoutLP.putConstraint(SpringLayout.EAST, previewPanel, 0, SpringLayout.EAST, layeredPane);
		previewPanel.setBackground(null);
		layeredPane.add(previewPanel);
		layoutPP = new SpringLayout();
		previewPanel.setLayout(layoutPP);
		
		
	}
	private void addLabels() {
		
		questionName = new JLabel("");
		questionName.setFont(new Font("Helvetica", Font.PLAIN, 30));
		questionName.setForeground(Color.WHITE);
		layoutPP.putConstraint(SpringLayout.NORTH, questionName, 103, SpringLayout.NORTH, previewPanel);
		layoutPP.putConstraint(SpringLayout.WEST, questionName, 0, SpringLayout.WEST, previewPanel);
		layoutPP.putConstraint(SpringLayout.EAST, questionName, 0, SpringLayout.EAST, previewPanel);
		questionName.setHorizontalAlignment(SwingConstants.CENTER);
		questionName.setBackground(null);
		previewPanel.add(questionName);
		
		questionText = new JLabel("");
		questionText.setBackground(null);
		layoutPP.putConstraint(SpringLayout.SOUTH, questionText, 100, SpringLayout.SOUTH, questionName);
		questionText.setFont(new Font("Helvetica", Font.PLAIN, 20));
		questionText.setForeground(Color.WHITE);
		questionText.setHorizontalAlignment(SwingConstants.CENTER);
		layoutPP.putConstraint(SpringLayout.WEST, questionText, 0, SpringLayout.WEST, previewPanel);
		layoutPP.putConstraint(SpringLayout.EAST, questionText, 0, SpringLayout.EAST, previewPanel);
		previewPanel.add(questionText);
		
		questionChoices = new JLabel("");
		layoutPP.putConstraint(SpringLayout.NORTH, questionChoices, 50, SpringLayout.SOUTH, questionText);
		questionChoices.setBackground(null);
		questionChoices.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		questionChoices.setForeground(Color.WHITE);
		questionChoices.setHorizontalAlignment(SwingConstants.CENTER);
		layoutPP.putConstraint(SpringLayout.WEST, questionChoices, 0, SpringLayout.WEST, previewPanel);
		layoutPP.putConstraint(SpringLayout.EAST, questionChoices, 0, SpringLayout.EAST, previewPanel);
		previewPanel.add(questionChoices);
	}
	
	private void addBackground() {
	
		backgroundPreview = new JLabel("");
		backgroundPreview.setBackground(null);
		layoutPP.putConstraint(SpringLayout.NORTH, backgroundPreview, 0, SpringLayout.NORTH, previewPanel);
		layoutPP.putConstraint(SpringLayout.WEST, backgroundPreview, 0, SpringLayout.WEST, previewPanel);
		layoutPP.putConstraint(SpringLayout.SOUTH, backgroundPreview, 0, SpringLayout.SOUTH, previewPanel);
		layoutPP.putConstraint(SpringLayout.EAST, backgroundPreview, 0, SpringLayout.EAST, previewPanel);
		previewPanel.add(backgroundPreview);
		backgroundPreview.setAlignmentY(Component.TOP_ALIGNMENT);
		backgroundPreview.setIconTextGap(0);
		
	}
	
	
	public void fillPreviewFields(int selectedIndex) {
		
		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
		questionName.setText("Frage " + (selectedIndex+1) + " - " + ques.getName());
		questionText.setText(ques.getQuestionText());
		questionChoices.setText(ques.getChoicesText());
		
	}
	
	/**
	 * Updates background according to current size of panel
	 */
	public void updateBackground() {
		
		int height = backgroundPreview.getHeight();
		int width = backgroundPreview.getWidth();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(EditorGUI.class.getResource("/images/Background.png"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		ImageIcon icon = new ImageIcon(ImageTools.resize(image,width,height));
		
		backgroundPreview.setIcon(icon);
		
	}
	
	public JLabel getBackgroundPanel() {
		
		return backgroundPreview;
	}
	
	

}
