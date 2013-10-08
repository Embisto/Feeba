package com.feeba.editor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;

import com.feeba.core.FeebaCore;
import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaLabel;
import com.feeba.editor.components.FeebaTextArea;
import com.feeba.editor.components.FeebaTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditSurveyMetaGUI extends JFrame {

	private static final long serialVersionUID = -8582909092577791603L;
	private JPanel contentPane;
	private FeebaTextField nameEdit;
	private FeebaButton confirmButton;
	private FeebaTextArea infoTextArea;
	private FeebaButton abortButton;
	public static EditSurveyMetaGUI frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new EditSurveyMetaGUI();
					frame.setVisible(true);
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					    		EditorController.metaEditorIsOpen = false;
					    }
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditSurveyMetaGUI() {
		
		setTitle("Metadaten des Fragebogens");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 533, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		FeebaLabel lblNameDesFragebogens = new FeebaLabel("Name des Fragebogens:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNameDesFragebogens, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNameDesFragebogens, 30, SpringLayout.WEST, contentPane);
		contentPane.add(lblNameDesFragebogens);
		
		nameEdit = new FeebaTextField();
		nameEdit.setText(FeebaCore.currentSurvey.getName());
		sl_contentPane.putConstraint(SpringLayout.NORTH, nameEdit, 10, SpringLayout.SOUTH, lblNameDesFragebogens);
		sl_contentPane.putConstraint(SpringLayout.WEST, nameEdit, 30, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, nameEdit, -15, SpringLayout.EAST, contentPane);
		contentPane.add(nameEdit);
		
		FeebaLabel lblInfotextwirdDen = new FeebaLabel("Infotext (wird den Nutzern zu Beginn der Umfrage angezeigt) :");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblInfotextwirdDen, 18, SpringLayout.SOUTH, nameEdit);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblInfotextwirdDen, 0, SpringLayout.WEST, lblNameDesFragebogens);
		contentPane.add(lblInfotextwirdDen);
		
		infoTextArea = new FeebaTextArea();
		infoTextArea.setText(FeebaCore.currentSurvey.getWelcomeMessage());
		sl_contentPane.putConstraint(SpringLayout.NORTH, infoTextArea, 16, SpringLayout.SOUTH, lblInfotextwirdDen);
		sl_contentPane.putConstraint(SpringLayout.WEST, infoTextArea, 0, SpringLayout.WEST, lblNameDesFragebogens);
		sl_contentPane.putConstraint(SpringLayout.EAST, infoTextArea, 0, SpringLayout.EAST, nameEdit);
		contentPane.add(infoTextArea);
		
		confirmButton = new FeebaButton("Speichern");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, infoTextArea, -21, SpringLayout.NORTH, confirmButton);
		confirmButton.setFont(new Font("Helvetica", Font.BOLD, 16));
		contentPane.add(confirmButton);
		
		abortButton = new FeebaButton("Abbrechen");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, abortButton, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, confirmButton, 0, SpringLayout.NORTH, abortButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, confirmButton, -21, SpringLayout.WEST, abortButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, abortButton, 0, SpringLayout.EAST, nameEdit);
		abortButton.setFont(new Font("Helvetica", Font.BOLD, 16));
		contentPane.add(abortButton);
		
		initListeners();
		
	}

	private void initListeners() {
		
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			EditorController.updateMetaData(nameEdit.getText(),infoTextArea.getText());
			EditorController.metaEditorIsOpen = false;
			frame.dispose();
				
			}
		});
		
		abortButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	    	
				EditorController.metaEditorIsOpen = false;
				frame.dispose();
				
			}
		});
		
		
	}
}
