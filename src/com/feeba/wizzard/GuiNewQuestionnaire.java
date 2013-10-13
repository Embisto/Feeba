package com.feeba.wizzard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.feeba.data.Question;
import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaTextField;

public class GuiNewQuestionnaire {
 
        private JFrame frame;
        private FeebaTextField nameTextField;
        private FeebaTextField infoscreentextInput;
 
        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        GuiNewQuestionnaire window = new GuiNewQuestionnaire();
                                        window.frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
 
        /**
         * Create the application.
         */
        public GuiNewQuestionnaire() {
                initialize();
        }
 
        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frame = new JFrame();
                frame.getContentPane().setBackground(new Color(0x17748F));
                frame.setBounds(100, 100, 655, 441);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(null);
               
               
                nameTextField = new FeebaTextField();
                nameTextField.setBounds(203, 22, 166, 20);
                frame.getContentPane().add(nameTextField);
                nameTextField.setColumns(10);
                
                JTextPane txtpnName = new JTextPane();
                txtpnName.setFont(new Font("Tahoma", Font.BOLD, 16));
                txtpnName.setEditable(false);
                txtpnName.setOpaque(false);
                txtpnName.setText("Name:");
                txtpnName.setBounds(43, 22, 73, 20);
                frame.getContentPane().add(txtpnName);
               
                JTextPane txtpnInfoscreentext = new JTextPane();
                txtpnInfoscreentext.setFont(new Font("Tahoma", Font.BOLD, 16));
                txtpnInfoscreentext.setEditable(false);
                txtpnInfoscreentext.setOpaque(false);
                txtpnInfoscreentext.setText("Infoscreentext:");
                txtpnInfoscreentext.setBounds(43, 95, 132, 20);
                frame.getContentPane().add(txtpnInfoscreentext);
               
                infoscreentextInput = new FeebaTextField();
                infoscreentextInput.setBounds(203, 95, 275, 129);
                frame.getContentPane().add(infoscreentextInput);
                
                FeebaButton btnNewQuestion = new FeebaButton("Neue Frage");
                btnNewQuestion.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent arg0) {
                		WizzardController.survey.setName(nameTextField.getText());
                		WizzardController.survey.setWelcomeMessage(infoscreentextInput.getText());
                     QuestionGUI.main(null);
                	}
                });
                btnNewQuestion.setBounds(493, 254, 110, 42);
                frame.getContentPane().add(btnNewQuestion);
                
                JLabel lblBackground = new JLabel("");
                lblBackground.setIcon(new ImageIcon(GuiNewQuestionnaire.class.getResource("/images/Background.png")));
                lblBackground.setBounds(0, -189, 639, 592);
                frame.getContentPane().add(lblBackground);
               
               
               
        }
}
