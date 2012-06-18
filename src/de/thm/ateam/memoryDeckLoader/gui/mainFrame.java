/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader.gui
 * mainFrame.java
 * 13.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import de.thm.ateam.memoryDeckLoader.Controller;

/**
 * @author Frank Kevin Zey
 *
 */
public class mainFrame extends JFrame {

	private static final long serialVersionUID = 3434479642153869508L;
	
	private Controller controller;
	private List iList;
	
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
		iList = new List();
		iList.add("test");
		iList.setVisible(true);
		
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
		MenuItem miDelete = new MenuItem("Delete");
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearImageContainer();
			}
		});
		MenuItem miExport = new MenuItem("Export");
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		
		/* cointainer for buttons etc ###################################################### */
		Container cbetc = new Container();
		JButton buttonBIAdd = new JButton("Add Backimage");
		JButton buttonFIAdd = new JButton("Add Frontimage");

		buttonBIAdd.setVisible(true);
		buttonFIAdd.setVisible(true);
		
		cbetc.add(buttonBIAdd);
		cbetc.add(buttonFIAdd);
		
		cbetc.setVisible(true);
		
		/* global container ################################################################ */
		Container c = new Container();
		c.add(iList);
		c.setVisible(true);
		
		/* adding all to frame ############################################################# */
		this.setMenuBar(mb);
		this.add(c);
		this.add(cbetc);
	}
	
	public mainFrame(boolean b, Controller c) throws Exception {
		this.controller = c;
		this.initialize(b);
		this.setBackground(Color.BLACK);
		this.list();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
	}
	
	private void clearImageContainer() {
		controller.deleteDeck();
		iList.removeAll();
		this.repaint();
	}

}
