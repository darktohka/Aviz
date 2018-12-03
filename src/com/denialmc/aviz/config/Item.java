package com.denialmc.aviz.config;

public class Item {
	
	private String name;
	private int quantity;
	private double cost;
	
	public Item(String name, int quantity, double cost) {
		this.name = name;
		this.quantity = quantity;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
}