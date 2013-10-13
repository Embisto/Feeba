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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.feeba.data.Question;
import com.feeba.data.QuestionType;
import com.feeba.editor.EditorGUI;
import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaTextField;

public class QuestionGUI extends JFrame {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private FeebaTextField textFieldName;
    private FeebaTextField textFieldQuestion;
    private FeebaTextField textFieldAnswers;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                    QuestionGUI frame = new QuestionGUI();
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
    public QuestionGUI() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 821, 544);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(0x17748F));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
           
           
            textFieldName = new FeebaTextField();
            textFieldName.setBounds(305, 47, 287, 35);
            contentPane.add(textFieldName);
            textFieldName.setColumns(10);
           
            JTextPane txtpnName = new JTextPane();
            txtpnName.setOpaque(false);
            txtpnName.setForeground(Color.WHITE);
            txtpnName.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnName.setEditable(false);
            txtpnName.setBackground(new Color(0x17748F));
            txtpnName.setText("Name:");
            txtpnName.setBounds(128, 47, 117, 35);
            contentPane.add(txtpnName);
           
            JTextPane txtpnArt = new JTextPane();
            txtpnArt.setOpaque(false);
            txtpnArt.setForeground(Color.WHITE);
            txtpnArt.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnArt.setEditable(false);
            txtpnArt.setBackground(new Color(0x17748F));
            txtpnArt.setText("Art:");
            txtpnArt.setBounds(128, 155, 99, 20);
            contentPane.add(txtpnArt);
           
            JTextPane txtpnAnzahlDerAntworten = new JTextPane();
            txtpnAnzahlDerAntworten.setOpaque(false);
            txtpnAnzahlDerAntworten.setForeground(Color.WHITE);
            txtpnAnzahlDerAntworten.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnAnzahlDerAntworten.setEditable(false);
            txtpnAnzahlDerAntworten.setBackground(new Color(0x17748F));
            txtpnAnzahlDerAntworten.setText("Antworten:");
            txtpnAnzahlDerAntworten.setBounds(128, 259, 120, 46);
            contentPane.add(txtpnAnzahlDerAntworten);
            
            JTextPane txtpnFrage = new JTextPane();
            txtpnFrage.setOpaque(false);
            txtpnFrage.setForeground(Color.WHITE);
            txtpnFrage.setBackground(new Color(0x17748F));
            txtpnFrage.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnFrage.setText("Frage:");
            txtpnFrage.setBounds(128, 102, 117, 33);
            contentPane.add(txtpnFrage);
            
            textFieldQuestion = new FeebaTextField();
            textFieldQuestion.setBounds(305, 102, 360, 33);
            contentPane.add(textFieldQuestion);
            
            textFieldQuestion.setColumns(10);
            
            textFieldAnswers = new FeebaTextField();
            textFieldAnswers.setBounds(305, 260, 381, 90);
            contentPane.add(textFieldAnswers);
            textFieldAnswers.setColumns(10);
            
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setBounds(305, 149, 381, 100);
            contentPane.add(panel);
              panel.setLayout(null);
             
              final JRadioButton rdbtnMehrfachauswahl = new JRadioButton("Mehrfachauswahl");
              rdbtnMehrfachauswahl.setBounds(0, 0, 327, 29);
              panel.add(rdbtnMehrfachauswahl);
              rdbtnMehrfachauswahl.setOpaque(false);
              rdbtnMehrfachauswahl.setForeground(Color.WHITE);
              rdbtnMehrfachauswahl.setFont(new Font("Tahoma", Font.BOLD, 16));
              rdbtnMehrfachauswahl.setBackground(new Color(0x17748F));
              
               final JRadioButton rdbtnFreitext = new JRadioButton("Freitext");
               rdbtnFreitext.setBounds(0, 32, 256, 29);
               panel.add(rdbtnFreitext);
               rdbtnFreitext.setOpaque(false);
               rdbtnFreitext.setForeground(Color.WHITE);
               rdbtnFreitext.setFont(new Font("Tahoma", Font.BOLD, 16));
               rdbtnFreitext.setBackground(new Color(0x17748F));
               
                JRadioButton rdbtnRadiobutton = new JRadioButton("Radiobutton");
                rdbtnRadiobutton.setBounds(0, 64, 256, 29);
                panel.add(rdbtnRadiobutton);
                rdbtnRadiobutton.setOpaque(false);
                rdbtnRadiobutton.setForeground(Color.WHITE);
                rdbtnRadiobutton.setFont(new Font("Tahoma", Font.BOLD, 16));
                rdbtnRadiobutton.setBackground(new Color(0x17748F));
                
                //Group the radio buttons. Dann kann immer nur eins ausgewählt werden. Oder mach es mit nem dropdown. Siehe PreviewOptions!
                //Da würd da Code vermutlich ewtas schöner werden, also das auslesen. Is auch irgendwo zu finden. Einfach dem selectionChanged Event folgen
