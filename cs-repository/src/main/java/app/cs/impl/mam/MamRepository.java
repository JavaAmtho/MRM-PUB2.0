package app.cs.impl.mam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.cs.interfaces.mam.AssetsRepository;

import com.cs.data.api.webservices.rest.IRestClient;

@Component
public class MamRepository implements AssetsRepository {

	private static final String CHARSET = "ISO-8859-1,utf-8;q=0.7,*;q=0.3"; //$NON-NLS-1$
	private static final String ACCEPT_CHARSET = "Accept-Charset"; //$NON-NLS-1$
	private static final String ACCEPTEDTYPES = "*/*"; //$NON-NLS-1$
	private static final String ACCEPT = "Accept"; //$NON-NLS-1$
	private static final String XML_HTTP_REQUEST = "XMLHttpRequest"; //$NON-NLS-1$
	private static final String X_REQUESTED_WITH = "X-Requested-With"; //$NON-NLS-1$
	private static final String USER_AGENT_INFO = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31"; //$NON-NLS-1$
	private static final String USER_AGENT = "User-Agent"; //$NON-NLS-1$
	private static final String HOST = "Host"; //$NON-NLS-1$
	private static final String LANGUAGE = "en-US,en;q=0.8"; //$NON-NLS-1$
	private static final String ACCEPT_LANGUAGE = "Accept-Language"; //$NON-NLS-1$
	@Value("${host}")
	private String HOSTIP;
	@Value("${url}")
	private String BASE_URL;
	@Value("${mamListUrl}")
	private String LIST_URL;
	@Value("${mamSearchUrl}")
	private String SEARCH_URL;

	private IRestClient client;

	@Autowired
	public MamRepository(IRestClient client) {
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
		return id == null || id == "" ? LIST_URL : LIST_URL + id; //$NON-NLS-1$
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
		return searchQuery == null || searchQuery == "" ? SEARCH_URL //$NON-NLS-1$
				: SEARCH_URL + searchQuery;
	}

}
