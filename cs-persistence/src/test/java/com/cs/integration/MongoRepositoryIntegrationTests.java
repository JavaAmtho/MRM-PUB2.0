package com.cs.integration;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.jpa.entities.Teacher;
import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class MongoRepositoryIntegrationTests {

	private MongoRepository crudRepository;
	@Autowired
	private MongoOperations mongoTemplate;

	private Update update;

	@Before
	public void setUp() {
		update = new Update();

		crudRepository = new MongoRepository(mongoTemplate, update);
	}

	@Test
	public void itShouldInsertAnObjectToMongoDB() {

		// given
		Student amar = new Student("2", "Amar", "First");
		// when
		crudRepository.save(amar);
		Student id = mongoTemplate.findById("2", Student.class);

		// then
		Assert.assertNotNull(id);
		Assert.assertEquals(amar.getId(), id.getId());
	}

	@Test
	public void itShouldGetObjectByKey() {
		// given
		Class<Student> type = Student.class;
		Student expectedAmar = new Student("1", "Amar", "First");
		// when

		crudRepository.save(expectedAmar);
		Student actualAmar = crudRepository.getObjectByKey(expectedAmar, type);
		// then
		Assert.assertEquals(expectedAmar.getId(), actualAmar.getId());

	}

	@Test
	public void itShouldAppendAListInADocument() {
		// given
		Student esha = new Student("0099", "esha", "First");

		// when
		crudRepository.updateById("01", "students", esha, Teacher.class);
		// then
	}

	@Test
	public void itShouldGetObjectsByAndCriteria() {
		// when
		String id = "01";
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("0099", "esha", "First"));
		List<Teacher> teacherWithGivenStudent = crudRepository
				.getObjectForAndCriteria("id", id, "students", students,
						Teacher.class);
		// then
		Assert.assertEquals(teacherWithGivenStudent.size(), 1);

	}

	@Test
	public void itShouldGetObjectByKeyAndType() {

		// given
		String id = "1";
		// when

		Student student = crudRepository.getObjectByKey(id, Student.class);

		// then
		Assert.assertEquals("Amar", student.getName());
		Assert.assertNotNull(student);

	}
}
