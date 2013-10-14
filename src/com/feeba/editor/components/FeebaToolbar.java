package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.feeba.core.FeebaCore;
import com.feeba.editor.EditorController;
import com.feeba.editor.EditorGUI;
import com.feeba.wizzard.NewSurveyGUI;

public class FeebaToolbar extends JPanel {

	private static final long serialVersionUID = -5429201372379307152L;
	private SpringLayout sl_toolBar;
	private JLabel feebaLogo;
	private FeebaButton loadSurveyButton;
	private FeebaButton saveSurveyButton;
	private FeebaButton startSurveyButton;
	private FeebaButton newSurveyButton;
	private FeebaButton editSurveyButton;
	
	public FeebaToolbar() {
		
		initDefaultStyle();
		addLogo();
		initButtons();
		initButtonListeners();
		
	}
	
	private void initDefaultStyle() {
		
		setBackground(new Color(0x222325));
		setAlignmentY(Component.CENTER_ALIGNMENT);
		setPreferredSize(new Dimension(16, 50));
		sl_toolBar = new SpringLayout();
		setLayout(sl_toolBar);
		
	}
	
	private void addLogo() {
		
		feebaLogo = new JLabel("");
		feebaLogo.setIcon(new ImageIcon(EditorGUI.class.getResource("/images/logo_toolbar.png")));
		add(feebaLogo);
		
		
	}
	
	private void initButtons() {
		

		JPanel buttons = new JPanel();
		sl_toolBar.putConstraint(SpringLayout.WEST, buttons, 0, SpringLayout.EAST, feebaLogo);
		sl_toolBar.putConstraint(SpringLayout.NORTH, buttons, 0, SpringLayout.NORTH, this);
		buttons.setOpaque(false);
		add(buttons);
		
		
		//createButtons
		loadSurveyButton = new FeebaButton("Fragebogen Laden");
		loadSurveyButton.isToolbarButton();
		saveSurveyButton = new FeebaButton("Fragebogen Speichern");
		saveSurveyButton.isToolbarButton();
		editSurveyButton = new FeebaButton("Metadaten Bearbeiten");
		editSurveyButton.isToolbarButton();
		startSurveyButton = new FeebaButton("Fragebogen Starten");
		startSurveyButton.isToolbarButton();
		newSurveyButton = new FeebaButton("Neuer Fragebogen");
		newSurveyButton.isToolbarButton();
		

		//addButtons
		buttons.add(loadSurveyButton);
		buttons.add(saveSurveyButton);
		buttons.add(editSurveyButton);
		buttons.add(newSurveyButton);
		buttons.add(startSurveyButton);

	}
	
	
	private void initButtonListeners() {
		
		startSurveyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(FeebaCore.currentSurvey!=null){
				EditorController.startSurvey();}
	        	else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}

				
			}
		});
		
		newSurveyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				NewSurveyGUI.main(null);

				
			}
		});
		
		editSurveyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(FeebaCore.currentSurvey!=null){
					EditorController.startMetaDataEditor();}
		        else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}				
				
			}

		});
		
		saveSurveyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
				if(FeebaCore.currentSurvey!=null){
					FeebaSaveFilechooser fc = new FeebaSaveFilechooser("Fragebogen speichern...");
					fc.show();
					}
		        else {JOptionPane.showMessageDialog(null, "Noch kein Fragebogen geladen!");}	

			}

		});
		loadSurveyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				FeebaLoadingFilechooser fc = new FeebaLoadingFilechooser("Fragebogen laden...");
				fc.show();
				
			}

		});
		
	}
	
	
	

}
