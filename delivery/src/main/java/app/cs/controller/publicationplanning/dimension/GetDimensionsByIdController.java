package app.cs.controller.publicationplanning.dimension;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.GetDimensionByIdRequest;
import app.cs.model.response.TreeResponse;

@Controller
public class GetDimensionsByIdController {

	private Interactor getPublicationsForGivenChannel;

	@Autowired
	public GetDimensionsByIdController(Interactor getPublicationsForGivenChannel) {
		this.getPublicationsForGivenChannel = getPublicationsForGivenChannel;
	}

	@RequestMapping(value = "/publication/get/{channelName}", method = RequestMethod.POST)
	public @ResponseBody
	List<MultiDimensionalObject> getPublicationBy(
			@RequestBody GetDimensionByIdRequest byIdRequest,
			@PathVariable String channelName) {
		return ((TreeResponse) getPublicationsForGivenChannel
				.execute(byIdRequest)).getTree();

	}

}
