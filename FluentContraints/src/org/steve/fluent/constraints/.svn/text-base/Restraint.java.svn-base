package org.steve.fluent.constraints;

public abstract class Restraint<T extends Enum<T>> {
	private final UnknownState<T> unknown;
	
	public Restraint(UnknownState<T> unknown) {
		this.unknown = unknown;
	}
	
	public void restrain() {
		this.performRestraint(this.unknown);
	}

	protected abstract void performRestraint(UnknownState<T> unknownState);
}
