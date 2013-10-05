package com.feeba.editor.components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.feeba.core.FeebaCore;
import com.feeba.editor.EditorController;

public class FeebaSaveFilechooser extends JFileChooser {

	private static final long serialVersionUID = -8192869469747390837L;
	
	public FeebaSaveFilechooser (String s) {
		
		setDialogTitle(s);
		initFilter();
		initDefaultProperties();
		
	}	
	
	private void initDefaultProperties(){

		setSelectedFile(new File(FeebaCore.currentSurvey.getName()+".feeba"));
	    setDialogTitle("Speichern unter...");
	    setDialogType(JFileChooser.SAVE_DIALOG);
	    setVisible(true); 

	}

	private void initFilter(){
		
		addChoosableFileFilter(new FileFilter() {
	        public boolean accept(File f) {
	            if (f.isDirectory())
	                return true;
	            return f.getName().toLowerCase().endsWith(".feeba");
	        }

	        public String getDescription() {
	            return "Feeba Fragebogen (*.feeba)";
	        }
	    });
		
	}
    
	public void show() {
		
		 final int result = showSaveDialog(null); 

		    if (result == JFileChooser.APPROVE_OPTION) { 
		        File saveFile = getSelectedFile(); 
		        String saveDir = saveFile.getPath(); 
		        EditorController.saveSurvey(saveDir);
		        
		    } 
	}

}
