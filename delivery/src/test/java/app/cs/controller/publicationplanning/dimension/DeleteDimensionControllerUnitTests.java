package app.cs.controller.publicationplanning.dimension;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationplanning.dimension.DeleteDimension;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.DeleteDimensionRequest;

@RunWith(MockitoJUnitRunner.class)
public class DeleteDimensionControllerUnitTests {

	private DeleteDimensionController deleteDimensionController;

	@Mock
	private DeleteDimension deleteDimension;

	@Mock
	private DeleteDimensionRequest deleteDimensionRequest;

	private MultiDimensionalObject dimension;

	@Before
	public void setUp() {

		dimension = new MultiDimensionalObject();
	}

	@Test
	public void itShouldCallDeleteInteractor() {

		deleteDimensionController = new DeleteDimensionController(
				deleteDimension, deleteDimensionRequest);

		dimension.setId("testId");
		deleteDimensionController.create(dimension);

		// verify

		verify(deleteDimension).execute(deleteDimensionRequest);

	}

}
