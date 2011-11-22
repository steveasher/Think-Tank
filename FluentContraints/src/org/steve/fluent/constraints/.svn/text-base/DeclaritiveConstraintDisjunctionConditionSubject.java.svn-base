package org.steve.fluent.constraints;

public class DeclaritiveConstraintDisjunctionConditionSubject<T extends Enum<T>> extends DeclaritiveConstraintConditionSubject<T> {
	private final DeclaritiveConstraintCondition<T> previousCondition;

	public DeclaritiveConstraintDisjunctionConditionSubject(DeclaritiveConstraintCondition<T> previousCondition, UnknownState<T> conditionSubject) {
		super(conditionSubject);
		this.previousCondition = previousCondition;
	}

	@Override
	public DeclaritiveConstraintCondition<T> is(T state) {
		return new DeclaritiveConstraintDisjunctionCondition<T>(this.previousCondition, this.getConditionSubject(), state);
	}
}
