package org.steve.fluent.constraints;

import java.util.Set;

public class DeclaritiveConstraintConjunctionCondition<S extends Enum<S>, P extends Enum<P>> extends DeclaritiveConstraintPositiveCondition<S> {

	private final DeclaritiveConstraintCondition<P> previousCondition;

	public DeclaritiveConstraintConjunctionCondition(DeclaritiveConstraintCondition<P> previousCondition, UnknownState<S> conditionSubject, S state) {
		super(conditionSubject, state);
		this.previousCondition = previousCondition;
	}
	
	@Override
	public boolean check(Set<UnknownState<?>> unknowns) {
		return super.check(unknowns) && previousCondition.check(unknowns);
	}
}
