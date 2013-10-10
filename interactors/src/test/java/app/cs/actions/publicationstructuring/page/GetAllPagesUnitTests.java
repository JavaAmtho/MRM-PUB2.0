package app.cs.actions.publicationstructuring.page;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.chapter.ChapterAndPageRepository;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.GetAllPagesRequest;

@RunWith(MockitoJUnitRunner.class)
public class GetAllPagesUnitTests {

	private GetAllPages getAllPages;

	@Mock
	private ChapterAndPageRepository repository;

	@Before
	public void setUp() throws Exception {
		getAllPages = new GetAllPages(repository);
	}

	@Test
	public void itShouldGetAllPagesFromGivenPublication() {
		// when
		String publicationId = "testPublication";
		GetAllPagesRequest request = new GetAllPagesRequest();
		request.setPublicationId(publicationId);
		getAllPages.execute(request);

		// verify
		verify(repository).getAllPages(publicationId);

	}
}
