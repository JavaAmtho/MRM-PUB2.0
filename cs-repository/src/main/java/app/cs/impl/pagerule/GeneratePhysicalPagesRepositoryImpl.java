package app.cs.impl.pagerule;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatefulRuleSession;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.cs.impl.model.PageRule;
import app.cs.impl.model.PageRules;
import app.cs.impl.model.RuleCondition;
import app.cs.impl.model.RuleConditions;
import app.cs.impl.model.RuleResult;
import app.cs.interfaces.pagerule.IRuleConditionsRepository;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;
import com.cs.data.api.webservices.rest.IRestClient;

@Component
public class GeneratePhysicalPagesRepositoryImpl implements
		IRuleConditionsRepository {

	private NoSqlRepository noSqlRepository;

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
	@Value("${generatePhysicalPages}")
	private String LIST_URL;

	private IRestClient client;

	protected static RuleServiceProvider ruleServiceProvider;
	protected LinkedHashMap<String, List<PageRule>> pageIdPageRuleMap = new LinkedHashMap<String, List<PageRule>>();

	@Autowired
	public GeneratePhysicalPagesRepositoryImpl(NoSqlRepository noSqlRepository,
			IRestClient client) {
		super();
		this.noSqlRepository = noSqlRepository;
		this.client = client;
		try {
			Class.forName("org.drools.jsr94.rules.RuleServiceProviderImpl");
			ruleServiceProvider = RuleServiceProviderManager
					.getRuleServiceProvider("http://drools.org/");
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void generatePhysicalPages(RuleConditions ruleConditions) {
		String id = ruleConditions.getId();

		// get rule from db
		List<PageRule> pageRules = noSqlRepository.find(id, PageRules.class)
				.getPageRules();

		// generate rule string
		StringBuffer allRulesString = new StringBuffer(
				"package app.cs.pagerule;\n");
		allRulesString.append("import app.cs.impl.model.*\n");
		allRulesString
				.append("global java.util.ArrayList<tRuleResult> pageRuleResultList\n");
		int counter = 1;
		Iterator<PageRule> pageRuleIterator = pageRules.iterator();
		while (pageRuleIterator.hasNext()) {
			allRulesString.append("\n\n");
			String ruleId = "rule " + counter;
			PageRule pageRule = pageRuleIterator.next();
			allRulesString.append(generatePageRuleString(ruleId, pageRule));
			counter++;
		}
		System.out.println(allRulesString.toString());

		// generate rule execution set
		StringReader ruleReader = new StringReader(allRulesString.toString());
		pageIdPageRuleMap.put(id, pageRules);
		generateRuleExecutionSet(id, ruleReader);

		// execute the rule to get the result
		Iterator<RuleCondition> pageRuleIterator1 = ruleConditions
				.getRuleConditions().iterator();
		while (pageRuleIterator1.hasNext()) {

			RuleCondition ruleCondition = pageRuleIterator1.next();
			System.out.println("test");
			System.out.println(ruleCondition.getVariable());
		}
		System.out.println(ruleConditions.getRuleConditions());
		ArrayList<RuleResult> pageRuleResultList = executePageRules(id,
				ruleConditions.getRuleConditions());

		Iterator<RuleResult> ruleConditionsIterator = pageRuleResultList
				.iterator();
		while (ruleConditionsIterator.hasNext()) {
			RuleResult condition = ruleConditionsIterator.next();
			System.out.println("condition");
			System.out.println(condition.getMasterPageId());
		}
		return;
		// create wbd and plan
		// createPhysicalPageAndPlan();
	}

	public ArrayList<RuleResult> executePageRules(String id,
			List<RuleCondition> ruleConditions) {

		ArrayList<RuleResult> pageRuleResultList = new java.util.ArrayList<RuleResult>();
		HashMap<String, ArrayList<RuleResult>> resultMap = new HashMap<String, ArrayList<RuleResult>>();
		resultMap.put("pageRuleResultList", pageRuleResultList);

		try {
			RuleRuntime ruleRuntime = ruleServiceProvider.getRuleRuntime();
			StatefulRuleSession session = (StatefulRuleSession) ruleRuntime
					.createRuleSession(id, resultMap,
							RuleRuntime.STATEFUL_SESSION_TYPE);

			Iterator<RuleCondition> ruleConditionsIterator = ruleConditions
					.iterator();
			while (ruleConditionsIterator.hasNext()) {
				RuleCondition condition = ruleConditionsIterator.next();
				System.out.println("condition");
				System.out.println(condition);
				session.addObject(condition);
			}
			session.executeRules();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		return resultMap.get("pageRuleResultList");
	}

	private String generatePageRuleString(String ruleId, PageRule pageRule) {
		StringBuffer ruleString = new StringBuffer("rule \"" + ruleId + "\"\n");
		ruleString.append("when \n");

		int counter = 1;
		Iterator<RuleCondition> pageRuleConditionIterator = pageRule
				.getRuleConditions().iterator();
		while (pageRuleConditionIterator.hasNext()) {
			RuleCondition condition = pageRuleConditionIterator.next();
			ruleString.append("\tcondition" + counter + " : RuleCondition");
			ruleString.append("(variable == \"" + condition.getVariable()
					+ "\", value " + condition.getOperator() + " \""
					+ condition.getValue() + "\"");
			ruleString.append(")\n");
			counter++;
		}
		ruleString.append("then\n");
		ruleString.append("\tSystem.out.println(\"rule" + ruleId
				+ "executed\");\n");
		ruleString.append("\tRuleResult result = new RuleResult();\n");
		ruleString.append("\tresult.setMasterPageId(\""
				+ pageRule.getRuleResult().getMasterPageId() + "\");\n");
		ruleString.append("\tresult.setAssortmentId(\""
				+ pageRule.getRuleResult().getAssortmentId() + "\");\n");
		ruleString.append("\tpageRuleResultList.add(result);\n");
		ruleString.append("end");

		return ruleString.toString();
	}

	protected void generateRuleExecutionSet(String pageId, StringReader rules) {
		try {

			RuleAdministrator ruleAdministrator = ruleServiceProvider
					.getRuleAdministrator();
			LocalRuleExecutionSetProvider ruleExecutionSetProvider = ruleAdministrator
					.getLocalRuleExecutionSetProvider(null);
			RuleExecutionSet ruleExecutionSet = ruleExecutionSetProvider
					.createRuleExecutionSet(rules, null);
			ruleAdministrator.registerRuleExecutionSet(pageId,
					ruleExecutionSet, null);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	protected String createPhysicalPageAndPlan() {
		Map<String, String> headerParameters = new HashMap<String, String>();
		prepareHeaderParameters(headerParameters);
		return client.get(LIST_URL, headerParameters);
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
