package com.cs.data.core.nosql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;
import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;
import com.cs.data.core.jpa.entities.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class NoSqlTemplateUnitTests {

	@Autowired
	private NoSqlRepository noSqlRepository;

	@Autowired
	private InMemoryNoSqlRepository inMemoryNoSqlRepository;

	@Before
	public void setUp() {

	}

	@Test
	public void itShouldInsertObjectToMongoDB() {

		// given

		Student studentInserted = new Student("8", "Amar", "First");

		// when

		String id = noSqlRepository.save(studentInserted);
		Student studentRetrieved = noSqlRepository.getObjectByKey(
				studentInserted, Student.class);

		// then

		Assert.assertNotNull(id);
		Assert.assertEquals(studentInserted.getId(), studentRetrieved.getId());
		Assert.assertEquals(studentInserted.getClass(),
				studentRetrieved.getClass());

	}

	@Test
	public void itShouldInsertObjectToRedis() {

		// given

		Student studentInserted = new Student("9", "Katrina", "First");

		// when

		String id = inMemoryNoSqlRepository.save(studentInserted);
		Student studentRetrieved = inMemoryNoSqlRepository.getObjectByKey(
				studentInserted, Student.class);
		// then

		Assert.assertNotNull(id);
		Assert.assertEquals(studentInserted.getId(), studentRetrieved.getId());
		Assert.assertEquals(studentInserted.getClass(),
				studentRetrieved.getClass());

	}

	@Test
	public void itShouldGetAnObjectFromMongoDB() {

		// given
		Student student = new Student("8", "Amar", "First");
		// when
		Student actualStudent = noSqlRepository.getObjectByKey(student,
				Student.class);
		// then
		Assert.assertEquals(student.getName(), actualStudent.getName());

	}

	@Test
	public void itShouldGetAnObjectFromRedis() {
		// given
		Student student = new Student("9", "Katrina", "First");
		inMemoryNoSqlRepository.save(student);
		// when
		Student actualStudent = inMemoryNoSqlRepository.getObjectByKey(student,
				Student.class);
		// then
		Assert.assertEquals(student.getName(), actualStudent.getName());

	}

}
