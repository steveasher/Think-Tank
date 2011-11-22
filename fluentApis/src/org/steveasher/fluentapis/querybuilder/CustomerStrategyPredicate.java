package org.steveasher.fluentapis.querybuilder;

import java.util.Date;
import java.util.List;

import org.steveasher.fluentapis.domain.*;

public class CustomerStrategyPredicate implements Predicate<Customer> {
	private final Predicate<Date> dateRange;
	private final Predicate<List<Item>> itemPred;
	
	public CustomerStrategyPredicate(Predicate<Date> dateRange, Predicate<List<Item>> itemPred) {
		this.dateRange = dateRange;
		this.itemPred = itemPred;
	}

	public boolean evaluate(Customer customer) {
		for (Transaction transaction : customer.getTransactions()) {
			if (dateRange == null || dateRange.evaluate(transaction.getDate())) {
				return itemPred.evaluate(transaction.getItems());
			}
		}
		
		return false;
	}
}