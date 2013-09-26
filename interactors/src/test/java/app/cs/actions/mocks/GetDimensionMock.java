package app.cs.actions.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;


@Component
public class GetDimensionMock implements Interactor {

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;
	
	private String jsonObject = "{\"firstName\": \"John\", \"lastName\": \"Smith\",\"age\": \"25\"}";

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public GetDimensionMock(IDimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;

	}

	public ResponseModel execute(RequestModel model) {

		try {
			return new StringResponse(jsonObject);
		} catch (Exception fileReadException) {

			return new StringResponse("No dimensions found");
		}
	}
}
