package com.cs.data.core.jpa;


import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cs.data.core.jpa.entities.Student;

public class ORMFactoryUnitTests {


	@Before
	public void setUp() {

	}

	@Test
	public void itShouldGetEntityManagerFactoryForHibernate() {

		//Given
		EntityManager entityManager;
		//when
		entityManager =ORMFactory.getEntityManagerForPersistence("hibernate");
		//then
		Assert.assertNotNull(entityManager);
	}
	
	@Test
	public void itShouldSaveStudentObjectToDataBaseWithHibernate(){
		//given
		Student expectedStudent=new Student("1","Amar","Potghan");
		Student actualStudent;
		
		//when
		EntityManager entityManager=ORMFactory.getEntityManagerForPersistence("hibernate");
		entityManager.persist(expectedStudent);
		actualStudent=entityManager.find(Student.class,  expectedStudent.getId());
		
		//then
		
		Assert.assertEquals(expectedStudent.getId(), actualStudent.getId());
		System.out.println(expectedStudent.getId());
		
	}
	
	@Test
	public void itShouldSaveStudentObjectToDataBaseWithEclipseLink(){
		//given
		Student expectedStudent=new Student("2","Esha","Narang");
		Student actualStudent;
		
		//when
		EntityManager entityManager=ORMFactory.getEntityManagerForPersistence("eclipselink");
		entityManager.persist(expectedStudent);
		actualStudent=entityManager.find(Student.class,  expectedStudent.getId());
		
		//then
		
		Assert.assertEquals(expectedStudent.getId(), actualStudent.getId());
		System.out.println(expectedStudent.getId());
		
	}

}
