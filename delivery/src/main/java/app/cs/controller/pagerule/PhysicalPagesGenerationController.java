package app.cs.controller.pagerule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.RuleConditionRequest;

@Controller
public class PhysicalPagesGenerationController {

	private Interactor generatePhysicalPages;
	
	@Autowired
	public PhysicalPagesGenerationController(Interactor generatePhysicalPages) {
		this.generatePhysicalPages = generatePhysicalPages;
	}

	@RequestMapping(value = { "/page/rule/generate/" }, method = RequestMethod.POST)
	public @ResponseBody
	String savePageRules(@RequestBody RuleConditionRequest ruleConditionRequest) {
		
		generatePhysicalPages.execute(ruleConditionRequest);
		return "success";
	}

}
