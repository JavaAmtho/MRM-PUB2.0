package app.cs.controller.publicationplanning.dimension;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.EditDimensionRequest;

@RunWith(MockitoJUnitRunner.class)
public class EditDimensionControllerUnitTests {

	private EditDimensionController editDimensionController;
	@Mock
	Interactor editDimension;

	@Test
	public void itShouldCallEditDimensionInteractor() {

		// given
		EditDimensionRequest request = new EditDimensionRequest();
		MultiDimensionalObject dimensionalObject = new MultiDimensionalObject();
		editDimensionController = new EditDimensionController(editDimension,
				request);

		// when
		editDimensionController.execute(dimensionalObject);
		// then

		verify(editDimension).execute(request);

	}
}
