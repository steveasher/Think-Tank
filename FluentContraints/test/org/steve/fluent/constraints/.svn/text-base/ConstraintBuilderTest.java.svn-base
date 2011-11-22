package org.steve.fluent.constraints;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.steve.fluent.constraints.ConstraintBuilder.*;
import static org.steve.fluent.constraints.AnimalType.*;
import static org.steve.fluent.constraints.Animal.*;
import static org.steve.fluent.constraints.Digit.*;
import static org.steve.fluent.constraints.Phalanx.*;
import static org.steve.fluent.constraints.SkinType.*;

public class ConstraintBuilderTest {
	UnknownState<AnimalType> animalType = new UnknownState<AnimalType>(EnumSet.allOf(AnimalType.class));
	UnknownState<Animal> animal = new UnknownState<Animal>(EnumSet.allOf(Animal.class));
	UnknownState<Phalanx> phalanx = new UnknownState<Phalanx>(EnumSet.allOf(Phalanx.class));
	UnknownState<SkinType> skin = new UnknownState<SkinType>(EnumSet.allOf(SkinType.class));
	Set<UnknownState<?>> unknowns;
	Constraint constraint;
	
	@Before
	public void before() {
		unknowns = new HashSet<UnknownState<?>>();
		unknowns.add(animalType);
		unknowns.add(phalanx);
		unknowns.add(skin);
		constraint = null;
	}
	
	
	@Test
	public void allMammalsHaveFeet() throws Exception {
		constraint = when(phalanx).is(none).then(animalType).isNot(mammal);
		
		phalanx.is(none);
		
		assertTrue(animalType.possibleStates().contains(mammal));
		
		constraint.constrain(unknowns);
		
		assertFalse(animalType.possibleStates().contains(mammal));
	}
	
	@Test
	public void onlyMammalsHaveFur() throws Exception {
		constraint = when(skin).is(furry).then(animalType).is(mammal);
		
		skin.is(furry);
		
		assertNull(animalType.value());
	
		constraint.constrain(unknowns);
		
		assertEquals(mammal, animalType.value());
	}
	
	@Test
	public void onlyBatsHaveWingsAndFur_positivePath() throws Exception {
		constraint = when(skin).is(furry).and(phalanx).is(wings).then(animal).is(bat);
		
		skin.is(furry);
		phalanx.is(wings);
		
		assertNull(animal.value());
		
		constraint.constrain(unknowns);
		
		assertEquals(bat, animal.value());
	}
	
	@Test
	public void onlyBatsHaveWingsAndFur_oneOffPath() throws Exception {
		constraint = when(skin).is(furry).and(phalanx).is(wings).then(animal).is(bat);
		
		skin.is(furry);
		phalanx.is(fins);
		
		assertNull(animal.value());
		
		constraint.constrain(unknowns);
		
		assertNull(animal.value());
	}
	
	@Test
	public void mammalsDoNotHaveScalesOrFeathers_forScales() throws Exception {
		constraint = when(skin).is(scaley).or(skin).is(feathery).then(animalType).isNot(mammal);
		
		skin.is(scaley);
		
		assertNull(animalType.value());
		
		constraint.constrain(unknowns);
		
		assertFalse(animalType.possibleStates().contains(mammal));
	}
	
	@Test
	public void mammalsDoNotHaveScalesOrFeathers_forFeathers() throws Exception {
		constraint = when(skin).is(scaley).or(skin).is(feathery).then(animalType).isNot(mammal);
		
		skin.is(feathery);
		
		assertNull(animalType.value());
		
		constraint.constrain(unknowns);
		
		assertFalse(animalType.possibleStates().contains(mammal));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void unique_superMiniSudoku() throws Exception {
		UnknownState<Digit> cell1A, cell1B, cell2A, cell2B;
		cell1A = new UnknownState<Digit>(EnumSet.of(_1, _2));
		cell1B = new UnknownState<Digit>(EnumSet.of(_1, _2));
		cell2A = new UnknownState<Digit>(EnumSet.of(_1, _2));
		cell2B = new UnknownState<Digit>(EnumSet.of(_1, _2));
		
		Set<UnknownState<?>> myUnknowns = new HashSet<UnknownState<?>>();
		myUnknowns.add(cell1A);
		myUnknowns.add(cell1B);
		myUnknowns.add(cell2A);
		myUnknowns.add(cell2B);
		
		Set<Constraint> constraints	= new HashSet<Constraint>();
		constraints.addAll(unique(cell1A, cell1B));
		constraints.addAll(unique(cell2A, cell2B));
		constraints.addAll(unique(cell1A, cell2A));
		constraints.addAll(unique(cell1B, cell2B));
		
		cell1B.is(_2);
		
		UnknownStateSolver.narrow(myUnknowns, constraints);
		
		assertEquals(_2, cell2A.value());
		assertEquals(_1, cell1A.value());
		assertEquals(_1, cell2B.value());
	}
}
