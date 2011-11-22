package org.steveasher.fluentapis.querybuilder;

import java.util.List;

import org.steveasher.fluentapis.domain.Customer;

public class SingularSpecifiers {
	public final static SingularSpecifier FIRST = new SingularSpecifier() {
		public Customer findAppropriateCustomer(List<Customer> customers) {
			return customers.get(0);
		}
	};
	
	public final static SingularSpecifier LAST = new SingularSpecifier() {
		public Customer findAppropriateCustomer(List<Customer> customers) {
			return customers.get(customers.size() - 1);
		}
	};

	public static interface SingularSpecifier {
		public Customer findAppropriateCustomer(List<Customer> customers);
	}
}
