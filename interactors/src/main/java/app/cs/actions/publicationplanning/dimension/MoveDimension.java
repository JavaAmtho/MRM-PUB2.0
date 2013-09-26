package app.cs.actions.publicationplanning.dimension;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.inmemory.InMemoryViewStructure;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.interfaces.slicingdicing.ITreeBuilder;
import app.cs.model.request.MoveDimensionRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.MultiDimensionalObjectResponse;
import app.cs.model.response.ResponseModel;

@Component
public class MoveDimension implements Interactor {

	private IDimensionRepository dimensionRepository;
	private ITreeBuilder builder;
	private InMemoryViewStructure structure;

	@Autowired
	public MoveDimension(IDimensionRepository dimensionRepository,
			ITreeBuilder builder, InMemoryViewStructure structure) {
		this.dimensionRepository = dimensionRepository;
		this.builder = builder;
		this.structure = structure;
	}

	public ResponseModel execute(RequestModel request) {

		MoveDimensionRequest dimensionRequest = (MoveDimensionRequest) request;
		MultiDimensionalObject dimensionalObject = dimensionRepository.move(
				dimensionRequest.getOldPath(), dimensionRequest.getNewPath(),
				dimensionRequest.getObjectInMove());

		builder.buildTreeForRootNode(dimensionalObject,
				getOrderTypes(dimensionalObject.getType()),
				dimensionalObject.getGroupId());

		return new MultiDimensionalObjectResponse(dimensionalObject);
	}

	protected String[] getOrderTypes(String type) {
		String[] types = structure.getCurrentViewStructure().split("-");
		return Arrays.copyOfRange(types, Arrays.asList(types).indexOf(type),
				types.length);

	}

}
