package app.cs.controller.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.PageRulesRequest;

@Controller
public class PageRuleSaveController {

	private Interactor createPageRules;

	@Autowired
	public PageRuleSaveController(Interactor createPageRules) {
		this.createPageRules = createPageRules;
	}

	@RequestMapping(value = { "/page/rule/save/" }, method = RequestMethod.POST)
	public @ResponseBody
	String savePageRules(@RequestBody PageRulesRequest pageRulesRequest) {
		System.out.println("in controller");
		createPageRules.execute(pageRulesRequest);
		return "success";
	}

}
