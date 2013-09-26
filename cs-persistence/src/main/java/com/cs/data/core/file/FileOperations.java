package com.cs.data.core.file;

import java.util.List;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.IRepository;


/**
 * The Interface FileOperations.
 */
public interface FileOperations extends IRepository {

	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#save(com.cs.data.core.GenericDomain)
	 */
	String save(GenericDomain objectToInsert);

	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#getObjectByKey(com.cs.data.core.GenericDomain, java.lang.Class)
	 */
	<P> P getObjectByKey(GenericDomain key, Class<P> type);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<GenericDomain> getAll();

}
