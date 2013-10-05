package app.cs.impl.slicingdicing;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.dimension.DimensionRepository;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IInMemoryDimensionGroup;

@RunWith(MockitoJUnitRunner.class)
public class TreeBuilderUnitTests {

	private TreeBuilder treeBuilder;

	@Mock
	private IInMemoryDimensionGroup cache;

	@Mock
	private DimensionRepository dimensionRepository;

	@Before
	public void setUp() {
		treeBuilder = new TreeBuilder(dimensionRepository);
	}

	public void itShouldParseGivenStringWithDelimeter() {
		// given
		String structure = "Campaign-MasterPublication-Publication";

		// when
		String[] rules = treeBuilder.getTypes(structure);
		// then
		assertThat(rules).isNotEmpty();
		assertThat(rules.length).isEqualTo(3);
	}

	@Test
	public void itShouldSaparateNotConnectedTrees() {

		// given
		String type = "Campaign";
		List<MultiDimensionalObject> result = new ArrayList<MultiDimensionalObject>();

		// when

		when(dimensionRepository.getDimensionsOfType(type)).thenReturn(result);
		List<MultiDimensionalObject> models = treeBuilder
				.getAllSeparatedTrees(type);
		// then
		verify(dimensionRepository).getDimensionsOfType(type);
		assertThat(models).isEqualTo(result);
	}

	@Test
	public void itShouldBuildTreeForGivenRoot() {
		// given
		MultiDimensionalObject dimensionModel = new MultiDimensionalObject(
				"cp01", "Campaign", "cp01", "cp01", "-1");
		ArrayList<String> groupIds = new ArrayList<String>();
		dimensionModel.setGroupId(groupIds);
		String[] rules = { "Campaign", "MasterPublication", "PublicationGroup",
				"Publication" };

		// when
		treeBuilder.buildTreeForRootNode(dimensionModel, rules, null);
		// then
		verify(dimensionRepository, times(1)).getDimensionsBy(rules[1],
				groupIds);
	}

	@Test
	public void itShouldReturnAllDimensionsOfGivenGroupIdsAndType() {
		// given
		List<String> groupIds = new ArrayList<String>();
		String type = "MasterPublication";

		// when

		treeBuilder.getAllChildrenOfCurrentRoot(groupIds, type);
		// then
		verify(dimensionRepository, times(1)).getDimensionsBy(type, groupIds);
	}

	@Test
	public void itShouldRemoveMinusOneFromGivenPath() {
		// given
		String path = "-1,A,B,C,D";

		// when
		String processedPath = treeBuilder.removeMinusOne(path);

		// then
		assertThat(processedPath).isEqualTo("A,B,C,D");
	}

	@Test
	public void itShouldNotRemoveIfPathDoesntStartWithMinusOne() {

		// given

		String path = "A,B,C,D,E";
		// when

		String processedPath = treeBuilder.removeMinusOne(path);

		// then
		assertThat(processedPath).isEqualTo(path);
	}

	@Test
	public void itShouldNotRemoveIfPathStartWithMinusOneAndPathOnlyContainsMinusOne() {

		// given

		String path = "-1";
		// when

		String processedPath = treeBuilder.removeMinusOne(path);

		// then
		assertThat(processedPath).isEqualTo(path);
	}
}
