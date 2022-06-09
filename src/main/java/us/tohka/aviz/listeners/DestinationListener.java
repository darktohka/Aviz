package us.tohka.aviz.listeners;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import us.tohka.aviz.guis.ItemEditGUI;
import us.tohka.aviz.guis.LabelEditGUI;
import us.tohka.aviz.guis.MainGUI;
import us.tohka.aviz.guis.MultiPrintGUI;
import us.tohka.aviz.guis.SettingsGUI;

public class DestinationListener extends AbstractAction {

    public static final int MAIN = 0;
    public static final int ITEM_EDIT = 1;
    public static final int LABEL_EDIT = 2;
    public static final int MULTI_PRINT = 3;
    public static final int SETTINGS = 4;

    private Window window;
    private int destination;

    public DestinationListener(Window window, int destination) {
        this.window = window;
        this.destination = destination;
    }

    public Window getWindow() {
        return window;
    }

    public Window getDestination() {
        switch (destination) {
            case MAIN:
                return new MainGUI();
            case ITEM_EDIT:
                return new ItemEditGUI();
            case LABEL_EDIT:
                return new LabelEditGUI();
            case MULTI_PRINT:
                return new MultiPrintGUI();
            case SETTINGS:
                return new SettingsGUI();
            default:
                return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        window.dispose();
        getDestination().setVisible(true);
    }
}