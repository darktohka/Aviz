package us.tohka.aviz;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import us.tohka.aviz.config.Config;
import us.tohka.aviz.guis.MainGUI;
import us.tohka.aviz.utils.Utils;
import com.google.gson.Gson;

public class Main {

    private static Config config;
    private static BufferedImage image;
    private static Image icon;

    public static Config getConfig() {
        return config;
    }

    public static BufferedImage getImage() {
        return image;
    }

    public static Image getIcon() {
        return icon;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Aviz by DenialMC!");
        System.out.println("Loading config...");
        File file = new File("config.json");

        if (!file.exists()) {
            Utils.writeToFile(file, "{}");
        }

        config = new Gson().fromJson(Utils.readFile("config.json"), Config.class);
        System.out.println("Loaded config!");
        System.out.println("Loading aviz image...");

        try {
            image = ImageIO.read(new File("image.png"));
        } catch (Exception e) {
            System.out.println("Aviz image couldn't be read!");
            return;
        }

        System.out.println("Loaded aviz image!");

        System.out.println("Loading icon...");
        icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/icon.png"));
        System.out.println("Loaded icon!");

        System.out.println("Setting look and feel...");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Set look and feel!");
        } catch (Exception e) {
            System.out.println("Couldn't set system look and feel.");
        }

        System.out.println("Starting...");
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
        System.out.println("Started!");
    }
}