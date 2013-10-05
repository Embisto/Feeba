package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FeebaButton extends JButton {

	private static final long serialVersionUID = 6851147994876943685L;
	
	
	public FeebaButton(String s) {
		
		this.setText(s);
		initDefaultButtonStyle();
		
	}
	
	public FeebaButton() {
		
		initDefaultButtonStyle();

		
	}
	
	protected void initDefaultButtonStyle() {
		
		
		setHorizontalTextPosition(SwingConstants.LEADING);
		setMargin(new Insets(0, 0, 0, 0));
		setForeground(Color.WHITE);
		setFont(new Font("Helvetica", Font.BOLD, 10));
		setOpaque(true);
		setBackground(new Color(0x17748F));
		setBorder(new LineBorder(new Color(0x17748F), 7));
		
		
	}
	
	public void isToolbarButton() {
		
		
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setAlignmentY(Component.TOP_ALIGNMENT);
		setPreferredSize(new Dimension(200, 50));
		
	}
	
	
	
}
