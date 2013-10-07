package com.feeba.editor.components;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.feeba.editor.EditorGUI;

public class CenterPanel extends JTabbedPane {

	private static final long serialVersionUID = 987813498021816429L;
	
	public CenterPanel(int tabPlacement) {
		
	initDefaultProperties(tabPlacement);	
		
	}

	private void initDefaultProperties(int tabPlacement) {
		
		setBorder(new LineBorder(Color.WHITE, 12));
		setBackground(null);
	    setTabPlacement(tabPlacement);
		
	}
	
	public void initListener() {
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        
		        EditorGUI.changeToTab(index);

		      }
		    };
		    
		addChangeListener(changeListener);
		
		
	}
	

}
