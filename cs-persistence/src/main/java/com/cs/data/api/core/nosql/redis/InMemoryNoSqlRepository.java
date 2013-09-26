package com.cs.data.api.core.nosql.redis;

import java.util.List;
import java.util.Set;

import com.cs.data.api.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.redis.RedisRepository;

/**
 * Interface that specifies a basic set InMemory Databse Operations. Implemented
 * by {@link RedisRepository}. A useful option for extensibility and testability
 * (as it can be easily mocked, stubbed, or be the target of a JDK proxy).
 * 
 * @author Amar
 * 
 */
public interface InMemoryNoSqlRepository extends NoSqlOperations {

	/**
	 * Method that deletes the value of given String key.
	 * 
	 * @param key
	 *            the key
	 */
	public abstract void delete(String key);

	/**
	 * Gets the value for given key.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public abstract String get(String key);

	/**
	 * Sets the value against given key.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public abstract void set(String key, String value);

	/**
	 * Find all keys.
	 * 
	 * @param pattern
	 *            the pattern
	 * @return the sets the
	 */
	public abstract Set findAllKeys(String pattern);

	/**
	 * Find all values.
	 * 
	 * @param keyPattern
	 *            the key pattern
	 * @return the list
	 */
	public abstract List<String> findAllValues(String keyPattern);

}