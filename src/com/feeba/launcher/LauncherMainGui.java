package com.feeba.launcher;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.feeba.editor.EditorGUI;
import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaLoadingFilechooser;
import com.feeba.wizzard.NewSurveyGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LauncherMainGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LauncherMainGui frame = new LauncherMainGui();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public LauncherMainGui() {
		setBackground(new Color(0x17748F));
		setTitle("Feeba");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 450);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		FeebaButton loadButton = new FeebaButton("Umfrage laden");
		loadButton.isWizzardButton();
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditorGUI.main(null);
				FeebaLoadingFilechooser fc = new FeebaLoadingFilechooser("Fragebogen laden...");
				fc.show();
			}
		});
		loadButton.setBounds(287, 44, 281, 60);
		contentPane.add(loadButton);
		
		FeebaButton newSurveyButton = new FeebaButton("Neue Umfrage erstellen");
		newSurveyButton.isWizzardButton();
		newSurveyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewSurveyGUI.main(null);
			}
		});
		
		newSurveyButton.setBounds(106, 135, 281, 67);
		contentPane.add(newSurveyButton);
		
		
		lblNewLabel.setIcon(new ImageIcon(LauncherMainGui.class.getResource("/images/Background.png")));
		lblNewLabel.setBounds(0, -186, 643, 614);
		contentPane.add(lblNewLabel);
	}
}
