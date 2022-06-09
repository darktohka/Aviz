package com.denialmc.aviz.listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.guis.LabelEditGUI;

public class LabelSelectListener implements ListSelectionListener {

    private LabelEditGUI gui;

    public LabelSelectListener(LabelEditGUI gui) {
        this.gui = gui;
    }

    public LabelEditGUI getGui() {
        return gui;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        int row = gui.getTable().getSelectedRow();

        if (row == -1) {
            return;
        }

        gui.setImageWithLabel(Main.getConfig().getLabel(row));
    }
}