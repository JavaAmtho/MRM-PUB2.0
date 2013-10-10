package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.DeleteAssortmentRequest;

@Controller
public class DeleteAssormentController {

	private Interactor deleteAssorment;
	private DeleteAssortmentRequest request;

	@Autowired
	public DeleteAssormentController(Interactor deleteAssortment,
			DeleteAssortmentRequest request) {
		this.deleteAssorment = deleteAssortment;
		this.request = request;
	}

	@RequestMapping("/assortment/delete/{assortmentId}")
	public void delete(@RequestParam String path, @PathVariable String assormentId) {
		request.name=assormentId;
		request.path=path;
		
		deleteAssorment.execute(request);

	}
}
