package org.steveasher.fluentapis.domain;

public enum Item {
	Book(17.95),
	CompactDisc(19.98),
	CandyBar(1.50);
	
	private final Double price;

	Item(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return this.price;
	}
}
