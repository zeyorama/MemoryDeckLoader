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
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		JPanel iP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		iList = new List(-1);
		iList.setSize(300, 450);
		iList.add("test");
		iList.setVisible(true);
		
		iList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
			}
		});
		
		iList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				JPopupMenu pop = new JPopupMenu();
				JMenuItem m = new JMenuItem("Delete");
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println(iList.getSelectedItem());
					}
				});
				pop.add(m);
				
				pop.show(iList.getParent(), iList.getParent().getX(), iList.getParent().getY());
			}
		});
		
		iP.add(iList);
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
		JButton buttonBIAdd = new JButton("Add Backimage");
		JButton buttonFIAdd = new JButton("Add Frontimage");

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
		iList.removeAll();
		this.repaint();
	}

}
