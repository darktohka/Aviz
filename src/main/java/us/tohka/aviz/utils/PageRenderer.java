package us.tohka.aviz.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import us.tohka.aviz.Main;
import us.tohka.aviz.config.Config;
import us.tohka.aviz.config.Item;
import us.tohka.aviz.config.Label;

public class PageRenderer {

    public static BufferedImage getNewPage(BufferedImage image, String date, String whereTo) {
        BufferedImage newImage = Utils.copyImage(image);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        for (Label label : Main.getConfig().getLabels()) {
            Utils.addText(graphics, label, date, whereTo);
        }

        return newImage;
    }

    public static List<BufferedImage> getPages(String date, String whereTo, boolean print) {
        Config config = Main.getConfig();
        int counter = config.getNewLineAtItem();
        int itemFontSize = config.getItemFontSize();
        int number = 0;
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        Graphics2D graphics = null;

        for (Item item : config.getItems()) {
            if (counter == config.getNewLineAtItem()) {
                if (!print && images.size() == 3) {
                    return images;
                }

                counter = 0;
                BufferedImage page = getNewPage(Main.getImage(), date, whereTo);
                graphics = (Graphics2D) page.getGraphics();

                images.add(page);
            }

            int y = config.getItemStartY() + (counter * config.getItemYIncrement());
            counter++;
            number++;

            Utils.addText(graphics, Utils.pad(number), Font.PLAIN, itemFontSize, config.getItemNumberX(), y);
            Utils.addText(graphics, item.getName(), Font.PLAIN, itemFontSize, config.getItemNameX(), y);
            Utils.addText(graphics, config.getPerName(), Font.PLAIN, itemFontSize, config.getItemPerNameX(), y);
            Utils.addText(graphics, String.valueOf(item.getQuantity()), Font.PLAIN, itemFontSize, config.getItemQuantityX(), y);
            Utils.addText(graphics, Utils.formatCost(item.getCost()), Font.PLAIN, itemFontSize, config.getItemCostX(), y);
        }

        if (!print) {
            images.replaceAll(image -> Utils.scaleImageToMaxWidth(image, 800));
        }

        return images;
    }

    public static List<JLabel> getPagesAsLabels(String date, String whereTo) {
        List<BufferedImage> pages = getPages(date, whereTo, false);
        List<JLabel> labels = new ArrayList<JLabel>();

        for (BufferedImage page : pages) {
            JLabel label = new JLabel();

            label.setIcon(new ImageIcon(page));
            labels.add(label);
        }

        return labels;
    }
}