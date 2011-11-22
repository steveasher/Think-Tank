package org.steveasher.fluentapis.querybuilder;

import java.util.List;

import org.steveasher.fluentapis.domain.Customer;

public interface CustomerQuery<T> {
	public T ask(List<Customer> customers);
}
