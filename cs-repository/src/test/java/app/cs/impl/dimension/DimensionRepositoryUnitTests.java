package app.cs.impl.dimension;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.inmemory.InMemoryDimensionGroup;
import app.cs.impl.inmemory.InMemoryViewStructure;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.utils.FileUtils;

import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(MockitoJUnitRunner.class)
public class DimensionRepositoryUnitTests {
	private DimensionRepository dimensionRepository;

	@Mock
	private MultiDimensionalObject dimensionModel;

	@Mock
	private FileUtils fileUtils;

	@Mock
	private InMemoryDimensionGroup cache;

	@Mock
	private MongoRepository repository;

	@Mock
	private DomainFactory factory;

	@Mock
	private InMemoryViewStructure viewStructure;

	@Mock
	private ImageLookup imageLookup;

	@Before
	public void setUp() {

		dimensionRepository = new DimensionRepository(fileUtils, cache,
				repository, factory, viewStructure, imageLookup);

	}

	@Test
	public void itShouldCreateADimension() {

		// given
		String groupId = "group";
		MultiDimensionalObject dimension = new MultiDimensionalObject("c01",
				"campaign", "co01", "CP01", "CP01,MP01,PG01,P01");
		// when
		when(cache.ifGroupIdExistsFor(dimension.getPath())).thenReturn(true);
		when(cache.getDimensionGroupIdFor(dimension.getPath())).thenReturn(
				groupId);
		MultiDimensionalObject createdObject = dimensionRepository
				.createDimension(dimension);

		// then
		verify(cache).ifGroupIdExistsFor(dimension.getPath());
		verify(cache).updateCache(dimension, groupId);
		assertThat(createdObject.getId()).isEqualTo(dimension.getId());

	}

	@Test
	public void itShouldGetAllDimensionInstances() {

		// given
		List<MultiDimensionalObject> expectedModels = new ArrayList<MultiDimensionalObject>();
		expectedModels.add(new MultiDimensionalObject());
		// when
		when(repository.findAll(MultiDimensionalObject.class)).thenReturn(
				expectedModels);
		List<MultiDimensionalObject> dimensions = dimensionRepository
				.getDimensions();
		// then
		verify(repository).findAll(MultiDimensionalObject.class);
		assertThat(dimensions).isNotEmpty();
		assertThat(dimensions).isEqualTo(expectedModels);

	}

	@Test
	public void itShouldGetDimensionsByType() {
		// given
		String type = "Campaign";
		// when

		dimensionRepository.getDimensionsOfType(type);
		// then

		verify(repository).getObjectsBy("type", type,
				MultiDimensionalObject.class);
	}

	@Test
	public void itShouldReturnAllDimensionsByGroupIdsAndType() {
		// given

		List<String> groupIds = null;
		String type2 = null;
		// when
		dimensionRepository.getDimensionsBy(type2, groupIds);
		// then
		verify(repository).getObjectForAndCriteria("type", type2, "groupIds",
				groupIds, MultiDimensionalObject.class);
	}

	@Test
	public void itShouldDeleteAllNodesUnderGivenNode() {

		when(viewStructure.getCurrentViewStructure()).thenReturn(
				"Marketing Initiative-Campaign-SubCampaign");

		// when

		MultiDimensionalObject dimension = new MultiDimensionalObject(
				"Marketing Initiative 01", "Campaign", "test", true);
		List<String> allPossibleDelatableTypes = dimensionRepository
				.getPossibleTypesWhichAreGoingToAffected("Campaign");
		dimensionRepository.delete(dimension);

		// then

		verify(repository).delete("groupIds", "type", dimension.getGroupId(),
				allPossibleDelatableTypes, MultiDimensionalObject.class);
	}

	@Test
	public void itShouldGetPossibleDeletableTypes() {

		when(viewStructure.getCurrentViewStructure()).thenReturn(
				"Marketing Initiative-Campaign-SubCampaign");
		List<String> allPossibleDelatableTypes = dimensionRepository
				.getPossibleTypesWhichAreGoingToAffected("Campaign");
		assertThat(allPossibleDelatableTypes).hasSize(2);
	}

	@Test
	public void itShouldMoveDimensionAndItsChildren() {
		// given
		String groupId = "group";
		MultiDimensionalObject dimension = new MultiDimensionalObject("c01",
				"Publication", "co01", "CP01", "CP01,MP01,PG01,P01");
		// when
		when(cache.ifGroupIdExistsFor(dimension.getPath())).thenReturn(true);
		when(cache.getDimensionGroupIdFor(dimension.getPath())).thenReturn(
				groupId);
		when(viewStructure.getCurrentViewStructure()).thenReturn(
				"Marketing Initiative-Campaign-SubCampaign");
		// when
		dimensionRepository.move(dimension.getPath(), dimension.getPath(),
				dimension);

		// then
		verify(repository).save(dimension);

	}

/*	@Test
	public void itShouldAddImageUrlIfDimensionIsAPublication() {
		// given
		String groupId = "group";
		MultiDimensionalObject dimension = new MultiDimensionalObject("pub01",
				"Publication", "pub01", "pub01", "CP01,MP01,PG01,P01");
		// when
		when(cache.ifGroupIdExistsFor(dimension.getPath())).thenReturn(true);
		when(cache.getDimensionGroupIdFor(dimension.getPath())).thenReturn(
				groupId);
		when(imageLookup.get("pub01")).thenCallRealMethod();
		MultiDimensionalObject createdObject = dimensionRepository
				.createDimension(dimension);

		// then
		verify(cache).ifGroupIdExistsFor(dimension.getPath());
		verify(cache).updateCache(dimension, groupId);
		assertThat(createdObject.getImageUrl()).isNotNull();

	}*/

}
