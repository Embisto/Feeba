package com.feeba.editor.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.feeba.core.FeebaCore;
import com.feeba.data.Question;
import com.feeba.editor.ChoicesChangedAdapter;
import com.feeba.editor.EditorGUI;

public class ChoicesEdit extends JPanel {

	private static final long serialVersionUID = 3313842464221502440L;
	private FeebaTextField[] editFields = new FeebaTextField[8];

	public ChoicesEdit() {

		initDefaultProperties();
		initEditFieldLables();
		initTextFields();

	}

	private void initDefaultProperties() {

		setBackground(Color.WHITE);
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 150, 0 };
		layout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		layout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(layout);

	}

	private void initEditFieldLables() {

		for (int i = 0; i < 8; i++) {

			JLabel label = new JLabel((char) (65 + i) + " : ");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints ll = new GridBagConstraints();
			ll.insets = new Insets(0, 0, 5, 5);
			ll.anchor = GridBagConstraints.WEST;
			ll.gridx = 0;
			ll.gridy = i;
			add(label, ll);

		}

	}

	private void initTextFields() {
		
		

		for (int i = 0; i < 8; i++) {

			FeebaTextField field = new FeebaTextField();
			field.isChoicesEdit();

			GridBagConstraints fieldLayout = new GridBagConstraints();
			fieldLayout.fill = GridBagConstraints.BOTH;
			fieldLayout.insets = new Insets(0, 0, 5, 0);
			fieldLayout.gridx = 1;
			fieldLayout.gridy = i;
			add(field, fieldLayout);
			editFields[i] = field;

		}

	}

	public FeebaTextField[] getEditFields() {

		return editFields;
	}
	
	/**
	 * Updates choices to current question
	 * @param selectedIndex index of current question
	 */
	public void updateChoices(int selectedIndex) {
		
		int latestUsedIndex = 7;
		Question ques = FeebaCore.currentSurvey.getQuestions().get(selectedIndex);
		
		while(editFields[latestUsedIndex].getText().equals(""))
		{
			latestUsedIndex--;
		}
		
		ArrayList<String> newChoices = new ArrayList<String>();
		
		for (int i = 0; i < latestUsedIndex+1;i++) {
			
			newChoices.add(editFields[i].getText());
			
		}
		
		
		ques.setChoices(newChoices);
		
		
	}

	public void initListeners() {
		
		ChoicesChangedAdapter cca = new ChoicesChangedAdapter(EditorGUI.getQuestionList(), EditorGUI.getPreviewPanel(), EditorGUI.getPreviewOptionPanel());
		
		for(int i = 0; i < editFields.length; i++) {
			editFields[i].addKeyListener(cca);
		}
		
	}
}