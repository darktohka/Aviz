package com.denialmc.aviz.items;

import java.awt.FlowLayout;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatter;

import com.denialmc.aviz.Main;
import com.denialmc.aviz.config.Config;
import com.denialmc.aviz.guis.SettingsGUI;
import com.denialmc.aviz.listeners.SettingIntListener;
import com.denialmc.aviz.listeners.SettingTextListener;

public class SettingPanel extends JPanel {

    public static final int STRING_TYPE = 0;
    public static final int INTEGER_TYPE = 1;

    private SettingsGUI gui;
    private String fieldName;
    private int fieldType;

    public SettingPanel(SettingsGUI gui, String fieldName, int fieldType, String text) {
        super();
        this.gui = gui;
        this.fieldName = fieldName;
        this.fieldType = fieldType;

        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        add(new JLabel(text));
        add(getSettingComponent());
    }

    public Field getField() throws Exception {
        Field field = Config.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public Class<?> getClassType() {
        switch (fieldType) {
            case STRING_TYPE:
                return String.class;
            case INTEGER_TYPE:
                return Integer.class;
            default:
                return null;
        }
    }

    public JComponent getSettingComponent() {
        if (fieldType == STRING_TYPE) {
            JTextField field = new JTextField(10);
            field.setText(getStringConfig());
            field.getDocument().addDocumentListener(new SettingTextListener(field));
            return field;
        } else if (fieldType == INTEGER_TYPE) {
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 1));
            JComponent editor = spinner.getEditor();
            JFormattedTextField field = (JFormattedTextField) editor.getComponent(0);
            DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();

            formatter.setCommitsOnValidEdit(true);
            spinner.setValue(getIntConfig());
            spinner.addChangeListener(new SettingIntListener(spinner));
            return spinner;
        }

        return null;
    }

    public void setConfig(Object object) {
        try {
            getField().set(Main.getConfig(), object);
        } catch (Exception e) {
            System.out.println("Oops.");
        }
    }

    public Object getConfig() throws Exception {
        return getField().get(Main.getConfig());
    }

    public int getIntConfig() {
        try {
            return (int) getConfig();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getStringConfig() {
        try {
            return getConfig().toString();
        } catch (Exception e) {
            return "";
        }
    }

    public void updateValue(Object object) {
        setConfig(object);
        gui.updatePage();
    }
}