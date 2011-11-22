package org.steve.fluent.constraints;

import java.util.*;

public class UnknownStateSolver {
	public static void narrow(Set<UnknownState<?>> relevantUnknowns, Set<Constraint> constraints) throws MisbehavingConstraintException {
		boolean runConstraints = true;
		int previousStates = countPossibleStates(relevantUnknowns);
		
		while (runConstraints) {
			runConstraints = false;
			
			for (Constraint constraint : constraints) {
				runConstraints |= constraint.constrain(relevantUnknowns);
			}
			
			int allPossibilities = countPossibleStates(relevantUnknowns);
			
			if (runConstraints && allPossibilities >= previousStates) {
				throw new MisbehavingConstraintException("Possible states didn't decrease.");
			}
			
			previousStates = allPossibilities;
		}
	}

	private static int countPossibleStates(Set<UnknownState<?>> relevantUnknowns) {
		int allPossibilities = 0;
		for (UnknownState<?> unknown : relevantUnknowns) {
			allPossibilities += unknown.possibleStates().size();
		}
		return allPossibilities;
	}
	
	public static class MisbehavingConstraintException extends Exception {
		public MisbehavingConstraintException(String message) {
			super(message);
		}
	}
}
