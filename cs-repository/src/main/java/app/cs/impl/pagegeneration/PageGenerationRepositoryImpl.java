package app.cs.impl.pagegeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.model.Product;
import app.cs.interfaces.pagegeneration.IPageGenerationRepository;

import com.cs.data.api.webservices.rest.IRestClient;
import com.sun.jersey.api.client.ClientResponse;

@Component
public class PageGenerationRepositoryImpl implements IPageGenerationRepository {

	private ChapterRepository chapterRepository;

	private static final String CHARSET = "ISO-8859-1,utf-8;q=0.7,*;q=0.3";
	private static final String ACCEPT_CHARSET = "Accept-Charset";
	private static final String ACCEPTEDTYPES = "*/*";
	private static final String ACCEPT = "Accept";
	private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
	private static final String X_REQUESTED_WITH = "X-Requested-With";
	private static final String USER_AGENT_INFO = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31";
	private static final String USER_AGENT = "User-Agent";
	private static final String HOSTIP = "192.168.135.104";
	private static final String HOST = "Host";
	private static final String LANGUAGE = "en-US,en;q=0.8";
	private static final String ACCEPT_LANGUAGE = "Accept-Language";
	private final String BASE_URL = "http://192.168.135.104/CS13.0Trunk/admin";
	private final String LIST_URL = BASE_URL + "/rest/whiteboard/1/";
	private IRestClient client;

	@Autowired
	public PageGenerationRepositoryImpl(IRestClient client,
			ChapterRepository chapterRepository) {
		this.client = client;
		this.chapterRepository = chapterRepository;
	}

	@Override
	public String createAndPlanWBD(String templateID, String assortmentID,
			String publicationID) {

		String input = "";
		String productIds = "";
		int countOfProducts = 1;

		// get the publication
		MultiDimensionalObject publication = chapterRepository
				.getParentPublicationByID(publicationID);

		// find the assortment
		MultiDimensionalObject assortment = chapterRepository
				.findMultiDimensionalObjectByID(publication, assortmentID);

		// get the products from assortment
		// iterate over them and create a json string of ids
		List<Product> products = assortment.getProducts();
		for (Product product : products) {
			if (countOfProducts < products.size()) {
				productIds += "{\"id\":\"" + product.getId() + "\"},";
			} else {
				productIds += "{\"id\":\"" + product.getId() + "\"}";
			}
			countOfProducts++;
		}

		// create string to POST
		input = "{\"templateID\":\"" + templateID + "\",\"products\":["
				+ productIds + "]}";

		Map<String, String> headerParameters = new HashMap<String, String>();
		prepareHeaderParameters(headerParameters);

		// create wbd and plan assortment
		ClientResponse response = client
				.post(LIST_URL, headerParameters, input);

		if (response.getStatus() != 200) {
			return "Not Successful";
		}

		String output = response.getEntity(String.class);
		return output;
	}

	private void prepareHeaderParameters(Map<String, String> headerParameters) {
		headerParameters.put(ACCEPT_LANGUAGE, LANGUAGE);
		headerParameters.put(HOST, HOSTIP);
		headerParameters.put(USER_AGENT, USER_AGENT_INFO);
		headerParameters.put(X_REQUESTED_WITH, XML_HTTP_REQUEST);
		headerParameters.put(ACCEPT, ACCEPTEDTYPES);
		headerParameters.put(ACCEPT_CHARSET, CHARSET);
	}

}
