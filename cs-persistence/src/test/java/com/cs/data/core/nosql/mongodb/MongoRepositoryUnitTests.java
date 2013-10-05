package com.cs.data.core.nosql.mongodb;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.jpa.entities.Teacher;

@RunWith(MockitoJUnitRunner.class)
public class MongoRepositoryUnitTests {

	private MongoRepository mongoRepository;
	@Mock
	private Student student;

	@Mock
	private MongoTemplate mongoTemplate;

	@Mock
	private Update update;

	@Before
	public void setUp() {

		mongoRepository = new MongoRepository(mongoTemplate, update);

	}

	@Test
	public void itShouldInsertAnObjectToMongoDB() {

		// when
		mongoRepository.save(student);

		// then

		verify(mongoTemplate).save(student);

	}

	@Test
	public void itShouldGetObjectByKey() {

		// when
		Student actualStudent = mongoRepository.getObjectByKey(student,
				student.getClass());

		// verify
		verify(mongoTemplate).findById(student.getKey(), student.getClass());

	}

	@Test
	public void itShouldAppendAListInADocument() {

		// given
		Student esha = new Student("0099", "esha", "First");
		Teacher teacher = new Teacher("01", null);

		// when
		mongoRepository.updateById("01", "students", esha, Teacher.class);

		// then
		verify(mongoTemplate).updateFirst(
				Query.query(Criteria.where("id").is("01")),
				update.push("students", esha), teacher.getClass());

	}

	@Test
	public void itShouldGetObjectsByAndCriteria() {
		// given
		String id = "01";
		String name = "esha";
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("0099", "esha", "First"));
		// when

		List<Teacher> teachers = mongoRepository.getObjectForAndCriteria("id",
				id, "students", students, Teacher.class);

		// verify
		verify(mongoTemplate).find(
				Query.query(Criteria.where("students").in(students).and("id")
						.is(id)), Teacher.class);
	}

	@Test
	public void itShouldGetObjectByKeyAndType() {

		// given
		String id = "1";
		// when

		Student student = mongoRepository.getObjectByKey(id, Student.class);

		// then
		verify(mongoTemplate).findById(id, Student.class);

	}

	@Test
	public void itShouldDeleteAllNodesThatSatisfiesGivenCondition() {

		// given

		String id = "teacher01";
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("0099", "esha", "First"));

		List<String> phoneNumbers = new ArrayList<String>();

		phoneNumbers.add("86005293479");
		phoneNumbers.add("9822566485");

		Teacher teacher = new Teacher(id, students, phoneNumbers);

		// when
		mongoRepository.delete("phoneNumbers", "students", phoneNumbers,
				students, teacher.getClass());

		// then
		verify(mongoTemplate).remove(
				Query.query(Criteria.where("phoneNumbers").in(phoneNumbers)
						.and("students").in(students)), teacher.getClass());
	}
}
