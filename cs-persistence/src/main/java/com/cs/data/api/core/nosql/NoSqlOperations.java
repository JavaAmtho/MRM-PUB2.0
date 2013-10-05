package com.cs.data.api.core.nosql;

import java.util.List;

import com.cs.data.api.core.IRepository;
import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * Interface that specifies a basic set of NoSql operations. Implemented by
 * {@link NoSqlRepository}. Not often used but a useful option for extensibility
 * and testability (as it can be easily mocked, stubbed, or be the target of a
 * JDK proxy).
 * 
 * @author Amar
 */
public interface NoSqlOperations extends IRepository {



	/**
	 * Gets the object by key.
	 * 
	 * @param <P>
	 *            the generic type
	 * @param key
	 *            the key
	 * @param objectKey
	 *            the object key
	 * @param class1
	 *            the class1
	 * @return the object by key
	 */
	<P> P getObjectByKey(String key, String objectKey, Class<P> class1);

	/**
	 * Find all.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param class1
	 *            the class1
	 * @return the list
	 */
	<T> List<T> findAll(Class<T> class1);

}
