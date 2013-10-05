package app.cs.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The Class ArrayUtils.
 */
public class ArrayUtils {

	/**
	 * Skip.
	 *
	 * @param inputs the inputs
	 * @param skipIndex the skip index
	 * @return the string[]
	 */
	public String[] skip(String[] inputs, int skipIndex) {

		inputs = Arrays.copyOfRange(inputs, 1, inputs.length);
		return inputs;

	}

	/**
	 * Union.
	 *
	 * @param <T> the generic type
	 * @param list1 the list1
	 * @param list2 the list2
	 * @return the list
	 */
	public <T> List<T> union(List<T> list1, List<T> list2) {
		Set<T> set = new HashSet<T>();

		set.addAll(list1);
		set.addAll(list2);

		return new ArrayList<T>(set);
	}

	/**
	 * Intersection.
	 *
	 * @param <T> the generic type
	 * @param list1 the list1
	 * @param list2 the list2
	 * @return the list
	 */
	public <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();

		for (T t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}
}
