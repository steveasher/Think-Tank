package org.steve.fluent.constraints.zebra;

import static org.junit.Assert.*;
import static org.steve.fluent.constraints.ConstraintBuilder.*;
import static org.steve.fluent.constraints.zebra.Color.*;
import static org.steve.fluent.constraints.zebra.Nationality.*;
import static org.steve.fluent.constraints.zebra.Pet.*;
import static org.steve.fluent.constraints.zebra.Cigarettes.*;
import static org.steve.fluent.constraints.zebra.House.*;
import static org.steve.fluent.constraints.zebra.Beverage.*;
import static org.steve.fluent.constraints.zebra.ListCreator.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.steve.fluent.constraints.*;

public class ZebraPuzzleTest {
	private static final int FIRST = 0;
	private static final int MIDDLE = 2;
	Set<Constraint> rules;
	Set<UnknownState<?>> allUnknowns;
	
	@Before
	public void before() {
		rules = new HashSet<Constraint>();
		allUnknowns = new HashSet<UnknownState<?>>();
	}
	
	@Test
	public void ZebraPuzzle() throws Exception {
	// 1. There are five houses.
		List<House> houses = thereAre(5).of(House.class);
		
	// 1.5. All qualities are unique across houses
		rules.addAll(unique(grabAll(houses, beverageGrabber)));
		rules.addAll(unique(grabAll(houses, cigarettesGrabber)));
		rules.addAll(unique(grabAll(houses, colorGrabber)));
		rules.addAll(unique(grabAll(houses, nationalityGrabber)));
		rules.addAll(unique(grabAll(houses, petGrabber)));
		
		for (House house : houses) {
			// 2. The Englishman lives in the red house.
			rules.addAll(pair(house.nationality, Englishman).with(house.color, red));
		
			// 3. The Spaniard owns the dog.
			rules.addAll(pair(house.nationality, Spaniard).with(house.pet, dog));
		
			// 4. Coffee is drunk in the green house.
			rules.addAll(pair(house.beverage, coffee).with(house.color, green));
		
			// 5. The Ukrainian drinks tea.
			rules.addAll(pair(house.nationality, Ukrainian).with(house.beverage, tea));
			
			// 6. The green house is immediately to the right of the ivory house.
			final int index = houses.indexOf(house);
			if (index < houses.size() - 1) rules.add(when(house.color).is(ivory).then(houses.get(index + 1).color).is(green));
		
			// 7. The Old Gold smoker owns snails.
			rules.addAll(pair(house.cigarettes, Old_Gold).with(house.pet, snails));
			
			// 8. Kools are smoked in the yellow house.
			rules.addAll(pair(house.cigarettes, Kools).with(house.color, yellow));
			
			// 13. The Lucky Strike smoker drinks orange juice.
			rules.addAll(pair(house.cigarettes, Lucky_Strike).with(house.beverage, orange_juice));
		
			// 14. The Japanese smokes Parliaments.
			rules.addAll(pair(house.nationality, Japanese).with(house.cigarettes, Parliament));
			
			// 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
			for (House nonNeighbor : findNonNeighbors(houses, house)) {
				rules.add(when(house.cigarettes).is(Chesterfield).then(nonNeighbor.pet).isNot(fox));
				rules.add(when(house.pet).is(fox).then(nonNeighbor.cigarettes).isNot(Chesterfield));
			}
			
			// 12. Kools are smoked in the house next to the house where the horse is kept.
			for (House nonNeighbor : findNonNeighbors(houses, house)) {
				rules.add(when(house.cigarettes).is(Kools).then(nonNeighbor.pet).isNot(horse));
				rules.add(when(house.pet).is(horse).then(nonNeighbor.cigarettes).isNot(Kools));
			}
			
			// 15. The Norwegian lives next to the blue house.
			for (House nonNeighbor : findNonNeighbors(houses, house)) {
				rules.add(when(house.nationality).is(Norwegian).then(nonNeighbor.color).isNot(blue));
				rules.add(when(house.color).is(blue).then(nonNeighbor.nationality).isNot(Norwegian));
			}
		}
		 
		// 9. Milk is drunk in the middle house.
		houses.get(MIDDLE).beverage.is(milk);
		
		// 10. The Norwegian lives in the first house.
		houses.get(FIRST).nationality.is(Norwegian);
		
		
		
		for (House house : houses) {
			allUnknowns.add(house.beverage);
			allUnknowns.add(house.cigarettes);
			allUnknowns.add(house.color);
			allUnknowns.add(house.nationality);
			allUnknowns.add(house.pet);
		}
		
		UnknownStateSolver.narrow(allUnknowns, rules);
		
		for (int i = 0; i < houses.size(); i++) {
			System.out.println(i + "--> " + houses.get(i));
		}
		
		List<House> zebraHouses = new ArrayList<House>();
		for (House house : houses) {
			if (house.pet.value() == zebra) zebraHouses.add(house);
		}
		
		assertEquals(1, zebraHouses.size());
		assertEquals(Japanese, zebraHouses.get(0).nationality.value());
	}

	private List<House> findNeighbors(List<House> houses, House house) {
		List<House> neighbors = new ArrayList<House>();
		int index = houses.indexOf(house);
		
		if (index > 0) {
			neighbors.add(houses.get(index - 1));
		}
		
		if (index < houses.size() - 1) {
			neighbors.add(houses.get(index + 1));
		}
		
		return neighbors;
	}
	
	public List<House> findNonNeighbors(List<House> houses, House house) {
		List<House> nonNeighbors = new ArrayList<House>();
		List<House> neighbors = findNeighbors(houses, house);
		
		for (House theHouse : houses) {
			if (!neighbors.contains(theHouse)) {
				nonNeighbors.add(theHouse);
			}
		}
		
		return nonNeighbors;
	}
	
	private <F extends Enum<F>> PairingFirstPart<F> pair(UnknownState<F> subject1, F value1) {
		return new PairingFirstPart<F>(subject1, value1);
	}
	
	private static class PairingFirstPart<F extends Enum<F>> {
		private final UnknownState<F> subject1;
		private final F value1;

		public PairingFirstPart(UnknownState<F> subject1, F value1) {
			this.subject1 = subject1;
			this.value1 = value1;
		}
		
		public <S extends Enum<S>> List<Constraint> with(UnknownState<S> subject2, S value2) {
			List<Constraint> constraints = new ArrayList<Constraint>();
			
			constraints.add(when(subject1).is(value1).then(subject2).is(value2));
			constraints.add(when(subject2).is(value2).then(subject1).is(value1));
			constraints.add(when(subject1).isNot(value1).then(subject2).isNot(value2));
			constraints.add(when(subject2).isNot(value2).then(subject1).isNot(value1));
			
			return constraints;
		}
	}
}