//                ButtonGroup group = new ButtonGroup();
//                group.add(birdButton);
//                group.add(catButton);
//                group.add(dogButton);
//                group.add(rabbitButton);
//                group.add(pigButton);

                
                
                FeebaButton btnFinished = new FeebaButton("Fertig");
                btnFinished.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent arg0) {
                		
                		// Aulagern Code is doppelt :) 
                		// Den typ würd ich ned in WC speichern sondern einfach hier, beziehungsweise gelich die Dropdown benutzen
                		if(rdbtnMehrfachauswahl.isSelected() == true){
                			WizzardController.type = QuestionType.MULTIPLE_CHOICE;
                		} else if (rdbtnFreitext.isSelected() == true){
                			WizzardController.type = QuestionType.FREETEXT;
                		} else {
                			WizzardController.type = QuestionType.SINGLE_SELECTION;
                		}
                		/** WizzardController.question.setType(WizzardController.type);
                		WizzardController.question.setName(textFieldName.getText());
                		WizzardController.question.setQuestionText(textFieldQuestion.getText());
                		WizzardController.question.setResults(WizzardController.answersToList(textFieldAnswers.getText())); */
                		
                		WizzardController.setQuestionData(textFieldName.getText(), textFieldQuestion.getText(), WizzardController.type, WizzardController.answersToList(textFieldAnswers.getText()));
                	// HIER IST MIR NICHT SO GANZ KLAR; WIE ICH DIE FRAGEN IN MEIN WIZZARD-SURVEY ABSPEICHERN SOLL... UND OB DAS ‹BERHAUPT
                	// IN DIESER KLASSE HIER PASSIEREN SOLL
                	//	WizzardController.survey.addQuestion(WizzardController.question, questionArgs);56t6
                		EditorGUI.main(null);
                	// Hier müsste dann FeebaCore.currentSurevey auf WizzardController.survey gesetzt werden und dann in den editor geladen werden,
                    // dazu muss ich noch ne kleinigkeit ändern
                		
                	}
                });
                btnFinished.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                        }
                });
                btnFinished.setBounds(656, 361, 108, 46);
                contentPane.add(btnFinished);
               
                FeebaButton btnNextQuestion = new FeebaButton("Neue Frage");
                btnNextQuestion.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                        
                        		if(rdbtnMehrfachauswahl.isSelected() == true){
                        			WizzardController.type = QuestionType.MULTIPLE_CHOICE;
                        		} else if (rdbtnFreitext.isSelected() == true){
                        			WizzardController.type = QuestionType.FREETEXT;
                        		} else {
                        			WizzardController.type = QuestionType.SINGLE_SELECTION;
                        		}

                        		WizzardController.setQuestionData(textFieldName.getText(), textFieldQuestion.getText(), WizzardController.type, WizzardController.answersToList(textFieldAnswers.getText()));
                        	// HIER IST MIR NICHT SO GANZ KLAR; WIE ICH DIE FRAGEN IN MEIN WIZZARD-SURVEY ABSPEICHERN SOLL... UND OB DAS ‹BERHAUPT
                        	// IN DIESER KLASSE HIER PASSIEREN SOLL
                        	//	WizzardController.survey.addQuestion(WizzardController.question, questionArgs);
                        		EditorGUI.main(null); // brauchts hier ned
                        	QuestionGUI.main(null); // würd ich ned neu laden sondern einfach alle felder leeren sonst werdens 800 Felder
                        }
                });
                btnNextQuestion.setBounds(535, 361, 111, 46);
                contentPane.add(btnNextQuestion);
                
                JLabel label = new JLabel("");
                label.setIcon(new ImageIcon(QuestionGUI.class.getResource("/images/Background.png")));
                label.setBounds(10, -60, 785, 611);
                contentPane.add(label);
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
