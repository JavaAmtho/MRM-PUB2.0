package app.cs.impl.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.inmemory.IInMemoryViewStructure;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;

/**
 * The Class ViewStructureCache. TODO move this complete package to data
 * package*** data.cache
 */
@Component
public class InMemoryViewStructure implements IInMemoryViewStructure {

	/** The no sql template for redis. */
	private InMemoryNoSqlRepository noSqlTemplateForRedis;

	/** The key. */
	private final String KEY = "view";

	/**
	 * Instantiates a new view structure cache.
	 * 
	 * @param noSqlTemplateForRedis
	 *            the no sql template for redis
	 */
	@Autowired
	public InMemoryViewStructure(InMemoryNoSqlRepository noSqlTemplateForRedis) {
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	@Override
	public String getCurrentViewStructure() {
		return noSqlTemplateForRedis.get(KEY);
	}

	@Override
	public void setCurrentViewStructure(String key, String value) {
		noSqlTemplateForRedis.set(key, value);
	}

}
