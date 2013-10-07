package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.feeba.core.FeebaCore;

public class FeebaLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public FeebaLabel(String s) {
		
		setText(s);
		initDefaultProperties();
		
	}

	private void initDefaultProperties() {
		
		setOpaque(true);
		setForeground(Color.WHITE);
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		setBorder(new LineBorder(FeebaCore.FEEBA_BLUE, 7));
		setBackground(FeebaCore.FEEBA_BLUE);
		
	}

}
