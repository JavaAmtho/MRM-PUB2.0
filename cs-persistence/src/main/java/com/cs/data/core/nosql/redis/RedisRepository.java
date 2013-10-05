package com.cs.data.core.nosql.redis;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;

/**
 * Class RedisRepository implements InMemoryNoSqlRepository.
 * 
 * @author Amar
 */

@Component
public class RedisRepository implements InMemoryNoSqlRepository {

	/** The redis template. */
	@SuppressWarnings("rawtypes")
	private RedisOperations redisTemplate;

	/**
	 * Instantiates a new redis repository.
	 * 
	 * @param redisTemplate
	 *            the redis template
	 */
	@Autowired
	public RedisRepository(RedisOperations redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/*
	 * Saves given object to configured redis Store.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#save(com.cs.data.core.GenericDomain
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#save(com.cs.data
	 * .core.GenericDomain)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String save(GenericDomain objectToInsert) {
		redisTemplate.opsForHash().put(objectToInsert.getKey(),
				objectToInsert.getObjectKey(), objectToInsert);

		return "inserted";
		// TODO Auto-generated method stub

	}

	/*
	 * Updates given object when configured redis Store.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#update(java.lang.Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#update(T)
	 */
	@Override
	public <T> T update(T query) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Deletes object when configured redis Store.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#delete(java.lang.Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#delete(T)
	 */
	@Override
	public <T> void delete(T objectToDelete) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#delete(java.lang
	 * .String)
	 */
	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		redisTemplate.delete(key);
	}

	/*
	 * Gets object by given object key.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(com.cs.data.core
	 * .GenericDomain, java.lang.Class)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#getObjectByKey(com
	 * .cs.data.core.GenericDomain, java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		// TODO Auto-generated method stub

		P object = (P) redisTemplate.opsForHash().get(key.getKey(),
				key.getObjectKey());

		return object;
	}

	/*
	 * Gets object by string key and object key by type.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(java.lang.String,
	 * java.lang.String, java.lang.Class)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#getObjectByKey(java
	 * .lang.String, java.lang.String, java.lang.Class)
	 */
	@Override
	public <P> P getObjectByKey(String key, String objectkey, Class<P> type) {
		P object = (P) redisTemplate.opsForHash().get(key, objectkey);
		return object;

	}

	/*
	 * Gets all objects.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#findAll(java.lang.Class)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#findAll(java.lang
	 * .Class)
	 */
	@Override
	public <T> List<T> findAll(Class<T> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#get(java.lang.String
	 * )
	 */
	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return (String) redisTemplate.opsForValue().get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#set(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#findAllKeys(java
	 * .lang.String)
	 */
	@Override
	public Set findAllKeys(String pattern) {
		// TODO Auto-generated method stub
		return redisTemplate.keys(pattern);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.core.nosql.redis.InMemoryNoSqlRepository#findAllValues(java
	 * .lang.String)
	 */
	@Override
	public List<String> findAllValues(String keyPattern) {

		return redisTemplate.opsForValue().multiGet(findAllKeys(keyPattern));

	}

}
