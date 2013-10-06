package com.feeba.editor.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import com.feeba.editor.EditorController;
import com.feeba.editor.EditorGUI;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class QuestionContainer extends JPanel {

	private static final long serialVersionUID = -5551689077708046263L;
	private JList questions;
	private FeebaButton addButton;
	private FeebaButton removeButton;

	public QuestionContainer() {
		setMaximumSize(new Dimension(200, 200));

		initDefaultProperties();
		addStrut(20);
		createQuestionList();
		addQuestionScroller();
		addButtonPanel();
		addStrut(20);

	}

	private void initDefaultProperties() {

		setOpaque(false);
		setPreferredSize(new Dimension(200, 200));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setAlignmentY(Component.TOP_ALIGNMENT);
		setBorder(null);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	}

	private void createQuestionList() {

		questions = new JList();
		questions.setMinimumSize(new Dimension(200, 200));
		questions.setMaximumSize(new Dimension(200, 200));
		questions.setPreferredSize(new Dimension(200, 10));
		questions.setFont(new Font("Helvetica", Font.PLAIN, 15));
		questions.setSelectionBackground(new Color(0x17748F));
		questions.setBorder(new LineBorder(Color.WHITE, 8));

		questions.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				int selectedIndex = questions.getSelectedIndex();
				if (selectedIndex != -1) {

					EditorGUI.selectedQuestionChanged();
				}

			}

		});

	}

	private void addStrut(int i) {

		Component verticalStrut = Box.createVerticalStrut(i);
		add(verticalStrut);
	}

	private void addQuestionScroller() {

		JScrollPane questionScroller = new JScrollPane(questions);
		questionScroller.setBorder(null);
		add(questionScroller);

	}

	private void addButtonPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setMaximumSize(new Dimension(200, 30));
		buttonPanel.setPreferredSize(new Dimension(200, 30));
		buttonPanel.setSize(new Dimension(200, 30));
		add(buttonPanel);

		addButton = new FeebaButton("Hinzuf\u00FCgen");
		addButton.isQuestionToolbarButton();
		addButton.setMinimumSize(new Dimension(0, 0));
		addButton.setVisible(false);

		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String s = (String) JOptionPane
						.showInputDialog(null,
								"Bitte geben Sie den Namen der Frage ein:\n",
								"Neue Frage", JOptionPane.PLAIN_MESSAGE, null,
								null, "");

				if ((s != null) && (s.length() > 0)) {

					EditorController.addQuestion(s);
					EditorController.initModel(questions);
					questions.setSelectedIndex(questions.getModel().getSize() - 1);

				}

			}
		});

		SpringLayout sl_panel = new SpringLayout();
		buttonPanel.setLayout(sl_panel);
		buttonPanel.add(addButton);
		
				removeButton = new FeebaButton("L\u00F6schen");
				removeButton.isQuestionToolbarButton();
				sl_panel.putConstraint(SpringLayout.NORTH, addButton, 0, SpringLayout.NORTH, removeButton);
				sl_panel.putConstraint(SpringLayout.WEST, addButton, 6, SpringLayout.EAST, removeButton);
				sl_panel.putConstraint(SpringLayout.NORTH, removeButton, 3, SpringLayout.NORTH, buttonPanel);
				sl_panel.putConstraint(SpringLayout.EAST, removeButton, -84, SpringLayout.EAST, buttonPanel);
				removeButton.setMinimumSize(new Dimension(0, 0));
				buttonPanel.add(removeButton);
				removeButton.setVisible(false);
				
						removeButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								int result = JOptionPane.showConfirmDialog(null,
										"Mšchten sie Frage "
												+ (questions.getSelectedIndex() + 1)
												+ " wirklich lšschen?", "",
												JOptionPane.YES_NO_OPTION);
				
								if (result == 0) {
				
									EditorGUI.removeQuestion();
				
								}
							}
						});

	}

	public int getSelectedIndex() {

		return questions.getSelectedIndex();

	}

	public JList getQuestionList() {

		return questions;
	}

	public void setButtonsVisible() {

		addButton.setVisible(true);
		removeButton.setVisible(true);

	}

}
