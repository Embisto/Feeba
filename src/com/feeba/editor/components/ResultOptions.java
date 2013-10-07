package com.feeba.editor.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.DefaultComboBoxModel;

import com.feeba.editor.EditorGUI;


public class ResultOptions extends JPanel {
	

	private static final long serialVersionUID = 7259317541176325144L;
	private SpringLayout layout;
	private JComboBox chartTypeSelector;
	private JButton resetDataButton;
	
	
	public ResultOptions() {
		
		initDefaultProperties();
		initChartTypeSelector();
		initResetDataButton();
		initSaveAsImageButton();
		initChartOptionLabel();

		
	}
	

	private void initDefaultProperties() {
		
		setVisible(false);
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(new Dimension(250, 400));
		setOpaque(false);
		layout = new SpringLayout();
		setLayout(layout);
			
		
	}
	
	private void initChartTypeSelector() {
		
		chartTypeSelector = new JComboBox();
		layout.putConstraint(SpringLayout.WEST, chartTypeSelector, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, chartTypeSelector, -52, SpringLayout.EAST, this);
		chartTypeSelector.setModel(new DefaultComboBoxModel(new String[] {"Kuchendiagramm", "Balkendiagramm", "Radardiagramm"}));
		chartTypeSelector.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        EditorGUI.generateChart();}
		});
		
		add(chartTypeSelector);
		
		FeebaLabel lblChartType = new FeebaLabel("Diagrammtyp:     ");
		layout.putConstraint(SpringLayout.NORTH, lblChartType, 21, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, lblChartType, -115, SpringLayout.EAST,this);
		layout.putConstraint(SpringLayout.NORTH, chartTypeSelector, 50, SpringLayout.NORTH, lblChartType);
		layout.putConstraint(SpringLayout.WEST, lblChartType, 0, SpringLayout.WEST, chartTypeSelector);
		
		add(lblChartType);
		
		
	}
	
	private void initResetDataButton() {
		
		resetDataButton = new JButton("Daten zur\u00FCcksetzen");
		layout.putConstraint(SpringLayout.WEST, resetDataButton, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, resetDataButton, -10, SpringLayout.EAST, this);
		resetDataButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EditorGUI.resetResults();
			}
		});
		
		add(resetDataButton);
		
	}
	
	private void initSaveAsImageButton() {
		
		JButton saveAsImageButton = new JButton("Diagramm als Bild speichern...");
		layout.putConstraint(SpringLayout.NORTH, saveAsImageButton, 40, SpringLayout.NORTH, resetDataButton);
		layout.putConstraint(SpringLayout.EAST, saveAsImageButton, -5, SpringLayout.EAST, this);
		saveAsImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				EditorGUI.saveChartImage();
			}
		});
		layout.putConstraint(SpringLayout.WEST, saveAsImageButton, 0, SpringLayout.WEST, chartTypeSelector);
		add(saveAsImageButton);
		
	}
	

	private void initChartOptionLabel() {
		
		FeebaLabel lblChartActions = new FeebaLabel("Diagrammaktionen:     ");
		layout.putConstraint(SpringLayout.EAST, lblChartActions, -80, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, resetDataButton, 50, SpringLayout.NORTH, lblChartActions);
		layout.putConstraint(SpringLayout.NORTH, lblChartActions, 50, SpringLayout.NORTH, chartTypeSelector);
		layout.putConstraint(SpringLayout.WEST, lblChartActions, 0, SpringLayout.WEST, chartTypeSelector);
		add(lblChartActions);
		
	}


	public JComboBox getChartTypeSelector() {
		
		return chartTypeSelector;
	}
	
	


	
	

	
	
	
	

}
