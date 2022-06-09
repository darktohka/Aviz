package us.tohka.aviz.guis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import us.tohka.aviz.items.SettingPanel;
import us.tohka.aviz.listeners.DestinationListener;
import us.tohka.aviz.listeners.SaveListener;
import us.tohka.aviz.utils.PageRenderer;
import us.tohka.aviz.utils.Utils;

public class SettingsGUI extends AvizGUI {

    private JPanel pagePanel;

    public void setup() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel settingPanel = new JPanel();
        pagePanel = new JPanel();

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        settingPanel.add(new SettingPanel(this, "newLineAtItem", SettingPanel.INTEGER_TYPE, "Start new page at item number:"));
        settingPanel.add(new SettingPanel(this, "itemStartY", SettingPanel.INTEGER_TYPE, "Item start on the Y axis:"));
        settingPanel.add(new SettingPanel(this, "itemYIncrement", SettingPanel.INTEGER_TYPE, "Item increment on the Y axis:"));
        settingPanel.add(new SettingPanel(this, "itemNumberX", SettingPanel.INTEGER_TYPE, "Item number on the X axis:"));
        settingPanel.add(new SettingPanel(this, "itemNameX", SettingPanel.INTEGER_TYPE, "Item name on the X axis:"));
        settingPanel.add(new SettingPanel(this, "itemPerNameX", SettingPanel.INTEGER_TYPE, "Item piece on the X axis:"));
        settingPanel.add(new SettingPanel(this, "itemQuantityX", SettingPanel.INTEGER_TYPE, "Item quantity on the X axis:"));
        settingPanel.add(new SettingPanel(this, "itemCostX", SettingPanel.INTEGER_TYPE, "Item cost on the X axis:"));
        settingPanel.add(new SettingPanel(this, "itemFontSize", SettingPanel.INTEGER_TYPE, "Item font size:"));
        settingPanel.add(new SettingPanel(this, "perName", SettingPanel.STRING_TYPE, "Localization for \"piece\":"));

        topPanel.add(settingPanel);
        topPanel.add(pagePanel);
        updatePage();

        Utils.createButton(bottomPanel, "Back", 100, 50, new DestinationListener(this, DestinationListener.MAIN));
        Utils.createButton(bottomPanel, "Save", 100, 50, new SaveListener());

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updatePage() {
        pagePanel.removeAll();

        for (JLabel label : PageRenderer.getPagesAsLabels(Utils.formatDate(), "Destination")) {
            pagePanel.add(label);
        }

        revalidate();
        pack();
    }
}