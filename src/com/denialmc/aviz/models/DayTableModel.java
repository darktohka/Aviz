package com.denialmc.aviz.models;

import javax.swing.table.AbstractTableModel;

import com.denialmc.aviz.utils.Utils;

public class DayTableModel extends AbstractTableModel {

    private String[] days;
    private String[] columnNames = {"Day", "Destination"};
    private Class<?>[] columnClass = {String.class, String.class};
 
    public DayTableModel(String... days) {
        this.days = days;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
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
        return days.length;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	if (columnIndex == 0) {
    		return Utils.getDay(rowIndex);
    	} else if (columnIndex == 1) {
    		return days[rowIndex];
    	} 
    	
    	return null;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    	if (columnIndex == 1) {
    		days[rowIndex] = (String) value;
    	}
    }
}