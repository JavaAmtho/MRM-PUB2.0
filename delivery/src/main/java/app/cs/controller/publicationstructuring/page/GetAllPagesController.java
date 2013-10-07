package app.cs.controller.publicationstructuring.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.GetAllPagesRequest;
import app.cs.model.response.TreeResponse;

@Controller
public class GetAllPagesController {

	private Interactor getAllPages;
	private GetAllPagesRequest request;

	@Autowired
	public GetAllPagesController(Interactor getAllPages,
			GetAllPagesRequest request) {
		this.getAllPages = getAllPages;
		this.request = request;
	}

	@RequestMapping(value = "/page/all/{publicationId}")
	public @ResponseBody
	List<MultiDimensionalObject> get(@PathVariable String publicationId) {
		request.setPublicationId(publicationId);
		return ((TreeResponse) getAllPages.execute(request)).getTree();

	}
}
