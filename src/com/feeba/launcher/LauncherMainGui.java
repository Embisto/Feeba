package com.feeba.launcher;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.feeba.editor.EditorGUI;
import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaLoadingFilechooser;
import com.feeba.wizzard.GuiNewQuestionnaire;
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
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditorGUI.main(null);
				FeebaLoadingFilechooser fc = new FeebaLoadingFilechooser("Fragebogen laden...");
				fc.show();
			}
		});
		loadButton.setBounds(325, 29, 281, 60);
		loadButton.setOpaque(false);
		contentPane.add(loadButton);
		
		FeebaButton questionsButton = new FeebaButton("Neue Umfrage erstellen");
		questionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuiNewQuestionnaire.main(null);
			}
		});
		questionsButton.setBounds(325, 100, 281, 67);
		questionsButton.setOpaque(false);
		contentPane.add(questionsButton);
		questionsButton.setFont(new Font("Manteka", Font.PLAIN, 25));
		
		
		lblNewLabel.setIcon(new ImageIcon(LauncherMainGui.class.getResource("/images/Background.png")));
		lblNewLabel.setBounds(10, -216, 643, 614);
		contentPane.add(lblNewLabel);
	}
}
