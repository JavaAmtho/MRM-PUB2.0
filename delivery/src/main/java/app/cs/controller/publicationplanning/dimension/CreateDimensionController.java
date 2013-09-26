package app.cs.controller.publicationplanning.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.DimensionInfo;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.CreateDimensionRequest;
import app.cs.model.response.MultiDimensionalObjectResponse;

/**
 * The Class NodeController.
 */
@Controller
public class CreateDimensionController {

	/** The Constant CREATE. */
	private static final String CREATE = "/dimension/create/{type}/name/{name}/path/{path}/folder/{folder}";

	/** The dimension service. */
	private Interactor createDimension;

	private CreateDimensionRequest createDimensionRequest;

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
	public CreateDimensionController(Interactor createDimension,
			CreateDimensionRequest createDimensionRequest) {
		this.createDimension = createDimension;
		this.createDimensionRequest = createDimensionRequest;

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
	MultiDimensionalObject create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") boolean isFolder,
			@RequestBody DimensionInfo dimensionInfo) {
		System.out.println("Path==>" + path);
		createDimensionRequest.setFolder(isFolder);
		createDimensionRequest.setName(name);
		createDimensionRequest.setPath(path);
		createDimensionRequest.setType(type);
		createDimensionRequest.setDimensionInfo(dimensionInfo);

		return ((MultiDimensionalObjectResponse) createDimension
				.execute(createDimensionRequest)).getResponse();

	}
}