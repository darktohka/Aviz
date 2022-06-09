package com.denialmc.aviz.listeners;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.denialmc.aviz.items.SettingPanel;

public class SettingTextListener implements DocumentListener {

    private JTextField field;

    public SettingTextListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        handleUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        handleUpdate();
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        handleUpdate();
    }

    public void handleUpdate() {
        SettingPanel panel = (SettingPanel) field.getParent();
        panel.updateValue(field.getText());
    }
}