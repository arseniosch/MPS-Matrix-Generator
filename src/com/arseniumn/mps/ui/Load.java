package com.arseniumn.mps.ui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.arseniumn.mps.model.Column;
import com.arseniumn.mps.model.MPSModel;
import com.arseniumn.mps.model.RHS;
import com.arseniumn.mps.model.Row;
import com.arseniumn.mps.util.FileTypeFilter;

public class Load {

	private MPSModel MPS_Model;
	private String[] info;

	public Load(MPSModel anMPS_Model) {
		this.MPS_Model = anMPS_Model;
	}

	@SuppressWarnings("resource")
	public void load() throws IOException {
		String userDir = System.getProperty("user.home");
		JFileChooser fs = new JFileChooser(userDir +"/Desktop");
		fs.setDialogTitle("Load an MPS file");
		fs.setFileFilter(new FileTypeFilter(".mps", "MPS File"));
		int result = fs.showOpenDialog(null);
		File file = null;
		if(result == JFileChooser.APPROVE_OPTION) {
			try {
				file = fs.getSelectedFile();
				BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		        String line = buffReader.readLine();
		        while (line != null) {	     	
		        	if(line.contains("NAME")) {
		        		info = line.replaceAll("[()]|NAME", "").split("\\s+");
		        		/* The result using the method with streams below:
		        		* [ , blend, MIN] -> [ blend, MIN]
		        		*/
		        		removeNullElements();		        		
		        		MPS_Model.setName(info[0]);
		        		if(info.length>1) MPS_Model.setType(info[1]);
		        		else MPS_Model.setType("MIN");
			            line = buffReader.readLine();		        			        		
		        	}
		        	if(line.contains("ROWS")) {
		        		//Read next line
			            line = buffReader.readLine();
			            while(!line.contains("COLUMNS")) {
			        		info = line.split("\\s+");
			        		/* The result using the method with streams below:
			        		* [ , N, OBJ] -> [ N, OBJ]
			        		*/
			        		removeNullElements();		     
			        		this.MPS_Model.getRows().add(new Row(info[0],info[1]));
				            line = buffReader.readLine();
			            }
		        	}
		        	if(line.contains("COLUMNS")) {
		        		//Read next line
			            line = buffReader.readLine();
			            while(!line.contains("RHS")) {
			        		info = line.split("\\s+");
			        		/* The result using the method with streams below:
			        		* [ , N, OBJ] -> [ N, OBJ]
			        		*/
			        		removeNullElements();		        
			        		if(info.length==3) this.MPS_Model.getColumns().add(new Column(info[0], info[1], info[2]));			        		
			        		else if(info.length>3)this.MPS_Model.getColumns().add(new Column(info[0], info[1], info[2], info[3], info[4]));		        		
			        		line = buffReader.readLine();
			            }
		        	}
		        	if(line.contains("RHS")){
		        		//Read next line
			            line = buffReader.readLine();
			            while(!line.contains("ENDATA")) {
			        		info = line.split("\\s+");
			        		/* The result using the method with streams below:
			        		* [ , N, OBJ] -> [ N, OBJ]
			        		*/
			        		removeNullElements();		   
			        		if(info.length>3) {
			        			this.MPS_Model.getRHS().add(new RHS(info[0], info[1], info[2], info[3], info[4]));	
			        		}
			        		else {
			        			this.MPS_Model.getRHS().add(new RHS(info[0], info[1], info[2]));	
			        		}
			        		line = buffReader.readLine();
			            }
		        	}
		            line = buffReader.readLine();		        			        		
		        }
				JOptionPane.showMessageDialog(null, "MPS file has been loaded successfull!");
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}					
		}		
	}

	private void removeNullElements() {
		info = Arrays.stream(info)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);		
	}
}
