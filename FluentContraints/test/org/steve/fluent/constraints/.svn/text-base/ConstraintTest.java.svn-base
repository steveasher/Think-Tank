package org.steve.fluent.constraints;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;


public class ConstraintTest {
	final String performRestraintAction = "performRestraint called";
	final Set<String> actions = new HashSet<String>();
	
	@Test
	public void constrainBehavior_PositivePath() throws Exception {
		Constraint constraint = new Constraint(new TestCondition(true), new TestRestraint(new UnknownState<AnimalType>(EnumSet.allOf(AnimalType.class))));
		
		assertTrue(constraint.constrain(null));
		assertEquals(1, actions.size());
		assertTrue(actions.contains(performRestraintAction));
	}
	
	@Test
	public void constrainBehavior_NegativePath() throws Exception {
		Constraint constraint = new Constraint(new TestCondition(false), new TestRestraint(new UnknownState<AnimalType>(EnumSet.allOf(AnimalType.class))));
		
		assertFalse(constraint.constrain(null));
		assertEquals(0, actions.size());
		assertFalse(actions.contains(performRestraintAction));
	}
	
	private final class TestCondition implements Condition {
		private final boolean checkValue;

		public TestCondition(boolean checkValue) {
			this.checkValue = checkValue;
		}
		
		public boolean check(Set<UnknownState<?>> unknowns) {
			return checkValue;
		}
	}

	private final class TestRestraint extends Restraint<AnimalType> {

		TestRestraint(UnknownState<AnimalType> unknown) {
			super(unknown);
		}

		@Override
		protected void performRestraint(UnknownState<AnimalType> unknownState) {
			actions.add(performRestraintAction);			
		}
	}
}
