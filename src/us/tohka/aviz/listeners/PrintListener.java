package us.tohka.aviz.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import us.tohka.aviz.utils.PrintableImage;

public class PrintListener implements ActionListener {

    private List<BufferedImage> images;
    private boolean saveOnly;

    public PrintListener(List<BufferedImage> images, boolean saveOnly) {
        this.images = images;
        this.saveOnly = saveOnly;
    }

    public List<BufferedImage> getImages() {
        return images;
    }

    public boolean getSaveOnly() {
        return saveOnly;
    }

    public void setSaveOnly(boolean saveOnly) {
        this.saveOnly = saveOnly;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (images.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You aren't printing anything!", "Oops!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (saveOnly) {
            String uuid = UUID.randomUUID().toString().substring(0, 4);

            for (int i = 0, s = images.size(); i < s; i++) {
                BufferedImage image = images.get(i);
                File file = new File(uuid + "-" + i + ".png");

                try {
                    ImageIO.write(image, "png", file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return;
        }

        PrinterJob job = PrinterJob.getPrinterJob();
        Book book = new Book();
        PageFormat format = new PageFormat();

        for (BufferedImage image : images) {
            book.append(new PrintableImage(job.defaultPage(), image, book), format);
        }

        job.setPageable(book);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}