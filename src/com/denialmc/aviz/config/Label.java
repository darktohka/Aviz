package com.denialmc.aviz.config;

import java.awt.Font;

public class Label {
	
	private String text;
	private String type;
	private int size;
	private int x;
	private int y;
	
	public Label(String text, String type, int size, int x, int y) {
		this.text = text;
		this.type = type;
		this.size = size;
		this.x = x;
		this.y = y;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
	
	public int getFontType() {
		if (type.equals("BOLD")) {
			return Font.BOLD;
		}
		
		return Font.PLAIN;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}