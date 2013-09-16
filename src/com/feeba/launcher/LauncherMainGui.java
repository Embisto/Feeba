package com.feeba.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import com.feeba.editor.EditorGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LauncherMainGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		contentPane.setBackground(new Color(0x17748F));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel loadButton = new JPanel();
		loadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditorGUI.main(null);
				EditorGUI.openFileChooser();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				enter(loadButton);
				}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit(loadButton);
			}
		});
		
		loadButton.setBackground(Color.WHITE);
		loadButton.setBounds(371, 28, 245, 40);
		contentPane.add(loadButton);
		
		JLabel loadButtonText = new JLabel("Umfrage laden");
		loadButtonText.setFont(new Font("Manteka", Font.PLAIN, 25));
		loadButton.add(loadButtonText);
		
		final JPanel newButton = new JPanel();
		newButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// add new action
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				enter(newButton);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit(newButton);
			}
		});
		
		newButton.setBackground(Color.WHITE);
		newButton.setBounds(237, 91, 379, 40);
		contentPane.add(newButton);
		
		JLabel newButtonText = new JLabel("Neue Umfrage erstellen");
		newButtonText.setFont(new Font("Manteka", Font.PLAIN, 25));
		newButton.add(newButtonText);
		
		JLabel Bars = new JLabel("");
		Bars.setBounds(0, 206, 1280, 229);
		contentPane.add(Bars);
		Bars.setIcon(new ImageIcon(LauncherMainGui.class.getResource("/images/bars.png")));
	}
	
	public void enter(JPanel panel) {
		
		panel.setBackground(Color.LIGHT_GRAY);
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
	    setCursor(cursor);
		
		
	}
	
	public void exit(JPanel panel) {
		
		panel.setBackground(Color.WHITE);
		Cursor cursor = Cursor.getDefaultCursor();
	    setCursor(cursor);
		
	}
	
	
}
