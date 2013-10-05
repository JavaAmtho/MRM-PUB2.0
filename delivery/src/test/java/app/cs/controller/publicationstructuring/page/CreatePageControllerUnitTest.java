package app.cs.controller.publicationstructuring.page;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationstructuring.page.CreatePage;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.CreatePageRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class CreatePageControllerUnitTest {

	private CreatePageController createPageController;

	@Mock
	private CreatePage createPage;
	
	@Mock
	private CreatePageRequest createPageRequest;

	@Mock
	private StringResponse response;

	@Before
	public void setUp() {
		createPageController = new CreatePageController(createPage, createPageRequest);

	}

	@Test
	public void itShouldCallPageServiceToCreateAPage() {

		// given
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;
		
		
		
		response.setResponseString("test");		
		
		ResponseModel responseModel = (ResponseModel)response;

		MultiDimensionalObject page = new MultiDimensionalObject(name, type, path, isFolder);

		// when
		when(createPage.execute(createPageRequest))
				.thenReturn(responseModel);
		String actualName = createPageController
				.execute(type, name, path, isFolder);
		// then
		verify(createPage).execute(createPageRequest);
		assertThat(actualName).isEqualTo(name);

	}

}
