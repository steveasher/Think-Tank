package org.steve.fluent.constraints;

import java.util.Set;

public class Constraint {
	private final Condition condition;
	private final Restraint<?> restraint;
	
	public Constraint(Condition condition, Restraint<?> restraint) {
		this.condition = condition;
		this.restraint = restraint;
	}
	
	public boolean constrain(Set<UnknownState<?>> unknowns) {
		boolean situationFound = condition.check(unknowns);
		
		if (situationFound) restraint.restrain();
		
		return situationFound;
	}
}
