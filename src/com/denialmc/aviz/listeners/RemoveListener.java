package com.denialmc.aviz.listeners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.models.ItemTableModel;
import com.denialmc.aviz.models.LabelTableModel;

public class RemoveListener extends AbstractAction {

	private JTable table;
	
	public RemoveListener(JTable table) {
		this.table = table;
	}
	
	public JTable getTable() {
		return table;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int row = table.getSelectedRow();
		
		if (row == -1) {
			return;
		}
		
		TableModel model = table.getModel();
		
		if (model instanceof ItemTableModel) {
			Main.getConfig().getItems().remove(row);
		} else if (model instanceof LabelTableModel) {
			Main.getConfig().getLabels().remove(row);
		}

		((AbstractTableModel) model).fireTableDataChanged();
	}
}