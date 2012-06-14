/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader.gui
 * mainFrame.java
 * 13.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author Frank Kevin Zey
 *
 */
public class mainFrame extends JFrame {

	private static final long serialVersionUID = 3434479642153869508L;
	
	private Container imageContainer;
	private boolean imageContainerSet;
	
	private void initialize(boolean b) throws Exception {
		this.setSize(550, 450);
		this.setLayout(new FlowLayout());
		this.setTitle("Memory Deck Packer");
		
		if (b)
			/*
			 * set Nimbus LookAndFeel
			 */
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			    if ("Nimbus".equals(info.getName())) {
			       UIManager.setLookAndFeel(info.getClassName());
			       break;
			    }
			}
		
		/* Container for Images ############################################################ */
		imageContainer = new Container();
		imageContainer.setVisible(false);
		imageContainerSet = false;
		
		/* MenuBar with Menus and MenuItems ################################################ */
		MenuBar mb = new MenuBar();
		
		Menu mFile = new Menu("File");
		MenuItem miExit = new MenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		MenuItem miNew = new MenuItem("New");
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!imageContainerSet)
					imageContainer.setVisible(true);
				else
					clearImageContainer();
			}
		});
		MenuItem miDelete = new MenuItem("Delete");
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				imageContainer.setVisible(false);
				imageContainerSet = false;
				clearImageContainer();
			}
		});
		MenuItem miExport = new MenuItem("Export");
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO exportieren der zip file mit bildern und der JSON file
			}
		});
		
		/* add to Menu File */
		mFile.add(miNew);
		mFile.add(miDelete);
		mFile.addSeparator();
		mFile.add(miExport);
		mFile.addSeparator();
		mFile.add(miExit);
		
		/* add to menu bar */
		mb.add(mFile);
		
		/* global container ################################################################ */
		Container c = new Container();
		c.add(imageContainer);
		
		/* adding all to frame ############################################################# */
		this.setMenuBar(mb);
		this.add(c);
	}
	
	public mainFrame(boolean b) throws Exception {
		this.initialize(b);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void clearImageContainer() {
		//TODO image container clear
	}

}
