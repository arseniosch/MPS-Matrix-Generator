package com.arseniosch.mps.ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.arseniosch.mps.model.MPSModel;
import com.arseniosch.mps.util.FileTypeFilter;

public class Save {

	public void save(MPSModel MPS_Model, JEditorPane editorPane) {
		final JFileChooser fs = new JFileChooser();
		fs.setDialogTitle("Save the arrays");
		fs.setSelectedFile(new File(MPS_Model.getName()+".txt"));
		fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));		
		int result = fs.showSaveDialog(null);
		if(result==JFileChooser.APPROVE_OPTION) {
			File fi = fs.getSelectedFile();
			try {		
				FileWriter file_writer = new FileWriter(fi.getPath());	
				String s = editorPane.getText();
		        file_writer.write(s);
				file_writer.flush();
				file_writer.close();
				JOptionPane.showMessageDialog(null, "LP-Problem saved successfully!");			
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}			
		}
	}

}
