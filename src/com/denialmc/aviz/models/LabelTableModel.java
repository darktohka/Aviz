package com.denialmc.aviz.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.denialmc.aviz.config.Label;
import com.denialmc.aviz.guis.LabelEditGUI;

public class LabelTableModel extends AbstractTableModel {
	
	private LabelEditGUI gui;
    private List<Label> labels;
    private String[] columnNames = {"Text", "Type", "Size", "X", "Y"};
    private Class<?>[] columnClass = {String.class, String.class, Integer.class, Integer.class, Integer.class};
 
    public LabelTableModel(LabelEditGUI gui, List<Label> labels) {
    	this.gui = gui;
        this.labels = labels;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
     
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount() {
        return labels.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Label label = labels.get(rowIndex);
    	
    	if (columnIndex == 0) {
    		return label.getText();
    	} else if (columnIndex == 1) {
    		return label.getType();
    	} else if (columnIndex == 2) {
    		return label.getSize();
    	} else if (columnIndex == 3) {
    		return label.getX();
    	} else if (columnIndex == 4) {
    		return label.getY();
    	}
    	
    	return null;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    	Label label = labels.get(rowIndex);
    	
    	if (columnIndex == 0) {
    		label.setText((String) value);
    	} else if (columnIndex == 1) {
    		label.setType((String) value);
    	} else if (columnIndex == 2) {
    		label.setSize((Integer) value);
    	} else if (columnIndex == 3) {
    		label.setX((Integer) value);
    	} else if (columnIndex == 4) {
    		label.setY((Integer) value);
    	}
    	
    	gui.setImageWithLabel(label);
    }
}