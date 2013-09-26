package app.cs.controller.publicationstructuring.chapter;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationstructuring.chapter.CreateChapter;
import app.cs.model.request.CreateChapterRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class CreateChapterControllerUnitTest {

	private CreateChapterController createChapterController;

	@Mock
	private CreateChapter createChapter;

	@Mock
	private CreateChapterRequest createChapterRequest;

	@Mock
	private StringResponse response;

	@Before
	public void setUp() {
		createChapterController = new CreateChapterController(createChapter,
				createChapterRequest);

	}

	@Test
	public void itShouldCallChapterServiceToCreateAChapter() {

		// given
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;

		response.setResponseString("test");

		ResponseModel responseModel = (ResponseModel) response;

		// when
		when(createChapter.execute(createChapterRequest)).thenReturn(
				responseModel);
		String actualName = createChapterController.execute(type, name, path,
				isFolder);
		// then
		verify(createChapter).execute(createChapterRequest);
		assertThat(actualName).isEqualTo(name);

	}

}
