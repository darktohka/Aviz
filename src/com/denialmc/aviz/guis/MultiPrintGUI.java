package com.denialmc.aviz.guis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.listeners.DestinationListener;
import com.denialmc.aviz.listeners.MultiPrintListener;
import com.denialmc.aviz.listeners.SaveListener;
import com.denialmc.aviz.models.DayTableModel;
import com.denialmc.aviz.utils.Utils;

public class MultiPrintGUI extends AvizGUI {
	
	private JLabel image;
	private DayTableModel model;
	
	public void setup() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		
		model = new DayTableModel(Main.getConfig().getDays());
		JTable table = new JTable(model);
		topPanel.add(new JScrollPane(table));
		this.setPreferredSize(new Dimension(500, 217));

		Utils.createButton(buttonPanel, "Back", 100, 50, new DestinationListener(this, DestinationListener.MAIN));
		Utils.createButton(buttonPanel, "Print", 100, 50, new MultiPrintListener(this));
		Utils.createButton(buttonPanel, "Save", 100, 50, new SaveListener());
		add(topPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public JLabel getImage() {
		return image;
	}
	
	public DayTableModel getModel() {
		return model;
	}
}