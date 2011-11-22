package org.steve.fluent.constraints;

import java.util.*;

public class DeclaritiveConstraintPositiveCondition<S extends Enum<S>> extends DeclaritiveConstraintCondition<S> implements Condition {
	public DeclaritiveConstraintPositiveCondition(UnknownState<S> conditionSubject, S conditionSubjectState) {
		super(conditionSubject, conditionSubjectState);
	}
	
	public boolean check(Set<UnknownState<?>> unknowns) {
		return unknowns != null && unknowns.contains(conditionSubject) && conditionSubject.value() == conditionSubjectState;
	}
}
