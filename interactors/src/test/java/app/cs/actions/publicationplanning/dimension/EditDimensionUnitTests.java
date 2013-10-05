package app.cs.actions.publicationplanning.dimension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.dimension.DimensionRepository;
import app.cs.model.request.EditDimensionRequest;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EditDimensionUnitTests {
	private EditDimension editDimension;
	@Mock
	private DimensionRepository dimensionRepository;

	@Test
	public void itShouldUpdateTheNewDimensionalObject() {
		// given
		EditDimensionRequest request = new EditDimensionRequest();
		editDimension = new EditDimension(dimensionRepository);
		// when
		editDimension.execute(request);
		// then
		verify(dimensionRepository).save(request.getDimensionalObject());

	}
}
