package com.denialmc.aviz.guis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.denialmc.aviz.listeners.AskPrintListener;
import com.denialmc.aviz.listeners.DestinationListener;
import com.denialmc.aviz.utils.PageRenderer;
import com.denialmc.aviz.utils.Utils;

public class MainGUI extends AvizGUI {

    public void setup() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        for (JLabel label : PageRenderer.getPagesAsLabels(Utils.formatDate(), "Destination")) {
            topPanel.add(label);
        }

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        Utils.createButton(bottomPanel, "Print", 100, 50, new AskPrintListener());
        Utils.createButton(bottomPanel, "Multi Printer", 100, 50, new DestinationListener(this, DestinationListener.MULTI_PRINT));
        Utils.createButton(bottomPanel, "Item Editor", 100, 50, new DestinationListener(this, DestinationListener.ITEM_EDIT));
        Utils.createButton(bottomPanel, "Label Editor", 100, 50, new DestinationListener(this, DestinationListener.LABEL_EDIT));
        Utils.createButton(bottomPanel, "Settings", 100, 50, new DestinationListener(this, DestinationListener.SETTINGS));

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}