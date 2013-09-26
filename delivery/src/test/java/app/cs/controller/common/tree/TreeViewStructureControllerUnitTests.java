package app.cs.controller.common.tree;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class TreeViewStructureControllerUnitTests {

	private TreeViewStructureController structure;
	@Mock
	private FileUtils fileUtils;

	@Mock
	private JSONParser parser;

	@Test
	public void itShouldGetAllAvailableDimensions() throws IOException,
			URISyntaxException, ParseException {
		// given
		Object test = new Object();
		String viewStructureId = "1";
		structure = new TreeViewStructureController(fileUtils);
		String content = "[{}]";
		when(fileUtils.getFileContents("schema1.json")).thenReturn(content);
		when(parser.parse(content)).thenReturn(test);
		// when

		
		Object actualObject=structure.get(viewStructureId);;
		// then
		assertThat(actualObject.toString()).isEqualTo(content);
		verify(fileUtils).getFileContents("schema1.json");
	}

	@Test
	public void itShouldGetDefaultViewStructure() throws IOException,
			URISyntaxException {
		// given
		structure = new TreeViewStructureController(fileUtils);
		String content = "expected";
		when(fileUtils.getFileContents("schema1.json")).thenReturn(content);
		// when

		String actualContent = structure.getDefault();
		// then
		verify(fileUtils).getFileContents("schema1.json");
		assertThat(actualContent).isEqualTo(content);
	}

}
