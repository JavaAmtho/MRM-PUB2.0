package app.cs.impl.delegate.factory;

import org.springframework.stereotype.Component;

import app.cs.impl.model.MultiDimensionalObject;

/**
 * A factory for creating Domain objects.
 */
@Component
public class DomainFactory implements IDomainFactory {
	@Override
	public MultiDimensionalObject getDomainObject(String type) {
		if (type.equals("MultiDimensionalObject"))
			return new MultiDimensionalObject();
		if (type.equals("HierarchicalObjet"))
			//TODO refactor should return HierarchicalObject object
			return new MultiDimensionalObject();

		return null;
	}

}
