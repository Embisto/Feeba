package com.feeba.tools;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

import com.feeba.core.FeebaCore;

public class ImageTools {
	
    //http://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	public static void saveChartImage(JLabel label, int selectedIndex) {

		BufferedImage bi = new BufferedImage(label.getWidth(),label.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);


		Graphics2D g2 = bi.createGraphics();
		label.paint(g2);
		g2.dispose();
		
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(FeebaCore.currentSurvey.getName()+"_"+FeebaCore.currentSurvey.getQuestions().get(selectedIndex).getName()+"_Reults.png"));
        chooser.setDialogTitle("Speichern unter...");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.addChoosableFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                return f.getName().toLowerCase().endsWith(".feeba");
              }
              public String getDescription () { return "PNG (*.png)"; }  
            });
        
      chooser.setVisible(true); 
        
      final int result = chooser.showSaveDialog(null); 

        if (result == JFileChooser.APPROVE_OPTION) { 
            File saveFile = chooser.getSelectedFile(); 
            String saveDir = saveFile.getPath(); 
		try {
			ImageIO.write(bi, "png", new File(saveDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
		
	}
	
}
