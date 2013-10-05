package com.cs.data.core.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


/**
 * A factory for creating ORM objects.
 */
public class ORMFactory {

	/**
	 * Gets the entity manager for persistence.
	 *
	 * @param persistenceUnitName the persistence unit name
	 * @return the entity manager for persistence
	 */
	public static EntityManager getEntityManagerForPersistence(
			final String persistenceUnitName) {
				return Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();

	}
}
