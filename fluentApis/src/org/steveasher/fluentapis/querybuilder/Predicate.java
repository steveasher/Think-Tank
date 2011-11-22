package org.steveasher.fluentapis.querybuilder;

public interface Predicate<T> {
	public boolean evaluate(T arg);
}
