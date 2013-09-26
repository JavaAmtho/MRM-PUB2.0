package com.cs.data.core.nosql.redis;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.jpa.entities.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class RedisRepositoryIntegrationTests {

	private RedisRepository crudRepository;

	@Autowired
	private RedisOperations<?, ?> redisTemplate;
	private ConfigurableApplicationContext context;

	@Before
	public void setUp() throws Exception {

		crudRepository = new RedisRepository(redisTemplate);
	}

	@Test
	public void itShouldInsertAnObjectToRedis() {

		// given
		Student amar = new Student("1", "Amar", "First");

		// When

		String id = crudRepository.save(amar);

		// then
		Assert.assertNotNull(id);

	}

	@Test
	public void itShouldReturnAnObjectByKey() {
		// given
		Student amar = new Student("2", "Esha", "First");
		crudRepository.save(amar);
		// when
		Student actualStudent = crudRepository.getObjectByKey(amar,
				Student.class);

		System.out.println(actualStudent);

		// then
		Assert.assertEquals(amar.getId(), actualStudent.getId());
	}

	@Test
	public void itShouldSetKeyAsStringAndValueAsStringToRedis() {
		// given
		String key = "key";
		String value = "value";
		String finalResult = "success";
		// when

		crudRepository.set(key, value);

	}

	@Test
	public void itShouldGetKeyAsStringAndValueAsStringToRedis() {
		// given
		String key = "key";
		String value = "value";
		String finalResult = "success";

		// when
		String actual = crudRepository.get(key);
		// then

		Assert.assertEquals(value, actual);
	}

	@Test
	public void itShouldDeleteObjectfromRedis() {
		// given
		String key = "key";

		// when
		crudRepository.delete(key);
		// then

	}

	@Test
	public void testIfItReturnsNullWhenGivenWrongKey() {
		// given
		String key = "watever";
		// when
		String value = crudRepository.get(key);

	}

	@Test
	public void itShouldGetAllKeys() {
		//
		String pattern = "*";

		// when

		Set keys = crudRepository.findAllKeys(pattern);

		System.out.println(keys);
		// then
		Assert.assertNotNull(keys);

	}

	@Test
	public void itShouldGetAllValues() {
		// when

		List<String> values = crudRepository.findAllValues("*");
		System.out.println(values);
	}

}