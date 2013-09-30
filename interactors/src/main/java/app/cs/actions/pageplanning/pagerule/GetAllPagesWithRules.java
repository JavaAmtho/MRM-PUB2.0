package app.cs.actions.pageplanning.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.PageRules;
import app.cs.interfaces.pagerule.IPageRuleRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.PageRulesResponse;
import app.cs.model.response.ResponseModel;

@Component
public class GetAllPagesWithRules implements Interactor {

	private IPageRuleRepository pageRuleRepository;
	private PageRulesResponse pageRulesResponse;

	@Autowired
	public GetAllPagesWithRules(IPageRuleRepository pageRuleRepository,
			PageRulesResponse pageRulesResponse) {
		this.pageRuleRepository = pageRuleRepository;
		this.pageRulesResponse = pageRulesResponse;
	}

	public ResponseModel execute(RequestModel requestMdel) {
		StringRequest request = (StringRequest) requestMdel;
		PageRules pageRules = pageRuleRepository.getPageRulesFor(request
				.getStringRequest());
		if(pageRules != null){
		    pageRulesResponse.setLogicalPageID(pageRules.getId());
            pageRulesResponse.setPageRules(pageRules.getPageRules());
        }
        return (ResponseModel) pageRulesResponse;
	}

}
