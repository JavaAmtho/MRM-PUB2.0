package app.cs.actions.publicationplanning.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

/**
 * The Class DimensionService.
 */
@Component
public class GetDimensions implements Interactor {

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public GetDimensions(IDimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;

	}

	public ResponseModel execute(RequestModel model) {

		try {
			return new StringResponse(dimensionRepository.getAllDimensions());
		} catch (Exception fileReadException) {

			return new StringResponse("No dimensions found");
		}
	}
}
