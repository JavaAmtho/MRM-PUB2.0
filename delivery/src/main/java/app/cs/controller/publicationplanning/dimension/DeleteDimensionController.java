
package app.cs.controller.publicationplanning.dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.DeleteDimensionRequest;

/**
 * The Class NodeController.
 */
@Controller
public class DeleteDimensionController {

	/** The Constant CREATE. */
	private static final String CREATE = "/dimension/delete/{type}/{name}";

	/** The dimension service. */
	private Interactor deleteDimension;

	private DeleteDimensionRequest deleteDimensionRequest;

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
	public DeleteDimensionController(Interactor deleteDimension,
			DeleteDimensionRequest deleteDimensionRequest) {
		this.deleteDimension = deleteDimension;
		this.deleteDimensionRequest = deleteDimensionRequest;

	}

	/**
	 * Creates the Dimension Model.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @return the string
	 */
	@RequestMapping(value = { CREATE })
	public @ResponseBody
	String create(@RequestBody MultiDimensionalObject dimensionTobeDeleted) {


		deleteDimensionRequest.setDimension(dimensionTobeDeleted);
		deleteDimension.execute(deleteDimensionRequest);
		return dimensionTobeDeleted.getId();

	}
}