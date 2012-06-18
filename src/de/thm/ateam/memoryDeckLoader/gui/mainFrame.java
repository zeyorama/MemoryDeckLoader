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

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.thm.ateam.memoryDeckLoader.Controller;

/**
 * @author Frank Kevin Zey
 *
 */
public class mainFrame extends JFrame {

	private static final long serialVersionUID = 3434479642153869508L;
	
	private Container imageContainer;
	private boolean imageContainerSet;
	private Controller controller;
	private JList<String> imgList;
	
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
		imageContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		dlm.addElement("test");
		
		imgList = new JList<String>(dlm);
		imgList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		imgList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		imgList.setVisibleRowCount(-1);
		imageContainer.add(imgList);
		
		JPopupMenu rightClickPopUp = new JPopupMenu();
		JMenuItem deleteRightClickPopUp = new JMenuItem("Delete Item");
		deleteRightClickPopUp.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//int index = imgList.getSelectedIndex();
			    //Deck deck = controller.getCurrentDeck();
				//deck.deleteByIndex(index);
			}
		} );
		rightClickPopUp.add(imgList);
		
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
				mainFrame.this.repaint();
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
		
		/* global container ################################################################ */
		Container c = new Container();
		c.add(imageContainer);
		
		/* adding all to frame ############################################################# */
		this.setMenuBar(mb);
		this.add(c);
	}
	
	public mainFrame(boolean b, Controller c) throws Exception {
		this.controller = c;
		this.initialize(b);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void clearImageContainer() {
		controller.deleteDeck();
		imgList.removeAll();
	}

}
