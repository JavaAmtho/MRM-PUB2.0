package app.cs.actions.publicationplanning.dimension;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.DimensionInfo;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.CreateDimensionRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.MultiDimensionalObjectResponse;
import app.cs.model.response.ResponseModel;

/**
 * The Class DimensionService. TODO remove out all annotation from class
 */
@Component
public class CreateDimension implements Interactor {

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
	public CreateDimension(IDimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;

	}

	public ResponseModel execute(RequestModel model) {

		CreateDimensionRequest request = (CreateDimensionRequest) model;

		MultiDimensionalObject dimension = (MultiDimensionalObject) dimensionRepository
				.getDomain(CONTENTOBJECT);

		setDimensionAttributes(dimension, request.getType(), request.getName(),
				request.getPath(), request.isFolder(),
				request.getDimensionInfo());
		return new MultiDimensionalObjectResponse(
				dimensionRepository.createDimension(dimension));
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
	 * @param dimensionInfo
	 */

	protected void setDimensionAttributes(MultiDimensionalObject dimension,
			String type, String name, String path, boolean isFolder,
			DimensionInfo dimensionInfo) {
		dimension.setId(name);
		dimension.setTitle(name);
		dimension.setIsFolder(isFolder);
		dimension.setPath(path);
		dimension.setName(name);
		dimension.setType(type);
		dimension.setDimensionInfo(dimensionInfo);
		dimension.setChildren(new ArrayList<MultiDimensionalObject>());

	}

}
