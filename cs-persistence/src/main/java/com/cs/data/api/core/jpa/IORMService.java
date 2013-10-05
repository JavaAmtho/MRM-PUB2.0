package com.cs.data.api.core.jpa;

public interface IORMService {

	/**
	 * Insert.
	 *
	 * @param objectToSave the object to save
	 */
	public abstract void insert(Object objectToSave);

	/**
	 * Find by.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param id the id
	 * @return the object
	 */
	public abstract <T> Object findBy(Class<T> type, Object id);

}