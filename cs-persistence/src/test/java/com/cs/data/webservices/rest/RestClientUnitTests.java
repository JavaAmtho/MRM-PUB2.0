package com.cs.data.webservices.rest;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.cs.data.api.webservices.rest.IRestClient;

public class RestClientUnitTests {

	private IRestClient restClient;

	@Test
	public void itShouldGetResponseForGivenRestUrl() {
		// given
		String url = "http://192.168.135.108/CS13.0/admin/rest/pim/list";

		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		prepareHeaderParameters(headerParameters);
		restClient = new RestClient();
		// when
		String products = restClient.get(url, headerParameters);

		// then
		assertThat(products).isNotNull();
		assertThat(products).contains("62");

	}

	private void prepareHeaderParameters(Map<String, String> headerParameters) {
		headerParameters.put("Accept-Language", "en-US,en;q=0.8");
		headerParameters.put("Host", "192.168.135.108");
		headerParameters
				.put("User-Agent",
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
		headerParameters.put("X-Requested-With", "XMLHttpRequest");
		headerParameters.put("Accept", "*/*");
		headerParameters
				.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
	}

}
