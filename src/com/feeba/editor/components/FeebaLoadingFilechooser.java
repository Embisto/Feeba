package com.feeba.editor.components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.feeba.editor.EditorGUI;

public class FeebaLoadingFilechooser extends JFileChooser {

	private static final long serialVersionUID = 4364834707187854378L;
	
	public FeebaLoadingFilechooser(String s) {
		
		setDialogTitle(s);
		initFilter();
		initDefaultProperties();
		
	}	
	
	private void initDefaultProperties(){
	    setDialogType(JFileChooser.OPEN_DIALOG); 
	    setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
	    setMultiSelectionEnabled(false);
	    setVisible(true); 

	}

	private void initFilter(){
		
	    addChoosableFileFilter(new FileFilter() {
	        public boolean accept(File f) {
	          if (f.isDirectory()) return true;
	          return f.getName().toLowerCase().endsWith(".feeba");
	        }
	        public String getDescription () { return "Feeba Fragebšgen (*.feeba)"; }  
	      });
		
	}
    
	public void show() {
		
	    final int result = showOpenDialog(null); 

	    if (result == JFileChooser.APPROVE_OPTION) {
	    	
	        File inputFile = getSelectedFile(); 
	        String inputDir = inputFile.getPath();
	    	EditorGUI.finishLoadingFile(inputDir);
	      
	        
	    } 

	}

	

}
