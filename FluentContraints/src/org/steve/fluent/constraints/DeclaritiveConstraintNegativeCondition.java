package org.steve.fluent.constraints;

import java.util.Set;

public class DeclaritiveConstraintNegativeCondition<T extends Enum<T>> extends DeclaritiveConstraintCondition<T> {
	public DeclaritiveConstraintNegativeCondition(UnknownState<T> conditionSubject, T conditionSubjectState) {
		super(conditionSubject, conditionSubjectState);
	}

	public boolean check(Set<UnknownState<?>> unknowns) {
		return unknowns != null && unknowns.contains(conditionSubject) && !conditionSubject.possibleStates().contains(conditionSubjectState);
	}

}
