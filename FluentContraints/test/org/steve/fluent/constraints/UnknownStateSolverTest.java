package org.steve.fluent.constraints;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.steve.fluent.constraints.Digit.*;


public class UnknownStateSolverTest {
	UnknownState<Digit> unknownDigitA, unknownDigitB, unknownDigitC;
	Set<UnknownState<?>> unknownDigits;
	EnumSet<Digit> setOfFirstThreeDigits = EnumSet.of(_1, _2, _3);
	
	@Before
	public void before() {
		unknownDigitA = new UnknownState<Digit>(setOfFirstThreeDigits);
		unknownDigitB = new UnknownState<Digit>(setOfFirstThreeDigits);
		unknownDigitC = new UnknownState<Digit>(setOfFirstThreeDigits);
		
		unknownDigits = new HashSet<UnknownState<?>>();
		unknownDigits.add(unknownDigitA);
		unknownDigits.add(unknownDigitB);
		unknownDigits.add(unknownDigitC);
	}
	
	@Test
	public void someElimination() throws Exception {
		Set<Constraint> constraints = new HashSet<Constraint>();
		constraints.add(unknownDigitA.isNotSameAs(unknownDigitB));
		constraints.add(unknownDigitA.isNotSameAs(unknownDigitC));

		unknownDigitB.is(_2);
		unknownDigitC.is(_3);
		
		assertEquals(setOfFirstThreeDigits, unknownDigitA.possibleStates());
		
		UnknownStateSolver.narrow(unknownDigits, constraints);
		
		assertEquals(_1, unknownDigitA.value());
	}
}
