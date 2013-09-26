/*package app.cs.text;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TextAssetInteractionsUnitTests {

	private TextAssetInteractions textAssetInteractions;

	@Mock
	private TextAssetRepository textAssetRepository;

	private String id = "62";

	@Before
	public void setUp() {
		textAssetInteractions = new TextAssetInteractions(textAssetRepository);
	}

	@Test
	public void itShouldGetAssetsFromTextRepository() {

		String expectedAssetList = "test text assets";
		// when
		when(textAssetRepository.getAssetsFor(id)).thenReturn(expectedAssetList);
		String assetList = textAssetInteractions.getAssets(id);

		// then
		verify(textAssetRepository).getAssetsFor(id);
		assertThat(assetList).isEqualTo(expectedAssetList);

	}
}*/