package app.cs.impl.delegate.factory;

import app.cs.impl.model.MultiDimensionalObject;

public interface IDomainFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.factory.IDomainFactory#getDomainObject(java.lang.String)
	 */
	public abstract MultiDimensionalObject getDomainObject(String type);

}