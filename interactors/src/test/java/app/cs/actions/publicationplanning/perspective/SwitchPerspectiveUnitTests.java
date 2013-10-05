package app.cs.actions.publicationplanning.perspective;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.interfaces.inmemory.IInMemoryViewStructure;
import app.cs.interfaces.slicingdicing.ITreeBuilder;
import app.cs.model.request.StringRequest;

/**
 * The Class DimensionService.
 */

@RunWith(MockitoJUnitRunner.class)
public class SwitchPerspectiveUnitTests {

	private SwitchPerspective switchPerspective;

	@Mock
	private ITreeBuilder treeBuilder;

	@Mock
	private IInMemoryViewStructure cache;

	@Before
	public void setUp() {
		switchPerspective = new SwitchPerspective(treeBuilder, cache);

	}

	@Test
	public void itShouldSwitchPerspective() {

		// given
		String KEY = "view";
		String structure = "Campaign-MasterPublication-Publication";
		String newstructure = "MasterPublication-Publication-Campaign";
		cache.setCurrentViewStructure(KEY, structure);

		// when
		StringRequest request = new StringRequest(newstructure);
		when(cache.getCurrentViewStructure()).thenReturn(newstructure);
		switchPerspective.execute(request);

		// then
		verify(cache).setCurrentViewStructure(KEY, newstructure);
		assertEquals(cache.getCurrentViewStructure(), newstructure);
	}
}