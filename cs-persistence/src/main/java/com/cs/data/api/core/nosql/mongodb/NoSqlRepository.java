package com.cs.data.api.core.nosql.mongodb;

import java.util.Collection;
import java.util.List;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.nosql.NoSqlOperations;

public interface NoSqlRepository extends NoSqlOperations {

	/**
	 * Update by id.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <P>
	 *            the generic type
	 * @param id
	 *            the id
	 * @param field
	 *            the field
	 * @param valueToAdd
	 *            the value to add
	 * @param type
	 *            the type
	 */
	public abstract <T, P> void updateById(String id, String field,
			P valueToAdd, Class<T> type);

	/**
	 * Find.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param key
	 *            the key
	 * @param class1
	 *            the class1
	 * @return the t
	 */
	public abstract <T> T find(String key, Class<T> class1);

	/**
	 * Gets the objects by type.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * @param type
	 *            the type
	 * @return the objects by
	 */
	public abstract <T> List<T> getObjectsBy(String field, String value,
			Class<T> type);

	/**
	 * Gets the object for and criteria.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <P>
	 *            the generic type
	 * @param <Q>
	 *            the generic type
	 * @param secondField
	 *            the second field
	 * @param secondFieldValue
	 *            the second field value
	 * @param firstField
	 *            the first field
	 * @param firstFieldValue
	 *            the first field value
	 * @param type
	 *            the type
	 * @return the object for and criteria
	 */
	public abstract <T, P, Q> List<Q> getObjectForAndCriteria(
			String secondField, P secondFieldValue, String firstField,
			Collection<T> firstFieldValue, Class<Q> type);

	/**
	 * Gets the object by key.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @return the object by key
	 */
	public abstract <T> T getObjectByKey(String id, Class<T> type);

	public abstract <P, T> void delete(String firstField, String secondField,
			List<P> groupId, List<T> possibleDeleteTypes,
			Class<? extends GenericDomain> class1);

}