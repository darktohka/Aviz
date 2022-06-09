package com.denialmc.aviz.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class PrintableImage implements Printable {

    private PageFormat format;
    private BufferedImage image;
    private Book book;

    public PrintableImage(PageFormat format, BufferedImage image, Book book) {
        this.format = format;
        this.image = image;
        this.book = book;
    }

    public PageFormat getFormat() {
        return format;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        if (pageIndex >= book.getNumberOfPages()) {
            return NO_SUCH_PAGE;
        }

        double width = format.getImageableWidth();
        int pageWidth = (int) Math.min(width, (double) image.getWidth());
        int pageHeight = pageWidth * image.getHeight() / image.getWidth();

        graphics.drawImage(image, (int) format.getImageableX(), (int) format.getImageableY(), pageWidth, pageHeight, null);
        return PAGE_EXISTS;
    }
}