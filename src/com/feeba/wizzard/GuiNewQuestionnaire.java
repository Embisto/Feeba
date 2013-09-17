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

public class GuiNewQuestionnaire {
 
        private JFrame frame;
        private JTextField nameTextField;
 
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
               
               
                nameTextField = new JTextField();
                nameTextField.setBounds(203, 22, 166, 20);
                frame.getContentPane().add(nameTextField);
                nameTextField.setColumns(10);
               
                JButton newQuestionButton = new JButton("Neue Frage");
                newQuestionButton.addMouseListener(new MouseAdapter() {
                        //go to questiontype
                        public void mouseClicked(MouseEvent e) {
                                QuestionType.main(null);
                                }
                       
                });
                newQuestionButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                               
                        }
                });
                newQuestionButton.setBounds(501, 252, 115, 23);
                frame.getContentPane().add(newQuestionButton);
               
                JTextPane txtpnName = new JTextPane();
                txtpnName.setFont(new Font("Tahoma", Font.BOLD, 16));
                txtpnName.setEditable(false);
                txtpnName.setBackground(new Color(0x17748F));
                txtpnName.setText("Name:");
                txtpnName.setBounds(43, 22, 73, 20);
                frame.getContentPane().add(txtpnName);
               
                JTextPane txtpnInfoscreentext = new JTextPane();
                txtpnInfoscreentext.setFont(new Font("Tahoma", Font.BOLD, 16));
                txtpnInfoscreentext.setEditable(false);
                txtpnInfoscreentext.setBackground(new Color(0x17748F));
                txtpnInfoscreentext.setText("Infoscreentext:");
                txtpnInfoscreentext.setBounds(43, 95, 132, 20);
                frame.getContentPane().add(txtpnInfoscreentext);
               
                JLabel bars = new JLabel("");
                bars.setIcon(new ImageIcon(GuiNewQuestionnaire.class.getResource("/images/bars.png")));
                bars.setBounds(-13, 191, 728, 235);
                frame.getContentPane().add(bars);
               
                JTextPane textPane = new JTextPane();
                textPane.setBounds(205, 102, 275, 129);
                frame.getContentPane().add(textPane);
        }
}
