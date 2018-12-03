package com.denialmc.aviz.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.AbstractTableModel;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.config.Label;
import com.denialmc.aviz.guis.LabelEditGUI;

public class PreviewMouseListener extends MouseInputAdapter {

	private LabelEditGUI gui;
	
	public PreviewMouseListener(LabelEditGUI gui) {
		this.gui = gui;
	}
	
	public LabelEditGUI getGui() {
		return gui;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		mouseDragged(event);
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		Icon icon = gui.getImage().getIcon();
	
		if (x < 0 || y < 0 || x > icon.getIconWidth() || y > icon.getIconHeight()) {
			return;
		}

		JTable table = gui.getTable();
		int row = table.getSelectedRow();
		
		if (row == -1) {
			return;
		}

		Label label = Main.getConfig().getLabel(row);
		
		label.setX(event.getX());
		label.setY(event.getY());
		((AbstractTableModel) table.getModel()).fireTableDataChanged();
		table.setRowSelectionInterval(row, row);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		JTable table = gui.getTable();
		int row = table.getSelectedRow();
		
		if (row == -1) {
			return;
		}

		Label label = Main.getConfig().getLabel(row);
		
		label.setSize(Math.max(1, label.getSize() - event.getUnitsToScroll()));
		((AbstractTableModel) table.getModel()).fireTableDataChanged();
		table.setRowSelectionInterval(row, row);
	}
}