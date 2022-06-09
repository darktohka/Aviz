package us.tohka.aviz.guis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumnModel;

import us.tohka.aviz.Main;
import us.tohka.aviz.config.Label;
import us.tohka.aviz.listeners.AddListener;
import us.tohka.aviz.listeners.DestinationListener;
import us.tohka.aviz.listeners.LabelSelectListener;
import us.tohka.aviz.listeners.PreviewMouseListener;
import us.tohka.aviz.listeners.RemoveListener;
import us.tohka.aviz.listeners.SaveListener;
import us.tohka.aviz.models.LabelTableModel;
import us.tohka.aviz.renderers.ErrorCellRenderer;
import us.tohka.aviz.utils.Utils;

public class LabelEditGUI extends AvizGUI {

    private JLabel image;
    private JTable table;

    public void setup() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        table = new JTable(new LabelTableModel(this, Main.getConfig().getLabels()));
        TableColumnModel model = table.getColumnModel();
        ErrorCellRenderer positiveRenderer = new ErrorCellRenderer(ErrorCellRenderer.POSITIVE_MODE);
        PreviewMouseListener previewListener = new PreviewMouseListener(this);

        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        table.getActionMap().put("delete", new RemoveListener(table));
        table.getSelectionModel().addListSelectionListener(new LabelSelectListener(this));
        model.getColumn(1).setCellRenderer(new ErrorCellRenderer(ErrorCellRenderer.LIST_MODE, "PLAIN", "BOLD", "ITALIC"));
        IntStream.range(2, 4).forEach(n -> model.getColumn(n).setCellRenderer(positiveRenderer));
        topPanel.add(new JScrollPane(table));

        image = new JLabel();
        image.addMouseListener(previewListener);
        image.addMouseWheelListener(previewListener);
        image.addMouseMotionListener(previewListener);
        setImageWithLabel(null);
        topPanel.add(image);

        Utils.createLabel(bottomPanel, "Special modifiers: [DATE], [WHERE]", Font.BOLD, 14, Color.BLACK);
        Utils.createLabel(bottomPanel, "Font styles: PLAIN, BOLD", Font.BOLD, 14, Color.BLACK);
        bottomPanel.add(Box.createVerticalStrut(5));
        Utils.createButton(buttonPanel, "Back", 100, 50, new DestinationListener(this, DestinationListener.MAIN));
        Utils.createButton(buttonPanel, "Add", 100, 50, new AddListener(table, 0));
        Utils.createButton(buttonPanel, "Save", 100, 50, new SaveListener());
        bottomPanel.add(buttonPanel);
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JLabel getImage() {
        return image;
    }

    public JTable getTable() {
        return table;
    }

    public void setImageWithLabel(Label label) {
        BufferedImage newImage = Utils.copyImage(Main.getImage());
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        if (label != null) {
            Utils.addText(graphics, label, Utils.formatDate(), "Destination");
        }

        image.setIcon(new ImageIcon(newImage));
    }
}