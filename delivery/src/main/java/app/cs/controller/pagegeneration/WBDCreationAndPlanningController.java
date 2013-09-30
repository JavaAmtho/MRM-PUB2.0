package app.cs.controller.pagegeneration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.WBDCreationAndPlanningRequest;
import app.cs.model.response.StringResponse;

@Controller
public class WBDCreationAndPlanningController {

	private Interactor createAndPlanWBD;
	private WBDCreationAndPlanningRequest request;

	@Autowired
	public WBDCreationAndPlanningController(Interactor createAndPlanWBD,
			WBDCreationAndPlanningRequest request) {
		this.createAndPlanWBD = createAndPlanWBD;
		this.request = request;

	}

	@RequestMapping(value = { "/page/createwbd/{ruleID}/{logicalPageID}/{publicationID}" }, method = RequestMethod.GET)
	public @ResponseBody
	String getPageRules(@PathVariable String ruleID,
			@PathVariable String logicalPageID,
			@PathVariable String publicationID) {
		request.setRuleID(ruleID);
		request.setLogicalPageID(logicalPageID);
		request.setPublicationID(publicationID);
		return ((StringResponse) createAndPlanWBD.execute(request))
				.getResponseString();
	}

}
