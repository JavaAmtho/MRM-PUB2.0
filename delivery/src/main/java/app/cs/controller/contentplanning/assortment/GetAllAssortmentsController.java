package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.GetAllAssortmentsRequest;
import app.cs.model.response.StringResponse;

@Controller
public class GetAllAssortmentsController {

	private Interactor getAllAssortments;
	private GetAllAssortmentsRequest request;

	@Autowired
	public GetAllAssortmentsController(Interactor getAllAssortments,
			GetAllAssortmentsRequest request) {

		this.getAllAssortments = getAllAssortments;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/getall/{pagePath}/{logicalPageID}")
	public @ResponseBody
	String getAllAssortments(@PathVariable String pagePath,
			@PathVariable String logicalPageID) {
		request.setPagePath(pagePath);
		request.setLogicalPageID(logicalPageID);
		return ((StringResponse) getAllAssortments.execute(request))
				.getResponseString();
	}
}
