package app.cs.actions.pageplanning.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.pagerule.IPageRuleRepository;
import app.cs.model.request.AllPageRulesRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.AllPageRulesResponse;
import app.cs.model.response.ResponseModel;

@Component
public class GetAllRulesForAllPages implements Interactor {

	private IPageRuleRepository pageRuleRepository;
	private AllPageRulesResponse allPageRulesResponse;

	@Autowired
	public GetAllRulesForAllPages(IPageRuleRepository pageRuleRepository,
			AllPageRulesResponse allPageRulesResponse) {
		this.pageRuleRepository = pageRuleRepository;
		this.allPageRulesResponse = allPageRulesResponse;
	}

	public ResponseModel execute(RequestModel requestModel) {
		AllPageRulesRequest allPageRulesRequest = (AllPageRulesRequest) requestModel;
		allPageRulesResponse.setListOfPageRules(pageRuleRepository
				.getAllRulesForAllPages(allPageRulesRequest.getPageIDs()));
		return (ResponseModel) allPageRulesResponse;
	}

}
