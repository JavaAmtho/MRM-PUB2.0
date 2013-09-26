package app.cs.controller.contentplanning.pim;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.pim.PimSearch;
import app.cs.model.request.StringRequest;
import app.cs.model.response.PIMOrMAMNode;
import app.cs.model.response.StringResponse;
import app.cs.presentor.JsonFormatter;

@RunWith(MockitoJUnitRunner.class)
public class PIMSearchControllerUnitTest {

	private PIMSearchController pimSearchController;

	@Mock
	private PimSearch pimSearch;

	@Mock
	private StringRequest request;

	private JsonFormatter formatter;

	@Before
	public void setUp() {

		formatter = new JsonFormatter();
		pimSearchController = new PIMSearchController(pimSearch, request,
				formatter);

	}

	@Test
	public void itShouldReturnSearchResultsFromPIMTree()
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		// given
		String key = "ABC";
		String result = "{\"62\":{\"id\":\"62\",\"label\":\"Media Player\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":true,\"description\":\"89,00, Blue\",\"service\":\"pim\"},\"91\":{\"id\":\"91\",\"label\":\"Books\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":\"true\",\"description\":\"24,80, white\",\"service\":\"pim\"},\"63\":{\"id\":\"63\",\"label\":\"Accessories\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":true,\"service\":\"pim\"}}";
		String expectedResult = "[PIMNode [id=91, title=Books, isfolder=true, type=item, image=image, description=24,80, white, service=pim, isLazy=false], PIMNode [id=62, title=Media Player, isfolder=true, type=item, image=image, description=89,00, Blue, service=pim, isLazy=false], PIMNode [id=63, title=Accessories, isfolder=true, type=item, image=image, description=null, service=pim, isLazy=false]]";
		StringResponse response = new StringResponse(result);

		// when
		when(pimSearch.execute(request)).thenReturn(response);

		List<PIMOrMAMNode> actualResult = pimSearchController.searchPIM(key);

		// then
		verify(pimSearch).execute(request);
		assertThat(actualResult.toString()).isEqualTo(expectedResult);

	}
}