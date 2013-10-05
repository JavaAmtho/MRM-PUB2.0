package app.cs.actions.publicationplanning.dimension;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.dimension.DimensionRepository;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.DeleteDimensionRequest;

@RunWith(MockitoJUnitRunner.class)
public class DeleteDimensionUnitTests {

	private DeleteDimension deleteDimension;

	@Mock
	private DimensionRepository dimensionRepository;

	@Test
	public void itShouldDeleteGivenDimension() {

		deleteDimension = new DeleteDimension(dimensionRepository);

		// when

		MultiDimensionalObject dimension = new MultiDimensionalObject();

		DeleteDimensionRequest requestMdel = new DeleteDimensionRequest();

		requestMdel.setDimension(dimension);
		deleteDimension.execute(requestMdel);
		// then

		verify(dimensionRepository).delete(dimension);
	}
}
