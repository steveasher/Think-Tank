package org.steve.fluent.constraints;

public abstract class DeclaritiveConstraintCondition<S extends Enum<S>> implements Condition {

	protected final UnknownState<S> conditionSubject;
	protected final S conditionSubjectState;
	
	public DeclaritiveConstraintCondition(UnknownState<S> conditionSubject, S conditionSubjectState) {
		this.conditionSubject = conditionSubject;
		this.conditionSubjectState = conditionSubjectState;
	}

	public <R extends Enum<R>> DeclaritiveConstraintRestraintSubject<S, R> then(UnknownState<R> restraintSubject) {
		return new DeclaritiveConstraintRestraintSubject<S, R>(this, restraintSubject);
	}

	public <R extends Enum<R>> DeclaritiveConstraintConditionSubject<R> and(UnknownState<R> anotherConditionSubject) {
		return new DeclaritiveConstraintConjunctionConditionSubject<R, S>(this, anotherConditionSubject);
	}

	public DeclaritiveConstraintConditionSubject<S> or(UnknownState<S> anotherConditionSubject) {
		return new DeclaritiveConstraintDisjunctionConditionSubject<S>(this, anotherConditionSubject);
	}

}