package app.cs.interfaces.assortment;

import app.cs.impl.model.MultiDimensionalObject;

public interface IAssortmentRepository {

	/**
	 * Save given chapter.
	 * 
	 * @param chapter
	 *            the chapter
	 * @return the string
	 */
	public abstract String save(MultiDimensionalObject chapter);

	/**
	 * Deletes given chapter for given old path.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param oldPath
	 *            the old path
	 * @return the string
	 */

	MultiDimensionalObject getDomain(String type);

	public abstract void copy(MultiDimensionalObject assortment);

	String updateAssortment(MultiDimensionalObject assortment);

}