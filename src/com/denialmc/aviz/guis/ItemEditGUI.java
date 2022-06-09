package com.denialmc.aviz.guis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.listeners.AddListener;
import com.denialmc.aviz.listeners.DestinationListener;
import com.denialmc.aviz.listeners.RemoveListener;
import com.denialmc.aviz.listeners.SaveListener;
import com.denialmc.aviz.models.ItemTableModel;
import com.denialmc.aviz.utils.Utils;

public class ItemEditGUI extends AvizGUI {

    public void setup() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JTable table = new JTable(new ItemTableModel(Main.getConfig().getItems()));

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        table.getActionMap().put("delete", new RemoveListener(table));

        topPanel.add(new JScrollPane(table));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        Utils.createButton(bottomPanel, "Back", 100, 50, new DestinationListener(this, DestinationListener.MAIN));
        Utils.createButton(bottomPanel, "Add (Up)", 100, 50, new AddListener(table, -1));
        Utils.createButton(bottomPanel, "Add (Down)", 100, 50, new AddListener(table, 1));
        Utils.createButton(bottomPanel, "Save", 100, 50, new SaveListener());
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}