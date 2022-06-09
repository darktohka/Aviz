package us.tohka.aviz.renderers;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import us.tohka.aviz.utils.Utils;

public class ErrorCellRenderer extends DefaultTableCellRenderer {

    public static final int LIST_MODE = 0;
    public static final int POSITIVE_MODE = 1;

    private int mode;
    private List<String> allowedWords;

    public ErrorCellRenderer(int mode, String... allowedWords) {
        this.mode = mode;
        this.allowedWords = Arrays.asList(allowedWords);
    }

    public ErrorCellRenderer(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public List<String> getAllowedWords() {
        return allowedWords;
    }

    public boolean testValue(Object value) {
        if (mode == LIST_MODE) {
            return allowedWords.contains(value.toString());
        } else if (mode == POSITIVE_MODE) {
            return Utils.parseInt(value.toString()) >= 0;
        }

        return false;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!testValue(value)) {
            component.setBackground(Color.RED);
        } else {
            component.setBackground(Color.WHITE);
        }

        return component;
    }
}