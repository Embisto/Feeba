package com.feeba.editor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.feeba.core.FeebaCore;
import com.feeba.data.Survey;

class ReorderListener extends MouseAdapter {

	   private JList list;
	   private int pressIndex = 0;
	   private int releaseIndex = 0;
	   private Survey survey = FeebaCore.currentSurvey;

	   public ReorderListener(JList list) {
	      if (!(list.getModel() instanceof DefaultListModel)) {
	         throw new IllegalArgumentException("List must have a DefaultListModel");
	      }
	      this.list = list;
	   }

	   @Override
	   public void mousePressed(MouseEvent e) {
	      pressIndex = list.locationToIndex(e.getPoint());
	   }

	   @Override
	   public void mouseReleased(MouseEvent e) {
	      releaseIndex = list.locationToIndex(e.getPoint());
	      if (releaseIndex != pressIndex && releaseIndex != -1) {
	         reorder();
	         EditorController.initModel(list);
	      }
	   }

	   @Override
	   public void mouseDragged(MouseEvent e) {
		   releaseIndex = list.locationToIndex(e.getPoint());
		      if (releaseIndex != pressIndex && releaseIndex != -1) {
		         reorder();
		      }
	      pressIndex = releaseIndex;      
	   }

	   private void reorder() {
	      DefaultListModel model = (DefaultListModel) list.getModel();
	      Object dragee = model.elementAt(pressIndex);
	      model.removeElementAt(pressIndex);
	      model.insertElementAt(dragee, releaseIndex);
	      survey.moveItemToPosition(pressIndex, releaseIndex);
	      EditorController.initModel(list);
	   }
	}