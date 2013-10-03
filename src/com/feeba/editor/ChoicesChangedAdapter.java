package com.feeba.editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class ChoicesChangedAdapter extends KeyAdapter {
	
	JList questions;
	JTextField[] inputs;
	JLabel questionName;
	JLabel questionText;
	JLabel questionChoices;
	
	
	public ChoicesChangedAdapter(JList questions, JTextField[] inputs, JLabel questionName, JLabel questionText, JLabel questionChoices) {
	
		super();
		this.questions = questions;
		this.inputs = inputs;
		this.questionName=questionName;
		this.questionText=questionText;
		this.questionChoices=questionChoices;
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		EditorController.updateChoices(questions.getSelectedIndex(), inputs);
		EditorGUI.fillPreviewFields(questions.getSelectedIndex(), questionName, questionText, questionChoices);
		
		
	}

}
