package org.steve.fluent.constraints.zebra;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
	public static ListCreatorNumber thereAre(int num) {
		return new ListCreatorNumber(num);
	}
	
	public static class ListCreatorNumber {
		private final int num;

		public ListCreatorNumber(int num) {
			this.num = num;
		}
		
		public <T> List<T> of(Class<T> type) {
			List<T> list = new ArrayList<T>();
			
			for (int i = 0; i < this.num; i++) {
				try {
					list.add(type.newInstance());
				} catch (Exception e) {
					// eat it
				}
			}
			
			return list;
		}
	}
	
	public static interface GrabStrategy<I, O> {
		public O grab(I input);
	}
	
	public static <I, O> List<O> grabAll(List<I> input, GrabStrategy<I, O> grabber) {
		List<O> list = new ArrayList<O>();
		for (I i : input) {
			list.add(grabber.grab(i));
		}
		return list;
	}
}