package app.cs.actions.publicationplanning.dimension;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.dimension.DimensionRepository;
import app.cs.model.request.GetDimensionByIdRequest;
import app.cs.model.response.ResponseModel;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetDimensionsByIdUnitTests {

	private GetPublicationsForGivenChannel getDimensionsById;

	@Mock
	private DimensionRepository dimensionRepository;

	@Before
	public void setUp() {
		getDimensionsById = new GetPublicationsForGivenChannel(
				dimensionRepository);
	}

	@Test
	public void itShouldGetDimensionById() {
		java.util.List<String> groupIds = Collections.EMPTY_LIST;
		GetDimensionByIdRequest model = new GetDimensionByIdRequest();
		model.groupIds = groupIds;
		ResponseModel responseModel = getDimensionsById.execute(model);

		verify(dimensionRepository).getDimensionsBy("Publication",
				model.groupIds);
	}
}
