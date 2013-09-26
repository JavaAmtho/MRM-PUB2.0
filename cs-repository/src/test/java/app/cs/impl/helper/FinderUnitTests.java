package app.cs.impl.helper;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.inmemory.IInMemoryViewStructure;

@RunWith(MockitoJUnitRunner.class)
public class FinderUnitTests {

	private MultiDimensionalObject publication;

	private Finder finder;

	@Mock
	private IInMemoryViewStructure structure;

	@Before
	public void setUp() {
		finder = new Finder(structure);
		publication = new MultiDimensionalObject("Test", "publication",
				"A,B,C,D,E", true);
		MultiDimensionalObject test = new MultiDimensionalObject("test01",
				"chapter", "A,B,C,D,E,publication", false);
		test.addchild(new MultiDimensionalObject("test03", "test", "test", true));
		publication.addchild(test);
		publication.addchild(new MultiDimensionalObject("test02", "test", "A",
				true));

	}

	@Test
	public void itShouldFindContentObjectWithGivenId() {
		// given
		String id = "test01";

		// when
		MultiDimensionalObject object = finder.find(publication, id);

		// then
		assertThat(object.getId()).isEqualTo(id);

	}

	@Test
	public void itShouldGetLastIndexOfCurrentViewStructure() {
		String viewStructure = "C-M-D-P";
		int index = finder.getLastIndexOf(viewStructure);
		assertThat(index).isEqualTo(3);
	}

	@Test
	public void itShouldGetPublicationIdForGivenPath() {
		// given
		String path = "A,B,C,D,E";
		// when
		when(structure.getCurrentViewStructure()).thenReturn("C-M-P-D");
		String actualPublicationId = finder.getPublicationId(path);
		// then
		verify(structure).getCurrentViewStructure();
		assertThat(actualPublicationId).isEqualTo("D");

	}

}
