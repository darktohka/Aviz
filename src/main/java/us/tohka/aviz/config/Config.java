package us.tohka.aviz.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private int itemFontSize;
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

    public int getItemFontSize() {
        return itemFontSize;
    }

    public List<Label> getLabels() {
        if (Objects.isNull(labels)) {
            labels = new ArrayList<Label>();
        }

        return labels;
    }

    public List<Item> getItems() {
        if (Objects.isNull(items)) {
            items = new ArrayList<Item>();
        }

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