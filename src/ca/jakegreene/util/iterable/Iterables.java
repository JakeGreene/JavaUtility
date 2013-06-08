package ca.jakegreene.util.iterable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Iterables {
	private Iterables() {}
	
	/**
	 * Create a safe version of <code>unsafe</code>.
	 * @param unsafe
	 * @return An empty Iterable if <code>unsafe</code> is null, otherwise <code>unsafe</code> is returned
	 */
	public static final <E> Iterable<E> safe(Iterable<E> unsafe) {
		if (unsafe == null) {
			return Collections.emptyList();
		} else {
			return unsafe;
		}
	}
	
	/**
	 * Flatten an iterable of iterables into a single iterable.
	 * @param nestedIterable
	 * @return
	 */
	public static final <E, I extends Iterable<E>> Iterable<E> flatten(Iterable<I> nestedIterable) {
		// TODO This currently is in O(n). Can be done in O(1) if we create a NestedIterable and a suitable Iterator
		List<E> flattened = new LinkedList<E>();
		for (I iterable : nestedIterable) {
			for (E element : iterable) {
				flattened.add(element);
			}
		}
		return flattened;
	}
	
}
