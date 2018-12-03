package com.denialmc.aviz.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.denialmc.aviz.config.Item;

public class ItemTableModel extends AbstractTableModel {
	
    private List<Item> items;
    private String[] columnNames = {"Name", "Quantity", "Cost"};
    private Class<?>[] columnClass = {String.class, Integer.class, Double.class};
 
    public ItemTableModel(List<Item> items) {
        this.items = items;
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
        return items.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Item item = items.get(rowIndex);
    	
    	if (columnIndex == 0) {
    		return item.getName();
    	} else if (columnIndex == 1) {
    		return item.getQuantity();
    	} else if (columnIndex == 2) {
    		return item.getCost();
    	}
    	
    	return null;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    	Item item = items.get(rowIndex);
    	
    	if (columnIndex == 0) {
    		item.setName((String) value);
    	} else if (columnIndex == 1) {
    		item.setQuantity((Integer) value);
    	} else if (columnIndex == 2) {
    		item.setCost((Double) value);
    	}
    }
}