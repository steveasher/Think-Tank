package org.steve.fluent.constraints;

import java.util.Set;

public class DeclaritiveConstraintRestraintSubject<S extends Enum<S>, R extends Enum<R>> {
	private final Condition condition;
	private final UnknownState<R> restraintSubject;

	public DeclaritiveConstraintRestraintSubject(Condition condition, UnknownState<R> restraintSubject) {
		this.condition = condition;
		this.restraintSubject = restraintSubject;
	}

	public Constraint is(final R state) {
		final ConditionChainer chain = new ConditionChainer(this.condition, new IsStatePossibleCondition<R>(this.restraintSubject, state));
		return new Constraint(chain, new Restraint<R>(this.restraintSubject) {
			@Override
			protected void performRestraint(UnknownState<R> unknownState) {
				unknownState.is(state);
			}
		});
	}
	
	public Constraint isNot(final R state) {
		return new Constraint(new ConditionChainer(this.condition, new IsStatePossibleCondition<R>(this.restraintSubject, state)), new Restraint<R>(this.restraintSubject) {
			@Override
			protected void performRestraint(UnknownState<R> unknownState) {
				unknownState.isNot(state);
			}
		});
	}
	
	public static class IsStatePossibleCondition<R extends Enum<R>> implements Condition {
		private final UnknownState<R> restraintSubject;
		private final R state;

		public IsStatePossibleCondition(UnknownState<R> restraintSubject, R state) {
			this.restraintSubject = restraintSubject;
			this.state = state;
		}
		
		public boolean check(Set<UnknownState<?>> unknowns) {
			return this.restraintSubject.possibleStates().contains(this.state);
		}
	}
	
	public static class ConditionChainer implements Condition {
		private final Condition condition1;
		private final Condition condition2;

		public ConditionChainer(Condition condition1, Condition condition2) {
			this.condition1 = condition1;
			this.condition2 = condition2;
		}

		public boolean check(Set<UnknownState<?>> unknowns) {
			return condition1.check(unknowns) && condition2.check(unknowns);
		}
	}
}
