package com.mingrisoft.bean;

public class Ware {
	private int id;
	private String wareName;
	private String warBewrite;
	private String spec;
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	private float stockPrice;
	private float retailPrice;
	private float associatorPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getWarBewrite() {
		return warBewrite;
	}
	public void setWarBewrite(String warBewrite) {
		this.warBewrite = warBewrite;
	}
	
	public float getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(float stockPrice) {
		this.stockPrice = stockPrice;
	}
	public float getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(float retailPrice) {
		this.retailPrice = retailPrice;
	}
	public float getAssociatorPrice() {
		return associatorPrice;
	}
	public void setAssociatorPrice(float associatorPrice) {
		this.associatorPrice = associatorPrice;
	}
	
}
