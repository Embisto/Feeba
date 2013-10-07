package com.feeba.editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JList;
import javax.swing.JTextField;

import com.feeba.editor.components.PreviewOptions;
import com.feeba.editor.components.PreviewPanel;

public class ChoicesChangedAdapter extends KeyAdapter {
	
	JList questions;
	JTextField[] inputs;
	PreviewPanel pp;
	PreviewOptions po;
	
	
	public ChoicesChangedAdapter(JList questions, PreviewPanel pp, PreviewOptions po) {
	
		super();
		this.questions = questions;
		this.pp = pp;
		this.po = po;
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		po.choicesChanged(questions.getSelectedIndex());
		pp.fillPreviewFields(questions.getSelectedIndex());
		
		
	}

}
