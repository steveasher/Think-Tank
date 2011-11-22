package org.steve.fluent.constraints;

import java.util.*;

public class ConstraintBuilder {
	public static <T extends Enum<T>> Set<Constraint> unique(UnknownState<T>... uniqueUnknowns) {
		return unique(Arrays.asList(uniqueUnknowns));
	}
	
	public static <T extends Enum<T>> Set<Constraint> unique(List<UnknownState<T>> uniqueUnknowns) {
		Set<Constraint> constraints = new HashSet<Constraint>();
		
		for (UnknownState<T> outerUnknown : uniqueUnknowns) {
			for (UnknownState<T> innerUnknown : uniqueUnknowns) {
				if (outerUnknown != innerUnknown) {
					constraints.add(outerUnknown.isNotSameAs(innerUnknown));
				}
			}
		}
		
		return constraints;
	}
	
	public static <T extends Enum<T>> DeclaritiveConstraintConditionSubject<T> when(UnknownState<T> conditionSubject) {
		return new DeclaritiveConstraintConditionSubject<T>(conditionSubject);
	}
}
