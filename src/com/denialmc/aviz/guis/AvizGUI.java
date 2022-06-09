package com.denialmc.aviz.guis;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.utils.Utils;

public class AvizGUI extends JFrame {

    public AvizGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aviz");
        setResizable(false);
        setIconImage(Main.getIcon());
        setup();
        pack();
        Utils.centerFrame(this);
    }

    public void setup() {
        // To be set in subclass
    }
}