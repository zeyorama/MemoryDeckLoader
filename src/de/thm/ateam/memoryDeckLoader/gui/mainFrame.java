/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader.gui
 * mainFrame.java
 * 13.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import de.thm.ateam.memoryDeckLoader.Controller;
import de.thm.ateam.memoryDeckLoader.Deck;
import de.thm.ateam.memoryDeckLoader.io.ImageLoader;

/**
 * @author Frank Kevin Zey
 *
 */
public class mainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3434479642153869508L;
	
	private Controller controller;
	private List iList;
	private JLabel backSideLabel;
	private MenuItem miExport;
	
	private void initialize(boolean b) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		this.setSize(550, 450);
		this.setLayout(new FlowLayout());
		this.setTitle("Memory Deck Packer");
		
		/*
		 * set Nimbus LookAndFeel
		 */
		if (b)
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			    if ("Nimbus".equals(info.getName())) {
			       UIManager.setLookAndFeel(info.getClassName());
			       break;
			    }
			}
		
		/* Container for Image-Paths ############################################################ */
		JPanel iP = new JPanel(new BorderLayout());
		
		backSideLabel = new JLabel("");
		
		iList = new List(-1);
		iList.setSize(300, 450);
		iList.setVisible(true);
		
		iList.addMouseListener(new MouseAdapter() {
	        @Override
			public void mousePressed(MouseEvent evt) {
	        	if (evt.getButton() == MouseEvent.BUTTON3)
	            	showMenu(evt);
	        }
		
	        public void showMenu(MouseEvent evt){
				JPopupMenu menu = new JPopupMenu();
			    JMenuItem item = new JMenuItem("Delete");
			    item.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						iList.remove(iList.getSelectedIndex());
					}
				});
			    menu.add(item);
			    menu.show(iList, evt.getX(), evt.getY()); 
			}
		});
		
		iP.add(backSideLabel, BorderLayout.NORTH);
		iP.add(iList, BorderLayout.SOUTH);
		iP.setVisible(true);
		
		/* MenuBar with Menus and MenuItems ################################################ */
		MenuBar mb = new MenuBar();
		
		Menu mFile = new Menu("File");
		MenuItem miExit = new MenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		MenuItem miNew = new MenuItem("New");
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearImageContainer();
			}
		});
		miExport = new MenuItem("Export");
		miExport.setEnabled(false);
		miExport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.newDeck(mainFrame.this);
				
				try {
					Deck d = controller.getCurrentDeck();
					
					if (d != null) {
						d.add(backSideLabel.getText());
						
						for (String s : iList.getItems())
							d.add(s);
						
						ZipFile zip = d.genZipFile();
						Enumeration<? extends ZipEntry> enu = zip.entries();
						if (zip != null)
							while (enu.hasMoreElements())
								System.out.println(enu.nextElement().getName());
					}
					
					clearImageContainer();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		/* add to Menu File */
		mFile.add(miNew);
		mFile.add(miExport);
		mFile.addSeparator();
		mFile.add(miExit);
		
		/* add to menu bar */
		mb.add(mFile);
		
		/* cointainer for buttons etc ###################################################### */
		JButton buttonBIAdd = new JButton("Add Backside Image");
		JButton buttonFIAdd = new JButton("Add Frontside Image");

		buttonBIAdd.setActionCommand("addBI");
		buttonFIAdd.setActionCommand("addFI");

		buttonBIAdd.addActionListener(this);
		buttonFIAdd.addActionListener(this);
		
		buttonBIAdd.setVisible(true);
		buttonFIAdd.setVisible(true);
		
		/* global container ################################################################ */
		JPanel p = new JPanel();
		
		p.setBackground(Color.BLACK);
		p.setLayout(new BorderLayout());
		
		p.add(iP, BorderLayout.NORTH);
		
		JPanel p2 = new JPanel();
		p2.setBackground(Color.BLACK);
		p2.setLayout(new FlowLayout());
		
		p2.add(buttonBIAdd);
		p2.add(buttonFIAdd);
		
		p.add(p2);
		
		p.setVisible(true);
		
		/* adding all to frame ############################################################# */
		this.setMenuBar(mb);
		this.add(p);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("addBI")) {
			File path = ImageLoader.getInstance().getImageWithUI(mainFrame.this, false)[0];
			if (path != null)
				backSideLabel.setText(path.getAbsolutePath());

			if (iList.getItems().length == 32)
				miExport.setEnabled(true);

		} else if (command.equals("addFI")) {
			File[] path = ImageLoader.getInstance().getImageWithUI(mainFrame.this, true);
			
			if (path != null)
				for (int i = 0; i < path.length; i++)
					iList.add(path[i].getAbsolutePath());
			
			if (iList.getItems().length >= 32)
				miExport.setEnabled(true);
			
		} else
			System.err.println("unknown command: "+command);
		
		this.pack();
		this.repaint();
	}
	
	public mainFrame(boolean b, Controller c) throws Exception {
		this.controller = c;
		this.initialize(b);
		this.setBackground(Color.BLACK);
		this.list();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.pack();
	}
	
	private void clearImageContainer() {
		controller.deleteDeck();
		backSideLabel.setText("");
		iList.removeAll();
		this.pack();
		this.repaint();
	}

}
