package app.cs.controller.publicationplanning.dimension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.cs.actions.publicationplanning.dimension.GetPublicationsForGivenChannel;
import app.cs.model.request.GetDimensionByIdRequest;
import app.cs.model.response.TreeResponse;

@RunWith(MockitoJUnitRunner.class)
public class GetDimensionsByIdConttrollerUnitTests {

	private GetDimensionsByIdController perspectiveController;
	@Mock
	private GetPublicationsForGivenChannel perspective;

	@Mock
	private GetDimensionByIdRequest byIdRequest;

	@Test
	public void itShouldCallGetDimensionsByInteractor() {

		perspectiveController = new GetDimensionsByIdController(perspective);
		// when
		when(perspective.execute(byIdRequest)).thenReturn(
				new TreeResponse(null));
		perspectiveController.getPublicationBy(byIdRequest,"");

		// then
		verify(perspective).execute(byIdRequest);

	}

}
