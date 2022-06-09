package us.tohka.aviz.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import us.tohka.aviz.Main;
import us.tohka.aviz.config.Item;
import us.tohka.aviz.config.Label;
import us.tohka.aviz.models.ItemTableModel;
import us.tohka.aviz.models.LabelTableModel;

public class AddListener implements ActionListener {

    private JTable table;
    private int offset;

    public AddListener(JTable table, int offset) {
        this.table = table;
        this.offset = offset;
    }

    public JTable getTable() {
        return table;
    }

    public int getOffset() {
        return offset;
    }

    public int getNextRow(int currentRow) {
        currentRow += offset;

        return currentRow <= 0 ? 0 : currentRow;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected!", "Oops!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TableModel model = table.getModel();

        if (model instanceof ItemTableModel) {
            Main.getConfig().getItems().add(getNextRow(row), new Item("Empty Item", 0, 0));
        } else if (model instanceof LabelTableModel) {
            Main.getConfig().getLabels().add(getNextRow(row), new Label("Empty Label", "PLAIN", 14, 0, 0));
        }

        ((AbstractTableModel) model).fireTableDataChanged();
    }
}