package org.steve.fluent.constraints;

public class DeclaritiveConstraintConjunctionConditionSubject<S extends Enum<S>, P extends Enum<P>> extends DeclaritiveConstraintConditionSubject<S> {
	private final DeclaritiveConstraintCondition<P> previousCondition;

	public DeclaritiveConstraintConjunctionConditionSubject(DeclaritiveConstraintCondition<P> previousCondition, UnknownState<S> conditionSubject) {
		super(conditionSubject);
		this.previousCondition = previousCondition;
	}

	@Override
	public DeclaritiveConstraintCondition<S> is(S state) {
		return new DeclaritiveConstraintConjunctionCondition<S, P>(this.previousCondition, this.getConditionSubject(), state);
	}
}
