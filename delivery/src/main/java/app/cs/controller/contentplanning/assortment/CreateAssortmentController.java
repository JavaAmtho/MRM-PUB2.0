package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.CreateAssortmentRequest;

@Controller
public class CreateAssortmentController {

	private Interactor createAssortment;

	private CreateAssortmentRequest request;

	@Autowired
	public CreateAssortmentController(Interactor createAssortment,
			CreateAssortmentRequest request) {

		this.createAssortment = createAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/create/{name}/{path}", method = RequestMethod.POST)
	public @ResponseBody
	String create(@RequestBody Assortment assortment,
			@PathVariable String name, @PathVariable String path) {
		request.setPath(path);
		request.setAssortment(assortment);
		request.setName(name);
		createAssortment.execute(request);
		return name;
	}

}
