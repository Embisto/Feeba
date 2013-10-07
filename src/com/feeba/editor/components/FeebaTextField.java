package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FeebaTextField extends JTextField {

	private static final long serialVersionUID = -3340405150131225258L;
	
	public FeebaTextField() {
		
		initDefaultProperties();
		
	}

	private void initDefaultProperties() {

		setForeground(Color.WHITE);
		setFont(new Font("Helvetica", Font.PLAIN, 20));
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 7));
		setColumns(10);
		
	}
	
	public void isChoicesEdit() {
		
		setBorder(new LineBorder(Color.LIGHT_GRAY, 4));
		setColumns(8);
		
	}
	

}
