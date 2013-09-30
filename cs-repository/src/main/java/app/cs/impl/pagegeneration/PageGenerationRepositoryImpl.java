package app.cs.impl.pagegeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.model.PageRule;
import app.cs.impl.model.PageRules;
import app.cs.impl.model.Product;
import app.cs.impl.pagerule.PageRuleRepositoryImpl;
import app.cs.interfaces.pagegeneration.IPageGenerationRepository;
import app.cs.interfaces.pagerule.IPageRuleRepository;

import com.cs.data.api.webservices.rest.IRestClient;
import com.sun.jersey.api.client.ClientResponse;

@Component
public class PageGenerationRepositoryImpl implements IPageGenerationRepository {

	private ChapterRepository chapterRepository;
	private IPageRuleRepository pageRuleRepository;

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
	@Value("${pageGenerationUrl}")
	private String LIST_URL;

	private IRestClient client;

	@Autowired
	public PageGenerationRepositoryImpl(IRestClient client,
			ChapterRepository chapterRepository,
			PageRuleRepositoryImpl pageRuleRepository) {
		this.client = client;
		this.chapterRepository = chapterRepository;
		this.pageRuleRepository = pageRuleRepository;
	}

	@Override
	public String createAndPlanWBD(String ruleID, String logicalPageID,
			String publicationID) {

		String output = "";
		String input = "";
		String productIds = "";
		String assortmentID = "";
		String masterPageID = "";

		int countOfProducts = 1;

		HashMap<String, String> additionalInformation = new HashMap<String, String>();

		// get the rule
		PageRules pageRules = pageRuleRepository.getPageRulesFor(logicalPageID);
		if (pageRules == null) {
			return "pageRules not found";
		}
		PageRule pageRule = pageRuleRepository
				.getPageRuleFor(pageRules, ruleID);
		if (pageRule == null) {
			return "pageRule not found";
		}

		// get the assortmentID and the masterPageID from the ruleResult
		assortmentID = pageRule.getRuleResult().getAssortmentId();
		masterPageID = pageRule.getRuleResult().getMasterPageId();

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
		input = "{\"templateID\":\"" + masterPageID + "\",\"products\":["
				+ productIds + "]}";

		Map<String, String> headerParameters = new HashMap<String, String>();
		prepareHeaderParameters(headerParameters);

		// create wbd and plan assortment
		ClientResponse response = client
				.post(LIST_URL, headerParameters, input);

		if (response.getStatus() != 200) {
			return "Not Successful";
		}

		output = response.getEntity(String.class);

		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(output);
			additionalInformation.put("mamFileID",
					(String) jsonObject.get("mamFileID"));
			additionalInformation.put("editUrl",
					(String) jsonObject.get("editorURL"));
		} catch (ParseException e) {
			return "Error";
		}

		// store the information in the rule
		pageRule.setAdditionalInformation(additionalInformation);
		pageRuleRepository.savePageRules(pageRules);

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
