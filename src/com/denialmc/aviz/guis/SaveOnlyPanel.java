package com.denialmc.aviz.guis;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SaveOnlyPanel extends JPanel {

    public JRadioButton saveOnly;
    public JRadioButton print;

    public boolean getSaveOnly() {
        return saveOnly.isSelected();
    }

    public SaveOnlyPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        ButtonGroup group = new ButtonGroup();
        saveOnly = new JRadioButton("Save Only", false);
        print = new JRadioButton("Print", true);

        group.add(saveOnly);
        group.add(print);
        add(saveOnly);
        add(print);
    }
}