package us.tohka.aviz.guis;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import us.tohka.aviz.Main;
import us.tohka.aviz.utils.Utils;

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