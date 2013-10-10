package app.cs.impl.inmemory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IInMemoryDimensionGroup;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;


/**
 * The Class DimensionGroupCache.
 */
@Component
public class InMemoryDimensionGroup implements IInMemoryDimensionGroup {

	/** The no sql template for redis. */
	private InMemoryNoSqlRepository redisRepository;

	/**
	 * Instantiates a new dimension group cache.
	 *
	 * @param noSqlTemplateForRedis the no sql template for redis
	 */
	@Autowired
	public InMemoryDimensionGroup(InMemoryNoSqlRepository noSqlTemplateForRedis) {
		this.redisRepository = noSqlTemplateForRedis;

	}

	@Override
	public String getDimensionGroupIdFor(String path) {
		// TODO Auto-generated method stub
		return redisRepository.get(path);
	}

	@Override
	public void updateCache(MultiDimensionalObject dimension, String groupId) {

		delete(dimension);
		redisRepository.set(
				dimension.getPath().concat("," + dimension.getName()), groupId);
	}

	/**
	 * Delete.
	 *
	 * @param dimension the dimension
	 */
	private void delete(MultiDimensionalObject dimension) {
		// TODO Auto-generated method stub

		redisRepository.delete(dimension.getPath());

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#addNewGroup(com.cs.data.business.api.model.IMultiDimensionalObject, java.lang.String)
	 */
	@Override
	public void addNewGroup(MultiDimensionalObject dimension, String groupId) {
		// TODO Auto-generated method stub
		if (dimension.isRoot()) {
			redisRepository.set(dimension.getName(), groupId);
		} else {
			redisRepository.set(
					dimension.getPath().concat("," + dimension.getName()),
					groupId);
		}

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#ifGroupIdExistsFor(java.lang.String)
	 */
	@Override
	public boolean ifGroupIdExistsFor(String path) {
		return getDimensionGroupIdFor(path) == null ? false : true;

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#getAllGroups()
	 */
	@Override
	public List<String> getAllGroups() {
		return null;
	}

}
