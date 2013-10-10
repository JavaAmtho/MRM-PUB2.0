package com.cs.data.core.nosql.mongodb;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

@Component
public class MongoRepository implements NoSqlRepository {

	private MongoOperations mongoTemplate;

	private Update update;

	@Autowired
	public MongoRepository(MongoOperations mongoTemplate, Update update) {
		this.mongoTemplate = mongoTemplate;
		this.update = update;
	}

	@Override
	public String save(GenericDomain objectToInsert) {
		mongoTemplate.save(objectToInsert);
		return "inserted";

	}

	@Override
	public <T> T update(T query) {

		return null;
	}

	@Override
	public <T> void delete(T objectToDelete) {
		mongoTemplate.remove(objectToDelete);
	}

	@Override
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		return mongoTemplate.findById(key.getKey(), type);
	}

	@Override
	public <P> P getObjectByKey(String key, String objectKey, Class<P> class1) {
		return null;
	}

	@Override
	public <T, P> void updateById(String id, String field, P valueToAdd,
			Class<T> type) {

		mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(id)),
				update.push(field, valueToAdd), type);

	}

	@Override
	public <T> List<T> findAll(Class<T> class1) {
		return mongoTemplate.findAll(class1);
	}

	@Override
	public <T> T find(String key, Class<T> class1) {

		return mongoTemplate.findById(key, class1);

	}

	@Override
	public <T> List<T> getObjectsBy(String field, String value, Class<T> type) {

		return mongoTemplate.find(Query.query(Criteria.where(field).is(value)),
				type);

	}

	@Override
	public <T, P, Q> List<Q> getObjectForAndCriteria(String secondField,
			P secondFieldValue, String firstField,
			Collection<T> firstFieldValue, Class<Q> type) {

		return mongoTemplate.find(
				Query.query(Criteria.where(firstField).in(firstFieldValue)
						.and(secondField).is(secondFieldValue)), type);

	}

	@Override
	public <T> T getObjectByKey(String id, Class<T> type) {
		return mongoTemplate.findById(id, type);

	}

	@Override
	public <P, T> void delete(String firstField, String secondField,
			List<P> groupId, List<T> possibleDeleteTypes,
			Class<? extends GenericDomain> type) {

		mongoTemplate.remove(
				Query.query(Criteria.where(firstField).in(groupId)
						.and(secondField).in(possibleDeleteTypes)), type);

	}

}
