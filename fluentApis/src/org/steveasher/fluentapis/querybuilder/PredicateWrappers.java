package org.steveasher.fluentapis.querybuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.steveasher.fluentapis.domain.Customer;
import org.steveasher.fluentapis.domain.Item;

public class PredicateWrappers {
	public static Predicate<Customer> bought(Predicate<List<Item>> itemPred) {
		return new CustomerStrategyPredicate(null, itemPred);
	}
	
	public static Predicate<Customer> bought(Predicate<List<Item>> itemPred, Predicate<Date> dateRange) {
		return new CustomerStrategyPredicate(dateRange, itemPred);
	}
	
	public static Predicate<List<Item>> items(final Item... itemsToCheckFor) {
		return new Predicate<List<Item>>() {
			private boolean foundAllItems = true;
			
			public boolean evaluate(List<Item> items) {
				for (Item itemToCheckFor : itemsToCheckFor) {
					foundAllItems &= items.contains(itemToCheckFor);
				}
				return foundAllItems;
			}
		};
	}
	
	public static Predicate<List<Item>> item(Item item) {
		return PredicateWrappers.items(item);
	}
	
	public static Predicate<List<Item>> anything() {
		return new Predicate<List<Item>>() {
			public boolean evaluate(List<Item> items) {
				return !items.isEmpty();
			}
		};
	}
	
	public static Predicate<Date> on(String date) {
		return PredicateWrappers.between(date, date);
	}
	
	public static Predicate<Date> between(final String firstDate, final String secondDate) {
		final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		
		return new Predicate<Date>() {
			public boolean evaluate(Date date) {
				try {
					final Date date1 = dateFormatter.parse(firstDate);
					final Date date2 = dateFormatter.parse(secondDate);
					
					return date1.equals(date) || date2.equals(date) || 
							(date.after(date1) && date.before(date2));
				} catch (ParseException e) {
					return false;
				}
			}
		};
	}
}
