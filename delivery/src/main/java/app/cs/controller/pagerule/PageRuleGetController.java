package app.cs.controller.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.PageRulesResponse;

@Controller
public class PageRuleGetController {

	private Interactor getAllPagesWithRules;
	private StringRequest request;

	@Autowired
	public PageRuleGetController(Interactor getAllPagesWithRules,
			StringRequest request) {
		this.getAllPagesWithRules = getAllPagesWithRules;
		this.request = request;

	}

	@RequestMapping(value = { "/page/rule/getrules/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	PageRulesResponse getPageRules(@PathVariable String id) {

		request.setStringRequest(id);
		return (PageRulesResponse) getAllPagesWithRules.execute(request);
	}

}
