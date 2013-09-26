package app.cs.actions.publicationplanning.dimension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.fest.assertions.Assertions.assertThat;

import app.cs.impl.inmemory.InMemoryViewStructure;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.slicingdicing.TreeBuilder;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.MoveDimensionRequest;

@RunWith(MockitoJUnitRunner.class)
public class MoveDimensionUnitTests {

	private MoveDimension dimension;

	@Mock
	private IDimensionRepository repository;

	@Mock
	private TreeBuilder builder;

	@Mock
	private InMemoryViewStructure structure;

	@Test
	public void itShouldMoveTheGivenDimensionToNewPath() {

		dimension = new MoveDimension(repository, builder, structure);

		MultiDimensionalObject dimensionalObject = new MultiDimensionalObject();
		dimensionalObject.setType("Campaign");
		when(structure.getCurrentViewStructure()).thenReturn(
				"Campaign-MasterPublication-Publication");
		MoveDimensionRequest request = new MoveDimensionRequest();
		request.setObjectInMove(dimensionalObject);
		when(
				repository.move(request.getOldPath(), request.getNewPath(),
						request.getObjectInMove())).thenReturn(
				dimensionalObject);

		// when
		dimension.execute(request);
		// verify
		verify(repository).move(request.getOldPath(), request.getNewPath(),
				dimensionalObject);

	}

	@Test
	public void itShouldReturnAllDimensionTypesFromCurrentTypeOfDimesnionInTheViewStructure() {
		// given
		dimension = new MoveDimension(repository, builder, structure);
		when(structure.getCurrentViewStructure())
				.thenReturn(
						"MarketingInitiative-Campaign-SubCampaign-Communication Plan-Communication Channel");
		// when
		String[] types = dimension.getOrderTypes("Campaign");
		// then
		assertThat(types).hasSize(4);
		assertThat(types[0]).isEqualTo("Campaign");
		assertThat(types[types.length-1]).isEqualTo("Communication Channel");

	}
}
