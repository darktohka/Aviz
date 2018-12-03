package com.denialmc.aviz.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.denialmc.aviz.config.Label;

public class Utils {

	private static DateFormat format = new SimpleDateFormat("dd.MM.y");
	private static DateFormatSymbols symbols = new DateFormatSymbols();
	
	public static String capitalizeString(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
	
	public static String readFile(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}
	
	public static String formatDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);

		if (calendar.get(Calendar.HOUR_OF_DAY) > 12) {
			calendar.add(Calendar.DATE, 1);
			date = calendar.getTime();
		}

		return format.format(date);
	}
	
	public static String formatDate() {
		return formatDate(new Date());
	}
	
	public static String formatCost(double number) {
		int whole = (int) number;
		double fraction = number - whole;
		
		if (whole == 0) {
			return String.format("%s bani", (int) (fraction * 100));
		} else if (fraction == 0.0) {
			return String.format("%d lei", whole);
		} else {
			return String.format("%d lei si %s bani", whole, (int) (fraction * 100));
		}
	}
	
	public static String pad(int number) {
		return String.format("%02d", number);
	}
	
	public static BufferedImage copyImage(BufferedImage image) {
		ColorModel model = image.getColorModel();
		boolean isAlphaPremultiplied = model.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(null);
		
		return new BufferedImage(model, raster, isAlphaPremultiplied, null);
	}
	
	public static void centerFrame(JFrame frame) {
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = environment.getScreenDevices()[0].getDefaultConfiguration().getBounds();
		
		int frameX = ((bounds.width - frame.getWidth()) / 2) + bounds.x;
		int frameY = ((bounds.height - frame.getHeight()) / 2) + bounds.y;
		
		frame.setLocation(frameX, frameY);
	}
	
	public static void writeToFile(File file, String data) {
		PrintWriter writer;
		
		try {
			writer = new PrintWriter(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			return;
		}
		
		writer.println(data);
		writer.close();
	}
	
	public static void writeToFile(String file, String data) {
		writeToFile(new File(file), data);
	}
	
	public static JButton createButton(JPanel panel, String text, int xSize, int ySize, ActionListener listener) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(xSize, ySize));
		button.addActionListener(listener);
		panel.add(button);
		
		return button;
	}
	
	public static JLabel createLabel(JPanel panel, String text, int fontType, int fontSize, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Segoe UI", fontType, fontSize));
		label.setForeground(color);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		
		return label;
	}
	
	public static void addText(Graphics2D graphics, Label label, String date, String whereTo) {
		addText(graphics, label.getText(), label.getFontType(), label.getSize(), label.getX(), label.getY(), date, whereTo);
	}
	
	public static void addText(Graphics2D graphics, String text, int type, int size, int x, int y, String date, String whereTo) {
		if (text.contains("[DATE]")) {
			text = text.replace("[DATE]", date);
		} else if (text.contains("[WHERE]")) {
			text = text.replace("[WHERE]", whereTo.toUpperCase());
		}

		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		graphics.setFont(new Font("Times New Roman", type, size));
		graphics.setColor(Color.BLACK);
		graphics.drawString(text, x, y);
	}
	
	public static void addText(Graphics2D graphics, String text, int type, int size, int x, int y) {
		addText(graphics, text, type, size, x, y, null, null);
	}
	
	public static int parseInt(String string) {
		try {
			return Integer.valueOf(string);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static int getWeekDay(int weekDay) {
		return weekDay != 6 ? weekDay + 2 : 1;
	}
	
	public static String getDay(int weekDay) {
		return symbols.getWeekdays()[getWeekDay(weekDay)];
	}
	
	public static Calendar getDayInAdvance(int weeks) {
		Calendar calendar = Calendar.getInstance(Locale.GERMANY);
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 7 * weeks);

		return calendar;
	}
	
	public static Date getDateFromDay(Calendar calendar, int weekDay) {
		calendar.set(Calendar.DAY_OF_WEEK, getWeekDay(weekDay));
		
		return calendar.getTime();
	}
}