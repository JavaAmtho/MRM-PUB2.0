package app.cs.impl.pim;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.cs.interfaces.mam.AssetsRepository;

import com.cs.data.api.webservices.rest.IRestClient;

@Component
public class PimRepository implements AssetsRepository {

	private static final String CHARSET = "ISO-8859-1,utf-8;q=0.7,*;q=0.3";
	private static final String ACCEPT_CHARSET = "Accept-Charset";
	private static final String ACCEPTEDTYPES = "*/*";
	private static final String ACCEPT = "Accept";
	private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
	private static final String X_REQUESTED_WITH = "X-Requested-With";
	private static final String USER_AGENT_INFO = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31";
	private static final String USER_AGENT = "User-Agent";
	private static final String HOST = "Host";
	private static final String LANGUAGE = "en-US,en;q=0.8";
	private static final String ACCEPT_LANGUAGE = "Accept-Language";
	@Value("${host}")
	private String HOSTIP;
	@Value("${url}")
	private String BASE_URL;
	@Value("${pimListUrl}")
	private String LIST_URL;
	@Value("${pimSearchUrl}")
	private String SEARCH_URL;
	private IRestClient client;

	@Autowired
	public PimRepository(IRestClient client) {
		this.client = client;

	}

	@Override
	public String getAssetsFor(String id) {

		String url = formListUrl(id);
		Map<String, String> headerParameters = new HashMap<String, String>();
		prepareHeaderParameters(headerParameters);
		return client.get(url, headerParameters);

	}

	private String formListUrl(String id) {
		return id == null || id == "" ? LIST_URL : LIST_URL + id;
	}

	@Override
	public String getSearchResults(String searchQuery) {
		String url = formSearchUrl(searchQuery);
		Map<String, String> headerParameters = new HashMap<String, String>();
		prepareHeaderParameters(headerParameters);
		return client.get(url, headerParameters);
	}

	private void prepareHeaderParameters(Map<String, String> headerParameters) {
		headerParameters.put(ACCEPT_LANGUAGE, LANGUAGE);
		headerParameters.put(HOST, HOSTIP);
		headerParameters.put(USER_AGENT, USER_AGENT_INFO);
		headerParameters.put(X_REQUESTED_WITH, XML_HTTP_REQUEST);
		headerParameters.put(ACCEPT, ACCEPTEDTYPES);
		headerParameters.put(ACCEPT_CHARSET, CHARSET);
	}

	private String formSearchUrl(String searchQuery) {
		return searchQuery == null || searchQuery == "" ? SEARCH_URL
				: SEARCH_URL + searchQuery;
	}

}
