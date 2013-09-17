package com.feeba.wizzard;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class QuestionType extends JFrame {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                    QuestionType frame = new QuestionType();
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
    public QuestionType() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 790, 589);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(0x17748F));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
           
            JButton btnFinished = new JButton("Fertig");
            btnFinished.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                    }
            });
            btnFinished.setBounds(675, 373, 89, 23);
            contentPane.add(btnFinished);
           
            JLabel lblNewLabel = new JLabel("New label");
            lblNewLabel.setIcon(new ImageIcon(QuestionType.class.getResource("/images/bars.png")));
            lblNewLabel.setBounds(0, 324, 796, 282);
            contentPane.add(lblNewLabel);
           
            JButton btnNextQuestion = new JButton("weiter");
            btnNextQuestion.addMouseListener(new MouseAdapter() {
                    //go to editor
                    public void mouseClicked(MouseEvent arg0) {
                    }
            });
            btnNextQuestion.setBounds(554, 373, 111, 23);
            contentPane.add(btnNextQuestion);
           
            JRadioButton rdbtnFreitext = new JRadioButton("Freitext");
            rdbtnFreitext.setFont(new Font("Tahoma", Font.BOLD, 16));
            rdbtnFreitext.setBackground(new Color(0x17748F));
            rdbtnFreitext.setBounds(305, 155, 109, 23);
            contentPane.add(rdbtnFreitext);
           
            JRadioButton rdbtnRadiobutton = new JRadioButton("Radiobutton");
            rdbtnRadiobutton.setFont(new Font("Tahoma", Font.BOLD, 16));
            rdbtnRadiobutton.setBackground(new Color(0x17748F));
            rdbtnRadiobutton.setBounds(305, 181, 146, 23);
            contentPane.add(rdbtnRadiobutton);
           
            JRadioButton rdbtnMehrfachauswahl = new JRadioButton("Mehrfachauswahl");
            rdbtnMehrfachauswahl.setFont(new Font("Tahoma", Font.BOLD, 16));
            rdbtnMehrfachauswahl.setBackground(new Color(0x17748F));
            rdbtnMehrfachauswahl.setBounds(305, 207, 179, 23);
            contentPane.add(rdbtnMehrfachauswahl);
           
            textField = new JTextField();
            textField.setBounds(305, 63, 214, 35);
            contentPane.add(textField);
            textField.setColumns(10);
           
            JTextPane txtpnName = new JTextPane();
            txtpnName.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnName.setEditable(false);
            txtpnName.setBackground(new Color(0x17748F));
            txtpnName.setText("Name:");
            txtpnName.setBounds(128, 63, 117, 35);
            contentPane.add(txtpnName);
           
            JTextPane txtpnArt = new JTextPane();
            txtpnArt.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnArt.setEditable(false);
            txtpnArt.setBackground(new Color(0x17748F));
            txtpnArt.setText("Art:");
            txtpnArt.setBounds(128, 155, 99, 20);
            contentPane.add(txtpnArt);
           
            JTextPane txtpnAnzahlDerAntworten = new JTextPane();
            txtpnAnzahlDerAntworten.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnAnzahlDerAntworten.setEditable(false);
            txtpnAnzahlDerAntworten.setBackground(new Color(0x17748F));
            txtpnAnzahlDerAntworten.setText("Anzahl der Antworten:");
            txtpnAnzahlDerAntworten.setBounds(128, 290, 120, 46);
            contentPane.add(txtpnAnzahlDerAntworten);
           
            JSlider slider = new JSlider();
            slider.setPaintTicks(true);
            slider.setSnapToTicks(true);
            slider.setPaintLabels(true);
            slider.setMaximum(8);
            slider.setMinimum(2);
            slider.setBounds(306, 290, 200, 23);
            contentPane.add(slider);
    }
    @SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
            component.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                    showMenu(e);
                            }
                    }
                    public void mouseReleased(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                    showMenu(e);
                            }
                    }
                    private void showMenu(MouseEvent e) {
                            popup.show(e.getComponent(), e.getX(), e.getY());
                    }
            });
    }
}
