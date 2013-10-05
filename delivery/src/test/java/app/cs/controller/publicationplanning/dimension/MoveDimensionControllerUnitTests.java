package app.cs.controller.publicationplanning.dimension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.MoveDimensionRequest;
import app.cs.model.response.MultiDimensionalObjectResponse;

@RunWith(MockitoJUnitRunner.class)
public class MoveDimensionControllerUnitTests {

	private MoveDimensionController moveDimensionController;

	@Mock
	private MoveDimensionRequest request;

	@Mock
	private Interactor moveDimension;

	@Test
	public void itShouldCallMoveInteractor() {

		// given

		String newPath = "Marketing Initiative01,Campaign,SubCampaign1";
		String oldPath = "Marketing Initiative01,Campaign,SubCampaign0";
		MultiDimensionalObject objectInMove = new MultiDimensionalObject(
				"CP01", "CP01",
				"Marketing Initiative01,Campaign,Sub-Campaign1", true);

		when(moveDimension.execute(request)).thenReturn(
				new MultiDimensionalObjectResponse(objectInMove));
		moveDimensionController = new MoveDimensionController(moveDimension,
				request);

		// when

		moveDimensionController.move(oldPath, newPath, objectInMove);
		// then

		verify(moveDimension).execute(request);

	}

}
