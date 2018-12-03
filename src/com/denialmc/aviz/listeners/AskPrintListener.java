package com.denialmc.aviz.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.denialmc.aviz.guis.SaveOnlyPanel;
import com.denialmc.aviz.utils.PageRenderer;
import com.denialmc.aviz.utils.Utils;

public class AskPrintListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JTextField destinationField = new JTextField();
		SaveOnlyPanel saveOnlyPanel = new SaveOnlyPanel();
		
		JComponent[] inputs = new JComponent[] {
				new JLabel("Destination:"),
				destinationField,
				saveOnlyPanel
		};
		
		int result = JOptionPane.showOptionDialog(null, inputs, "Where are you going?", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Continue"}, JOptionPane.OK_OPTION);
		
		if (result != JOptionPane.OK_OPTION) {
			return;
		}
		
		String destination = destinationField.getText();
		
		if (destination.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have to tell me where you're going!", "Oops!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		new PrintListener(PageRenderer.getPages(Utils.formatDate(), destinationField.getText(), true), saveOnlyPanel.getSaveOnly()).actionPerformed(event);
	}
}