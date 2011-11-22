package org.steve.fluent.constraints;

public class DeclaritiveConstraintConditionSubject<T extends Enum<T>> {
	private final UnknownState<T> conditionSubject;

	public DeclaritiveConstraintConditionSubject(UnknownState<T> conditionSubject) {
		this.conditionSubject = conditionSubject;
	}

	public DeclaritiveConstraintCondition<T> is(T state) {
		return new DeclaritiveConstraintPositiveCondition<T>(conditionSubject, state);
	}
	
	public DeclaritiveConstraintCondition<T> isNot(T state) {
		return new DeclaritiveConstraintNegativeCondition<T>(conditionSubject, state);
	}
	
	protected UnknownState<T> getConditionSubject() {
		return this.conditionSubject;
	}
}
 