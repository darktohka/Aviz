package us.tohka.aviz.config;

import java.util.List;

public class Config {

    private int newLineAtItem;
    private String perName;
    private int itemStartY;
    private int itemYIncrement;
    private int itemNumberX;
    private int itemNameX;
    private int itemPerNameX;
    private int itemQuantityX;
    private int itemCostX;
    // and it also works recursively
    private List<Label> labels;
    private List<Item> items;
    private String[] days = {"", "", "", "", "", "", ""};

    public int getNewLineAtItem() {
        return newLineAtItem;
    }

    public String getPerName() {
        return perName;
    }

    public int getItemStartY() {
        return itemStartY;
    }

    public int getItemYIncrement() {
        return itemYIncrement;
    }

    public int getItemNumberX() {
        return itemNumberX;
    }

    public int getItemNameX() {
        return itemNameX;
    }

    public int getItemPerNameX() {
        return itemPerNameX;
    }

    public int getItemQuantityX() {
        return itemQuantityX;
    }

    public int getItemCostX() {
        return itemCostX;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public List<Item> getItems() {
        return items;
    }

    public Label getLabel(int index) {
        return labels.get(index);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public String[] getDays() {
        return days;
    }
}