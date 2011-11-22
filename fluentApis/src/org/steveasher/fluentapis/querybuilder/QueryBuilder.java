package org.steveasher.fluentapis.querybuilder;

import java.util.List;

import org.steveasher.fluentapis.Util;
import org.steveasher.fluentapis.domain.Customer;
import org.steveasher.fluentapis.querybuilder.SingularSpecifiers.SingularSpecifier;

public class QueryBuilder {

	public CustomerQuery<List<Customer>> whichCustomers(final Predicate<Customer> predicate) {
		return new CustomerQuery<List<Customer>>() {
			public List<Customer> ask(List<Customer> customers) {
				return Util.select(customers, predicate);
			}
		};
	}

	public CustomerQuery<Customer> whichCustomer(final SingularSpecifier specifier, final Predicate<Customer> predicate) {
		return new CustomerQuery<Customer>() {
			public Customer ask(List<Customer> customers) {
				return specifier.findAppropriateCustomer(Util.select(customers, predicate));
			}
		};
	}

	public CustomerQuery<Integer> howManyCustomers(final Predicate<Customer> predicate) {
		return new CustomerQuery<Integer>() {
			public Integer ask(List<Customer> customers) {
				return Util.select(customers, predicate).size();
			}
		};
	}

	public CustomerQuery<Boolean> haveAnyCustomers(final Predicate<Customer> predicate) {
		return new CustomerQuery<Boolean>() {
			public Boolean ask(List<Customer> customers) {
				return Util.select(customers, predicate).size() > 0;
			}
		};
	}
}
