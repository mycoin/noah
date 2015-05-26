package org.ionnic.test.reflect;

import java.util.Map;

public class Product {

	private int id;
	private String produectName;
	private float price;
	private boolean isOnline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduectName() {
		return produectName;
	}

	public void setProduectName(String produectName) {
		this.produectName = produectName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public void setHashMap(Map<?, ?> isOnline) {

	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return "id: " + id + "produectName: " + produectName + "price: " + price + "isOnline: " + isOnline;
	}

}
