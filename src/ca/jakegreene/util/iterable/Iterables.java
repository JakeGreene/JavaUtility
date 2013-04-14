package ca.jakegreene.util.iterable;

import java.util.Collections;

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
}
