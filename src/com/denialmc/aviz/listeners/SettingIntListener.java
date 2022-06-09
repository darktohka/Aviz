package com.denialmc.aviz.listeners;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.denialmc.aviz.items.SettingPanel;

public class SettingIntListener implements ChangeListener {

    private JSpinner spinner;

    public SettingIntListener(JSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        SettingPanel panel = (SettingPanel) spinner.getParent();
        panel.updateValue(spinner.getValue());
    }
}