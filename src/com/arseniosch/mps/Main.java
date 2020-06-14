package com.arseniosch.mps;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.arseniosch.mps.ui.MPSHandler;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if("Windows".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
					        break;
					        }
					    }					
					JFrame frame = new MPSHandler();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
