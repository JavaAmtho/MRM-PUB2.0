package app.cs.impl.delegate.factory;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import app.cs.impl.model.MultiDimensionalObject;

public class DomainFactoryTests {

	private IDomainFactory factory;

	@Before
	public void setUp() {
		factory = new DomainFactory();

	}

	@Test
	public void itShouldCreateInstanceOfContentObject() {
		// when
		MultiDimensionalObject testObject = factory.getDomainObject("MultiDimensionalObject");

		// then
		assertThat(testObject).isInstanceOf(MultiDimensionalObject.class);

	}
}
