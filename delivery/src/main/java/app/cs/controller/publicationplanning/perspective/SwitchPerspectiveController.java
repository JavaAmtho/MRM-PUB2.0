package app.cs.controller.publicationplanning.perspective;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.StringRequest;
import app.cs.model.response.TreeResponse;

/**
 * The Class NodeController.
 */
@Controller
public class SwitchPerspectiveController {

	/** The dimension service. */
	private Interactor switchPerspective;

	private StringRequest request;

	/**
	 * Instantiates a new node controller.
	 * 
	 * @param dimensionService
	 *            the dimension service
	 * @param factory
	 *            the factory
	 * @param cache
	 *            the cache
	 */
	@Autowired
	public SwitchPerspectiveController(Interactor switchPerspective,
			StringRequest request) {
		this.switchPerspective = switchPerspective;
		this.request = request;

	}

	@RequestMapping(value = "/dimension/get/{structure}")
	public @ResponseBody
	List<MultiDimensionalObject> getDimensionsBy(@PathVariable String structure) {

		request.setStringRequest(structure);
		TreeResponse output =  ((TreeResponse) switchPerspective.execute(request)); 
		return output.getTree();
	}
}