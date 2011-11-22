package org.steveasher.fluentapis.domain;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	private String name;
	private List<Transaction> transactions;
	
	public Customer() {
		this.transactions = new ArrayList<Transaction>();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Transaction> getTransactions() {
		return this.transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
