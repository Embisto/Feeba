package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class FeebaTextArea extends JTextArea {

	private static final long serialVersionUID = -5123128980027449970L;
	
	public FeebaTextArea() {
		
		initDefaultProperties();
		
	}

	private void initDefaultProperties() {
		
		setLineWrap(true);
		setWrapStyleWord(true);
		setForeground(Color.WHITE);
		setFont(new Font("Helvetica", Font.PLAIN, 16));
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 6));
		setRows(3);
		
	}
	

}
