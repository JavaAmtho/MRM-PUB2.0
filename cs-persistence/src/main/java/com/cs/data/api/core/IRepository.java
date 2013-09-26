package com.cs.data.api.core;



/**
 * The Interface IRepository.
 * TODO
 *Create Interface for each Data/Service/Business
 *Create Common Interface with all above Interface 
 *Inject those using IOC and it composition way create object 
 *Create Common Facade that provide all the above impl base on interface. 
 */
public interface IRepository {
	
	 /**
 	 * Save.
 	 *
 	 * @param objectToInsert the object to insert
 	 * @return the string
 	 */
 	String save(GenericDomain objectToInsert);
	
	/**
	 * Update.
	 *
	 * @param <T> the generic type
	 * @param query the query
	 * @return the t
	 */
	<T> T update(T query);
	
	/**
	 * Delete.
	 *
	 * @param <T> the generic type
	 * @param objectToDelete the object to delete
	 * @return the t
	 */
	<T> void delete(T objectToDelete);
	
	/**
	 * Gets the object by key.
	 *
	 * @param <P> the generic type
	 * @param key the key
	 * @param s the s
	 * @return the object by key
	 */
	<P> P getObjectByKey(GenericDomain key, Class<P> s);
	
}
