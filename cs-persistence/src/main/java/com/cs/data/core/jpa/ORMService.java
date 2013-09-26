package com.cs.data.core.jpa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.data.api.core.jpa.IORMService;



/**
 * The Class ORMService.
 */
public class ORMService implements IORMService {

	/** The entity manager. */
	private EntityManager entityManager;

	/**
	 * Instantiates a new oRM service.
	 */
	public ORMService() {

	}

	/**
	 * Instantiates a new oRM service.
	 *
	 * @param entityManager the entity manager
	 */
	@Autowired
	public ORMService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

	/**
	 * Instantiates a new oRM service.
	 *
	 * @param ormTool the orm tool
	 */
	public ORMService(String ormTool) {
		this.entityManager = ORMFactory
				.getEntityManagerForPersistence("hibernate");
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.jpa.IORMService#insert(java.lang.Object)
	 */
	@Override
	public void insert(Object objectToSave) {
		// TODO Auto-generated method stub
		entityManager.persist(objectToSave);

	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.jpa.IORMService#findBy(java.lang.Class, java.lang.Object)
	 */
	@Override
	public <T> Object findBy(Class<T> type, Object id) {
		// TODO Auto-generated method stub
		return entityManager.find(type, id);
	}

}
