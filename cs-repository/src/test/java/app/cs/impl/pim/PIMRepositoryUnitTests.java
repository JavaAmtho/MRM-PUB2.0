package app.cs.impl.pim;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.data.webservices.rest.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class PIMRepositoryUnitTests {

	private static final String SEARCH_URL = "http://192.168.135.104/CS13.0Trunk/admin/rest/pim/search/";
	private PimRepository pimRepository;
	@Mock
	private RestClient restClient;

	private final String BASE_URL = "http://192.168.135.104/CS13.0Trunk/admin/rest/pim/list/";

	@Before
	public void setup() {
		pimRepository = new PimRepository(restClient);

	}

	@Test
	public void itShouldRetrieveAllProductsFromGivenSourceUrlIfIdIsProvided() {
		// given
		String id = "62";
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		prepareHeaderParameters(headerParameters);
		String url = BASE_URL + id;

		// when
		when(restClient.get(url, headerParameters)).thenReturn(result);
		String products = pimRepository.getAssetsFor(id);

		// then

		verify(restClient).get(url, headerParameters);
		assertThat(products).isEqualTo(result);

	}

	private void prepareHeaderParameters(Map<String, String> headerParameters) {
		headerParameters.put("Accept-Language", "en-US,en;q=0.8");
		headerParameters.put("Host", "192.168.135.104");
		headerParameters
				.put("User-Agent",
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
		headerParameters.put("X-Requested-With", "XMLHttpRequest");
		headerParameters.put("Accept", "*/*");
		headerParameters
				.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
	}

	@Test
	public void itShouldRetrieveAllProductsFromGivenSourceUrlIfIdIsNotProvided() {
		// given
		String id = null;
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		prepareHeaderParameters(headerParameters);
		String url = BASE_URL + id;

		// when
		when(restClient.get(BASE_URL, headerParameters)).thenReturn(result);
		String products = pimRepository.getAssetsFor(id);

		// then

		verify(restClient).get(BASE_URL, headerParameters);
		assertThat(products).isEqualTo(result);

	}

	@Test
	public void itShouldRetrieveSearchResults() {
		// given

		String searchQuery = "benchmark";
		String expectedResult = "expected";
		// when
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		prepareHeaderParameters(headerParameters);

		String url = SEARCH_URL + searchQuery;
		when(restClient.get(url, headerParameters)).thenReturn(expectedResult);

		String searchResults = pimRepository.getSearchResults(searchQuery);
		// then
		verify(restClient).get(url, headerParameters);
		assertThat(searchResults).isEqualTo(expectedResult);

	}
}
