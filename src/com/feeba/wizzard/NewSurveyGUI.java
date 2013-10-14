package com.feeba.wizzard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaTextArea;
import com.feeba.editor.components.FeebaTextField;
import javax.swing.JScrollPane;

public class NewSurveyGUI {
 
        private JFrame frmNeuerFragebogen;
        private FeebaTextField nameTextField;
        private FeebaTextArea infoscreentextInput;
 
        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        NewSurveyGUI window = new NewSurveyGUI();
                                        window.frmNeuerFragebogen.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
 
        /**
         * Create the application.
         */
        public NewSurveyGUI() {
                initialize();
        }
 
        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frmNeuerFragebogen = new JFrame();
                frmNeuerFragebogen.setTitle("Neuer Fragebogen");
                frmNeuerFragebogen.getContentPane().setBackground(new Color(0x17748F));
                frmNeuerFragebogen.setBounds(100, 100, 655, 441);
                frmNeuerFragebogen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmNeuerFragebogen.getContentPane().setLayout(null);
               
               
                nameTextField = new FeebaTextField();
                nameTextField.setBounds(203, 22, 300, 35);
                frmNeuerFragebogen.getContentPane().add(nameTextField);
                nameTextField.setColumns(10);
                
                JLabel txtpnName = new JLabel();
                txtpnName.setForeground(Color.WHITE);
                txtpnName.setFont(new Font("Tahoma", Font.BOLD, 16));
                txtpnName.setOpaque(false);
                txtpnName.setText("Name:");
                txtpnName.setBounds(43, 22, 73, 20);
                frmNeuerFragebogen.getContentPane().add(txtpnName);
               
                JTextPane txtpnInfoscreentext = new JTextPane();
                txtpnInfoscreentext.setForeground(Color.WHITE);
                txtpnInfoscreentext.setEditable(false);
                txtpnInfoscreentext.setOpaque(false);
                txtpnInfoscreentext.setText("Infoscreentext:");
                txtpnInfoscreentext.setFont(new Font("Tahoma", Font.BOLD, 16));
                txtpnInfoscreentext.setBounds(43, 95, 132, 20);
                frmNeuerFragebogen.getContentPane().add(txtpnInfoscreentext);
                
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBorder(null);
                scrollPane.setBounds(203, 95, 300, 129);
                frmNeuerFragebogen.getContentPane().add(scrollPane);
               
                infoscreentextInput = new FeebaTextArea();
                scrollPane.setViewportView(infoscreentextInput);
                
                FeebaButton btnNewQuestion = new FeebaButton("Neue Frage");
                btnNewQuestion.isWizzardButton();
                btnNewQuestion.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent arg0) {
                		WizzardController.survey.setName(nameTextField.getText());
                		WizzardController.survey.setWelcomeMessage(infoscreentextInput.getText());
                        NewQuestionGUI.main(null);
                	}
                });
                
                btnNewQuestion.setBounds(493, 254, 110, 42);
                frmNeuerFragebogen.getContentPane().add(btnNewQuestion);
                
                JLabel lblBackground = new JLabel("");
                lblBackground.setIcon(new ImageIcon(NewSurveyGUI.class.getResource("/images/Background.png")));
                lblBackground.setBounds(0, -189, 671, 618);
                frmNeuerFragebogen.getContentPane().add(lblBackground);
               
               
               
        }
}
