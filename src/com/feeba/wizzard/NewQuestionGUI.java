package com.feeba.wizzard;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.feeba.core.FeebaCore;
import com.feeba.data.QuestionType;
import com.feeba.editor.EditorGUI;
import com.feeba.editor.components.FeebaButton;
import com.feeba.editor.components.FeebaTextArea;
import com.feeba.editor.components.FeebaTextField;

import javax.swing.JScrollPane;

public class NewQuestionGUI extends JFrame {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private FeebaTextField textFieldName;
    private FeebaTextField textFieldQuestion;
    private FeebaTextArea textFieldAnswers;
    private QuestionType type;
    private JRadioButton rdbtnMehrfachauswahl;
    private JRadioButton rdbtnFreitext;
    private JRadioButton rdbtnRadiobutton;
    private JLabel lblAnswers;
    private JScrollPane scrollPane;
    private FeebaButton btnNextQuestion;
    private FeebaButton btnFinished;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                    public void run() {
                            try {
                                    NewQuestionGUI frame = new NewQuestionGUI();
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
    public NewQuestionGUI() {
    	setTitle("Frage hinzuf\u00FCgen");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 821, 544);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(0x17748F));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
           
           
            textFieldName = new FeebaTextField();
            textFieldName.setBounds(305, 47, 381, 35);
            contentPane.add(textFieldName);
            textFieldName.setColumns(10);
           
            JLabel txtpnName = new JLabel();
            txtpnName.setOpaque(false);
            txtpnName.setForeground(Color.WHITE);
            txtpnName.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnName.setBackground(new Color(0x17748F));
            txtpnName.setText("Name:");
            txtpnName.setBounds(128, 47, 117, 35);
            contentPane.add(txtpnName);
           
            JLabel txtpnArt = new JLabel();
            txtpnArt.setOpaque(false);
            txtpnArt.setForeground(Color.WHITE);
            txtpnArt.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnArt.setBackground(new Color(0x17748F));
            txtpnArt.setText("Art:");
            txtpnArt.setBounds(128, 155, 99, 20);
            contentPane.add(txtpnArt);
           
            lblAnswers = new JLabel();
            lblAnswers.setOpaque(false);
            lblAnswers.setForeground(Color.WHITE);
            lblAnswers.setFont(new Font("Tahoma", Font.BOLD, 16));
            lblAnswers.setBackground(new Color(0x17748F));
            lblAnswers.setText("Antworten:");
            lblAnswers.setBounds(128, 259, 120, 46);
            contentPane.add(lblAnswers);
            
            JLabel txtpnFrage = new JLabel();
            txtpnFrage.setOpaque(false);
            txtpnFrage.setForeground(Color.WHITE);
            txtpnFrage.setBackground(new Color(0x17748F));
            txtpnFrage.setFont(new Font("Tahoma", Font.BOLD, 16));
            txtpnFrage.setText("Frage:");
            txtpnFrage.setBounds(128, 102, 117, 33);
            contentPane.add(txtpnFrage);
            
            textFieldQuestion = new FeebaTextField();
            textFieldQuestion.setBounds(305, 102, 381, 33);
            contentPane.add(textFieldQuestion);
            
            textFieldQuestion.setColumns(10);
            
            scrollPane = new JScrollPane();
            scrollPane.setBorder(null);
            scrollPane.setBounds(305, 260, 381, 90);
            contentPane.add(scrollPane);
            
            textFieldAnswers = new FeebaTextArea();
            scrollPane.setViewportView(textFieldAnswers);
            textFieldAnswers.setToolTipText("");
            textFieldAnswers.setColumns(10);
            
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setBounds(305, 149, 381, 100);
            contentPane.add(panel);
              panel.setLayout(null);
             
              rdbtnMehrfachauswahl = new JRadioButton("Mehrfachauswahl");
              rdbtnMehrfachauswahl.setBounds(0, 0, 327, 29);
              panel.add(rdbtnMehrfachauswahl);
              rdbtnMehrfachauswahl.setOpaque(false);
              rdbtnMehrfachauswahl.setForeground(Color.WHITE);
              rdbtnMehrfachauswahl.setFont(new Font("Tahoma", Font.BOLD, 16));
              rdbtnMehrfachauswahl.setBackground(new Color(0x17748F));
              rdbtnMehrfachauswahl.addActionListener(new ActionListener(){
            	    public void actionPerformed(ActionEvent e) {
            	      
            	    	scrollPane.setVisible(true);
            	    	lblAnswers.setVisible(true);
            	    	enableButtons(true);
            	    }
            	});
              
               rdbtnFreitext = new JRadioButton("Freitext");
               rdbtnFreitext.setBounds(0, 32, 256, 29);
               panel.add(rdbtnFreitext);
               rdbtnFreitext.setOpaque(false);
               rdbtnFreitext.setForeground(Color.WHITE);
               rdbtnFreitext.setFont(new Font("Tahoma", Font.BOLD, 16));
               rdbtnFreitext.setBackground(new Color(0x17748F));
               rdbtnFreitext.addActionListener(new ActionListener(){
           	    public void actionPerformed(ActionEvent e) {
           	      
           	    	scrollPane.setVisible(false);
        	    	lblAnswers.setVisible(false);
        	    	enableButtons(true);

           	    }
           	});
               
                rdbtnRadiobutton = new JRadioButton("Einfachauswahl");
                rdbtnRadiobutton.setBounds(0, 64, 256, 29);
                panel.add(rdbtnRadiobutton);
                rdbtnRadiobutton.setOpaque(false);
                rdbtnRadiobutton.setForeground(Color.WHITE);
                rdbtnRadiobutton.setFont(new Font("Tahoma", Font.BOLD, 16));
                rdbtnRadiobutton.setBackground(new Color(0x17748F));
                rdbtnRadiobutton.addActionListener(new ActionListener(){
               	    public void actionPerformed(ActionEvent e) {
               	      
               	    	scrollPane.setVisible(true);
            	    	lblAnswers.setVisible(true);
            	    	enableButtons(true);

               	    }
               	});
                
                ButtonGroup group = new ButtonGroup();
                group.add(rdbtnRadiobutton);
                group.add(rdbtnFreitext);
                group.add(rdbtnMehrfachauswahl);
                
                //QuestionType hier wohl ned aufrufbar - aber die Radiobuttonelemente konnt ich auch ned in meinem Wizzard aufrufe
                // Weil du ned einfach ne methode innerhalb einer methode machen kannst :D

                
                btnFinished = new FeebaButton("Fertig");
                btnFinished.setEnabled(false);
                btnFinished.isWizzardButton();
                btnFinished.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent arg0) {
                		
                		WizzardController.newQuestion(textFieldName.getText(), textFieldQuestion.getText(), getType(), WizzardController.answersToList(textFieldAnswers.getText()));
                		FeebaCore.currentSurvey = WizzardController.survey;
                		EditorGUI.main(null);
                		dispose();
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
               
                btnNextQuestion = new FeebaButton("Neue Frage");
                btnNextQuestion.setEnabled(false);
                btnNextQuestion.isWizzardButton();
                btnNextQuestion.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                        


                        		WizzardController.newQuestion(textFieldName.getText(), textFieldQuestion.getText(), getType(), WizzardController.answersToList(textFieldAnswers.getText()));

                        		textFieldName.setText(null);
                        		textFieldQuestion.setText(null);
                        		textFieldAnswers.setText(null);
                        		enableButtons(false);
                        }
                });
                btnNextQuestion.setBounds(535, 361, 111, 46);
                contentPane.add(btnNextQuestion);
                
                JLabel background = new JLabel("");
                background.setIcon(new ImageIcon(NewQuestionGUI.class.getResource("/images/Background.png")));
                background.setBounds(-61, -32, 882, 554);
                contentPane.add(background);
    }
    protected void enableButtons(boolean b) {
		
    	btnNextQuestion.setEnabled(b);
    	btnFinished.setEnabled(b);
		
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
    
    
    public QuestionType getType(){
		if(rdbtnMehrfachauswahl.isSelected() == true){
			type = QuestionType.MULTIPLE_CHOICE;
		} else if (rdbtnFreitext.isSelected() == true){
			type = QuestionType.FREETEXT;
		} else {
			type = QuestionType.SINGLE_SELECTION;
		}
		return type;
	}

}
