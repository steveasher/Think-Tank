package org.steveasher.fluentapis.customercreator;

import org.steveasher.fluentapis.domain.Customer;
import org.steveasher.fluentapis.domain.Transaction;

public class CustomerCreator extends Customer implements Cloneable {

	public CustomerCreator that(Transaction transaction) throws CloneNotSupportedException {
		CustomerCreator newCustomerCreator = this.clone();
		newCustomerCreator.getTransactions().add(transaction);
		return newCustomerCreator;
	}

	public CustomerCreator named(String name) throws CloneNotSupportedException {
		CustomerCreator newCustomerCreator = this.clone();
		newCustomerCreator.setName(name);
		return newCustomerCreator;
	}

	public CustomerCreator and(Transaction transaction) throws CloneNotSupportedException {
		return this.that(transaction);
	}
	
	@Override
	protected CustomerCreator clone() throws CloneNotSupportedException {
		CustomerCreator newCustomerCreator = new CustomerCreator();
		newCustomerCreator.setName(this.getName());
		
		for (Transaction transaction : this.getTransactions()) {
			newCustomerCreator.getTransactions().add(transaction);
		}
		
		return newCustomerCreator;
	}
}
