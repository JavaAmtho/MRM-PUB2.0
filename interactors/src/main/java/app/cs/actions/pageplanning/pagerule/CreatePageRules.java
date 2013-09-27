package app.cs.actions.pageplanning.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.PageRules;
import app.cs.interfaces.pagerule.IPageRuleRepository;
import app.cs.model.request.PageRulesRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;

@Component
public class CreatePageRules implements Interactor {

	private IPageRuleRepository pageRuleRepository;
	private PageRules pageRules;

	@Autowired
	public CreatePageRules(IPageRuleRepository pageRuleRepository,
			PageRules pageRules) {
		this.pageRuleRepository = pageRuleRepository;
		this.pageRules = pageRules;
	}

	public ResponseModel execute(RequestModel requestModel) {
		PageRulesRequest pageRulesRequest = (PageRulesRequest) requestModel;
		pageRules.setId(pageRulesRequest.getLogicalPageID());
		pageRules.setPageRules(pageRulesRequest.getPageRules());
		pageRuleRepository.savePageRules(pageRules);
		return null;
	}

}
