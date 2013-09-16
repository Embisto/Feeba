package com.feeba.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.feeba.data.Question;
import com.feeba.data.Survey;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class EditorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JList list;
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//start editor maximized
					EditorGUI frame = new EditorGUI();
					frame.setState(Frame.NORMAL);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Dimension dimension = toolkit.getScreenSize();
					frame.setSize(dimension);
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
	public EditorGUI() {
		setTitle("Feeba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Fragebogen laden\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				openFileChooser();
			}

		});
		toolBar.add(btnNewButton);
		
		JButton btnFragebogenSpeichern = new JButton("Fragebogen Speichern");
		btnFragebogenSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				saveFileChoser();
			}

		});
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
	
		toolBar.add(btnFragebogenSpeichern);
		
		JButton btnUmfrageStarten = new JButton("Umfrage Starten");
		btnUmfrageStarten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EditorController.startSurvey();
				
			}
		});
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		toolBar.add(btnUmfrageStarten);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		label = new JLabel("");
		label.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		tabbedPane.addTab("Vorschau", null, label, null);
		
		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox, BorderLayout.WEST);
		

		
		
		Component verticalStrut_1 = Box.createVerticalStrut(16);
		verticalBox.add(verticalStrut_1);
		
		list = new JList();
		list.setDragEnabled(true);
		list.setPreferredSize(new Dimension(200, 10));
		
		JScrollPane scrollPane = new JScrollPane(list);
		verticalBox.add(scrollPane);
		
		JLabel lblQuestions = new JLabel("Questions");
		lblQuestions.setOpaque(true);
		lblQuestions.setBackground(Color.LIGHT_GRAY);
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblQuestions);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1, 65, 200, 0};
		gbl_panel.rowHeights = new int[]{16, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(200);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_horizontalStrut_2.gridx = 2;
		gbc_horizontalStrut_2.gridy = 0;
		panel.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JLabel lblBearbeiten = new JLabel("Bearbeiten");
		GridBagConstraints gbc_lblBearbeiten = new GridBagConstraints();
		gbc_lblBearbeiten.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBearbeiten.insets = new Insets(0, 0, 5, 0);
		gbc_lblBearbeiten.gridx = 2;
		gbc_lblBearbeiten.gridy = 1;
		panel.add(lblBearbeiten, gbc_lblBearbeiten);
	}

	public void openFileChooser() {
		
		final JFileChooser chooser = new JFileChooser("Fragebogen laden"); 
        chooser.setDialogType(JFileChooser.OPEN_DIALOG); 
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
        
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
              if (f.isDirectory()) return true;
              return f.getName().toLowerCase().endsWith(".feeba");
            }
            public String getDescription () { return "Feeba Fragebšgen (*.feeba)"; }  
          });
          chooser.setMultiSelectionEnabled(false);

        chooser.setVisible(true); 
        
        final int result = chooser.showOpenDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            File inputFile = chooser.getSelectedFile(); 
            String inputDir = inputFile.getPath(); 
            EditorController.loadSurvey(inputDir,list,label);
            
        } 
		
		
	}
	
	private void saveFileChoser() {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(EditorController.loadedSurvey.getName()+".feeba"));
        chooser.setDialogTitle("Speichern unter...");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                return f.getName().toLowerCase().endsWith(".feeba");
            }

            public String getDescription() {
                return "Feeba Fragebogen (*.feeba)";
            }
        });
        
      chooser.setVisible(true); 
        
        final int result = chooser.showSaveDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            File saveFile = chooser.getSelectedFile(); 
            String saveDir = saveFile.getPath(); 
            EditorController.saveSurvey(saveDir);
            
        } 
		
	}

	public static void showData(Survey loadedSurvey, JList list, JLabel backgroundLabel) {
		
		
		  
		try {
			backgroundLabel.setIcon(new ImageIcon(resize(ImageIO.read(EditorGUI.class.getResource("/images/Background.png")),backgroundLabel.getWidth(),backgroundLabel.getHeight())));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultListModel model = new DefaultListModel();
		int index = 1;
	    for(Question q : loadedSurvey.getQuestions()){
	         model.addElement("Frage " + (index++) +": " +q.getName());
	    } 
	    
	    list.setModel(model);     
	    list.setSelectedIndex(0);
		
		
	}
	
	//http://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
}
