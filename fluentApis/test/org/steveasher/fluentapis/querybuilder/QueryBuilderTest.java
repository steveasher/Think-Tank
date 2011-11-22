package org.steveasher.fluentapis.querybuilder;

import static org.junit.Assert.*;
import static org.steveasher.fluentapis.domain.Item.*;
import static org.steveasher.fluentapis.querybuilder.PredicateWrappers.*;
import static org.steveasher.fluentapis.querybuilder.SingularSpecifiers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.steveasher.fluentapis.customercreator.CustomerCreator;
import org.steveasher.fluentapis.customercreator.TransactionCreator;
import org.steveasher.fluentapis.domain.Customer;

public class QueryBuilderTest {
	private final CustomerCreator createCustomer = new CustomerCreator();
	private final TransactionCreator buys = new TransactionCreator();
	private final QueryBuilder questionThatAsks = new QueryBuilder();
		
	@Test
	public void whoBoughtABook() throws Exception {
		CustomerCreator bookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator cdBuyer = createCustomer.that(buys.item(CompactDisc));
		
		List<Customer> customers = listWith(bookBuyer, cdBuyer);
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(item(Book)));
		
		List<Customer> output = question.ask(customers);
		assertEquals(1, output.size());
		assertTrue(output.contains(bookBuyer));
	}
	
	@Test
	public void doNotMatchUnlessAllItemsPurchased() throws Exception {
		CustomerCreator bookAndCDBuyer = createCustomer.that(buys.items(Book, CompactDisc));
		CustomerCreator bookBuyer = createCustomer.that(buys.items(Book));
		
		List<Customer> customers = listWith(bookAndCDBuyer, bookBuyer);
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(items(Book, CompactDisc)));
		
		List<Customer> output = question.ask(customers);
		assertEquals(1, output.size());
		assertTrue(output.contains(bookAndCDBuyer));
	}
	
	@Test
	public void whoBoughtABookWithTwoBookBuyers() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator cdBuyer = createCustomer.that(buys.item(CompactDisc));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer, cdBuyer);
		
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(item(Book)));
		
		List<Customer> output = question.ask(customers);
		assertTrue(output.contains(firstBookBuyer));
		assertTrue(output.contains(secondBookBuyer));
		assertEquals(2, output.size());
	}
	
	@Test
	public void whoBoughtACandyBarWhenNoOneDid() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator cdBuyer = createCustomer.that(buys.item(CompactDisc));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer, cdBuyer);
		
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(item(CandyBar)));
		
		List<Customer> output = question.ask(customers);
		assertEquals(0, output.size());
	}
	
	@Test
	public void howManyCustomersBoughtABook() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator cdBuyer = createCustomer.that(buys.item(CompactDisc));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer, cdBuyer);
		
		CustomerQuery<Integer> question = questionThatAsks.howManyCustomers(bought(item(Book)));
		
		Integer output = question.ask(customers);
		assertEquals(2, output);
	}
	
	@Test
	public void howManyCustomersBoughtACandyBarWhenNoOneDid() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator cdBuyer = createCustomer.that(buys.item(CompactDisc));
		
		List<Customer> customers = listWith(firstBookBuyer, cdBuyer);
		
		CustomerQuery<Integer> question = questionThatAsks.howManyCustomers(bought(item(CandyBar)));
		
		Integer output = question.ask(customers);
		assertEquals(0, output);
	}
	
	@Test
	public void haveAnyCustomersBoughtACD() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book));
		CustomerCreator cdBuyer = createCustomer.that(buys.item(CompactDisc));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer, cdBuyer);
		
		CustomerQuery<Boolean> question = questionThatAsks.haveAnyCustomers(bought(item(Book)));
		
		Boolean output = question.ask(customers);
		assertEquals(true, output);
	}
	
	@Test
	public void whichCustomersBoughtABookOnDate() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book).on("01/01/2007"));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book).on("02/02/2007"));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer);
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(item(Book), on("01/01/2007")));
		
		List<Customer> output = question.ask(customers);
		assertTrue(output.contains(firstBookBuyer));
		assertEquals(1, output.size());
	}
	
	@Test
	public void whichCustomersBoughtABookOnDateWhenNoOneDid() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book).on("01/01/2007"));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book).on("02/02/2007"));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer);
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(item(Book), on("03/03/2007")));
		
		List<Customer> output = question.ask(customers);
		assertEquals(0, output.size());
	}
	
	@Test
	public void whichCustomersBoughtABookBetweenDates() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book).on("01/01/2007"));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book).on("01/02/2007"));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer);
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(item(Book), between("12/31/2006", "01/01/2007")));
		
		List<Customer> output = question.ask(customers);
		assertTrue(output.contains(firstBookBuyer));
		assertEquals(1, output.size());
	}
	
	@Test
	public void whichCustomersBoughtAnythingOnDate() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book).on("01/01/2007"));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(CandyBar).on("01/02/2007"));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer);
		
		CustomerQuery<List<Customer>> question = questionThatAsks.whichCustomers(bought(anything(), between("12/31/2006", "01/01/2007")));
		
		List<Customer> output = question.ask(customers);
		assertTrue(output.contains(firstBookBuyer));
		assertEquals(1, output.size());
	}
	
	@Test
	public void whichCustomerLastBoughtABook() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book).on("01/01/2007"));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book).on("01/02/2007"));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer);
		
		CustomerQuery<Customer> question = questionThatAsks.whichCustomer(LAST, bought(item(Book)));
		
		Customer output = question.ask(customers);
		assertEquals(secondBookBuyer, output);
	}
	
	@Test
	public void whichCustomerFirstBoughtABook() throws Exception {
		CustomerCreator firstBookBuyer = createCustomer.that(buys.item(Book).on("01/01/2007"));
		CustomerCreator secondBookBuyer = createCustomer.that(buys.item(Book).on("01/02/2007"));
		
		List<Customer> customers = listWith(firstBookBuyer, secondBookBuyer);
		
		CustomerQuery<Customer> question = questionThatAsks.whichCustomer(FIRST, bought(item(Book)));
		
		Customer output = question.ask(customers);
		assertEquals(firstBookBuyer, output);
	}

	private List<Customer> listWith(Customer... customers) {
		return Arrays.asList(customers);
	}
}
