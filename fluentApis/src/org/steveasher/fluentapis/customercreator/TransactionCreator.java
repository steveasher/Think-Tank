package org.steveasher.fluentapis.customercreator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.steveasher.fluentapis.domain.Item;
import org.steveasher.fluentapis.domain.Transaction;

public class TransactionCreator extends Transaction implements Cloneable {
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

	public TransactionCreator items(Item... items) throws CloneNotSupportedException {
		TransactionCreator newTransactionCreator = this.clone();
		newTransactionCreator.setItems(Arrays.asList(items));
		return newTransactionCreator;
	}

	public TransactionCreator on(String date) throws CloneNotSupportedException, ParseException {
		TransactionCreator newTransactionCreator = null;
		
		newTransactionCreator = this.clone();
		newTransactionCreator.setDate(dateFormatter.parse(date));

		return newTransactionCreator;
	}

	public TransactionCreator item(Item item) throws CloneNotSupportedException {
		return this.items(item);
	}

	@Override
	protected TransactionCreator clone() throws CloneNotSupportedException {
		TransactionCreator newTransactionCreator = new TransactionCreator();
		newTransactionCreator.setDate(this.getDate());
		
		List<Item> newItems = new ArrayList<Item>();
		for (Item item : this.getItems()) {
			newItems.add(item);
		}
		
		newTransactionCreator.setItems(newItems);
		
		return newTransactionCreator;
	}
}
