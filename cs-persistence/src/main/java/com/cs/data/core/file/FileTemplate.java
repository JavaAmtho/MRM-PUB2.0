package com.cs.data.core.file;

import java.util.List;

import com.cs.data.api.core.GenericDomain;


/**
 * The Class FileTemplate.
 */
public class FileTemplate implements FileOperations {

	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#update(java.lang.Object)
	 */
	@Override
	public <T> T update(T query) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#delete(java.lang.Object)
	 */
	@Override
	public <T> void delete(T objectToDelete) {
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.file.FileOperations#save(com.cs.data.core.GenericDomain)
	 */
	@Override
	public String save(GenericDomain objectToInsert) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.file.FileOperations#getObjectByKey(com.cs.data.core.GenericDomain, java.lang.Class)
	 */
	@Override
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.file.FileOperations#getAll()
	 */
	@Override
	public List<GenericDomain> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
