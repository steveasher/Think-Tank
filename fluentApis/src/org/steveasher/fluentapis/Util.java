package org.steveasher.fluentapis;

import java.util.ArrayList;
import java.util.List;

import org.steveasher.fluentapis.querybuilder.Predicate;

public class Util {
	public static <T> List<T> select(List<T> list, Predicate<T> pred) {
		List<T> newList = new ArrayList<T>();
		for (T element : list) {
			if (pred.evaluate(element)) {
				newList.add(element);
			}
		}
		return newList;
	}
}
