package app.cs.actions.mocks;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

/**
 * CreateDimensionMock
 */
@Component
public class CreateDimensionMock implements Interactor{

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;

	/** The contentobject. */
	private final String CONTENTOBJECT = "MultiDimensionalObject";

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public CreateDimensionMock(IDimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;

	}

	public ResponseModel execute(RequestModel model) {

		return new StringResponse(UUID.randomUUID().toString());
	}

	/**
	 * Sets the dimension attributes.
	 * 
	 * @param dimension
	 *            the dimension
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 */

	protected void setDimensionAttributes(MultiDimensionalObject dimension,
			String type, String name, String path, boolean isFolder) {
		

	}

}
