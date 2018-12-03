package com.denialmc.aviz.listeners;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.denialmc.aviz.guis.MultiPrintGUI;
import com.denialmc.aviz.guis.SaveOnlyPanel;
import com.denialmc.aviz.models.DayTableModel;
import com.denialmc.aviz.utils.PageRenderer;
import com.denialmc.aviz.utils.Utils;

public class MultiPrintListener implements ActionListener {

	private MultiPrintGUI gui;
	
	public MultiPrintListener(MultiPrintGUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		DayTableModel model = gui.getModel();
		List<JCheckBox> destinationBoxes = new ArrayList<JCheckBox>();
		
		for (int i = 0; i < model.getRowCount(); i++) {
			String destination = (String) model.getValueAt(i, 1);
			
			if (destination.isEmpty()) {
				continue;
			}

			JCheckBox destinationBox = new JCheckBox(destination);
			destinationBox.setName(String.valueOf(i));
			destinationBox.setSelected(true);
			destinationBoxes.add(destinationBox);
		}
		
		List<JComponent> inputs = new ArrayList<JComponent>();
		
		inputs.add(new JLabel("Destinations:"));
		inputs.addAll(destinationBoxes);
		
		JPanel weekPanel = new JPanel();
		JSpinner weekSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		
		weekPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		weekPanel.add(new JLabel("How many weeks in advance:"));
		weekPanel.add(weekSpinner);
		inputs.add(weekPanel);
		
		SaveOnlyPanel saveOnlyPanel = new SaveOnlyPanel();
		inputs.add(saveOnlyPanel);
		
		JComponent[] inputArray = inputs.toArray(new JComponent[inputs.size()]);
		
		int result = JOptionPane.showOptionDialog(null, inputArray, "Multi Printer", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Continue"}, JOptionPane.OK_OPTION);
		
		if (result != JOptionPane.OK_OPTION) {
			return;
		}
		
		List<BufferedImage> pages = new ArrayList<BufferedImage>();
		int weeks = (int) weekSpinner.getValue();
		Calendar week = Utils.getDayInAdvance(weeks);
		
		for (JCheckBox box : destinationBoxes) {
			if (box.isSelected()) {
				pages.addAll(PageRenderer.getPages(Utils.formatDate(Utils.getDateFromDay(week, Integer.valueOf(box.getName()))), box.getText(), true));
			}
		}
		
		new PrintListener(pages, saveOnlyPanel.getSaveOnly()).actionPerformed(event);
	}
}