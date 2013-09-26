package app.cs.controller.contentplanning.textassets;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class TextAssetControllerUnitTests {

	@Mock
	private FileUtils fileUtils;

	private TextAssetController textAssetController;

	@Before
	public void setUp() {
		textAssetController = new TextAssetController(fileUtils);
	}

	@Test
	public void itShouldReturnTextDataFromTextAssets() throws IOException,
			URISyntaxException {

		String contents = "result";
		// when
		String actualContents = textAssetController.getTextAssets();

		// then

		verify(fileUtils).getFileContents("textassets.json");
	}

}