package com.denialmc.aviz.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaveListener implements ActionListener {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void actionPerformed(ActionEvent event) {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "Are you sure?", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        Utils.writeToFile("config.json", gson.toJson(Main.getConfig()));
        JOptionPane.showMessageDialog(null, "Successfully saved the config!", "Success!", JOptionPane.INFORMATION_MESSAGE);
    }
}