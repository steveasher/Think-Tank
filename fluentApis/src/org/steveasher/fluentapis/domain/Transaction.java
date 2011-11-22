package org.steveasher.fluentapis.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
	private Date date;
	private List<Item> items;
	
	public Transaction() {
		this.items = new ArrayList<Item>();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
