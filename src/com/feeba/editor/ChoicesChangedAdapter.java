package com.feeba.editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JList;
import javax.swing.JTextField;

import com.feeba.editor.components.PreviewPanel;

public class ChoicesChangedAdapter extends KeyAdapter {
	
	JList questions;
	JTextField[] inputs;
	PreviewPanel pp;
	
	
	public ChoicesChangedAdapter(JList questions, JTextField[] inputs, PreviewPanel pp) {
	
		super();
		this.questions = questions;
		this.inputs = inputs;
		this.pp = pp;
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		EditorController.updateChoices(questions.getSelectedIndex(), inputs);
		pp.fillPreviewFields(questions.getSelectedIndex());
		
		
	}

}
