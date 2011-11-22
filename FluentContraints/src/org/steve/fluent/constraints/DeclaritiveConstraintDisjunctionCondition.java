package org.steve.fluent.constraints;

import java.util.Set;

public class DeclaritiveConstraintDisjunctionCondition<T extends Enum<T>> extends DeclaritiveConstraintPositiveCondition<T> {
	private final DeclaritiveConstraintCondition<T> previousCondition;

	public DeclaritiveConstraintDisjunctionCondition(DeclaritiveConstraintCondition<T> previousCondition, UnknownState<T> conditionSubject, T state) {
		super(conditionSubject, state);
		this.previousCondition = previousCondition;
	}
	
	@Override
	public boolean check(Set<UnknownState<?>> unknowns) {
		return super.check(unknowns) || previousCondition.check(unknowns);
	}
}
