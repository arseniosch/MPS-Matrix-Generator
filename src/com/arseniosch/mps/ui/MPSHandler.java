package com.arseniosch.mps.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.arseniosch.mps.model.MPSModel;

@SuppressWarnings("serial")
public class MPSHandler extends JFrame {

	private JPanel contentPane;	
	private MPSModel MPS_Model;
	private JEditorPane editorPane;	

	public MPSHandler() throws IOException {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		this.setVisible(true);
		setTitle("MPSH");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		setBounds(100, 100, 1300, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadf = new JMenuItem("Open...                                       (F3)");
		mntmLoadf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				load();
			}
		});
		mntmLoadf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnFile.add(mntmLoadf);
		
		
		JMenuItem mntmSave = new JMenuItem("Save                                            (F5)");
		mntmSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				save();
			}
		});
		mntmSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit                                   (Shift+F6)");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		mntmExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnFile.add(mntmExit);
		
		JMenu mnNewMenu = new JMenu("Run");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmRun = new JMenuItem("Run                                      (Ctrl+S)");
		mntmRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				run();
			}
		});
		mntmRun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmRun.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons8_play_18px.png")));
		mnNewMenu.add(mntmRun);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34,40,44));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBorderPainted(false);
		contentPane.add(menuBar_1, BorderLayout.NORTH);
		JButton button_1 = new JButton("");
		menuBar_1.add(button_1);
		button_1.setMargin(new Insets(0, 3, 0, 5));
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		button_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons8_opened_folder_18px.png")));
		button_1.setOpaque(false);
		button_1.setIconTextGap(0);
		button_1.setBackground(new Color(34, 40, 44));
		
		JButton button = new JButton("");
		menuBar_1.add(button);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        run();
			}
		});
		button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons8_circled_play_18px.png")));
		button.setMargin(new Insets(0, 3, 0, 3));
		button.setIconTextGap(0);
		JButton button_2 = new JButton("");
		menuBar_1.add(button_2);
		button_2.setMargin(new Insets(0, 3, 0, 3)); 
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons8_save_18px.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				save();
			}
		});
		button_2.setOpaque(false);
		button_2.setIconTextGap(0);
		button_2.setBackground(new Color(34, 40, 44));
		
		editorPane = new JEditorPane();
		editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode())
				{
		        case KeyEvent.VK_S :
		            if(e.isControlDown())
		               run();
		            break;
		        case KeyEvent.VK_F3 :		            
		               load();
		            break;        
		        case KeyEvent.VK_F5 :		            
		               save();
		            break;  
		        case KeyEvent.VK_F6 :		   
		        	if(e.isShiftDown())
		        		System.exit(0);
		            break; 
				}
			}
		});
		editorPane.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 12));
		contentPane.add(editorPane, BorderLayout.CENTER);
		final JScrollPane scrollPane = new JScrollPane(editorPane);     
		scrollPane.setBorder(null);
	    getContentPane().add(scrollPane);
	    editorPane.requestFocusInWindow();
	}
	
	protected void save() {
		Save file = new Save();
		file.save(MPS_Model, editorPane);		
	}

	protected void load() {
		MPS_Model = new MPSModel();
		Load file = new Load(MPS_Model);
		try {
			file.load();
			editorPane.setText(null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}

	public void run() {
		if(MPS_Model==null) {
			JOptionPane.showMessageDialog(null, "No MPS file has been selected");
			load();
		}
		else {
			MPS_Model.constructArrays();
	        String s = (MPS_Model.getName()+"\t ("+MPS_Model.getType()+")\n \n");
	        s += "BEGIN MinMax \n";
	        s += "MinMax = "+Arrays.toString(MPS_Model.getMinMax())+"\n";
	        s += "END MinMax \n \n \n";
	        s += "BEGIN Eqin \n";
	        s += "Eqin = "+Arrays.toString(MPS_Model.getEqin())+"\n";
	        s += "END Eqin \n \n \n";
	        s += "BEGIN b \n";
	        s += "b = \n"+Arrays.toString(MPS_Model.getB())+"\n";
	        s += "END b \n \n \n";
	        s += "BEGIN C \n";
	        s += "c = \n"+Arrays.toString(MPS_Model.getC())+"\n";
	        s += "END C \n \n \n";
	        s += "BEGIN A \n";
	        s += "A = \n";
	        int i=0;
	        for(double[] row : MPS_Model.getA()) {
	        	//print all the rows except for the one that has the "N"
	        	if(i!=MPS_Model.getInd_zero())
	        		s += Arrays.toString(row)+"\n";
	        	i++;
	        }
	        s += "END A \n \n \n";
	        editorPane.setText(s);
		}
	}
}