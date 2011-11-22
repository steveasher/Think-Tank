package org.steveasher.fluentapis.customercreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.steveasher.fluentapis.domain.Item.Book;
import static org.steveasher.fluentapis.domain.Item.CandyBar;
import static org.steveasher.fluentapis.domain.Item.CompactDisc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.steveasher.fluentapis.domain.Customer;
import org.steveasher.fluentapis.domain.Item;
import org.steveasher.fluentapis.domain.Transaction;

public class CustomerCreatorTest {
	private final CustomerCreator createCustomer = new CustomerCreator();
	private final TransactionCreator bought = new TransactionCreator();
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

	
	@Test
	public void createCustomerWithTraditionalStyle() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(Item.Book);
		items.add(Item.CompactDisc);
		
		Transaction transaction = new Transaction();
		transaction.setItems(items);
		transaction.setDate(dateFormatter.parse("01/01/2007"));
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		
		Customer customer = new Customer();
		customer.setTransactions(transactions);
		
		assertTrue(customer.getTransactions().get(0).getItems().contains(Book));
		assertTrue(customer.getTransactions().get(0).getItems().contains(CompactDisc));
		assertEquals(2, customer.getTransactions().get(0).getItems().size());
		assertEquals(dateFormatter.parse("01/01/2007"), customer.getTransactions().get(0).getDate());
	}
	
	@Test
	public void createCustomerWithCreator() throws Exception {
		Customer customer = createCustomer.that(bought.items(Book, CompactDisc).on("01/01/2007"));
		
		assertTrue(customer.getTransactions().get(0).getItems().contains(Book));
		assertTrue(customer.getTransactions().get(0).getItems().contains(CompactDisc));
		assertEquals(2, customer.getTransactions().get(0).getItems().size());
		assertEquals(dateFormatter.parse("01/01/2007"), customer.getTransactions().get(0).getDate());
	}
	
	@Test
	public void createCustomerWithName() throws Exception {
		Customer customer = createCustomer.named("Bob");
		
		assertEquals("Bob", customer.getName());
	}
	
	@Test
	public void makeSureTransactionsAreClonedForAddingItems() throws Exception {
		TransactionCreator firstTransaction = bought.item(CandyBar);
		TransactionCreator secondTransaction = bought.item(CompactDisc);
		
		assertNotSame(firstTransaction, secondTransaction);
	}
	
	@Test
	public void makeSureTransactionsAreClonedForAddingDate() throws Exception {
		TransactionCreator firstTransaction = bought.on("01/01/2007");
		TransactionCreator secondTransaction = bought.on("01/01/2007");
		
		assertNotSame(firstTransaction, secondTransaction);
	}
	
	@Test
	public void makeSureCustomerAreClonedForAddingTransactions() throws Exception {
		Customer firstCustomer = createCustomer.that(bought.item(CandyBar));
		Customer secondCustomer = createCustomer.that(bought.item(CandyBar));
		
		assertNotSame(firstCustomer, secondCustomer);
	}
	
	
	@Test
	public void makeSureCustomerAreClonedForAddingName() throws Exception {
		Customer firstCustomer = createCustomer.named("Bob");
		Customer secondCustomer = createCustomer.named("Bob");
		
		assertNotSame(firstCustomer, secondCustomer);
	}
}
