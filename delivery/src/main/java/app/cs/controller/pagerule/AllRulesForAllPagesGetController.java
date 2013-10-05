package app.cs.controller.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.AllPageRulesRequest;
import app.cs.model.response.AllPageRulesResponse;

@Controller
public class AllRulesForAllPagesGetController {

	private Interactor getAllRulesForAllPages;

	@Autowired
	public AllRulesForAllPagesGetController(Interactor getAllRulesForAllPages) {
		this.getAllRulesForAllPages = getAllRulesForAllPages;

	}

	@RequestMapping(value = { "/page/rule/getallrules/" }, method = RequestMethod.POST)
	public @ResponseBody
	AllPageRulesResponse getRulesForAllPages(
			@RequestBody AllPageRulesRequest allPageRulesRequest) {
		return (AllPageRulesResponse) getAllRulesForAllPages
				.execute(allPageRulesRequest);
	}
}
