package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import com.feeba.data.Question;
import com.feeba.data.QuestionType;
import com.feeba.editor.EditorGUI;

public class PreviewOptions extends JPanel {

	private static final long serialVersionUID = -797503711326430429L;
	
	private SpringLayout layout;
	
	private JComboBox questionTypeEdit;
	private FeebaLabel questionType;
	private FeebaLabel lblChoices;
	private FeebaTextField questionNameEdit;
	private FeebaTextArea questionTextEdit;
	private ChoicesEdit choicesEdit;
	
	private static boolean listenerEnabled = true;

	
	public PreviewOptions() {
		
		initDefaultProperties();
		initQuestionTypeEdit();
		initNameEdit();
		initContentEdit();
		initChoicesEdit();
		
	}

	private void initDefaultProperties() {

		setVisible(false);
		setBorder(new LineBorder(Color.WHITE, 10));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(250, 70000));
		layout = new SpringLayout();
		setLayout(layout);
		
	}
	

	private void initQuestionTypeEdit() {
		
		questionTypeEdit = new JComboBox();
		layout.putConstraint(SpringLayout.EAST, questionTypeEdit, -14, SpringLayout.EAST, this);
		questionTypeEdit.setModel(new DefaultComboBoxModel(QuestionType.values()));
		questionTypeEdit.addItemListener(new ItemListener() {
		     @Override
		     public void itemStateChanged(ItemEvent e) {
		    	 if(listenerEnabled ) {
		    		 
		    		 EditorGUI.changeQuestionType((QuestionType)questionTypeEdit.getSelectedItem());
		    	 }
		     }
		 });
		
		add(questionTypeEdit);
		
		questionType = new FeebaLabel("Fragetyp:     ");
		layout.putConstraint(SpringLayout.WEST, questionType, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, questionTypeEdit, 6, SpringLayout.SOUTH, questionType);
		layout.putConstraint(SpringLayout.WEST, questionTypeEdit, 10, SpringLayout.WEST, questionType);
		layout.putConstraint(SpringLayout.NORTH, questionType, 10, SpringLayout.NORTH, this);
		add(questionType);
		
	}
	

	private void initNameEdit() {
		
		
		FeebaLabel lblQuestionName = new FeebaLabel("Name:    ");
		layout.putConstraint(SpringLayout.WEST, lblQuestionName, 0, SpringLayout.WEST, questionType);
		layout.putConstraint(SpringLayout.NORTH, lblQuestionName, 10, SpringLayout.SOUTH, questionTypeEdit);
		add(lblQuestionName);
		
		questionNameEdit = new FeebaTextField();
		layout.putConstraint(SpringLayout.NORTH, questionNameEdit, 10, SpringLayout.SOUTH, lblQuestionName);
		layout.putConstraint(SpringLayout.WEST, questionNameEdit, 0, SpringLayout.WEST, questionTypeEdit);
		layout.putConstraint(SpringLayout.EAST, questionNameEdit, 0, SpringLayout.EAST, questionTypeEdit);
		questionNameEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				EditorGUI.questionNameChanged(questionNameEdit.getText());
				
			}
		});

		add(questionNameEdit);
		
	}
	
	private void initContentEdit() {
		
		
		FeebaLabel lbQuestion = new FeebaLabel("Frage:    ");
		layout.putConstraint(SpringLayout.NORTH, lbQuestion, 10, SpringLayout.SOUTH, questionNameEdit);
		layout.putConstraint(SpringLayout.WEST, lbQuestion, 0, SpringLayout.WEST, questionType);
		add(lbQuestion);
		
		questionTextEdit = new FeebaTextArea();
		layout.putConstraint(SpringLayout.NORTH, questionTextEdit, 10, SpringLayout.SOUTH, lbQuestion);
		layout.putConstraint(SpringLayout.WEST, questionTextEdit, 0, SpringLayout.WEST, questionTypeEdit);
		layout.putConstraint(SpringLayout.EAST, questionTextEdit, 0, SpringLayout.EAST, questionTypeEdit);
		questionTextEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				EditorGUI.questionContentChanged(questionTextEdit.getText());
			}
		});
		

		add(questionTextEdit);
		
	}
	
	private void initChoicesEdit() {
		
		
		lblChoices = new FeebaLabel("Antwortm\u00F6glichkeiten:    ");
		layout.putConstraint(SpringLayout.NORTH, lblChoices, 6, SpringLayout.SOUTH, questionTextEdit);
		layout.putConstraint(SpringLayout.WEST, lblChoices, 10, SpringLayout.WEST, this);
		add(lblChoices);
		
		choicesEdit = new ChoicesEdit();
		layout.putConstraint(SpringLayout.NORTH, choicesEdit, 17, SpringLayout.SOUTH, lblChoices);
		layout.putConstraint(SpringLayout.WEST, choicesEdit, 0, SpringLayout.WEST, questionTypeEdit);
		add(choicesEdit);

		

	}

	public FeebaTextField[] getEditFields() {
		return choicesEdit.getEditFields();
	}

	public void fillEditFields(Question ques) {
		
		toggleListener();
		questionNameEdit.setText(ques.getName());
		questionTextEdit.setText(ques.getQuestionText());
		questionTypeEdit.setSelectedItem(ques.getType());
		toggleListener();
		
		toggleChoicesVisibilty();
		fillChoices(ques);
		
	}

	private void toggleListener() {
		
		listenerEnabled = !listenerEnabled;
		
	}
	
	public void toggleChoicesVisibilty() {

		if(questionTypeEdit.getSelectedItem().equals(QuestionType.FREETEXT)) {
			
			lblChoices.setVisible(false);
			choicesEdit.setVisible(false);
		}
		else {
			
			lblChoices.setVisible(true);
			choicesEdit.setVisible(true);
			
		}
		
	}
	
	private void fillChoices(Question ques) {
		
		resetChoices();
		
		if(ques.getType().equals(QuestionType.FREETEXT)) {
			return;
		}
	
		
		for (int i = 0; i< ques.getChoices().size();i++)
		{
			choicesEdit.getEditFields()[i].setText(ques.getChoices().get(i));
		}
		
 }

	private void resetChoices() {
		
		for (FeebaTextField field:choicesEdit.getEditFields()) {
			field.setText("");
			
		}
		
	}

	public void choicesChanged(int selectedIndex) {
		
		choicesEdit.updateChoices(selectedIndex);
		
	}

	public void initListeners() {
		
		choicesEdit.initListeners();
		
	}


}
