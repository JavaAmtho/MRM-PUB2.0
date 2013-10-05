package com.cs.data.core.jpa;

import org.junit.Assert;
import org.junit.Test;

import com.cs.data.core.jpa.entities.Student;

public class ORMServiceUnitTests {

	private ORMService ormService;

	@Test
	public void itShouldSaveAndFindObjectUsingHibernate() {

		// given

		Student student = new Student("4", "Katrina", "Grad");

		// when

		ormService = new ORMService("hibernate");
		ormService.insert(student);
		Student studentActual = (Student) ormService.findBy(Student.class,
				student.getId());
		// then

		Assert.assertEquals(student.getId(), studentActual.getId());
		Assert.assertEquals(student.getClass(), studentActual.getClass());

	}

	@Test
	public void itShouldSaveAndFindObjectUsingEclipseLink() {

		// given

		Student student = new Student("5", "Kareena", "Grad");

		// when

		ormService = new ORMService("eclipselink");
		ormService.insert(student);
		Student studentActual = (Student) ormService.findBy(Student.class,
				student.getId());
		// then

		Assert.assertEquals(student.getId(), studentActual.getId());
		Assert.assertEquals(student.getClass(), studentActual.getClass());

	}

}
