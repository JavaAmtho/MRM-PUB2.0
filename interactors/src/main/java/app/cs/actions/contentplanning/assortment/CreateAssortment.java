package app.cs.actions.contentplanning.assortment;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.DimensionInfo;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.CreateAssortmentRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CreateAssortment implements Interactor {

	private IAssortmentRepository assortmentRepository;

	private String type = "Assortment";

	@Autowired
	public CreateAssortment(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {

		CreateAssortmentRequest createAssortmentRequest = (CreateAssortmentRequest) request;
		MultiDimensionalObject assortmentObject = (MultiDimensionalObject) assortmentRepository
				.getDomain("MultiDimensionalObject");

		Assortment assortment = createAssortmentRequest.getAssortment();

		assortmentObject.setId(UUID.randomUUID().toString());
		assortmentObject.setPath(createAssortmentRequest.getPath());
		assortmentObject.setName(createAssortmentRequest.getName());
		assortmentObject.setTitle(createAssortmentRequest.getName());
		assortmentObject.setIsFolder(false);
		assortmentObject.setType(type);
		assortmentObject.setDimensionInfo(new DimensionInfo());
		assortmentObject.setProducts(assortment.getProducts());
		assortmentRepository.save(assortmentObject);
		return new EmptyResponse();

	}

}
