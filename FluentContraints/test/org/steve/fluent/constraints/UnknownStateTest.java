package org.steve.fluent.constraints;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.steve.fluent.constraints.AnimalType.*;


public class UnknownStateTest {
	UnknownState<AnimalType> unknown;
	final EnumSet<AnimalType> fullSet = EnumSet.allOf(AnimalType.class);
	
	@Before
	public void before() {
		unknown = new UnknownState<AnimalType>(fullSet);
	}
	
	@Test
	public void construction() throws Exception {
		assertEquals(fullSet, unknown.possibleStates());
	}
	
	@Test
	public void removeSomePossibilities() throws Exception {
		unknown.isNot(bird);
		unknown.isNot(fish);
		
		assertEquals(2, unknown.possibleStates().size());
		assertTrue(unknown.possibleStates().contains(mammal));
		assertTrue(unknown.possibleStates().contains(reptile));
	}
	
	@Test
	public void setToValue() throws Exception {
		assertNull(unknown.value());
		
		unknown.is(bird);
		
		assertEquals(bird, unknown.value());
		assertEquals(EnumSet.of(bird), unknown.possibleStates());
	}
	
	@Test
	public void uniqueUnknowns() throws Exception {
		UnknownState<AnimalType> notTheseAnimals = new UnknownState<AnimalType>(EnumSet.of(bird));
		
		Constraint constraint = unknown.isNotSameAs(notTheseAnimals);
		
		Set<UnknownState<?>> unknowns = new HashSet<UnknownState<?>>();
		unknowns.add(unknown);
		unknowns.add(notTheseAnimals);
		
		assertTrue(constraint.constrain(unknowns));
		
		assertEquals(EnumSet.complementOf(EnumSet.of(bird)), unknown.possibleStates());
	}
}
